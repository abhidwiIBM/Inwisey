package com.ibm.inwisey.services;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class ApplicationContextService implements ApplicationContextAware {

	@Autowired
    private static ApplicationContext applicationContext;     

    public void setApplicationContext(ApplicationContext context) throws BeansException {
      applicationContext = context;
    }

    public static ApplicationContext getApplicationContext() {
      return applicationContext;
    }
}
