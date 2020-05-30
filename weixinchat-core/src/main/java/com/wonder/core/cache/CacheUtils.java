package com.wonder.core.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wonder.core.Utils.HttpClientUtil;

@Component(value="cacheUtils")
public class CacheUtils {
	private final static Logger logger = LoggerFactory.getLogger(CacheUtils.class);
	
	
	
	@Autowired
	MapCache mapCache;
	
	@SuppressWarnings({ "static-access", "rawtypes", "unchecked" })
	public String getGoldMapCatchePrice(String spot, int overTime,int flag) {
		Boolean isOverTime = this.mapCache.isOverTime(spot, new Long(overTime));
		JSONObject jsonObject;
		try {
			if (isOverTime) {
				logger.info("牌价已超时，重新刷新缓存");
				if(this.mapCache.GOLDAGTDPRICE.equals(spot)) {
					 jsonObject = HttpClientUtil.doGet(
							"http://api.k780.com/?app=finance.shgold&goldid=1052&version=3&appkey=51412&sign=99a291c772ca77fd6aee3906f0fadc4e&format=json");
				}else {
					 jsonObject = HttpClientUtil.doGet(
							"http://api.k780.com/?app=finance.shgold&goldid=1051&version=3&appkey=51412&sign=99a291c772ca77fd6aee3906f0fadc4e&format=json");
			
				}
				
				String result = jsonObject.getString("result");
				logger.info("查询贵金属价格[{}]",result);
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
				this.mapCache.putData(MapCache.GOLDAGTDPRICE_original, result);
			}else {
				logger.info("牌价未超时，返回缓存价格");
			}
			if(flag==1) {
				return (String) this.mapCache.getMap().get(spot);
			}else {
				return (String) this.mapCache.getMap().get(MapCache.GOLDAGTDPRICE_original);
			}
			
		} catch (Exception e) {
			logger.error("异常[{}]", e);
		}

		return null;
	}
	
	
	
	
	
	public String getSpotMapCatchePrice(String spot,int overTime) {
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
	
	
	
	public String getCNYMapCatchePrice(String spot,int overTime) {
		Boolean isOverTime=this.mapCache.isOverTime(spot, new Long(overTime));
		try {
			if (isOverTime) {
				logger.info("getCNYMapCatchePrice");
				JSONObject jsonObject = HttpClientUtil.doGet(
						"http://www.chinamoney.com.cn/r/cms/www/chinamoney/data/fx/rfx-sp-quot.json?t=1572968607432&t=1572968607441");
				String json=jsonObject.toJSONString();
				this.mapCache.putData(spot, getPrice(json));
			}
			return (String) this.mapCache.getMap().get(spot);
		} catch (Exception e) {
			logger.error("异常[{}]",e);
		}
		
		return null;
	}
}
