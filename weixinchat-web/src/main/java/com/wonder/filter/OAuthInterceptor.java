package com.wonder.filter;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.wonder.core.dao.WxUserDAO;
import com.wonder.core.schema.WxUser;
import com.wonder.utils.ConfigUtil;
import com.wonder.utils.OAuth;
import com.wonder.utils.WebSession;
/**
 * 对所有请求进行拦截
 *
 */
public class OAuthInterceptor implements HandlerInterceptor  {
	@Autowired
	WxUserDAO wxUserDAO;
	
	private final static Logger log = LoggerFactory.getLogger(OAuthInterceptor.class);
	
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		//log.info("进入afterCompletion");
		
	}
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		//log.info("进入postHandle");
		
	}
	
	/**
	 * 1、判断session中是否有openid，如果有则放行，如果没有则继续
	 * 2、如果请求中带有code，则获取openid
	 * 		2.1、检查openid是否已注册，如未注册跳注册页，注册页设置用户名，登录名为openid为自动填写，注册成功后，关闭页面
	 * 		2.2、检查openid已注册，跳转登录页，输入密码登录，将openID记录session
	 * 3、如果session没有openID，且请求中未带code，则将uri重定向到授权页
	 * 
	 */
	
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object obj) throws Exception {
		String uri = req.getServletPath();
		log.info("进入preHandle,请求地址" + uri);
		if (uri.contains("getCNYPrice.do") || uri.contains("getMarginPrice.do") || uri.contains("getGoldAGTDPrice.do")
				|| uri.contains("getGoldAUTDPrice.do") || uri.contains("marginPriceWeb.do")
				|| uri.contains("GoldAGTDWeb.do") || uri.contains("GoldAUTDWeb.do")
				||uri.contains("loginOut.do")
//				||uri.contains("setAgtdPriceWeb.do")
				) {
			return true;
		}
		// 1、session存在openid放行
		if (WebSession.isSessionEffective(req)) {
			return true;
		}
		String code = req.getParameter("code");
		if (code != null) {
			log.info("proHandle中的code值:[{}]", code);
			String openid = OAuth.getOpenId(code);
			resp.sendRedirect(ConfigUtil.WEBURL+"/login.do"+"?openid="+openid);//跳转到登录页
		}else {
			String url=OAuth.getSnsapi_baseUrl(ConfigUtil.WEBURL+uri);
			log.info(url);
			resp.sendRedirect(url);
			
		}
		
		return false;
	}
	
}
