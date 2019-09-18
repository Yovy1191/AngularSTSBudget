package com.example.demo;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

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
	    
	    
	    @Bean
	    public ReloadableResourceBundleMessageSource messageSource(){
	        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
	        messageSource.setBasename("classpath:messages");
	        messageSource.setDefaultEncoding("UTF-8");
	        return messageSource;
	    }
	    
	    @Bean
	    public CookieLocaleResolver localeResolver(){
	        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
	        localeResolver.setDefaultLocale(Locale.ENGLISH);
	        localeResolver.setCookieName("my-locale-cookie");
	        localeResolver.setCookieMaxAge(3600);
	        return localeResolver;
	    }

	    @Bean
	    public LocaleChangeInterceptor localeInterceptor(){
	        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
	        interceptor.setParamName("lang");
	        return interceptor;
	    }

	    @Override
	    public void addInterceptors(InterceptorRegistry registry) {
	        registry.addInterceptor(localeInterceptor());
	    }

	    @Bean
	    public InternalResourceViewResolver viewResolver(){
	        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
	        viewResolver.setViewClass(JstlView.class);
	        viewResolver.setPrefix("/src/main/resources/templates/");
	        viewResolver.setSuffix(".jsp");
	        return viewResolver;

	    }
	 
}
