package com.wonder.controller.login;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.wonder.core.dao.WxUserDAO;
import com.wonder.core.schema.WxUser;

/**
 * 微信授权页
 * 引导用户进入授权连接，可以通过菜单等形式让用户访问。
 * @author jinrong.wang
 *
 */
@Controller
public class LoginController {
	private static Logger log = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	WxUserDAO wxUserDAO;
	
	/**
	 * 根据openid判断用户是否已注册
	 * 已注册跳转登录页，未注册跳转注册页
	 * @param openid
	 * @return
	 */
	@RequestMapping("/login.do")
	public ModelAndView login(@RequestParam(value = "openid") String openid){
		log.info("openid[{}]",openid);
		log.info("进入login");
		//免注册
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("openid",openid);
//		mav.getModel().put("openid", openid);
//		mav.setViewName("login");
//		return mav;

		//是否跳转注册页
		WxUser wxUser = wxUserDAO.selectByOpenid(openid);

		//如果用户存在跳转登录页登录
		if(wxUser!=null) {
			ModelAndView mav = new ModelAndView();
			mav.addObject("openid",openid);
			mav.getModel().put("openid", openid);
			mav.setViewName("login");
			return mav;
		}else {
			//如果用户不存在,跳转注册页
			ModelAndView mav = new ModelAndView();
			mav.addObject("openid",openid);
			mav.getModel().put("openid", openid);
			mav.setViewName("reg");
			log.info("用户不存在,跳转注册页");
			return mav;
		}
		
	}
	@RequestMapping(value="/reg.do")
	public ModelAndView regAction(){
		log.info("进入reg");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("reg");
		return mav;
	}
	
	
	@RequestMapping(value = "/loginOut.do")
	public ModelAndView loginOut(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		log.info("进入loginOut");
		req.getSession().removeAttribute("openid");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		return mav;

	}
	
	
	@RequestMapping(value="/toLogin.do")
	@ResponseBody
	public String tologin(HttpServletRequest req,
						  HttpServletResponse resp,
					@RequestParam(value = "password") String password,
					@RequestParam(value = "account") String account) throws Exception{
		req.setCharacterEncoding("utf-8");  
		resp.setContentType("text/html;charset=utf-8"); 
		log.info("进入toLogin"); 
		log.info("account:"+account); 
		log.info("password:"+password);
		Map<String,String> map = new HashMap<String,String>();
		map.put("result","登录成功");
		map.put("status","1");
		req.getSession().setAttribute("openid", account);//记录session
		req.getSession().setMaxInactiveInterval(300);
//		try{
//			WxUser wxUser = wxUserDAO.selectByOpenid(account);
//			if(wxUser!=null
////					&&password.equals(wxUser.getPwd())
//					) {
//				log.info("用户[{}]登录成功",wxUser.getOpenid());
//				map.put("result","登录成功");
//				map.put("status","1");
//				req.getSession().setAttribute("openid", account);//记录session
//				req.getSession().setMaxInactiveInterval(300);
////				req.getSession().setAttribute("refresh_token", refresh_token);
//			}else {
//				map.put("result","密码错误");
//				map.put("status","2");
//				log.info("用户[{}]密码错误",wxUser.getOpenid());
//			}
			
			
//		}catch(Exception e){
//			e.printStackTrace();
//
//		}
		return  JSON.toJSONString(map);
	}
	
	
	
	@RequestMapping("/toReg.do")
	@ResponseBody
	public String testabc(HttpServletRequest req,
						  HttpServletResponse resp,
					@RequestParam(value = "password") String password,
					@RequestParam(value = "name") String name) throws Exception{
		System.out.println("进入toReg");
		req.setCharacterEncoding("utf-8");  
		resp.setContentType("text/html;charset=utf-8"); 
		log.info("进入toReg"); 
		log.info("name:"+name); 
		log.info("password:"+password);
		Map<String,String> map = new HashMap<String,String>();
		try{
			WxUser wxUser = wxUserDAO.selectByOpenid(name);
			
			//如果用户存在跳转登录页登录
			if(wxUser==null) {
				wxUser = new WxUser();
				wxUser.setOpenid(name);
				wxUser.setPwd(password);
				wxUserDAO.insertByWxUser(wxUser);
				map.put("result","注册成功");
				map.put("status","1");
				log.info("注册成功"); 
			}else {
				map.put("result","你已注册过，无需重复注册");
				map.put("status","2");
				log.info("你已注册过，无需重复注册"); 
			}
		}catch(Exception e){
			log.error("error[{}]",e);
			
		}
		return  JSON.toJSONString(map);
	}
	
	
	
	public WxUserDAO getWxUserDAO() {
		return wxUserDAO;
	}
	public void setWxUserDAO(WxUserDAO wxUserDAO) {
		this.wxUserDAO = wxUserDAO;
	}
	
	
	
}
