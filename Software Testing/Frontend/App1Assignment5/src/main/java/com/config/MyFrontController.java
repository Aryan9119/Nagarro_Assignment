package com.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
/**
 * This class is used to configure the front controller for the web app
 * @author aryanverma
 *
 */
public class MyFrontController extends AbstractAnnotationConfigDispatcherServletInitializer {

	/**
	 * Returns the root configuration classes for the web application.
	 * @return an array of classes representing the root configuration classes
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { MyMvcConfig.class };
	}

	/**
	 * Returns the servlet configuration classes for the web application.
	 * In this case, the servlet configuration is null because all the configuration
	 * is specified in the root configuration class.
	 * @return an array of classes representing the servlet configuration classes
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	/**
	 * Returns the servlet mappings for the web application.
	 * In this case, the mapping is for the root URL of the web application.
	 * @return an array of strings representing the servlet mappings
	 */
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}


}