# Simple Library CRUD application

This is a spring boot application with an H2 backing database. By using an H2 database I was able to just let spring/hibernate handle most of the work, I just configured it a bit.

I assumed JSON would be a good response format.

***Project Requirements***

Using Java, write a minimal library API that can perform the following functions:
List all books in the library
CRUD operations on a single book
Your application does not have to be fully functional, just spend around an hour or two and send it over once it’s completed. We generally allow a week to complete this because we know that you have other obligations in your life.

***System Requirements***

Java 14
maven

*Helpful to have postman or some other tool for calling API's.

***Where to get the source***
```
https://github.com/bradchamberlain/cd_library
```

***Database Configuration***

The database should be setup and included.  If not, you'll need to uncomment these lines in the application.properties file and then re-start the application.
```
#spring.jpa.hibernate.ddl-auto=create-drop
#spring.datasource.initialization-mode=always
```
This will create the table and initialize a few records.  If you leave those lines in the application,then your database will be wiped clean on 
every restart so I usually build it once to get it fresh then remove those lines. 

***How to build***
```
./mvnw package
```
or
```
mvn package
```

***How to run***
```
./mvnw spring-boot:run
```
or
```
mvn spring-boot:run
```

***Database configuration***
```
http://localhost:8080/h2-console/
```
* ***Never***, ever, check in a file that contains a password value like I did here.  This should be done in an environment variable.
* ***Never***, ***never***, ever use "password" as a password

## Sample Requests

*Get a list of all books in library:*
```
GET: http://localhost:8080/api/v1/books
```

*Create new book:*
```
POST: http://localhost:8080/api/v1/books
Headers: 
    Accept: application/json 
    Content-Type: application/json

Body:
    {
        "title": "Mason & Dixon: A Novel",
        "author": "Thomas Pynchon",
        "pages": 784
    }
```

*Get a book:*
```
GET: http://localhost:8080/api/v1/books/{{book_id}}
```

*Update existing book:*
```
PUT: http://localhost:8080/api/v1/books/{{book_id}}
Headers: 
    Accept: application/json 
    Content-Type: application/json
Body:
    {
        "title": "Mason & Dixon: A Novel",
        "author": "Thomas Pynchon",
        "pages": 785
    }
```

*Delete book:*
```
http://localhost:8080/api/v1/books/{{book_id}}
```
