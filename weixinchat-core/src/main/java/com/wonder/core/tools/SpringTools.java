package com.wonder.core.tools;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wonder.core.dao.DeptDAO;



public class SpringTools {
	@SuppressWarnings({ "rawtypes", "resource" })
	public static Object getDao(Class cls){
		ApplicationContext ac = 
				new ClassPathXmlApplicationContext("classpath:spring.xml", "classpath:spring-mybatis.xml" );
		Object o=ac.getBean(DeptDAO.class);
		return  o;
		
	}
	
	@SuppressWarnings({ "rawtypes", "resource" })
	public static Object selectByPrimaryKey(Class cls){
		ApplicationContext ac = 
				new ClassPathXmlApplicationContext("classpath:spring.xml", "classpath:spring-mybatis.xml" );
		Object o=ac.getBean(DeptDAO.class);
		return  o;
		
	}
	
}
