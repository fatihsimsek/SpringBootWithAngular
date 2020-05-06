package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class BeanExample implements InitializingBean, DisposableBean {

	private static final Logger logger =LoggerFactory.getLogger(DemoApplication.class);
	
	@Override
	public void destroy() throws Exception {
		logger.info("BeanExample destroy");
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		logger.info("afterProperties Set");
	}
}
