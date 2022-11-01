# movie-api
### Проводник для запуска приложения

### База данных:
1. Доступ к базе данных: http://localhost:8080/movie-api/h2-console (data source: user = "sa", password =)
### Terminal:
1. mvn package
2. cd target
3. java -jar core-api-1.0-SNAPSHOT.jar

### Logging:
1. В консоль
2. В папку: target/logfile.log

### Http:
1. Путь к запросам:
   * movie-api/http/films/request_for_films.http
   * movie-api\http\utils\request_swagger-ui.http
   * movie-api\http\utils\request_actuator_health.http
   
