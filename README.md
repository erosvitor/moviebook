# About
Project created in the course API REST with Quarkus.

## Requirements
* JDK 17
* Maven 3.8.x
* MySQL 8
* Postman

## Steps to Setup
1. Create the database
```
CREATE DATABASE moviebook CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci;

USE moviebook;

CREATE TABLE movies (
  id INTEGER NOT NULL AUTO_INCREMENT,
  title VARCHAR(80) NOT NULL,
  genre VARCHAR(60) NOT NULL,
  year SMALLINT NOT NULL,
  main_actor VARCHAR(80) NOT NULL,
  watched_when DATE NOT NULL,
  watched_where VARCHAR(20) NOT NULL,
  synopse TEXT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE utf8mb3_general_ci;
```

2. Clone the application
```
git clone https://github.com/erosvitor/moviebook.git
```

3. Build the project
```
mvn clean package
```

4. Run the project
```
java -jar ./target/quarkus-app/quarkus-run.jar
```

## Swagger
```
http://localhost:8080/q/swagger-ui/
```

## Using the project via Postman
1. Create movie
```
POST http://localhost:8080/movies

{
  "title": "Momentum",
  "genre": "Ação",
  "year": 2015,
  "mainActor": "Olga Kurylenko",
  "watchedWhen": "2023-01-05",
  "watchedWhere": "Youtube",
  "synopse": null
}
```

2. Read all movies
```
GET http://localhost:8080/movies
```

3. Update movie data
```
PUT http://localhost:8080/movies

{
  "id": 1,
  "title": "Momentum",
  "genre": "Ação",
  "year": 2015,
  "mainActor": "Olga Kurylenko",
  "watchedWhen": "2023-01-05",
  "watchedWhere": "Youtube",
  "synopse": "Excelente filme"
}
```

4. Delete movie
```
DELETE http://localhost:8080/movies/1
```

## Using the project via curl
1. Create movie
```
curl --location 'http://localhost:8080/movies' \
     --header 'Content-Type: application/json' \
     --data ' {
         "title": "Momentum",
         "genre": "Ação",
         "year": 2015,
         "mainActor": "Olga Kurylenko",
         "watchedWhen": "2023-01-05",
         "watchedWhere": "Youtube",
         "synopse": null
       }'
```

2. Read all movies
```
curl --location 'http://localhost:8080/movies'
```

3. Update movie data
```
curl --location --request PUT 'http://localhost:8080/movies' \
     --header 'Content-Type: application/json' \
     --data ' {
         "id": 1,
         "title": "Momentum",
         "genre": "Ação",
         "year": 2015,
         "mainActor": "Olga Kurylenko",
         "watchedWhen": "2023-01-05",
         "watchedWhere": "Youtube",
         "synopse": "Excelente filme"
      }'
```

4. Delete movie
```
curl --location --request DELETE 'http://localhost:8080/movies/1'
```

## License
This project is under license from MIT. For more details, see the LICENSE file.
