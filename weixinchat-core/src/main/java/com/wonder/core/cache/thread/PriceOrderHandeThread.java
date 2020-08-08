package com.wonder.core.cache.thread;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.wonder.core.Utils.Messageemplate;
import com.wonder.core.cache.CacheUtils;
import com.wonder.core.cache.MapCache;

public class PriceOrderHandeThread implements Runnable{
	private final static Logger logger = LoggerFactory.getLogger(PriceOrderHandeThread.class);
	
	private boolean active = true;
	
	private boolean isUse= true;
	
	private int interval=60;
	@Autowired
	private CacheUtils cacheUtils;
	@Autowired
	private MapCache mapCache;
	public PriceOrderHandeThread(){
		logger.info("PriceOrderHandeThread构造方法");
	}
	
	
	public  void init() {
		logger.info("PriceOrderHandeThread初始化");
		if(isUse) {
			this.active=true;
			new Thread(this).start();
			logger.info("闹钟线程已启动");
		}else {
			logger.info("闹钟线程未启动");
		}
	}

	public void destroy() {
		
	}
	@SuppressWarnings("static-access")
	@Override
	public void run() {
		while(active) {
			try {
				logger.info("闹钟轮询");
				Object obj= mapCache.getMap().get(mapCache.CLOCK_LIST);//设置闹钟的列表
				boolean isUpdate=false;
				if(obj!=null) {
					Set<String> set = (Set<String>) obj;
					if(set.size()>0) {
						String openid = "";
						Iterator<String> it = set.iterator();
						while(it.hasNext()){
							openid=(String)it.next();
							logger.info("[{}]是否到价",openid);
							boolean isAchieve=isAGTDAchievePrice(openid);
							if(isAchieve) {
								//已到价从列表移除
								it.remove();
								isUpdate=true;
							}
						}
						if(isUpdate) {
							//更新缓存
							mapCache.getMap().put(mapCache.CLOCK_LIST,set);
							logger.info(JSON.toJSONString(set));
						}
					}else {
						logger.info("闹钟列表无数据");
					}
					
				}else {
					logger.info("闹钟列表为空");
				}
				Thread.sleep(1000L*interval);
			}catch(Exception e) {
				try {
					Thread.sleep(1000L*interval);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				logger.error("无闹钟列表轮询异常[{}]",e);
				
			}
		}
	}
	
	
	public boolean isAGTDAchievePrice(String openid) {
		
		Object buyObj=mapCache.getMap().get(this.mapCache.OPENID_BUY_SETTING+openid);
		Object sellObj=mapCache.getMap().get(this.mapCache.OPENID_SELL_SETTING+openid);
		boolean isOverTime= mapCache.isOverTime(this.mapCache.OPENID_BUY_SETTING+openid, 24*60*60*7L);
		if(isOverTime) {
			//超时就删了
			mapCache.delData(this.mapCache.OPENID_BUY_SETTING+openid);
			mapCache.delData(this.mapCache.OPENID_SELL_SETTING+openid);
			return true;
		}else {
			int buyClock=(int) buyObj;//设置的买多价
			int sellClock=(int)sellObj;//设置的卖空价
			int sell_price=0;//市场卖空价
			int buy_price=0;//市场买多价
			@SuppressWarnings("rawtypes")
			Map map1 = new HashMap();
			try {
				String price = cacheUtils.getGoldMapCatchePrice(MapCache.GOLDAGTDPRICE, 300, 2);
				map1 = JSON.parseObject(price);
				
			} catch (Exception e) {
				logger.error("异常不处理:[{}]",e);
			}
			sell_price = Integer.valueOf((String) map1.get("sell_price"));
			buy_price = Integer.valueOf((String) map1.get("buy_price"));
			if (buyClock == 0 || sellClock == 0 || sell_price == 0 || buy_price == 0) {
				return false;
			}
			
			//sell_price市场卖价跌到小于设置的买多价时
			if (sell_price <= buyClock) {
				logger.info("缓存价格[{}]",map1);
				logger.info("openid[{}],设置买多到价，设置价格[{}]",openid,buyClock);
				try {
					Messageemplate.setMessageToUser(openid, String.valueOf(sell_price), String.valueOf(buy_price), String.valueOf(buyClock), String.valueOf(sellClock),
							(String) this.mapCache.get(this.mapCache.ACCESS_TOKEN),
							"4z_tdbt40avtEg_9egRAVs_wwQy7V1Rf116ErrRe3t8");
					mapCache.delData(this.mapCache.OPENID_BUY_SETTING+openid);
					mapCache.delData(this.mapCache.OPENID_SELL_SETTING+openid);
					return true;
				} catch (Exception e) {
					logger.error("发送模板消息异常:[{}]",e);
				}
			} else if (sellClock <= buy_price) {
				logger.info("缓存价格[{}]",map1);
				logger.info("openid[{}],设置卖空到价，设置价格[{}]",openid,sellClock);
				try {
					Messageemplate.setMessageToUser(openid, String.valueOf(sell_price), String.valueOf(buy_price), String.valueOf(buyClock), String.valueOf(sellClock),
							(String) this.mapCache.get(this.mapCache.ACCESS_TOKEN),
							"diDvk0lbgDoQ9B6XxOtjyiou-IDrkc6YNFQk4PYwH6g");
					mapCache.delData(this.mapCache.OPENID_BUY_SETTING+openid);
					mapCache.delData(this.mapCache.OPENID_SELL_SETTING+openid);
					return true;
				} catch (Exception e) {
					logger.error("发送模板消息异常:[{}]",e);
				}
			}
		}
		
		return false;
	}
	
	
	
	
	
}
