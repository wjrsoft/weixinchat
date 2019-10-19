package com.wonder.po;

/**
 * 主要用作于图文消息
 * @author 锦荣
 *
 */
public class News {
	/*
	 * 消息标题
	 */
	private String Title;
	/*
	 * 消息描述
	 */
	private String Description;
	/*
	 * 消息图片
	 */
	private String PicUrl;
	/*
	 * 单击消息跳转链接
	 */
	private String Url;
	
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getPicUrl() {
		return PicUrl;
	}
	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
}
