package com.wonder.utils;

import java.util.Properties;

import org.apache.log4j.Logger;
/**
 * 读取config.properties
 * @author yp-tc-m-7157
 *
 */
public class ConfigUtil {
	private static Logger log = Logger.getLogger(ConfigUtil.class);
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
			 WXSKURL="http://1be4d358.ngrok.io/tel-weixin-web";
			 WEBURL="http://1be4d358.ngrok.io/tel-weixin-web";
			 APPID="wx89704883e840ad12";
			 APPSECRET="164b46657ce328e80fd2db4484748f1a";
			 TOKEN="tokenSignature";
			 LOGIN_INTERFACE="https://qa.yeepay.com/selfservice/customerLoginInterface.action";
			 //统一配置
			 //WEBURL="http://e235da5a.ngrok.io/tel-app-web";
//			 APPID=ConfigConst.WeixinAppID.toString();
//			 APPSECRET=ConfigConst.WeixinAppsecret.toString();
//			 TOKEN=ConfigConst.WeixinSignatureToken.toString();
//			 log.info("外网IP:"+WEBURL);
			 
		}
}
