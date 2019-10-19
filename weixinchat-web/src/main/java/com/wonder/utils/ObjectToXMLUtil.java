package com.wonder.utils;

import com.thoughtworks.xstream.XStream;
import com.wonder.po.ImageMessage;
import com.wonder.po.MusicMessage;
import com.wonder.po.News;
import com.wonder.po.NewsMessage;
import com.wonder.po.TextMessage;

public class ObjectToXMLUtil {
	
	
	
	/*
	 * 将TextMessage 对象 转换成XML
	 */
	public static String textMessageToXml(TextMessage texMessage){
		XStream xstream=new XStream();
		xstream.alias("xml", texMessage.getClass());
		return xstream.toXML(texMessage);
	}
	
	
	/**
	 * 图文消息对象NewsMessage转为xml
	 * @param newsMessage
	 * @return
	 */
	public static String newsMessageToXml(NewsMessage newsMessage){
		XStream xstream = new XStream();
		xstream.alias("xml", newsMessage.getClass());
		xstream.alias("item", new News().getClass());
		return xstream.toXML(newsMessage);
	}
	
	
	
	
	/**
	 * 图片消息转为xml
	 * @param imageMessage
	 * @return
	 */
	public static String imageMessageToXml(ImageMessage imageMessage){
		XStream xstream = new XStream();
		xstream.alias("xml", imageMessage.getClass());
		return xstream.toXML(imageMessage);
	}
	
	/**
	 * 音乐消息转为xml
	 * @param musicMessage
	 * @return
	 */
	public static String musicMessageToXml(MusicMessage musicMessage){
		XStream xstream = new XStream();
		xstream.alias("xml", musicMessage.getClass());
		return xstream.toXML(musicMessage);
	}
	
}
