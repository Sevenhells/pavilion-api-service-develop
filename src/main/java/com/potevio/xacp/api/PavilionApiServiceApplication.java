package com.potevio.xacp.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Controller
@Configuration
@EnableDiscoveryClient
@EnableEurekaClient
@SpringBootApplication
//@EnableWebMvc
@MapperScan(basePackages={"com.potevio.xacp.api.*.mappers","com.potevio.xacp.api.common.base"})
public class PavilionApiServiceApplication extends WebMvcConfigurerAdapter  {
	
    public static void main(String[] args) {
    	SpringApplication.run(PavilionApiServiceApplication.class, args);
    }

}	
