package com.wonder.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wonder.utils.HttpClientUtil;

/**
 * 处理器
 */
@Controller
public class GetMarginPrice {
	private final static Logger logger = LoggerFactory.getLogger(GetMarginPrice.class);
	@RequestMapping("/getMarginPrice.do")
	@ResponseBody
	public JSONArray hello(HttpServletRequest req, HttpServletResponse resp) {
		//允许跨域请求
		resp.setHeader("Access-Control-Allow-Origin", "*");
		String json = "";
		System.out.println("进入getMarginPrice.do");
		try {
			JSONObject	jsonObject = HttpClientUtil.doGet(
					"http://www.chinamoney.com.cn/r/cms/www/chinamoney/data/fx/cpair-quot.json?t=1562509287483&t=1562509297494'");
			json=jsonObject.toJSONString();
		} catch (Exception e) {
			logger.error(e.getMessage());
			}
		return JSON.parseArray(getPrice(json));
	}
	
	public String getPrice(String json) {
		JSONObject jsonObject = JSONObject.parseObject(json);
		Object obj = jsonObject.get("records");
		logger.info(JSON.toJSONString(obj));
		return JSON.toJSONString(obj);
	}

}
