package com.wonder.controller.price;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.wonder.core.cache.CacheUtils;
import com.wonder.core.cache.MapCache;

@Controller
public class PriceController {
	@Autowired
	CacheUtils cacheUtils;
	private static Logger log = LoggerFactory.getLogger(PriceController.class);
	@RequestMapping("/marginPriceWeb.do")//菜单配置
	public ModelAndView marginPriceWeb(){
		log.info("进入marginPriceWeb");
		//如果用户存在跳转登录页登录
		ModelAndView mav = new ModelAndView();
		mav.setViewName("marginPrice");
		return mav;
		
	}
	
	
	@RequestMapping("/marginCNYWeb.do")//菜单配置
	public ModelAndView cNYPriceWeb(){
		log.info("进入login");
		//如果用户存在跳转登录页登录
		ModelAndView mav = new ModelAndView();
		mav.setViewName("CNYPrice");
		return mav;
		
	}
	
	@RequestMapping("/GoldAGTDWeb.do")//菜单配置
	public ModelAndView goldAGTDWeb(){
		log.info("进入login");
		//如果用户存在跳转登录页登录
		ModelAndView mav = new ModelAndView();
		mav.setViewName("AGTDPrice");
		return mav;
		
	}
	@RequestMapping("/GoldAUTDWeb.do")//菜单配置
	public ModelAndView GoldAUTDWeb(){
		log.info("进入login");
		//如果用户存在跳转登录页登录
		ModelAndView mav = new ModelAndView();
		mav.setViewName("AUTDPrice");
		return mav;
		
	}
	
	
	@RequestMapping("/setAgtdPriceWeb.do")//菜单配置
	public ModelAndView setAgtdPriceWeb(HttpServletRequest req,
			  HttpServletResponse resp){
		log.info("进入setAgtdPriceWeb");
		// 取缓存数据，超过时间更新缓存
		Object str=req.getSession().getAttribute("openid");
		String price="";
		String sell_price="";
		String buy_price="";
		@SuppressWarnings("rawtypes")
		Map map1 = new HashMap();
		try {
			price = cacheUtils.getGoldMapCatchePrice(MapCache.GOLDAGTDPRICE, 300, 2);
			map1 = JSON.parseObject(price);
		} catch (Exception e) {
			log.error("异常不处理:[{}]",e);
		}
		sell_price = (String) map1.get("sell_price");
		buy_price = (String) map1.get("buy_price");
		
		System.out.println("==========111=="+price);
		//如果用户存在跳转登录页登录
		ModelAndView mav = new ModelAndView();
		mav.getModel().put("openid", str!=null?String.valueOf(str):"");
		mav.getModel().put("sell_price", sell_price);
		mav.getModel().put("buy_price", buy_price);
		mav.setViewName("AGTDPriceSetting");
		return mav;
	}


	public CacheUtils getCacheUtils() {
		return cacheUtils;
	}


	public void setCacheUtils(CacheUtils cacheUtils) {
		this.cacheUtils = cacheUtils;
	}
	
}
