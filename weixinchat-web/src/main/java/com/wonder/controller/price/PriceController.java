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
		log.info("进入login");
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
	
}
