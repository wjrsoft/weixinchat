package com.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;
import com.wonder.core.dao.WxUserDAO;
import com.wonder.core.schema.WxUser;

public class WxUserTestMain {
	private final static Logger logger = LoggerFactory.getLogger(WxUserTestMain.class);
	@SuppressWarnings("resource")
	
//	public static void main(String[] args) {
//		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring-core.xml",
//				"classpath:spring-mybatis.xml");
//		WxUserDAO wxUserDAO = (WxUserDAO) ac.getBean("wxUserDAO");
//		WxUser wxUser = new WxUser();
//		wxUser = (WxUser) wxUserDAO.selectByOpenid("o0faKw0cZaQw78n2lY5xBEkdfQ8I");
//		logger.info(JSON.toJSONString(wxUser));
//
//	}
	
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring-core.xml",
				"classpath:spring-mybatis.xml");
		WxUserDAO wxUserDAO = (WxUserDAO) ac.getBean("wxUserDAO");
		WxUser wxUser = new WxUser();
		wxUser.setOpenid("123456");
		wxUserDAO.insertByWxUser(wxUser);
		logger.info(JSON.toJSONString(wxUser));

	}
}
