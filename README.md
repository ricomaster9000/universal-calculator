# universal-calculator
Dynamic Modular Extendable Universally usable calculator app (MVC design pattern with strong emphasis on SOLID design)

For Java 17+ (will migrate to Java 21 once AWS officially starts supporting Java 21) - 2 years max from now

A serious personal project of mine to create a powerful universally usable java web app that can perform basic to advanced to cpu-intensive calculations accurrately and nearly on par with C++ C level performance using the Java Foreign Function and Memory API.

For now only basic calculations and the features around the usage of the Foreign Function and Memory API will be implemented.

I intend to use this for one of my other private personal projects.

make sure maven is installed on your local machine make sure JAVA_HOME path has been set to point to a Java 17 JDK

## to test&run locally, run the following commands in console:
- run: mvn clean install
- run: mvn spring-boot:run
- install Postman to send requests locally to web app, postman collection that contains a collection of requests one can send to the available endpoints - https://api.postman.com/collections/2864126-5044291c-23bc-4f04-a587-cf140413415e?access_key=PMAT-01HFXSKV12D8YTPB9QFZ8T2PVM
## to compile and run from jar (the compiled jar is likely what will be deployed to a server)
- run: mvn clean install
- run: cd target
- run: java -jar universalcalculator-0.4.1.jar
## post-run info
### to access embedded derby db via a db client
- install DBeaver
- when selecting what type of database to connect to, pick Apache Derby Embedded
- the db folder is dbDirectory inside the project directory, use that
- use the username and password set in the application.properties
- MAKE SURE NOTHING ELSE IS INTERACTING WITH THE DB WHEN TRYING TO CONNECT, so web app cannot run if you want to connect to db**

amazon-linux-extras install epel -y
yum update -y --skip-broken
yum install -y certbot
sudo yum install certbot-nginx
