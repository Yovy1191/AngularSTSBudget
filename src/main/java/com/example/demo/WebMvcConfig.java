package com.example.demo;

import java.util.Locale;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;



@Configuration 
public class WebMvcConfig implements WebMvcConfigurer {
	


	
	 @Override
	  public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		 registry.addResourceHandler("/jquery/**") //
         .addResourceLocations("classpath:/META-INF/resources/webjars/jquery/1.11.1/");
		 
		 registry.addResourceHandler("/bootstrap/**") //
         .addResourceLocations("classpath:/META-INF/resources/webjars/bootstrap/3.3.7/");
		 
		 
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
	    public LocaleResolver localeResolver() {
	       SessionLocaleResolver localeResolver = new SessionLocaleResolver();
	       localeResolver.setDefaultLocale(Locale.US);
	       return localeResolver;
	    }
	    @Bean
	    public LocalValidatorFactoryBean validator(MessageSource messageSource) {
	       LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
	       bean.setValidationMessageSource(messageSource);
	       return bean;
	    }
	    
//	    @Bean
//	    public CookieLocaleResolver localeResolver(){
//	        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
//	        localeResolver.setDefaultLocale(Locale.ENGLISH);
//	        localeResolver.setCookieName("my-locale-cookie");
//	        localeResolver.setCookieMaxAge(3600);
//	        return localeResolver;
//	    }

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
