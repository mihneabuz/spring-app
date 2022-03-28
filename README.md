# HackItAll 2022

Spring Backend made for 24 hour hackathon

Frontend available at: https://github.com/mihneabuz/react-app/tree/hackitall
Agents available at: https://github.com/mihneabuz/file-agent

Agents connect to the backend and send regular heartbeats
Agents also expose routes for quering and manipulating the file system

Frontend users can see and interact with all the agents available to them

Building:
  ./gradlew build
  
Running:
  java -jar build/libs/project-0.0.1-SNAPSHOT.jar
