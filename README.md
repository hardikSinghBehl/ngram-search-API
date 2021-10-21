## Search API to handle incorrect spellings
### Based on N-gram index algorithm: using [MySQL Ngram Full-Text Parser](https://dev.mysql.com/doc/refman/5.7/en/fulltext-search-ngram.html)
#### Sample Screen-Recording
https://user-images.githubusercontent.com/69693621/138235026-bfbba08f-a254-4d2c-a81a-d2193bbd84d4.mov

### Tech stack
* MySQL Ngram Text Parser [(REFERENCE)](https://dev.mysql.com/doc/refman/5.7/en/fulltext-search-ngram.html)
* EntityManager (JpaRepository can also be used)
* Spring-boot to expose REST API

### Important files 
* [SQL migration scripts](https://github.com/hardikSinghBehl/ngram-search-API)
* [Character.class](https://github.com/hardikSinghBehl/ngram-search-API/blob/main/src/main/java/com/behl/dragonera/entity/Character.java)
* [CharacterService](https://github.com/hardikSinghBehl/ngram-search-API/blob/main/src/main/java/com/behl/dragonera/service/CharacterService.java)

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
