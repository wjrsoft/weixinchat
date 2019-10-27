package com.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;
import com.wonder.core.dao.DeptDAO;
import com.wonder.core.schema.Dept;

public class SpringDaoTest {
	private final static Logger logger = LoggerFactory.getLogger(SpringDaoTest.class);
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring.xml",
				"classpath:spring-mybatis.xml");
		DeptDAO deptDAO = (DeptDAO) ac.getBean("deptDAO");
		Dept dept = new Dept();
		dept = deptDAO.selectByPrimaryKey(20);
		logger.info(JSON.toJSONString(dept));

	}
}
