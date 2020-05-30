package com.wonder.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.wonder.core.cache.MapCache;
import com.wonder.utils.WeixinUtil;

public class TokenCacheThread implements Runnable{
	private final static Logger logger = LoggerFactory.getLogger(TokenCacheThread.class);
	
	private boolean active = true;
	
	private boolean isUse= true;
	
	private int interval=600;
	@Autowired
	private MapCache mapCache;
	public TokenCacheThread(){
		logger.info("TokenCacheThread构造方法");
	}
	
	
	public  void init() {
		logger.info("TokenCacheThread初始化");
		if(isUse) {
			this.active=true;
			new Thread(this).start();
			logger.info("token线程已启动");
		}else {
			logger.info("token线程未启动");
		}
	}

	public void destroy() {
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(active) {
			try {
				setTokenCache();//设置token
				Thread.sleep(1000L*interval);
			}catch(Exception e) {
				logger.error("刷新牌价异常[{}]",e);
				try {
					setTokenCache();//设置token
					Thread.sleep(10000L*interval);
					
				}catch(Exception e1) {
					logger.error("刷新牌价异常[{}]",e1);
					
				}
			}
		}
	}
	
	@SuppressWarnings("static-access")
	public void setTokenCache()  {
		
		Boolean isOverTime = this.mapCache.isOverTime(this.mapCache.ACCESS_TOKEN, new Long(mapCache.ACCESS_TOKEN_TIME));
		if(isOverTime) {
			try {
				String token = String.valueOf(WeixinUtil.getAccessToken().getToken());
				logger.info("票据[{}]"+token);
				this.mapCache.putData(mapCache.ACCESS_TOKEN, token);
			}catch(Exception e){
				logger.error("票据[{}]"+e);
			}
			
		}else {
			logger.info("缓存票据[{}]",this.mapCache.get(mapCache.ACCESS_TOKEN));
		}
		
		
	}
}
