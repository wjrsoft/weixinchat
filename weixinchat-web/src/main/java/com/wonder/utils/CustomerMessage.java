package com.wonder.utils;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


/**
 * 客服消息，必须关注着先发起消息，客服才能回复
 * @author yp-tc-m-7157
 *
 */
public class CustomerMessage {
	//增加客服账号
	String addCustomerAccount="https://api.weixin.qq.com/customservice/kfaccount/add?access_token=ACCESS_TOKEN";
	//修改客服人员信息
	String updateCustomerAccount="https://api.weixin.qq.com/customservice/kfaccount/update?access_token=ACCESS_TOKEN";
	//删除客服人员信息
	String deleteCustomerAccount="https://api.weixin.qq.com/customservice/kfaccount/del?access_token=ACCESS_TOKEN";
	//设置客服人员头像
	String setHeadImage="http://api.weixin.qq.com/customservice/kfaccount/uploadheadimg?access_token=ACCESS_TOKEN&kf_account=KFACCOUNT";
	//获取所有客服信息
	String getAllAccountInfo="https://api.weixin.qq.com/cgi-bin/customservice/getkflist?access_token=ACCESS_TOKEN";
	//客服消息发送
	String sendMessage="https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
	
	
	//@Test  
	public void addCustomerAccount() throws Exception{
		Map<String,String> map=new HashMap<String,String>();
		map.put("kf_account", "test2@test2");
		map.put("nickname", "客服2");
		map.put("password", "pswmd5");
		String json=JSON.toJSONString(map);
		System.out.println(json);
		String url=addCustomerAccount.replace("ACCESS_TOKEN", WeixinUtil.getAccessToken().getToken());//Access_Token消息两小时过时，需要定时器自动获取，或者数据库存储
		JSONObject js=HttpClientUtil.doPostStr(url, json);
		System.out.println(JSON.toJSONString(js));
	}
}
