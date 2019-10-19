package com.wonder.po;

/**
 * 文本消息
 * @author jinrong.wang
 *
 */
public class TextMessage extends BaseMessage{
	/**
	 * 消息内容
	 */
	private String Content;
	/**
	 * 消息ID
	 */
	private String MsgId;
	
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public String getMsgId() {
		return MsgId;
	}
	public void setMsgId(String msgId) {
		MsgId = msgId;
	}
}
