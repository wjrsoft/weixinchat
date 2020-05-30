package com.wonder.utils;

import com.wonder.po.Preview;

import net.sf.json.JSONObject;

/**
 * 预览接口
 * 开发者可通过该接口发送消息给指定用户，在手机端查看消息的样式和排版
 * @author jinrong.wang
 *
 */
public class sendUserInfo {
    public Preview preview;
    public static void main(String[] args) throws Exception {
    	Preview preview=new Preview();
    	preview.setMsgtype("text");
    	preview.setText(" hi : what are you dong now");
    	preview.setTouser("o0faKw0cZaQw78n2lY5xBEkdfQ8I");
    	String token=WeixinUtil.getAccessToken().getToken();//获取存储的token，有效期为两个小时
    	String context=JSONObject.fromObject(preview).toString();
    	System.out.println(context);
    	int result=WeixinUtil.sendMsgToUser(token, context);
    	System.out.println(result);
    }
}
