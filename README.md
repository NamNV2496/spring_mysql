# spring_mysql
this is example of Spring and MySQL use: jdbc, springboot and hibernate in each branch

postman
GET: http://localhost:8080/test

POST: http://localhost:8080/test_insert
{
    "id":19,
    "name":"test_Insert",
    "email":"test_Insert@JPA.com",
    "phone":"98877647647"
}

PUT: http://localhost:8080/test_update
{
    "id":19,
    "name":"test_Update",
    "email":"test_Update@JPA.com",
    "phone":"0000000"
}

DELETE: http://localhost:8080/test_delete
{
    "id":19,
}
