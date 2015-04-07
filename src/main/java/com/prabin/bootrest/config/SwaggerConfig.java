package com.prabin.bootrest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import com.wordnik.swagger.model.ApiInfo;

@Configuration
@EnableSwagger
@EnableAutoConfiguration
public class SwaggerConfig {

	private SpringSwaggerConfig springSwaggerConfig;
	
	@Autowired
	public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {
		this.springSwaggerConfig = springSwaggerConfig;
	}
	
	public SwaggerSpringMvcPlugin customImplementation() {
		return new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
			.apiInfo(new ApiInfo("Todo app", "This is a test app", null, null, null, null))
			.includePatterns("/api.*");
	}
}
