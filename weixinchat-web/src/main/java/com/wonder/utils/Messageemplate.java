//package com.wonder.utils;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.junit.Test;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.wonder.po.AccessToken;
///**
// * 模板消息
// * @author jinrong.wang
// *
// */
//public class Messageemplate {
//	
//	String MessageType="https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=ACCESS_TOKEN";//发送消息的模板
//	String getMessageType="https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token=ACCESS_TOKEN";//查看消息模板
//	String getMessageID="https://api.weixin.qq.com/cgi-bin/template/api_add_template?access_token=ACCESS_TOKEN";//查看模板ID
//	String getAllMessageType="https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token=ACCESS_TOKEN";
//	String deleteMessageTemplate="https://api.weixin.qq.com/cgi-bin/template/del_private_template?access_token=ACCESS_TOKEN";//删除消息模板
//	String sendMessage="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
//	Map<String,String> map=new HashMap<String,String>();
//	JSONObject jsonObject;
//	
//	
////	
////	@Test
////	/**
////	 * 消息模板，使用消息模板可以给用户发上万条消息的消息通知,要求使用post请求
////	 */
////	public void addMessageTest() throws ParseException, IOException, Exception{
////		map.put("industry_id1", "1");
////		map.put("industry_id2", "2");
////		String content=JSON.toJSONString(map);
////		System.out.println(content);
////		String url = MessageType.replace("ACCESS_TOKEN", IoUtil.getToken());
////		jsonObject =HttpClientUtil.doPostStr(url, content);//Token有效时间是两个小时，所以在生产上需要合理的定时任务刷新Token
////		System.out.println(jsonObject.fromObject(jsonObject).toString());
////	}
////	
////	
////	@Test
////	public  void deleteMessageById() throws Exception{
////		map.put("template_id","DdDVcHZLiPB-NC3QOx97LCAC6_jc6G_jDOHa9Gf7q2o");
////		String content=JSON.toJSONString(map);
////		System.out.println(content);
////		String url = deleteMessageTemplate.replace("ACCESS_TOKEN", IoUtil.getToken());
////		jsonObject =HttpClientUtil.doPostStr(url, content);//Token有效时间是两个小时，所以在生产上需要合理的定时任务刷新Token
////		System.out.println(jsonObject.fromObject(jsonObject).toString());
////	}
////	
////	@Test
////	/**
////	 * 获取设置的行业信息
////	 * @throws Exception
////	 */
////	public void getMessageTemplate() throws Exception{
////		String url = getMessageType.replace("ACCESS_TOKEN", IoUtil.getToken());
////		jsonObject=HttpClientUtil.doGetStr(url);
////		System.out.println(jsonObject.toString());
////	}
////	/**
////	 * 获取模板ID
////	 * @throws Exception
////	 */
////	@Test
////	public void getMessageID() throws Exception{
////		map.put("template_id_short", "TM00001");
////		String content=JSON.toJSONString(map);
////		String url = getMessageID.replace("ACCESS_TOKEN", IoUtil.getToken());
////		jsonObject=HttpClientUtil.doPostStr(url, content);
////		System.out.println(jsonObject.toString());
////	}
////	
////	/**
////	 * 获取所有消息模板列表 get请求
////	 * @throws Exception 
////	 */
//	@Test
//	public void getAllMessageType() throws Exception{
////		String URL=getMessageType.replace("ACCESS_TOKEN", IoUtil.getToken());
//		String URL=getMessageType.replace("ACCESS_TOKEN", "33_P1vJhIkKBmU7KXqu5DSXFTzBhZ_oUTnOtVShuj7RN3c1QiWG-4W8wxwDmKU01LILxkUhekpLI6-Gu96zA4l4Gyx0AeYGpw5B5wSgozHKQ-PH3FMySfEhS9xu3QxORJbUh79NFVONILeO7HFjR");
//		jsonObject=HttpClientUtil.doGetStr(URL);
//		System.out.println(jsonObject.toString());
//	}
////	
////	@Test
//	public void setMessageToUser() throws Exception{
//		Map<String,Object> map=new HashMap<String,Object>();
//		//二级Json
//		Map<String,Object> map2=new HashMap<String,Object>();
//		//三级Json
//		Map<String,String> map3=new HashMap<String,String>();
//		Map<String,String> map4=new HashMap<String,String>();
//		Map<String,String> map5=new HashMap<String,String>();
//		Map<String,String> map6=new HashMap<String,String>();
//		
//		Map<String,String> map7=new HashMap<String,String>();
//		map3.put("color", "#173177");
//		map3.put("value", "恭喜你购买成功");
//		
//		map4.put("value", "中国帅小伙");
//		map4.put("color", "#173177");
//		map5.put("value", "100");
//		map5.put("color", "#173177");
//		map6.put("value", "2018-03-18");
//		map6.put("color", "#173177");
//		map7.put("value", "欢迎你下次购买");
//		map7.put("color", "#173177");
//		map2.put("first", map3);
//		map2.put("keyword1", map4);
//		map2.put("keyword2", map5);
//		map2.put("keyword3", map6);
//		map2.put("remark", map7);
//		map.put("touser", "o0faKw0cZaQw78n2lY5xBEkdfQ8I");
//		map.put("data", map2);//数据
//		map.put("template_id", "u-tDAQRAKuPRAqjQ-zPGf3gm-l2j_vj_YtKifNjYJE0");
//		System.out.println("map2:"+JSON.toJSONString(map2));
//		//sendMessage=https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN
//		AccessToken token = WeixinUtil.getAccessToken();
//		System.out.println("票据"+token.getToken());
//		String url=sendMessage.replace("ACCESS_TOKEN",token.getToken());
//		 HttpClientUtil.doPostStr(url, JSON.toJSONString(map));
////		System.out.println(JSON.toJSONString(map));
////		System.out.println(jsonObject.toString());
//	}
//	
//	
//	
//	
//	
//	
//	public void test(){
//	}
//}
