## Search API to handle Spelling-Corrections
### Based on N-gram index algorithm: using [MySQL Ngram Full-Text Parser](https://dev.mysql.com/doc/refman/5.7/en/fulltext-search-ngram.html)
#### Sample Screen-Recording
https://user-images.githubusercontent.com/69693621/138235026-bfbba08f-a254-4d2c-a81a-d2193bbd84d4.mov

### Tech stack
* MySQL Ngram Text Parser [(REFERENCE)](https://dev.mysql.com/doc/refman/5.7/en/fulltext-search-ngram.html)
* EntityManager (JpaRepository can also be used)
* Flyway
* Spring-boot to expose REST API

### Important files 
* [SQL migration scripts](https://github.com/hardikSinghBehl/ngram-search-API)
* [Character.class](https://github.com/hardikSinghBehl/ngram-search-API/blob/main/src/main/java/com/behl/dragonera/entity/Character.java)
* [CharacterService](https://github.com/hardikSinghBehl/ngram-search-API/blob/main/src/main/java/com/behl/dragonera/service/CharacterService.java)

### Customizing response through score values
ngram index algorithm computes `scores` for every record in accordance with the provided keyword. Below attached is a sample screenshot to show the results (look at the score column)

<img width="655" alt="Screenshot 2021-10-21 at 6 43 37 PM" src="https://user-images.githubusercontent.com/69693621/138284922-2f3eae3d-7337-4149-81d8-6a44732f232a.png">

Depending on the usecase in front and functionality to be achieved by a particular application, we can put a condition in our native sql query to return only those records which have a computed score greater than `{value-needed: example 1.5, 0.7 etc}`

### Attaching sample query

```
SELECT id, name, bio,
    MATCH(name,bio) 
    AGAINST('James Potter') as score
FROM characters 
WHERE 
    MATCH(name,bio) 
    AGAINST('James Potter') >1.4 ORDER BY score DESC;
```
<img width="575" alt="Screenshot 2021-10-21 at 6 52 41 PM" src="https://user-images.githubusercontent.com/69693621/138286579-dc4384c0-2624-4f2c-8914-8a64f4913759.png">

The count of the data was reduced significantly just by introducing the greater than score condition
We can even make the value dynamic depending upon the size of records being matched or have the frontend provide the value using request-parameter to the API 

### Local Setup
* Install Java 17 (recommended to use [SdkMan](https://sdkman.io))

`sdk install java 17-open`
* Install Maven (recommended to use [SdkMan](https://sdkman.io))

`sdk install maven`

* Clone the repo and configure datasource values in the application.properties file, run the below command

`mvn clean install`

* To start the application

`mvn spring-boot:run &`

* Access the sole API on below path(s)
  * **to view all character records**
  
  ```
  http://localhost:8080/characters
  ```
  * **to view character records based on a searchable keyword** (page starts from index 1 and count is set to 10 of no count param is provided)
  
  ```
  http://localhost:8080/characters?keyword=harry&count=20&page=1
  ```
