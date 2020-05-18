package com.wonder.controller.price;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PriceController {
	private static Logger log = LoggerFactory.getLogger(PriceController.class);
	@RequestMapping("/marginPriceWeb.do")//菜单配置
	public ModelAndView marginPriceWeb(){
		log.info("进入marginPriceWeb");
		//如果用户存在跳转登录页登录
		ModelAndView mav = new ModelAndView();
		mav.setViewName("marginPrice");
		return mav;
		
	}
	
	
	@RequestMapping("/marginCNYWeb.do")//菜单配置
	public ModelAndView cNYPriceWeb(){
		log.info("进入login");
		//如果用户存在跳转登录页登录
		ModelAndView mav = new ModelAndView();
		mav.setViewName("CNYPrice");
		return mav;
		
	}
	
	@RequestMapping("/GoldAGTDWeb.do")//菜单配置
	public ModelAndView goldAGTDWeb(){
		log.info("进入login");
		//如果用户存在跳转登录页登录
		ModelAndView mav = new ModelAndView();
		mav.setViewName("AGTDPrice");
		return mav;
		
	}
	@RequestMapping("/GoldAUTDWeb.do")//菜单配置
	public ModelAndView GoldAUTDWeb(){
		log.info("进入login");
		//如果用户存在跳转登录页登录
		ModelAndView mav = new ModelAndView();
		mav.setViewName("AUTDPrice");
		return mav;
		
	}
	
}
