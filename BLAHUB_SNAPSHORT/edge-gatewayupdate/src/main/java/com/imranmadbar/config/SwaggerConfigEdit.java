//package com.imranmadbar.config;
//
//import java.net.UnknownHostException;
//import java.util.ArrayList;
//import java.util.List;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.http.client.utils.URIBuilder;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.client.discovery.DiscoveryClient;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Mono;
//import springfox.documentation.swagger.web.SwaggerResource;
//import springfox.documentation.swagger.web.SwaggerResourcesProvider;
//
//
//@Slf4j
//@Configuration
//public class SwaggerConfigEdit {
//    private static final Logger logger = LoggerFactory.getLogger(SwaggerConfigEdit.class);
//
//    @Value("#{'${dmz.microservices}'.split(',') }")
//    private List<String> excludeMicroservices;
//
//    @Value("${server.port}")
//    private int serverPort;
//
//    String ipAddress = "localhost";
//
//    private final DiscoveryClient discoveryClient;
//
//    public SwaggerConfigEdit(DiscoveryClient discoveryClient) throws UnknownHostException {
//        this.discoveryClient = discoveryClient;
//    }
//
//    private List<SwaggerResource> getSwaggerResource() {
//
//        List<SwaggerResource> resources = new ArrayList<>();
//
//        List<String> services = discoveryClient.getServices();
//
//        services.removeAll(excludeMicroservices);
//
//        String regex = "^(.*?(\\beureka-\\b)[^$]*)$";
//        List<String> urlList = new ArrayList<>();
//        services.stream()
//                .filter(x -> !x.matches(regex))
//                .forEach(x -> {
//                    try {
//                        String url =
//                                new URIBuilder()
//                                        .setScheme("http")
//                                        .setHost(ipAddress)
//                                        .setPort(serverPort)
//                                        .setPathSegments(x, "swagger-resources")
//                                        .build()
//                                        .toString();
//                        urlList.add(url);
//                        logger.info("urlList:"+urlList);
//
//                    } catch (Exception e) {
//                        logger.error(e.getMessage() + ": " + e.getCause());
//                    }
//
//                    Mono<ResponseEntity<List<SwaggerResource>>> resultStr =  null;
//                    WebClient webClient = WebClient.create();
//                    logger.info("---------------SwaggerResource Call Start -----------------");
//                    for (String url:urlList){
//
//                        SwaggerResource obj = new SwaggerResource();
////                        obj.setLocation("/"+x+"/v2/api-docs?group=Student Service 1.0.0.RELEASE");
//
//
//                        resultStr = webClient
//                                .get()
//                                .uri(url).accept(MediaType.APPLICATION_JSON)
//                                .retrieve()
//                                .toEntity(new ParameterizedTypeReference<List<SwaggerResource>>() {
//                                });
//                        resultStr.subscribe(v->{
//                            if (v.getStatusCode() == HttpStatus.OK && v.getBody() != null) {
//                                System.out.println("ResultSuccrp"+ v.getBody().get(0).getName());
//
//                                obj.setLocation("/"+x+v.getBody().get(0).getUrl());
//
//                            }
//                        });
//
//                        resources.add(obj);
//                    }
//
//
//
//
//                });
//
//
//
//
//
//        return resources;
//    }
//
//    List<SwaggerResource> getSwaggerResourceList(List<String> urlList){
//
//        List<SwaggerResource> resources = new ArrayList<>();
//        Mono<ResponseEntity<List<SwaggerResource>>> resultStr =  null;
//        WebClient webClient = WebClient.create();
//        logger.info("---------------SwaggerResource Call Start -----------------");
//        for (String url:urlList){
//
//            SwaggerResource obj = new SwaggerResource();
//            resultStr = webClient
//                    .get()
//                    .uri(url).accept(MediaType.APPLICATION_JSON)
//                    .retrieve()
//                    .toEntity(new ParameterizedTypeReference<List<SwaggerResource>>() {
//                    });
//            resultStr.subscribe(v->{
//                if (v.getStatusCode() == HttpStatus.OK && v.getBody() != null) {
//                    System.out.println("ResultSuccrp"+ v.getBody().get(0).getName());
//
//                    obj.setName(v.getBody().get(0).getName());
//
//                    String location = "/" + v.getBody().get(0).getName()+ v.getBody().get(0).getUrl();
//                    obj.setLocation(location);
//                    obj.setUrl(location);
//
//                }
//            });
//
//            resources.add(obj);
//        }
//
//        logger.info("---------------SwaggerResource Call End ----------------");
//
//
//        System.out.println("resultStr:"+resources);
//
//
//
//        return resources;
//
//    }
//
//
//
//    @Primary
//    @Bean
//    public SwaggerResourcesProvider swaggerResourcesProvider() {
//        return this::getSwaggerResource;
//    }
//}
//
//
