#RESTFUL API
##DataBase - MySQL
###Tables:
*student 
*course
*student_course
##Rest api Functions
###student controller
*create new student: Post http://localhost:8080/student
                     body{name}
*get student by id: Get http://localhost:8080/student/{id}
*get all students: Get heep://localhost:8080/student
###course controller
*create new student: Post http://localhost:8080/course
                     body{name}
*get course by id: Get http://localhost:8080/course/{id}
*get all courses: Get heep://localhost:8080/course
###student_course controller
*register student and course: Post http://localhost:8080/student_course
                              body{student_ID,Course_ID}
*get student and course relational table: Get http://localhost:8080/student_course