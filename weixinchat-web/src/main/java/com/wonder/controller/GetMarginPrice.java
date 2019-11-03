package com.wonder.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wonder.core.cache.MapCache;
import com.wonder.utils.HttpClientUtil;

/**
 * 获取外汇价格
 */
@Controller
public class GetMarginPrice {
	private final static Logger logger = LoggerFactory.getLogger(GetMarginPrice.class);
	
	
	@Autowired
	MapCache mapCache;
	@SuppressWarnings({ "unused", "static-access" })
	@RequestMapping("/getMarginPrice.do")
	@ResponseBody
	public JSONArray getMarginPrice(HttpServletRequest req, HttpServletResponse resp) {
		//允许跨域请求
		resp.setHeader("Access-Control-Allow-Origin", "*");
		String json = "";
		String mapCatchePrice="";
		System.out.println("进入getMarginPrice.do");
		try {
			//取缓存数据，超过时间更新缓存
			mapCatchePrice=getMapCatchePrice(this.mapCache.SPOTPRICE,300);
			logger.info("价格[{}]",mapCatchePrice);
//			JSONObject	jsonObject = HttpClientUtil.doGet(
//					"http://www.chinamoney.com.cn/r/cms/www/chinamoney/data/fx/cpair-quot.json?t=1562509287483&t=1562509297494'");
//			json=jsonObject.toJSONString();
		} catch (Exception e) {
			logger.error("异常[{}]",e);
		}
//		System.out.println(this.getPrice(json));
//		System.out.println(mapCatchePrice);
		return JSON.parseArray(mapCatchePrice);
//		return JSON.parseArray(this.getPrice(json));
	}
	
	
	public String getMapCatchePrice(String spot,int overTime) {
		Boolean isOverTime=this.mapCache.isOverTime(spot, new Long(overTime));
		try {
			
			if (isOverTime) {
				JSONObject jsonObject = HttpClientUtil.doGet(
						"http://www.chinamoney.com.cn/r/cms/www/chinamoney/data/fx/cpair-quot.json?t=1562509287483&t=1562509297494'");
				String json=jsonObject.toJSONString();
				this.mapCache.putData(spot, getPrice(json));
			}
			return (String) this.mapCache.getMap().get(spot);
		} catch (Exception e) {
			logger.error("异常[{}]",e);
		}
		
		return null;
	}
	
	public String getPrice(String json) {
		JSONObject jsonObject = JSONObject.parseObject(json);
		Object obj = jsonObject.get("records");
		logger.info(JSON.toJSONString(obj));
		return JSON.toJSONString(obj);
	}


	public MapCache getMapCache() {
		return mapCache;
	}


	public void setMapCache(MapCache mapCache) {
		this.mapCache = mapCache;
	}

	
}
