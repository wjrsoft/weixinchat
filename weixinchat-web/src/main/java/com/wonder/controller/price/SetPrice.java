package com.wonder.controller.price;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.wonder.core.cache.MapCache;

@Controller
public class SetPrice {
	private static Logger logger = LoggerFactory.getLogger(SetPrice.class);
	
	@Autowired
	MapCache mapCache;
	
	
	@SuppressWarnings({ "static-access", "unchecked" })
	@RequestMapping("/SettingAGTDPrice.do")
	@ResponseBody
	public String SettingAGTDPrice(HttpServletRequest req, HttpServletResponse resp,
			@RequestParam(value = "openid") String openid, @RequestParam(value = "buy") String buy,
			@RequestParam(value = "sell") String sell, @RequestParam(value = "buy_price") String buy_price,
			@RequestParam(value = "sell_Price") String sellPrice) {
		
		logger.info("进入SettingAGTDPrice"); 
		logger.info("openid:"+openid); 
		logger.info("buy:"+buy);
		logger.info("sell:"+sell);
		logger.info("buy_price:"+buy_price);
		logger.info("sellPrice:"+sellPrice);
		
		int vbuy=Integer.valueOf(buy);
		int vsell=Integer.valueOf(sell);
		int vbuy_price=Integer.valueOf(buy_price);
		int vsellPrice=Integer.valueOf(sellPrice);
		Map<String,String> map = new HashMap<String,String>();
		boolean isCache=true;
		try{
//			if (vbuy >= vsellPrice) {
//				map.put("result", "买多，看跌设定价，要小于参考买多价");
//				map.put("status","2");
//				isCache=false;
//			} else if (vsell <= vbuy_price) {
//				map.put("status","2");
//				map.put("result","卖空，看涨，要大于参考卖空价");
//				isCache=false;
//			}else {
//				map.put("result", "设定成功，到价消息推送");
//				map.put("status","1");
//			}
			map.put("result", "设定成功，到价消息推送");
			map.put("status","1");
			if(isCache) {
				Set<String> set = new HashSet<String>();
				set=(Set<String>) mapCache.getMap().get(mapCache.PRICE_CLOCK_LIST)==null?new HashSet<String>():(HashSet<String>) mapCache.getMap().get(mapCache.PRICE_CLOCK_LIST);
				set.add(openid);
				mapCache.putData(mapCache.CLOCK_LIST, set);//设置闹钟的列表
				mapCache.putData(this.mapCache.OPENID_BUY_SETTING+openid, vbuy);
				mapCache.putData(this.mapCache.OPENID_SELL_SETTING+openid, vsell);
				logger.info("闹钟设置[{}]，[{}}",this.mapCache.OPENID_SELL_SETTING+openid,this.mapCache.getMap().get(this.mapCache.OPENID_SELL_SETTING+openid));
				logger.info("闹钟设置[{}]，[{}}",this.mapCache.OPENID_BUY_SETTING+openid,this.mapCache.getMap().get(this.mapCache.OPENID_BUY_SETTING+openid));
			}
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		return  JSON.toJSONString(map);
	}

	}
