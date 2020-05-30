package com.wonder.core.cache.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.wonder.core.cache.CacheUtils;
import com.wonder.core.cache.MapCache;

public class PriceCacheThread implements Runnable{
	private final static Logger logger = LoggerFactory.getLogger(PriceCacheThread.class);
	
	private boolean active = true;
	
	private boolean isUse= true;
	
	private int interval=600;
	@Autowired
	private CacheUtils cacheUtils;
	
	public PriceCacheThread(){
		logger.info("PriceCacheThread构造方法");
	}
	
	
	public  void init() {
		logger.info("PriceCacheThread初始化");
		if(isUse) {
			this.active=true;
			new Thread(this).start();
			logger.info("牌价线程已启动");
		}else {
			logger.info("牌价线程未启动");
		}
	}

	public void destroy() {
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(active) {
			try {
				logger.info("刷新牌价");
				//刷新白银缓存价格
				cacheUtils.getGoldMapCatchePrice(MapCache.GOLDAGTDPRICE, MapCache.GOLDAGTDPRICE_CATCHE_TIME,2);
				Thread.sleep(1000L*interval);
			}catch(Exception e) {
				logger.error("刷新牌价异常[{}]",e);
				try {
					logger.info("刷新牌价");
					Thread.sleep(10000L*interval);
				}catch(Exception e1) {
					logger.error("刷新牌价异常[{}]",e1);
					
				}
			}
		}
	}
	
	
}
