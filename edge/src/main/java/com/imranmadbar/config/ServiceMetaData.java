package com.imranmadbar.config;


public class ServiceMetaData {

    private String name;

    private String urlSwaggerResource;

    private String urlActuator;

    // region <Getter and Setter>

    public String getName() {
        return name;
    }

    public ServiceMetaData setName(String name) {
        this.name = name;
        return this;
    }

    public String getUrlSwaggerResource() {
        return urlSwaggerResource;
    }

    public ServiceMetaData setUrlSwaggerResource(String urlSwaggerResource) {
        this.urlSwaggerResource = urlSwaggerResource;
        return this;
    }

    public String getUrlActuator() {
        return urlActuator;
    }

    public ServiceMetaData setUrlActuator(String urlActuator) {
        this.urlActuator = urlActuator;
        return this;
    }

}
