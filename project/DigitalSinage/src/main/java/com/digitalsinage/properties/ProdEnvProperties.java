package com.digitalsinage.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "prod")
public class ProdEnvProperties extends Properties {


}
