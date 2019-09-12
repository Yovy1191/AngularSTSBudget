package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration 
public class WebMvcConfig implements WebMvcConfigurer {
	
	 @Override
	  public void addResourceHandlers(ResourceHandlerRegistry registry) {
		 registry.addResourceHandler("/bootstrap/**") //
         .addResourceLocations("classpath:/META-INF/resources/webjars/bootstrap/3.3.7/");
		 
		 registry.addResourceHandler("/jquery/**") //
         .addResourceLocations("classpath:/META-INF/resources/webjars/jquery/1.11.1/");
		 
		 registry.addResourceHandler("/popper/**") //
         .addResourceLocations("classpath:/META-INF/resources/webjars/popper.js/1.14.1/umd/");
		 
		 registry
         .addResourceHandler("/resources/**")
         .addResourceLocations("/resources/"); 
		 
		 registry
		 .addResourceHandler("/webjars/**")
		 .addResourceLocations("classpath:/META-INF/resources/webjars/");
	  }
	 
}
