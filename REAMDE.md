# Java Work Assessment

For Java 21+

make sure maven is installed on your local machine
make sure JAVA_HOME path has been set to point to a Java 21 JDK

#### to test&run locally, run the following commands in console:

- run: mvn clean install
- run: mvn spring-boot:run

#### to compile and run from jar (the compiled jar is likely what will be deployed to a server)

- run: mvn clean install
- run: cd target
- run: java -jar app_1_0_0.jar

#### post-run info

after app is running, go to http://localhost:8081/calculate/conversion

##### logs are saved in logstash folder where the app is running
