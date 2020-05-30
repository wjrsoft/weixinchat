package com.wonder.controller.price;

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
import com.wonder.core.cache.CacheUtils;
import com.wonder.core.cache.MapCache;

/**
 * 获取外汇价格
 */
@Controller
public class GetGoldPrice {
	private final static Logger logger = LoggerFactory.getLogger(GetGoldPrice.class);

	@Autowired
	MapCache mapCache;
	@Autowired
	CacheUtils cacheUtils;

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
			mapCatchePrice = cacheUtils.getGoldMapCatchePrice(this.mapCache.GOLDAGTDPRICE, MapCache.GOLDAGTDPRICE_CATCHE_TIME,1);
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
			mapCatchePrice = this.cacheUtils.getGoldMapCatchePrice(this.mapCache.GOLDAUTDPRICE, this.mapCache.GOLDAUTDPRICE_CATCHE_TIME,1);
			logger.info("价格[{}]", mapCatchePrice);
		} catch (Exception e) {
			logger.error("异常[{}]", e);
		}
		return JSON.parseArray(mapCatchePrice);
	}
	

	
	
	
	public MapCache getMapCache() {
		return mapCache;
	}

	public void setMapCache(MapCache mapCache) {
		this.mapCache = mapCache;
	}



	public CacheUtils getCacheUtils() {
		return cacheUtils;
	}



	public void setCacheUtils(CacheUtils cacheUtils) {
		this.cacheUtils = cacheUtils;
	}
	
	
}
