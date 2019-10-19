package com.wonder.utils;

import java.util.Date;
import java.util.List;


import org.apache.log4j.Logger;

import com.wonder.po.Image;
import com.wonder.po.ImageMessage;
import com.wonder.po.Music;
import com.wonder.po.MusicMessage;
import com.wonder.po.News;
import com.wonder.po.NewsMessage;
import com.wonder.po.TextMessage;

/**
 * 消息处理工具类
 * @author jinrong.wang
 *
 */
public class MessageUtil {
	private static Logger log = Logger.getLogger(MessageUtil.class);
	//微信SDK下载的
	public static final String MESSAGE_TEXT = "text";
	public static final String MESSAGE_NEWS = "news";
	public static final String MESSAGE_IMAGE = "image";
	public static final String MESSAGE_VOICE = "voice";
	public static final String MESSAGE_MUSIC = "music";
	public static final String MESSAGE_VIDEO = "video";
	public static final String MESSAGE_LINK = "link";
	public static final String MESSAGE_LOCATION = "location";
	public static final String MESSAGE_EVNET = "event";
	public static final String MESSAGE_SUBSCRIBE = "subscribe";
	public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";
	public static final String MESSAGE_CLICK = "CLICK";
	public static final String MESSAGE_VIEW = "VIEW";
	public static final String MESSAGE_SCANCODE= "scancode_push";
	
	
	
	////////////////////////////////消息组装////////////////////////////////////////////////////////////
	/**
	 * 图文消息的组装
	 * @param toUserName
	 * @param fromUserName
	 * @param listNews 图文消息集合
	 * @return
	 */
	public static String initNewsMessage(String toUserName,String fromUserName,List<News> listNews){
		String message = null;
		NewsMessage newsMessage = new NewsMessage();
		newsMessage.setToUserName(fromUserName);
		newsMessage.setFromUserName(toUserName);
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(MESSAGE_NEWS);
		newsMessage.setArticles(listNews);
		newsMessage.setArticleCount(listNews.size());
      	message = ObjectToXMLUtil.newsMessageToXml(newsMessage);
        log.info(message);
		return message;
	}
	
	
	
	/**
	 * 组装图片消息，MediaId是上传至微信服务平台的图片id,关于上传图片到微信平台的代码可以调用WeixinUtil.MediaId();
	 * @param toUserName
	 * @param fromUserName
	 * @return
	 */
	public static String initImageMessage(String toUserName,String fromUserName,String mediaId){
		String message = null;
		Image image = new Image();
		image.setMediaId(mediaId);
		ImageMessage imageMessage = new ImageMessage();
		imageMessage.setFromUserName(toUserName);
		imageMessage.setToUserName(fromUserName);
		imageMessage.setMsgType(MESSAGE_IMAGE);
		imageMessage.setCreateTime(new Date().getTime());
		imageMessage.setImage(image);
		message = ObjectToXMLUtil.imageMessageToXml(imageMessage);
		return message;
	}
	/**
	 * 组装音乐消息
	 * @param toUserName
	 * @param fromUserName
	 * @return
	 */
	public static String initMusicMessage(String toUserName,String fromUserName,Music music){
		String message = null;
		MusicMessage musicMessage = new MusicMessage();
		musicMessage.setFromUserName(toUserName);
		musicMessage.setToUserName(fromUserName);
		musicMessage.setMsgType(MESSAGE_MUSIC);
		musicMessage.setCreateTime(new Date().getTime());
		musicMessage.setMusic(music);
		message = ObjectToXMLUtil.musicMessageToXml(musicMessage);
		return message;
	}
	
	//文本类型回复
	public  static String initText(String fromUserName,String toUserName,String content){
		TextMessage text=new TextMessage();
		text.setFromUserName(toUserName);//消息接收者
		text.setToUserName(fromUserName);//开发者微信号
		//MESSAGE_TEXT = "text"
		text.setMsgType(MessageUtil.MESSAGE_TEXT);//消息类型为文不能消息
		text.setCreateTime(new Date().getTime());//时间参数
		text.setContent(content);//文本内容
		return ObjectToXMLUtil.textMessageToXml(text);//消息转换成XML格式
	}
	
	
}
