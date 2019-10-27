package com.wonder.utils;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class OAuth {
	
	private static Logger log = Logger.getLogger(OAuth.class);
	/**
	 * 引导授权页
	 */
	public  static String snsapi_base="https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
	
	public static String snsapi_userinfo="https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
	
	
	public static String getUserinfo="https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	/**
	 * 使用accessToken获取微信openid信息
	 */
	public static String accessToken=" https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	
	
	public static String refreshToken ="https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
	/**
	 * 静默授权
	 * 拼接一个请求地址，并且将域名和请求路劲进行URLEncoder编码
	 * @param weburl
	 * @return
	 */
	public static String getSnsapi_Userinfo(String weburl) {
		String  url=snsapi_userinfo.replaceFirst("APPID", ConfigUtil.APPID);
		try{
			String  encodeWeburl = java.net.URLEncoder.encode(weburl!=null?weburl:"" ,"utf-8" );
			url=url.replace("REDIRECT_URI", encodeWeburl);
		}catch(Exception e){
			e.printStackTrace();
		}
		return url;
		
	}
	
	
	/**
	 * 用户授权
	 * @param action
	 * @return
	 */
	public static String getSnsapi_baseUrl(String weburl) {
		String  url=snsapi_base.replaceFirst("APPID", ConfigUtil.APPID);
		try{
			String  encodeWeburl = java.net.URLEncoder.encode(weburl!=null?weburl:"" ,"utf-8" );
			url=url.replace("REDIRECT_URI", encodeWeburl);
		}catch(Exception e){
			e.printStackTrace();
		}
		return url;
		
	}
	
	
	
	
	
	public static String getRedirectUrl(String action){
		action=ConfigUtil.WEBURL+action;
		return "redirect:"+getSnsapi_baseUrl(action);
	}
	
	/**
	 * 获取Token,openid信息
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public static JSONObject getAccessToken(String code) throws Exception {
		String url = accessToken.replace("APPID", ConfigUtil.APPID);
		url = url.replace("SECRET", ConfigUtil.APPSECRET);
		url = url.replace("CODE", code);
		JSONObject json=HttpClientUtil.doGet(url);
		return json;
	}
	/**
	 * 拉取用户信息，前提是必须用snsapi_userinfo获取到的code
	 * @param code
	 * @return
	 */
	public static JSONObject getUserInfo(String code){
		//获取用户的openid,accesstoken
		JSONObject json=null;
		String url =null;
		try{
			log.info("拉取用户基本openid");
			url = accessToken.replace("APPID", ConfigUtil.APPID);
			url = url.replace("SECRET", ConfigUtil.APPSECRET);
			url = url.replace("CODE", code);
			 json=HttpClientUtil.doGet(url);
		}catch(Exception e){
			e.printStackTrace();
		}
		try{
			//获取用户的
			log.info("拉取用户信息");
			String accessToken=json.getString("access_token");
			String openid=json.getString("openid");
			url=getUserinfo.replace("ACCESS_TOKEN", accessToken);
			url=url.replace("OPENID", openid);
			 json=HttpClientUtil.doGetStr(url);
			 log.info(JSON.toJSONString(json));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return json;
	}
	/**
	 * 使用refresh_token 这样就可以不要重复通过微信授权页获取openid,而是服务器直接取
	 * 获取Token,openid信息
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public static JSONObject getRefreshToken(String refresh_token) throws Exception {
		String url = refreshToken.replace("APPID", ConfigUtil.APPID);
		url = url.replace("refresh_token", refresh_token);
		JSONObject json=HttpClientUtil.doGet(url);
		return json;
	}
	
	
}
