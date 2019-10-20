package com.wonder.controller.wx;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.wonder.po.Music;
import com.wonder.po.News;
import com.wonder.utils.ConfigUtil;
import com.wonder.utils.MessageUtil;
import com.wonder.utils.SignatureUtil;
import com.wonder.utils.XMLToMap;

/**
 * 微信doGet,doPost请求处理
 * @author jinrong.wang
 *
 */
@Controller
public class WeixinServlet extends HttpServlet {
	private final static Logger logger = LoggerFactory.getLogger(WeixinServlet.class);  
	@Override
	/**
	 * 签名验证一微信发的是get请求
	 * 正确响应微信发送的Token验证,校验signature, timestamp, nonce
	 * 
	 */
	@RequestMapping(value="/wx.do",method = RequestMethod.GET)
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		System.out.println("*******doGet请求，进入WeixinServlet");
		logger.info("**************doGet请求，进入WeixinServlet");
		String signature=req.getParameter("signature");
		String timestamp=req.getParameter("timestamp");
		String nonce=req.getParameter("nonce");
		String echostr=req.getParameter("echostr");
		PrintWriter out=resp.getWriter();
		if(SignatureUtil.checkSignature(signature, timestamp, nonce)){//校验参数
			out.print(echostr);
		}else{
			System.out.println("签名失败");
		}
	}
	
	@Override
	/**
	 * 微信客户端消息回复会走Post请求
	 * 请求是XML格式，响应请求也是XML格式，这个方法类似于控制器，解析用户发送内容，调用不同方法进行响应
	 * doPost类似于请求控制器
	 */
	@RequestMapping(value="/wx.do",method = RequestMethod.POST)
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		logger.info("**************doPost请求，进入WeixinServlet");
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out=resp.getWriter();
		String message="";
		try {
			Map<String,String> map=XMLToMap.xmlToMap(req);//将XML请求，转换成Map
			String fromUserName=map.get("FromUserName");//发送方帐号（一个OpenID）
			String toUserName=map.get("ToUserName");//开发者微信号
			String createTime=map.get("CreateTime");//消息创建时间 （整型）
			String msgType=map.get("MsgType");//消息类型
			String content=map.get("Content"); //文本消息内容
			String MsgId=map.get("MsgId");//消息id，64位整型
			String eventType = map.get("Event");//事件类型
			logger.info("传入的消息类型是："+msgType+",内容是："+content);
			logger.info("客户端XML数据有:"+JSON.toJSONString(map));
			
			
			/**
			 * 消息类型
			 */
			if (MessageUtil.MESSAGE_TEXT.equals(msgType)) {
				//文本消息回复，收到什么回复什么，如果不在if条件中，则默认收到什么回复什么
				message = MessageUtil.initText(fromUserName, toUserName,content);
				if ("1".equals(content)) {
					//文本消息
					message = MessageUtil.initText(fromUserName, toUserName, "欢迎使用微信公众号"); 
				} else if ("2".equals(content)) {
					//图文消息
					message = MessageUtil.initNewsMessage(toUserName, fromUserName,getNewsList());
				}else if("3".equals(content)){
					//媒体消息，回复图片，回复图片消息时，必须先将图片传至微信平台上，并获取图片id
					message = MessageUtil.initImageMessage(toUserName,fromUserName,"IHdqAbSmXJJZNlj9CKZAIk3suK4LLjM2OPJi9fCuOB_hKPBKgg3YOyE2uUJQwHXJ");
				}else if("4".equals(content)){
					//媒体消息，回复一首歌给你听
					message = MessageUtil.initMusicMessage(toUserName,fromUserName,getMusic());
				} 
			} 
			
			
			
			/**
			 * 事件类型
			 */
			if(MessageUtil.MESSAGE_EVNET.equals(msgType)){
				logger.info("传入一个事件,且事件类型为:"+eventType);
				if(MessageUtil.MESSAGE_SUBSCRIBE.equals(eventType)){
					System.out.println("微信关注事件");
					message = MessageUtil.initNewsMessage(toUserName, fromUserName,getNewsList());
					//saveUserInfo(fromUserName);
				}else if(MessageUtil.MESSAGE_CLICK.equals(eventType)){
					System.out.println("菜单点击事件");
					message = MessageUtil.initNewsMessage(toUserName, fromUserName,getNewsList());
				}
				else if(MessageUtil.MESSAGE_VIEW.equals(eventType)){ 
					System.out.println("菜单查看事件");
					String url = map.get("EventKey");
					System.out.println("菜单查看Key："+url);
					message = MessageUtil.initText(toUserName, fromUserName, url);
				}else if(MessageUtil.MESSAGE_SCANCODE.equals(eventType)){
					System.out.println("微信扫描事件");
					String key = map.get("EventKey");
					message = MessageUtil.initText(toUserName, fromUserName, key);//微信扫描事件并不会响应，一般是用来存储一些扫描信息的
				}
			} else if(MessageUtil.MESSAGE_LOCATION.equals(msgType)){//地图
				String label = map.get("Label");
				message = MessageUtil.initText(toUserName, fromUserName, label);
			}
			
			
			out.write(message);
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			out.close();
		}
		
		
	}
	
	
	
	
	
	
	
	@RequestMapping(value="/wx1.do")
	protected void test(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		logger.info("**************doGet请求，进入WeixinServlet");
		String signature=req.getParameter("signature");
		String timestamp=req.getParameter("timestamp");
		String nonce=req.getParameter("nonce");
		String echostr=req.getParameter("echostr");
		PrintWriter out=resp.getWriter();
		if(SignatureUtil.checkSignature(signature, timestamp, nonce)){//校验参数
			out.print(echostr);
		}else{
			System.out.println("签名失败");
		}
	}
	
	////////////////消息内容封装,这里只是举例//////////////////////////
	
	public List getNewsList(){
		List<News> newsList = new ArrayList<News>();
		News news = new News();
		news.setTitle("哈士奇，拆家队长");
//		news.setDescription("西伯利亚雪橇犬（俄语：Сибирский хаски，英语：Siberian husky），常见别名哈士奇，昵称为二哈。西伯利亚雪橇犬体重介于雄犬20-27公斤，雌犬16-23公斤，身高大约雄犬肩高53-58厘米，雌犬51-56厘米，是一种中型犬。西伯利亚雪橇犬是原始的古老犬种，在西伯利亚东北部、格陵兰南部生活");
		news.setPicUrl(ConfigUtil.WEBURL+"/image/dog.jpg");
		news.setUrl("http://www.baidu.com");
		newsList.add(news);
		News news2 = new News();
		news2.setTitle("熊猫也比人类舒服啊");
		news2.setDescription("西伯利亚雪橇犬（俄语：Сибирский хаски，英语：Siberian husky），常见别名哈士奇，昵称为二哈。西伯利亚雪橇犬体重介于雄犬20-27公斤，雌犬16-23公斤，身高大约雄犬肩高53-58厘米，雌犬51-56厘米，是一种中型犬。西伯利亚雪橇犬是原始的古老犬种，在西伯利亚东北部、格陵兰南部生活");
		news2.setPicUrl(ConfigUtil.WEBURL+"/image/xiongmao.jpg");
		news2.setUrl("http://www.baidu.com");
		newsList.add(news2);
		News news3= new News();
		news3.setTitle("唐嫣");
		news3.setPicUrl(ConfigUtil.WEBURL+"/image/tangyan.jpg");
		news3.setUrl(ConfigUtil.WEBURL+"/image/tangyan.jpg");
		newsList.add(news3);
		return newsList;
	}
	
	public Music getMusic(){
		Music music =new Music();
		music.setThumbMediaId("BQJWZxvVCCjevC4LSm_Hsd3MPC9pC4GPtNNylGIldfvSn-eUxFQZA9GsZaPVdjrf");
		music.setTitle("see you again");
		music.setDescription("速7片尾曲");
		music.setMusicUrl(ConfigUtil.WEBURL+"/resource/See You Again.mp3");
		music.setHQMusicUrl(ConfigUtil.WEBURL+"/resource/See You Again.mp3");
		
		return music;
	}
	
	
	
