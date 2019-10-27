package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.wonder.core.schema.Dept;
import com.wonder.core.server.DeptService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml", "classpath:spring-mybatis.xml" })
public class Junit4TestDept {
	private static final Logger logger = LoggerFactory.getLogger(Junit4TestDept.class);
	public DeptService deptService;

	public DeptService getDeptService() {
		return deptService;
	}

	@Autowired
	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}

	@Test
	public void test() {
		Dept dept = new Dept();
		dept = deptService.selectByPrimaryKey(20);
		logger.info("===============:"+JSON.toJSONStringWithDateFormat(dept, "yyyy-MM-dd HH:mm:ss"));
	}

}