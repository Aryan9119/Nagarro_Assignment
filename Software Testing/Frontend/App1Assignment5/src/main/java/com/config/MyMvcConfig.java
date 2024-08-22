
package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com" })
public class MyMvcConfig implements WebMvcConfigurer {
	@Bean
	
	/**
	 * Configures the view resolver used to resolve JSP views.
	 * @return An InternalResourceViewResolver object that resolves JSP views.
	 */
	public InternalResourceViewResolver resolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setViewClass(JstlView.class);
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
//	@Bean
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }
	
	/**
	 *Configures the resource handlers used to serve static resources like CSS and JavaScript files.
	 *@param registry A ResourceHandlerRegistry object that is used to register the resource handlers.
	*/ 

	
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
}
