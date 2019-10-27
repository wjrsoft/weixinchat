package com.test;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import com.alibaba.fastjson.JSONObject;

public class SendPost {
	public static String post(String url, List<NameValuePair> params) throws UnsupportedEncodingException {
		 // 创建一个httpclient链接对象
		  HttpClient httpclient = new DefaultHttpClient();
		  try {
		   // 创建一个post对象
		   HttpPost httppost = new HttpPost(url);    
		   // 发送http请求参数
		   httppost.setEntity(new UrlEncodedFormEntity(params));
		   // 创建响应处理器处理服务器响应内容
		   ResponseHandler<String> responseHandler = new BasicResponseHandler();
		   // 执行请求并获取结果
		   String responseBody = null;
		   try {
		    responseBody = httpclient.execute(httppost, responseHandler);
		   } catch (ClientProtocolException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		   } catch (IOException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		   }
		   if (responseBody != null) {
		    return responseBody;
		   }
		  } catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
		   // 当不再需要HttpClient实例时,关闭连接管理器以确保释放所有占用的系统资源
		   httpclient.getConnectionManager().shutdown();
		  }
		  return "";
	}
	public static String get(String url) throws ClientProtocolException, IOException {
		 // 创建一个httpclient链接对象
		   HttpClient httpclient = new DefaultHttpClient();
		   HttpGet httpGet = new HttpGet(url);    
		   ResponseHandler<String> responseHandler = new BasicResponseHandler();
		   // 执行请求并获取结果
		   String responseBody = null;
		    responseBody = httpclient.execute(httpGet, responseHandler);
		    return responseBody;
	}
	
	public static void main(String[] args) throws ClientProtocolException, IOException {
		String str="https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx0d804932750ad1d1&secret=26f206c509e1bd8a796f6c3fd132e2d6&code=061tMWTu1QtThc0ZljXu1T3fUu1tMWTl&grant_type=authorization_code";
		String result =get(str);
		JSONObject test=new JSONObject();
		test=test.parseObject(result);
		System.out.println(result); 
		System.out.println(test.get("errcode"));
		
	}
}
