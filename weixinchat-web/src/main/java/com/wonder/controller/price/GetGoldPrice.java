package com.wonder.controller.price;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
public class GetGoldPrice {
	private final static Logger logger = LoggerFactory.getLogger(GetGoldPrice.class);

	@Autowired
	MapCache mapCache;

	@SuppressWarnings({ "unused", "static-access" })
	@RequestMapping("/getGoldAGTDPrice.do")
	@ResponseBody
	public JSONArray getGoldPrice(HttpServletRequest req, HttpServletResponse resp) {
		// 允许跨域请求
		resp.setHeader("Access-Control-Allow-Origin", "*");
		String json = "";
		String mapCatchePrice = "";
		System.out.println("进入getGoldAGTDPrice.do");
		try {
			// 取缓存数据，超过时间更新缓存
			mapCatchePrice = getMapCatchePrice(this.mapCache.GOLDAGTDPRICE, 300);
			logger.info("价格[{}]", mapCatchePrice);
		} catch (Exception e) {
			logger.error("异常[{}]", e);
		}
		return JSON.parseArray(mapCatchePrice);
	}
	
	
	
	@SuppressWarnings({ "unused", "static-access" })
	@RequestMapping("/getGoldAUTDPrice.do")
	@ResponseBody
	public JSONArray getGoldAUTDPrice(HttpServletRequest req, HttpServletResponse resp) {
		// 允许跨域请求
		resp.setHeader("Access-Control-Allow-Origin", "*");
		String json = "";
		String mapCatchePrice = "";
		System.out.println("进入getGoldAUTDPrice.do");
		try {
			// 取缓存数据，超过时间更新缓存
			mapCatchePrice = getMapCatchePrice(this.mapCache.GOLDAUTDPRICE, 300);
			logger.info("价格[{}]", mapCatchePrice);
		} catch (Exception e) {
			logger.error("异常[{}]", e);
		}
		return JSON.parseArray(mapCatchePrice);
	}
	
	
	
	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	public String getMapCatchePrice(String spot, int overTime) {
		Boolean isOverTime = this.mapCache.isOverTime(spot, new Long(overTime));
		logger.info("isOverTime=", isOverTime);
		JSONObject jsonObject;
		try {

			if (isOverTime) {
				if(this.mapCache.GOLDAGTDPRICE.equals(spot)) {
					 jsonObject = HttpClientUtil.doGet(
							"http://api.k780.com/?app=finance.shgold&goldid=1052&version=3&appkey=51412&sign=99a291c772ca77fd6aee3906f0fadc4e&format=json");
				}else {
					 jsonObject = HttpClientUtil.doGet(
							"http://api.k780.com/?app=finance.shgold&goldid=1051&version=3&appkey=51412&sign=99a291c772ca77fd6aee3906f0fadc4e&format=json");
			
				}
				
				String result = jsonObject.getString("result");
				List list = new ArrayList();
				Map map1 = JSON.parseObject(result);
				Set<String> keys = map1.keySet();
				for (String key : keys) {
					Map map = new HashMap();
					map.put("name", key);
					map.put("name1", key);
					map.put("value", map1.get(key));
					list.add(map);
				}
				String json = JSON.toJSONString(list);
				this.mapCache.putData(spot, json);
			}
			return (String) this.mapCache.getMap().get(spot);
		} catch (Exception e) {
			logger.error("异常[{}]", e);
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

	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	public static void main(String[] args) throws Exception {
		JSONObject jsonObject = HttpClientUtil.doGet(
				"http://api.k780.com/?app=finance.shgold&goldid=1052&version=3&appkey=51412&sign=99a291c772ca77fd6aee3906f0fadc4e&format=json");
		String result = jsonObject.getString("result");
		List list = new ArrayList();
		Map map1 = JSON.parseObject(result);
		Set<String> keys = map1.keySet();
		for (String key : keys) {
			Map map = new HashMap();
			map.put("name", key);
			map.put("name1", key);
			map.put("value", map1.get(key));
			list.add(map);
		}
		String json = JSON.toJSONString(list);
	}

}
