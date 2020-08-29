# PsycholinguisticParser

# Installations requiered for running the app:

1. follow the instruction under the headlines Requirements + Compilation: https://github.com/OnlpLab/yap
2. install JDK + JRE: https://docs.oracle.com/javase/10/install/installation-jdk-and-jre-linux-platforms.htm#JSJIG-GUID-4A6BD592-1840-4BB4-A758-4CD49E9EE88B 

Run the following commands: (yum install can be repladed by sudo apt-install)
1. yum install jq
2. yum install nvm
3. yum install npm
4. yum install libcap-devel

# Make the following code changes:

1. Put your external IP instead of 132.70.30.35 in the following files (you can change the ports as well):
        a. demo 2/src/main/java/com/example/demo/controller/FilesController.java: .
        b. demo 2/src/main/resources/application.properties
        c. react-file-upload/.env
        d. react-file-upload/src/http-common.js
2. replace the path /home/ANT.AMAZON.COM/adiknob/yapproj/src/yap/yap inside demo 2/server.sh to the YAP path in your computer.

# For running the app:

1. inside demo 2 run ./mvnw spring-boot:run
2. inside demo 2 run bash server.sh

A short overview of the project's files in Hebrew can be found here:
https://docs.google.com/document/d/1cs4jTrVKheNVtylf4dFzirCLagGeNpdlSxsRRIw6QGE/edit?usp=sharing
