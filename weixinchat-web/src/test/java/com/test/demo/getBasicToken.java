package com.test.demo;

import com.wonder.po.AccessToken;
import com.wonder.utils.IoUtil;
import com.wonder.utils.WeixinUtil;


public class getBasicToken {
	public static void main(String[] args) throws  Exception {
		//获取票据时间
		AccessToken token = WeixinUtil.getAccessToken();
		System.out.println("票据:"+token.getToken());
		System.out.println("有效时间:"+token.getExpiresIn());
		System.out.println(token.getToken());
		//将token存起来先，要是数据库就存库里
		IoUtil io=new IoUtil();
		io.setToken(token.getToken());
	}
}
