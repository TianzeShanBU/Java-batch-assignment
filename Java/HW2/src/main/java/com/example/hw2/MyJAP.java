package com.example.hw2;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;

import javax.persistence.*;
import javax.sql.DataSource;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

public class MyJAP {
    private DataSource getDataSource() {
        final MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser("root");
        dataSource.setPassword("1234");
        dataSource.setUrl("jdbc:mysql://localhost:3306/school");
        return dataSource;
    }

    private Properties getProperties() {
        final Properties properties = new Properties();
        properties.put( "hibernate.dialect", "org.hibernate.dialect.MySQLDialect" );
        properties.put( "hibernate.connection.driver_class", "com.mysql.jdbc.Driver" );
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");
        return properties;
    }

    private EntityManagerFactory entityManagerFactory(DataSource dataSource, Properties hibernateProperties ){
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.example.hw2");
        em.setJpaVendorAdapter( new HibernateJpaVendorAdapter() );
        em.setJpaProperties( hibernateProperties );
        em.setPersistenceUnitName( "demo-unit" );
        em.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        em.afterPropertiesSet();
        return em.getObject();
    }

    public static void main(String[] args) {
        MyJAP jpa = new MyJAP();
        DataSource dataSource = jpa.getDataSource();
        Properties properties = jpa.getProperties();
        EntityManagerFactory entityManagerFactory = jpa.entityManagerFactory(dataSource,properties);
        EntityManager em = entityManagerFactory.createEntityManager();
        PersistenceUnitUtil unitUtil = entityManagerFactory.getPersistenceUnitUtil();
//
//        selectDataFromTable(em, "Student");
//        selectDataById(em, "Student",1);
//        removeDataById(em,"Student",1);
        joinData(em);
    }


/**
 *  1. build relation between hibernate (entitymanager) and database table
 *  2. create many to many relation in database
 *  3. use 1 - m + m - 1 in hibernate
 *  4. write jpql to select data / select data by id / join data
 *  5. write jpql to remove data
 *
 *  don't use : spring data jpa
 *              many to many annotation
 *              hibernate auto creation
 */
    private static void selectDataFromTable(EntityManager em, String tableName){
        Query query = em.createQuery("SELECT a From "+ tableName +" a");
        List<Object> typeList = (List<Object>) query.getResultList();
        Iterator i = typeList.iterator();
        Object obj;
        while(i.hasNext()){
            obj = i.next();
            System.out.println(obj.toString());
        }
    }

    private static void selectDataById(EntityManager em, String tableName, Integer id){
        Query query = em.createQuery("SELECT a From "+ tableName +" a" + " WHERE " + "a.id = "+ id);
        List<Object> typeList =  query.getResultList();
        Iterator i = typeList.iterator();
        Object obj;
        while(i.hasNext()){
            obj = i.next();
            System.out.println(obj.toString());
        }
    }

    private static void joinData(EntityManager em){
        Query query = em.createQuery("SELECT sc.stu.id,sc.stu.name,sc.course.id,sc.course.name FROM Student_Course sc");
        List<Student_Course> l = (List<Student_Course>) query.getResultList();
        Iterator it = l.iterator();
        Object[] obj;
        while(it.hasNext()){
            obj =(Object[]) it.next();
            for(int i = 0;i< obj.length;i++){
                System.out.printf(" %-10s |",String.valueOf(obj[i]));
            }
            System.out.println();
        }

    }

    private static void removeDataById(EntityManager em, String tableName,Integer id){
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Query query = null;
        if(tableName.equals("Student")) {
             query = em.createQuery("DELETE From Student_Course sc where sc.stu.id = :id");
        }else if(tableName.equals("Course")){
             query = em.createQuery("DELETE From Student_Course sc where sc.course.id = :id");
        }else{
            System.out.println("Invalid tableName");
        }

        if(query!=null) {
            query.setParameter("id", id);
            query.executeUpdate();
            Query remove = em.createQuery("DELETE From " + tableName + " a" + " WHERE a.id = :id");
            remove.setParameter("id", id);
            remove.executeUpdate();
            tx.commit();
        }

    }

}
