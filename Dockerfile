FROM adoptopenjdk/openjdk15:ubi
ADD Task/target/task-0.0.1.jar .
EXPOSE 8080
CMD java -jar task-0.0.1.jar
