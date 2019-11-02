package com.wonder.menu;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wonder.utils.ConfigUtil;

public class CreateMenu2 {
	//获取新的Token
    private static   String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
  //创建菜单
  	private static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
  	//授权页地址
	public static String snsapi_userinfo="https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
	private static String APPID="wx0d804932750ad1d1";
    private static String APPSECRET="26f206c509e1bd8a796f6c3fd132e2d6";
    
    public static void main(String[] args) throws ParseException, IOException {
    	//获取accessToken
    	String url = ACCESS_TOKEN_URL.replace("APPID", APPID).replace("APPSECRET", APPSECRET);
    	com.alibaba.fastjson.JSONObject jsonObject = doGetStr(url);
    	String accessToken=jsonObject.getString("access_token").toString();
    	System.out.println("accessToken:"+accessToken);
    	//创建菜单 
		String menu=JSON.toJSON(initMenu()).toString();
		System.out.println("menu:"+menu);
		String createMenuUrl = CREATE_MENU_URL.replace("ACCESS_TOKEN", accessToken);
		jsonObject =doPostStr(createMenuUrl, menu);
		System.out.println("创建结果："+JSON.toJSONString(jsonObject));
	}
	
    
    /**
	 * 组装菜单，没有数据库，先对象存储，最后会封装成数据库配置模式
	 * @return
	 */
	public static Menu initMenu(){
		Menu menu = new Menu();
		//点击事件Type=click，后台根据key实现业务逻辑
		ViewButton button11 = new ViewButton();
		button11.setName("价格");
		button11.setType("view");
//		button11.setUrl(ConfigUtil.WEBURL+"/receipt/query");
		button11.setUrl(getSnsapi_Userinfo("http://1j6712x825.iask.in/weixinchat-web/"+"marginPriceWeb"));
		//视图按钮，Type=view,点击后跳转到设置的URL中
		ViewButton button21 = new ViewButton();
		button21.setName("价格数据");
		button21.setType("view");
		button21.setUrl("http://1j6712x825.iask.in/weixinchat-web/"+"getMarginPrice.do");
		
		ViewButton button22 = new ViewButton();
		button22.setName("查询1");
		button22.setType("view");
		button22.setUrl("http://1j6712x825.iask.in/weixinchat-web/"+"/wx/auth");
		
		ViewButton button23 = new ViewButton();
		button23.setName("待查询");
		button23.setType("view");
		button23.setUrl("http://1j6712x825.iask.in/weixinchat-web/"+"wx/auth");
		
		Button button02 = new Button();
		button02.setName("国际价格");
		button02.setSub_button(new Button[]{button21,button22,button23});
		
		//视图按钮，登录页
		ViewButton button31 = new ViewButton();
		button31.setName("我的账户");
		button31.setType("view");
		button31.setUrl("http://1j6712x825.iask.in/weixinchat-web/"+"wx/auth");
		
		ViewButton button32 = new ViewButton();
		button32.setName("管理1");
		button32.setType("view");
		button32.setUrl("http://1j6712x825.iask.in/weixinchat-web"+"/wx/auth");
		
		ViewButton button33 = new ViewButton();
		button33.setName("管理2");
		button33.setType("view");
		button33.setUrl("http://1j6712x825.iask.in/weixinchat-web/"+"/wx/auth");
		
		ViewButton button34 = new ViewButton();
		button34.setName("操作员");
		button34.setType("view");
		button34.setUrl("http://108748fc.ngrok.io/test"+"/wx/auth");
		
		
		Button button03 = new Button();
		button03.setName("商户");
		button03.setSub_button(new Button[]{button31,button32,button33,button34});
 
		//设置运营中心，搜索，查看为一级菜单
		menu.setButton(new Button[]{button11,button02,button03});
		return menu;
	}
    
    
	/**
	 * 静默授权
	 * 拼接一个请求地址，并且将域名和请求路劲进行URLEncoder编码
	 * @param weburl
	 * @return
	 */
	public static String getSnsapi_Userinfo(String weburl) {
		String  url=snsapi_userinfo.replaceFirst("APPID", ConfigUtil.APPID);
		try{
			String  encodeWeburl = java.net.URLEncoder.encode(weburl!=null?weburl:"" ,"utf-8" );
			url=url.replace("REDIRECT_URI", encodeWeburl);
		}catch(Exception e){
			e.printStackTrace();
		}
		return url;
		
	}
	
    
	public static JSONObject doGetStr(String url) throws ParseException, IOException{
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		JSONObject jsonObject = null;
		HttpResponse httpResponse = client.execute(httpGet);
		HttpEntity entity = httpResponse.getEntity();
		if(entity != null){
			String result = EntityUtils.toString(entity,"UTF-8");
			jsonObject = JSONObject.parseObject(result);
		}
		return jsonObject;
	}
	
	public static JSONObject doPostStr(String url,String outStr) throws ParseException, IOException{
		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost httpost = new HttpPost(url);
		JSONObject jsonObject = null;
		httpost.setEntity(new StringEntity(outStr,"UTF-8"));
		HttpResponse response = client.execute(httpost);
		String result = EntityUtils.toString(response.getEntity(),"UTF-8");
		jsonObject = JSONObject.parseObject(result);
		return jsonObject;
	}
}
