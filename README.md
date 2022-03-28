# Microservice with Gradle 







BahsSnapshort Docker:


		=>docker network create app-net
		=>docker image build -t imranmadbar/bahs-config-server .
		=>docker push imranmadbar/bahs-config-server 
		=>docker container run --network app-net --name bahsConfigServer --env ENV_EUREKA_URL=http://172.19.0.2:9092/eureka/ -p 9091:9091 -d imranmadbar/bahs-config-server
		=>docker container run --network app-net --name bahsConfigServer --env ENV_EUREKA_URL=http://bahsDiscoveryEureka:9092/eureka/ -p 9091:9091 -d imranmadbar/bahs-config-server



		=>docker image build -t imranmadbar/bahs-discovery-eureka .
		=>docker push imranmadbar/bahs-discovery-eureka
		=>docker container run --network app-net --name bahsDiscoveryEureka -p 9092:9092 -d imranmadbar/bahs-discovery-eureka




		=>java -jar student-service-1.0.0.RELEASE.jar CONFIG_SERVER=configserver:http://172.19.0.3:9091
		=>docker image build -t imranmadbar/bahs-student-service .
		=>docker push imranmadbar/bahs-student-service
		=>docker container run --network app-net --name bahsStudentServer --env ENV_EUREKA_URL=http://172.19.0.2:9092/eureka/ -p 8081:8081 -d imranmadbar/bahs-student-service
		=>docker container run --network app-net --name bahsStudentServer --env ENV_EUREKA_URL=http://172.19.0.2:9092/eureka/ --env ENV_CONFIG_SERVER_URL=configserver:http://172.19.0.3:9091 -p 8081:8081 -d imranmadbar/bahs-student-service  
		=>docker container run --network app-net --name bahsStudentServer --env ENV_EUREKA_URL=http://172.19.0.2:9092/eureka/ --env ENV_CONFIG_SERVER_URL=configserver:http://9aea6bf64c02:9091 -p 8081:8081 -d imranmadbar/bahs-student-service
		=>docker container run --network app-net --name bahsStudentServer --env ENV_EUREKA_URL=http://bahsDiscoveryEureka:9092/eureka/ --env ENV_CONFIG_SERVER_URL=configserver:http://bahsConfigServer:9091 -p 8081:8081 -d imranmadbar/bahs-student-service   
		
		
		=>docker image build -t imranmadbar/bahs-course-service .
		=>docker push imranmadbar/bahs-course-service
		=>docker container run --network app-net --name bahsCourseServer --env ENV_EUREKA_URL=http://bahsDiscoveryEureka:9092/eureka/ --env ENV_CONFIG_SERVER_URL=configserver:http://bahsConfigServer:9091 -p 8082:8082 -d imranmadbar/bahs-course-service   

		=>docker image build -t imranmadbar/bahs-edge-gateway .
		=>docker push imranmadbar/bahs-edge-gateway
		=>docker container run --network app-net --name bahsEdgeGateway --env ENV_EUREKA_URL=http://bahsDiscoveryEureka:9092/eureka/ --env ENV_CONFIG_SERVER_URL=configserver:http://bahsConfigServer:9091 -p 9090:9090 -d imranmadbar/bahs-edge-gateway 
		

		=>docker ps -a
		=>docker start containerId
  
