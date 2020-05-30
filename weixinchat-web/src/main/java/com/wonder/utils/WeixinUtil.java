package com.wonder.utils;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import org.apache.http.ParseException;

import com.alibaba.fastjson.JSONObject;
import com.wonder.po.AccessToken;

/**
 * 跟Token有关的请求工具类
 * @author Stephen
 *
 */
public class WeixinUtil {
	private static final String APPID=ConfigUtil.APPID;
	private static final String APPSECRET = ConfigUtil.APPSECRET;
	//获取新的Token
	private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	//上传消息
	private static final String UPLOAD_URL = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
	//创建菜单
	private static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	//查询菜单
	private static final String QUERY_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
	//删除菜单
	private static final String DELETE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
	//给用户发送消息
	private static final String PREVIEW="https://api.weixin.qq.com/cgi-bin/message/mass/preview?access_token=ACCESS_TOKEN";
	//获取用户列表
	private static final String USER_URL="https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID";
	//获取用户信息
	private static final String USER_INFO_URL="https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	
	
	
	/**
	 * 获取accessToken
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	public static AccessToken getAccessToken() throws ParseException, IOException{
		AccessToken token = new AccessToken();
		String url = ACCESS_TOKEN_URL.replace("APPID", APPID).replace("APPSECRET", APPSECRET);
		JSONObject jsonObject = HttpClientUtil.doGetStr(url);
		if(jsonObject!=null){
			token.setToken(jsonObject.getString("access_token"));
			token.setExpiresIn(jsonObject.getInteger("expires_in"));
		}
		return token;
	}
	/**
	 * 创建菜单并不需要XML,只需要json
	 * @param token
	 * @param menu
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	public static int createMenu(String token,String menu) throws ParseException, IOException{
		int result = 0;
		//https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN
		String url = CREATE_MENU_URL.replace("ACCESS_TOKEN", token);
		JSONObject jsonObject = HttpClientUtil.doPostStr(url, menu);
		if(jsonObject != null){
			result = jsonObject.getInteger("errcode");
		}
		return result;
	}
	
	/**
	 * 菜单的创建与删除，因为菜单是存在于微信平台，所以需要查询
	 * @param token
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	public static JSONObject queryMenu(String token) throws ParseException, IOException{
		String url = QUERY_MENU_URL.replace("ACCESS_TOKEN", token);
		JSONObject jsonObject = HttpClientUtil.doGetStr(url);
		return jsonObject;
	}
	
	public static int deleteMenu(String token) throws ParseException, IOException{
		String url = DELETE_MENU_URL.replace("ACCESS_TOKEN", token);
		JSONObject jsonObject = HttpClientUtil.doGetStr(url);
		int result = 0;
		if(jsonObject != null){
			result = jsonObject.getInteger("errcode");
		}
		return result;
	}
	
	
	/**
	 * 获取UserList
	 * @param accessToken
	 * @param NEXT_OPENID  下一个用户IPEN_ID因为一次兜取数据有限
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	public static String getUserList(String accessToken,String NEXT_OPENID) throws ParseException, IOException{
		String url = USER_URL.replace("ACCESS_TOKEN", accessToken).replace("NEXT_OPENID", NEXT_OPENID);
		JSONObject jsonObject = HttpClientUtil.doGetStr(url);
		return jsonObject.toString();
	}
	
	public static JSONObject getUserInfo(String accessToken,String openID) throws ParseException, IOException{
		String url = USER_INFO_URL.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openID);
		JSONObject jsonObject = HttpClientUtil.doGetStr(url);
		return jsonObject;
	}
	
	
	
	/**
	 * 发送消息给用户
	 * @throws IOException 
	 * @throws ParseException 
	 */
	public static int sendMsgToUser(String accessToken,String content) throws ParseException, IOException{
		int result = 0;
		String url = PREVIEW.replace("ACCESS_TOKEN", accessToken);
		JSONObject jsonObject = HttpClientUtil.doPostStr(url, content);
		System.out.println("===json:"+jsonObject);
		if(jsonObject != null){
			result = jsonObject.getIntValue("errcode");
		}
		return result;
	}
	
	
	
	
	
	/**
	 * 文件上传
	 * @param filePath
	 * @param accessToken
	 * @param type
	 * @return
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchProviderException
	 * @throws KeyManagementException
	 */
	public static String upload(String filePath, String accessToken,String type) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException {
		File file = new File(filePath);
		if (!file.exists() || !file.isFile()) {
			throw new IOException("文件不存在");
		}

		String url = UPLOAD_URL.replace("ACCESS_TOKEN", accessToken).replace("TYPE",type);
		
		URL urlObj = new URL(url);
		//连接
		HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();

		con.setRequestMethod("POST"); 
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setUseCaches(false); 

		//设置请求头信息
		con.setRequestProperty("Connection", "Keep-Alive");
		con.setRequestProperty("Charset", "UTF-8");

		//设置边界
		String BOUNDARY = "----------" + System.currentTimeMillis();
		con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);

		StringBuilder sb = new StringBuilder();
		sb.append("--");
		sb.append(BOUNDARY);
		sb.append("\r\n");
		sb.append("Content-Disposition: form-data;name=\"file\";filename=\"" + file.getName() + "\"\r\n");
		sb.append("Content-Type:application/octet-stream\r\n\r\n");

		byte[] head = sb.toString().getBytes("utf-8");

		//获得输出流
		OutputStream out = new DataOutputStream(con.getOutputStream());
		//输出表头
		out.write(head);

		//文件正文部分
		//把文件已流文件的方式 推入到url中
		DataInputStream in = new DataInputStream(new FileInputStream(file));
		int bytes = 0;
		byte[] bufferOut = new byte[1024];
		while ((bytes = in.read(bufferOut)) != -1) {
			out.write(bufferOut, 0, bytes);
		}
		in.close();

		//结尾部分
		byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");//定义最后数据分隔线

		out.write(foot);

		out.flush();
		out.close();

		StringBuffer buffer = new StringBuffer();
		BufferedReader reader = null;
		String result = null;
		try {
			//定义BufferedReader输入流来读取URL的响应
			reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			if (result == null) {
				result = buffer.toString();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				reader.close();
			}
		}

		JSONObject jsonObj = JSONObject.parseObject(result);
		System.out.println(jsonObj);
		String typeName = "media_id";
		if(!"image".equals(type)){
			typeName = type + "_media_id";
		}
		String mediaId = jsonObj.getString(typeName);
		return mediaId;
	}
	
	
}
