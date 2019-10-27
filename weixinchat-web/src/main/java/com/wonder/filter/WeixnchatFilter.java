package com.wonder.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class WeixnchatFilter  implements Filter{
	private final static Logger logger = LoggerFactory.getLogger(WeixnchatFilter.class);
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("----Filter初始化----");
		//System.out.println("----Filter初始化----"); 
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 对request、response进行一些预处理  
        request.setCharacterEncoding("UTF-8");  
        response.setCharacterEncoding("UTF-8");  
        response.setContentType("text/html;charset=UTF-8");  
        logger.info("----调用service之前执行一段代码----");  
        chain.doFilter(request, response); // 执行目标资源，放行  
        logger.info("----调用service之后执行一段代码----");  
		
	}

	public void destroy() {
		//System.out.println("----Filter销毁----"); 
		
	}

	
	
	
}
