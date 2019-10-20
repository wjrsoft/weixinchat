package com.wonder.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 读取config.properties
 * @author wangjinrong
 */
public class ConfigUtil {
	private final static Logger logger = LoggerFactory.getLogger(ConfigUtil.class);
	//外网IP
	public static String WEBURL;
	//微信appID
	public static String APPID;
	//微信APPSECRET
	public static String APPSECRET;
	//微信签名TOKEN需要跟页面配置一致
	public static String TOKEN;
	
	public static String WXSKURL;
	public static String LOGIN_INTERFACE;
	static{
			 WXSKURL="http://1be4d358.ngrok.io/weixin-web";
			 WEBURL="http://1j6712x825.iask.in//weixinchat-web";
			 APPID="wx0d804932750ad1d1";
			 APPSECRET="26f206c509e1bd8a796f6c3fd132e2d6";
			 TOKEN="tokenSignature";
			 LOGIN_INTERFACE="test";
			 logger.info("weburl[{}]"+WEBURL);
		}
	
}
