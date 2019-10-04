//package com.test;
//
//import javax.annotation.Resource;
//
//import org.apache.log4j.Logger;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.alibaba.fastjson.JSON;
//import com.yeepay.g3.tel.weixin.core.dao.UserMapper;
//import com.yeepay.g3.tel.weixin.core.entity.User;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath:spring.xml", "classpath:spring-mybatis.xml" })
//public class TestUser {
//	private static final Logger logger = Logger.getLogger(TestUser.class);
//	@Resource
//	UserMapper userDao;
//	@Test
//	 public void test(){
//		User u=new User();
//		u.setId((long) 123);
//		u.setNickname("aabc");
//		u.setOpenid("111");
//		userDao.insert(u);
//	}
//	@Test
//	public void test02(){
//		User u=new User();
//		u.setId((long) 123);
//		u.setNickname("aabc");
//		u.setOpenid("111");
//		
//		System.out.println(JSON.toJSONString(userDao.selectByPrimaryKey("111")));
//	}
//	
//}
