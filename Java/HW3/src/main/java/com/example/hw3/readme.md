# RESTFUL API
## DataBase - MySQL
### Tables:
* student(id,name)
* course(id,name)
* student_course(id,s_id,c_id)
## Rest api Functions
### student controller
* create new student: Post http://localhost:8080/student  
  RequestBody{name}
* update student: Put http://localhost:8080/student/{id}
* get student by id: Get http://localhost:8080/student/{id}
* get all students: Get heep://localhost:8080/student?size=5&pageNum=1
### course controller
* create new student: Post http://localhost:8080/course  
  RequestBody{name}
* get course by id: Get http://localhost:8080/course/{id}
* get all courses: Get heep://localhost:8080/course
### student_course controller
* register student and course: Post http://localhost:8080/student_course  
  RequestBody{student_ID,Course_ID}
* get student and course relational table: Get http://localhost:8080/student_course
