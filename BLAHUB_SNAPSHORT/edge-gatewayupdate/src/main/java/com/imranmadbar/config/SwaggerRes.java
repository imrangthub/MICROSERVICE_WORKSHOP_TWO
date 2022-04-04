package com.imranmadbar.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
class SwaggerRes {

    String location;
    String name;
    String swaggerVersion;
    String url;

}