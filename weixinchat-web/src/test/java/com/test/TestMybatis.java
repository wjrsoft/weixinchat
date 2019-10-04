//package com.test;
//
//import java.util.List;
//
//import org.apache.log4j.Logger;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.alibaba.fastjson.JSON;
//import com.yeepay.g3.tel.weixin.core.entity.Emp;
//import com.yeepay.g3.tel.weixin.core.server.EmpServerI;
//
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath:spring.xml", "classpath:spring-mybatis.xml" })
//public class TestMybatis {
//	private static final Logger logger = Logger.getLogger(Test.class);
//	public EmpServerI empServerI;
//	public EmpServerI getEmpServerI() {
//		return empServerI;
//	}
//	@Autowired
//	public void setEmpServerI(EmpServerI empServerI) {
//		this.empServerI = empServerI;
//	}
//	
//    @Test
//   	public void getEmpById() {
//   		Emp u = empServerI.getEmpById((short) 7369 );
//   		logger.info(JSON.toJSONStringWithDateFormat(u, "yyyy-MM-dd HH:mm:ss"));
//   		System.out.println("aaaaaaaaaaaaaaaaaaaa");    
//   	}
//	
//	
//    @Test
//   	public void findAll() {
//   		List<Emp> u = empServerI.findAll();
//   		logger.info(JSON.toJSONStringWithDateFormat(u, "yyyy-MM-dd HH:mm:ss")); 
//   	}
//	
//    @Test
//    public void findByEmp(){
//    	Emp emp=new Emp();
//    	emp.setDeptno((float) 20);
//    	List<Emp> u = empServerI.findByEmp(emp);
//    	logger.info(JSON.toJSONStringWithDateFormat(u, "yyyy-MM-dd HH:mm:ss")); 
//    }
//	
//    @Test
//    public void insert(){
//    	Emp emp=new Emp();
//    	emp.setEmpno(1112);
//    	emp.setEname("aa");
//    	
//    	emp.setDeptno((float) 30);
//    	int u = empServerI.insert(emp);
//    	logger.info(JSON.toJSONStringWithDateFormat(u, "yyyy-MM-dd HH:mm:ss")); 
//    }
//	
//	
//	
//	
//	
//	
//}
