package com.test.demo;

import com.wonder.utils.ConfigUtil;

public class InitMenu {
	/**
	 * 组装菜单，没有数据库，先对象存储，最后会封装成数据库配置模式
	 * @return
	 */
	public static Menu initMenu(){
		Menu menu = new Menu();
		//点击事件Type=click，后台根据key实现业务逻辑
		ClickButton button11 = new ClickButton();
		button11.setName("查看");
		button11.setType("click");
		button11.setKey("11");
		//视图按钮，Type=view,点击后跳转到设置的URL中
		ViewButton button21 = new ViewButton();
		button21.setName("搜索");
		button21.setType("view");
		button21.setUrl("http://www.baidu.com");
		
		//视图按钮，登录页
		ViewButton button30 = new ViewButton();
		button30.setName("商户登录");
		button30.setType("view");
		button30.setUrl(ConfigUtil.WEBURL+"login.html");
		//点击按钮，调用摄像头，并将扫码的请求发到后台，可以设置一个key
		ClickButton button31 = new ClickButton();
		button31.setName("扫码收款");
		button31.setType("scancode_push");
		button31.setKey("31");
		//调起地图，选择后发送后台
		ClickButton button32 = new ClickButton();
		button32.setName("位置");
		button32.setType("location_select");
		button32.setKey("32");
		//设置运营中心为一级菜单，位置和扫码为二级菜单
		Button button = new Button();
		button.setName("运营中心");
		button.setSub_button(new Button[]{button30,button31,button32});
		//设置运营中心，搜索，查看为一级菜单
		menu.setButton(new Button[]{button,button21,button11});
		return menu;
	}
	
	
	/**
	 * 组装菜单，没有数据库，先对象存储，最后会封装成数据库配置模式
	 * @return
	 */
	public static Menu initMenu2(){
		Menu menu = new Menu();
		//点击事件Type=click，后台根据key实现业务逻辑
		ViewButton button11 = new ViewButton();
		button11.setName("收款");
		button11.setType("view");
		button11.setUrl(ConfigUtil.WEBURL+"login.html");
		//视图按钮，Type=view,点击后跳转到设置的URL中
		ViewButton button21 = new ViewButton();
		button21.setName("交易查询");
		button21.setType("view");
		button21.setUrl(ConfigUtil.WEBURL+"login.html");
		
		ViewButton button22 = new ViewButton();
		button22.setName("退款查询");
		button22.setType("view");
		button22.setUrl(ConfigUtil.WEBURL+"login.html");
		
		ViewButton button23 = new ViewButton();
		button23.setName("待复核查询");
		button23.setType("view");
		button23.setUrl(ConfigUtil.WEBURL+"login.html");
		
		Button button02 = new Button();
		button02.setName("我的交易");
		button02.setSub_button(new Button[]{button21,button22,button23});
		
		//视图按钮，登录页
		ViewButton button31 = new ViewButton();
		button31.setName("我的账户");
		button31.setType("view");
		button31.setUrl(ConfigUtil.WEBURL+"login.html");
		
		ViewButton button32 = new ViewButton();
		button32.setName("网点管理");
		button32.setType("view");
		button32.setUrl(ConfigUtil.WEBURL+"login.html");
		
		ViewButton button33 = new ViewButton();
		button33.setName("二维码管理");
		button33.setType("view");
		button33.setUrl(ConfigUtil.WEBURL+"login.html");
		
		ViewButton button34 = new ViewButton();
		button34.setName("操作员管理");
		button34.setType("view");
		button34.setUrl(ConfigUtil.WEBURL+"login.html");
		
		
		Button button03 = new Button();
		button03.setName("商户中心");
		button03.setSub_button(new Button[]{button31,button32,button33,button34});
 
		//设置运营中心，搜索，查看为一级菜单
		menu.setButton(new Button[]{button11,button02,button03});
		return menu;
	}
}
