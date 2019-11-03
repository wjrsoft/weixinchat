package com.wonder.core.cache;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 
 * @author wangjinrong
 * 20191103
 */
@Component(value="mapCache")
public class MapCache {
	private final static Logger log = LoggerFactory.getLogger(MapCache.class);
	
	Map<String,Object> map = new HashMap<String,Object>();
	public static String SPOTPRICE="spotPrice";
	Date now = new Date(); 
	SimpleDateFormat dateFormat = new SimpleDateFormat("HHmmss");//可以方便地修改日期格式
	
	
	/**
	 * 缓存存放对象和对象时间
	 * @param key
	 * @param obj
	 */
	public void putData(String key,Object obj) {
		map.put(key, obj);
		map.put(key+"time", new Date());
		log.info("缓存key[{}]，已更新",key);
	}
	
	
	
	/**
	 * 判断缓存时间是否超时
	 * 不存在也超时
	 * @param key
	 * @param secondTime
	 * @return
	 */
	public boolean isOverTime(String key ,Long secondTime) {
		
		Object object = this.map.get(key+"time");
		if (object == null) {
			log.info("缓存key[{}]，为空",key);
			return true;
		}
		
		Date objectTime = (Date) object;
		Date now = new Date();
		Long interval = (now.getTime() - objectTime.getTime()) / 1000;
		log.info("缓存key[{}]时间相差[{}]",key,interval);
		if(secondTime-interval<0) {
			return true;
		}else {
			return false;
		}
	}
	
	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
}
