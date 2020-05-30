package com.test.demo;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.ParseException;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wonder.po.AccessToken;
import com.wonder.utils.HttpClientUtil;
import com.wonder.utils.WeixinUtil;
/**
 * 模板消息
 * @author jinrong.wang
 *
 */
public class Messageemplate {
	
	String MessageType="https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=ACCESS_TOKEN";//发送消息的模板
	String getMessageType="https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token=ACCESS_TOKEN";//查看消息模板
	String getMessageID="https://api.weixin.qq.com/cgi-bin/template/api_add_template?access_token=ACCESS_TOKEN";//查看模板ID
	String getAllMessageType="https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token=ACCESS_TOKEN";
	String deleteMessageTemplate="https://api.weixin.qq.com/cgi-bin/template/del_private_template?access_token=ACCESS_TOKEN";//删除消息模板
	String sendMessage="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
	Map<String,String> map=new HashMap<String,String>();
	JSONObject jsonObject;
	
	
//	
	@Test
	/**
	 * 消息模板，使用消息模板可以给用户发上万条消息的消息通知,要求使用post请求
	 */
	public void addMessageTest() throws ParseException, IOException {
		map.put("industry_id1", "1");
		map.put("industry_id2", "2");
		String content=JSON.toJSONString(map);
		System.out.println(content);
		String url = MessageType.replace("ACCESS_TOKEN", WeixinUtil.getAccessToken().getToken());
		jsonObject =HttpClientUtil.doPostStr(url, content);//Token有效时间是两个小时，所以在生产上需要合理的定时任务刷新Token
		System.out.println(JSON.toJSONString(jsonObject));
	}
//	
//	
//	@Test
//	public  void deleteMessageById() throws Exception{
//		map.put("template_id","DdDVcHZLiPB-NC3QOx97LCAC6_jc6G_jDOHa9Gf7q2o");
//		String content=JSON.toJSONString(map);
//		System.out.println(content);
//		String url = deleteMessageTemplate.replace("ACCESS_TOKEN", IoUtil.getToken());
//		jsonObject =HttpClientUtil.doPostStr(url, content);//Token有效时间是两个小时，所以在生产上需要合理的定时任务刷新Token
//		System.out.println(jsonObject.fromObject(jsonObject).toString());
//	}
//	
	@Test
	/**
	 * 获取设置的行业信息
	 * @throws Exception
	 */
	public void getMessageTemplate() throws Exception{
		String url = getMessageType.replace("ACCESS_TOKEN", WeixinUtil.getAccessToken().getToken());
		jsonObject=HttpClientUtil.doGetStr(url);
		System.out.println(jsonObject.toString());
	}
//	/**
//	 * 获取模板ID
//	 * @throws Exception
//	 */
	@Test
	public void getMessageID() throws Exception{
		map.put("template_id_short", "vxEPuFOnZCE42lmEqJ0wvTp0gskzfO1soR54OGvXmFk");
		String content=JSON.toJSONString(map);
		String url = getMessageID.replace("ACCESS_TOKEN", WeixinUtil.getAccessToken().getToken());
		jsonObject=HttpClientUtil.doPostStr(url, content);
		System.out.println(jsonObject.toString());
	}
//	
//	/**
//	 * 获取所有消息模板列表 get请求
//	 * @throws Exception 
//	 */
	@Test
	public void getAllMessageType() throws Exception{
//		String URL=getMessageType.replace("ACCESS_TOKEN", IoUtil.getToken());
		String URL=getMessageType.replace("ACCESS_TOKEN", WeixinUtil.getAccessToken().getToken());
		jsonObject=HttpClientUtil.doGetStr(URL);
		System.out.println(jsonObject.toString());
	}
//	
	@Test
	public void setMessageToUser() throws Exception{
		Map<String,Object> map=new HashMap<String,Object>();
		//二级Json
		Map<String,Object> map2=new HashMap<String,Object>();
		//三级Json
		Map<String,String> map3=new HashMap<String,String>();
		Map<String,String> map4=new HashMap<String,String>();
		Map<String,String> map5=new HashMap<String,String>();
		Map<String,String> map6=new HashMap<String,String>();
		map3.put("color", "#173177");
		map3.put("value", "123");
		
		map4.put("value", "245");
		map4.put("color", "#173177");
		
		map5.put("value", "123423");
		map5.put("color", "#173177");
		
		map6.put("value", "12342");
		map6.put("color", "#173177");
		//map2  data
		map2.put("sell_price", map3);
		map2.put("buy_price", map4);
		map2.put("buy", map5);
		map2.put("sell", map6);
		map.put("touser", "o0faKw0cZaQw78n2lY5xBEkdfQ8I");
		map.put("data", map2);//数据
		map.put("template_id", "diDvk0lbgDoQ9B6XxOtjyiou-IDrkc6YNFQk4PYwH6g");
		System.out.println("map2:"+JSON.toJSONString(map2));
		AccessToken token = WeixinUtil.getAccessToken();
		System.out.println("票据"+token.getToken());
		String url=sendMessage.replace("ACCESS_TOKEN",token.getToken());
		Object json= HttpClientUtil.doPostStr(url, JSON.toJSONString(map));
		System.out.println(JSON.toJSONString(json));
	}
	
	
	
	
	
	       
	@Test
	public void setAccesstoken() {
		
	}
}
