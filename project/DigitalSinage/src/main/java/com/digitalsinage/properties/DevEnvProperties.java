package com.digitalsinage.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "dev")
public class DevEnvProperties extends Properties {


}
