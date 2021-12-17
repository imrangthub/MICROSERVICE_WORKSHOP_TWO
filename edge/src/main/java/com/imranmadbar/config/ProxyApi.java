package com.imranmadbar.config;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

/**
 * Responsibility:
 *
 * @author Rezaul Hasan
 * @since 01-Sep-2019
 */
@Configuration
public class ProxyApi {

    private static Logger logger = LoggerFactory.getLogger(ProxyApi.class);

    @Value("#{'${imranmadbar.microservices}'.split(',') }")
    private List<String> microservices;

    @Autowired ZuulProperties properties;

    @Autowired private DiscoveryClient discoveryClient;

    @Autowired private RestTemplate restTemplate;

    @Value("${server.port}")
    private int serverPort;


    @Primary
    @Bean
    public SwaggerResourcesProvider swaggerResourcesProvider() {
        return this::getSwaggerResource;
    }

    private List<SwaggerResource> getSwaggerResource() {

        List<SwaggerResource> resources = new ArrayList<>();

        List<ServiceMetaData> urls =
                microservices.stream()
                        .map(
                                x ->
                                        new ServiceMetaData()
                                                .setUrlSwaggerResource(
                                                        "http://localhost:" + serverPort + "/" + x + "/swagger-resources")
                                                .setName(x))
                        .collect(Collectors.toList());

        for (int i = 0; i < urls.size(); i++) {
            try {

                ServiceMetaData serviceMetaData = urls.get(i);

                ResponseEntity<List<SwaggerResource>> response =
                        restTemplate.exchange(
                                serviceMetaData.getUrlSwaggerResource(),
                                HttpMethod.GET,
                                null,
                                new ParameterizedTypeReference<List<SwaggerResource>>() {});

                if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {

                    resources.addAll(
                            response.getBody().stream()
                                    .peek(
                                            y -> {
                                                String location = "/" + serviceMetaData.getName() + y.getUrl();
                                                y.setLocation(location);
                                                y.setUrl(location);
                                            })
                                    .collect(Collectors.toList()));
                }

            } catch (Exception e) {

                logger.error(e.getMessage() + ": " + e.getCause());
            }
        }

        return resources;
    }

}

