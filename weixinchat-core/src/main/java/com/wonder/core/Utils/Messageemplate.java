package com.wonder.core.Utils;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
/**
 * 模板消息
 * @author jinrong.wang
 *
 */
public class Messageemplate {
	
	static String  MessageType="https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=ACCESS_TOKEN";//发送消息的模板
	static String getMessageType="https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token=ACCESS_TOKEN";//查看消息模板
	static String getMessageID="https://api.weixin.qq.com/cgi-bin/template/api_add_template?access_token=ACCESS_TOKEN";//查看模板ID
	static String getAllMessageType="https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token=ACCESS_TOKEN";
	static String deleteMessageTemplate="https://api.weixin.qq.com/cgi-bin/template/del_private_template?access_token=ACCESS_TOKEN";//删除消息模板
	static String sendMessage="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
	JSONObject jsonObject;
	
//	
	public static void setMessageToUser(String openid,String sell_price,String buy_price,String buy,String sell,String token,String templateid) throws Exception{
		Map<String,Object> map=new HashMap<String,Object>();
		//二级Json
		Map<String,Object> map2=new HashMap<String,Object>();
		//三级Json
		Map<String,String> map3=new HashMap<String,String>();
		Map<String,String> map4=new HashMap<String,String>();
		Map<String,String> map5=new HashMap<String,String>();
		Map<String,String> map6=new HashMap<String,String>();
		//模板值
		map3.put("color", "#173177");
		map3.put("value", sell_price);
		
		map4.put("value", buy_price);
		map4.put("color", "#173177");
		
		map5.put("value", buy);
		map5.put("color", "#173177");
		
		map6.put("value", sell);
		map6.put("color", "#173177");
		//map2  data
		map2.put("sell_price", map3);
		map2.put("buy_price", map4);
		map2.put("buy", map5);
		map2.put("sell", map6);
		map.put("touser", openid);
		map.put("data", map2);//数据
		map.put("template_id", templateid);
		System.out.println("map2:"+JSON.toJSONString(map2));
		System.out.println("票据"+token);
		String url=sendMessage.replace("ACCESS_TOKEN",token);
		Object json= HttpClientUtil.doPostStr(url, JSON.toJSONString(map));
		System.out.println(JSON.toJSONString(json));
	}
}