//	public void  saveUserInfo(String openid) {
//		try{
//			System.out.println();
//			String accessToken="";//IoUtil.getToken();//获取存储的token，有效期为两个小时
//			AccessToken token = WeixinUtil.getAccessToken();
//			accessToken=token.getToken();
//			JSONObject json=WeixinUtil.getUserInfo(accessToken, openid);
//			UserInfo u=new UserInfo();
//			u=getUserInfo(json);
//			userInfoServer.insert(u);
//		}catch(Exception e){
//			e.printStackTrace();
//			logger.info("关注者信息保存失败");
//		}
//		
//	}
	
	
//	public UserInfo getUserInfo(JSONObject json) throws Exception{
//		UserInfo u=new UserInfo();
//		u.setId((long) 1234);
//		u.setOpenid(json.getString("openid"));
//		u.setSex(json.getString("sex"));
//		u.setNickname(json.getString("nickname"));
//		u.setCity(json.getString("city"));
//		u.setProvince(json.getString("province"));
//		u.setCountry(json.getString("country"));
//		u.setSubscribe(json.getString("subscribe"));
//		u.setHeadimgurl(json.getString("headimgurl"));
//		u.setSubscribeTime(json.getString("subscribe_time"));
//		u.setTagidList(json.getString("tagid_list"));
//		u.setRemark(json.getString("remark"));
//		u.setLanguage(json.getString("language"));
//		u.setGroupid(json.getString("groupid"));
//		u.setSubscribeScene(json.getString("subscribe_scene"));
//		u.setQrScene(json.getString("qr_scene"));
//		u.setQrSceneStr(json.getString("qr_scene_str"));
//		logger.info("关注者用户信息为:"+JSON.toJSONString(u));
//		return u;
//	}
	
}
