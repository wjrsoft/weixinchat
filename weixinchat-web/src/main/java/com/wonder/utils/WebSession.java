package com.wonder.utils;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

/**
 * 登录验证，demo搭建临时使用，后期会使用shiro进行登录验证
 * @author wangjinrong
 * @date  20180519
 */
public class WebSession {
	private final static Logger log = LoggerFactory.getLogger(WebSession.class);
	/**
	 * 判断当前用户是否登录
	 * @param req
	 * @param session
	 * @return boolean
	 */
	public static boolean  isSessionEffective(HttpServletRequest req){
		String openid=(String) req.getSession().getAttribute("openid");
		if(openid!=null) {
			log.info("session中有[{}]的会话信息",openid);
		}else {
			log.info("session中无[{}]的会话信息",openid);
		}
		return  openid!=null?true:false;
	}
	
	/**
	 * 登录超时或未登录
	 */
	public static void saveSession(HttpServletRequest req,String openid,String refresh_token){
		req.getSession().setAttribute("openid", openid);
		req.getSession().setAttribute("refresh_token", refresh_token);
	}
	
	/**
	 * 如果code不为空，则获取openid并存入session
	 * @param req
	 * @param code
	 * @throws Exception
	 */
	public static void setSessionBycode(HttpServletRequest req,String code) throws Exception{
		if(code!=null){
			log.info("授权页返回code，将openid信息放到session中");
			JSONObject json=OAuth.getUserInfo(code);
			WebSession.saveSession(req, json.getString("openid"), json.getString("refresh_token"));
		}
	}
	
	
	
	/**
	 * 如果session中没有openid信息，则获取网页授权页重定向地址
	 * @param req
	 * @return
	 */
	public static String getRedirectUrl(HttpServletRequest req){
		//需要知道是那个微信客户端请求的吧，超时了需要获取openid
		String action=req.getServletPath();
		String url="login";
		if(!WebSession.isSessionEffective(req)){
			url= OAuth.getRedirectUrl(action);
			log.info("session超时或失效，将重定向获取openid");
			return url;
		}
		return null;
	}
	
	/**
	 * @throws Exception 
	 * 
	 */
	public static String  setSession(HttpServletRequest req,String code,String action) throws Exception{
		String url="login";
		if(code!=null){
			log.info("将openid放到session");
			JSONObject json=OAuth.getAccessToken(code);
			WebSession.saveSession(req, json.getString("openid"), json.getString("refresh_token"));
		}
		//需要知道是那个微信客户端请求的吧，超时了需要获取openid
		if(!WebSession.isSessionEffective(req)){
			url= OAuth.getRedirectUrl(action);
			log.info("session超时或失效，将重定向获取openid");
			return url;
		}
		return null;
	}
	
}
