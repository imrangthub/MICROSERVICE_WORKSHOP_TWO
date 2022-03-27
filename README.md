# Microservice with Gradle 








BLAHUB_SNAPSHORT
---------------------------------------------

=>docker network create app-net

=>docker container run --network app-net --name bahsDiscoveryEureka -p 9092:9092 -d imranmadbar/bahs-discovery-eureka

=>docker container run --network app-net --name bahsConfigServer --env ENV=http://172.19.0.2:9092/eureka/ -p 9091:9091 -d imranmadbar/bahs-config-server




=>docker image build -t imranmadbar/bahs-student-service .

=>docker container run --network app-net --name bahsStudentServer --env ENV_EUREKA=http://172.19.0.2:9092/eureka/ -p 8081:8081 -d imranmadbar/bahs-student-service   
