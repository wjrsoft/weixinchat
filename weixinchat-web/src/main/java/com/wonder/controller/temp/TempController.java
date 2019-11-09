package com.wonder.controller.temp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TempController {
	private static Logger log = LoggerFactory.getLogger(TempController.class);
	@RequestMapping(value="/temp.do")
	public ModelAndView regAction(){
		log.info("进入reg");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("temp");
		return mav;
	}
	
}
