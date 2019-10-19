package com.wonder.po;

/**
 * 预览接口
 * 开发者可通过该接口发送消息给指定用户，在手机端查看消息的样式和排版
 * 图文消息为mpnews，文本消息为text，语音为voice，音乐为music，图片为image，视频为video，卡券为wxcard
 * @author jinrong.wang
 *
 */
public class Preview {
	private String touser;
	private String msgtype;
	private String media_id;
	private String text;
	public String getTouser() {
		return touser;
	}
	public void setTouser(String touser) {
		this.touser = touser;
	}
	public String getMsgtype() {
		return msgtype;
	}
	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}
	public String getMedia_id() {
		return media_id;
	}
	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	
}
