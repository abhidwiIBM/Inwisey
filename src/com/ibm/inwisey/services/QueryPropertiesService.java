package com.ibm.inwisey.services;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class QueryPropertiesService {
	
	private Properties Property = new Properties();
	
	public QueryPropertiesService(String propertyPath){
		
		try{
		InputStream stream = new FileInputStream(propertyPath);		
		Property.load(stream);
		} catch( Exception e){
			System.out.println(e);
		}
			
	}

	public Properties getInstance() {
		return this.Property;
	}

	public void setProperty(Properties property) {
		this.Property = property;
	}
	
	
	

}

