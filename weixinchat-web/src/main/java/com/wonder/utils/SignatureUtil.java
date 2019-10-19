package com.wonder.utils;

import java.security.MessageDigest;
import java.util.Arrays;

import org.apache.log4j.Logger;
/**
 * 签名工具类
 * @author jinrong.wang
 *
 */
public class SignatureUtil {
	
	private static Logger log = Logger.getLogger(SignatureUtil.class);
	
	/**
	 *
	 * 	开发者提交信息后，微信服务器将发送GET请求到填写的服务器地址URL上
	 * 微信公号号开发者通过检验signature对请求进行校验（下面有校验方式）。若确认此次GET请求来自微信服务器，请原样返回echostr参数内容，则接入生效，成为开发者成功，否则接入失败。加密/校验流程如下： 
	 * 1）将token、timestamp、nonce三个参数进行字典序排序 2）将三个参数字符串拼接成一个字符串进行sha1加密 3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @return
	 */
	//获取微信签名TOKEN
	public static final String token=ConfigUtil.TOKEN;  
	
	public static boolean checkSignature(String signature,String timestamp,String nonce){
		String[] arr=new String[]{token,timestamp,nonce};
		//排序
		Arrays.sort(arr);
		//生成字符串
		StringBuffer content=new StringBuffer();
		for(int i=0;i<arr.length;i++){
			content.append(arr[i]);
		}
		log.info("排序生成的字符串："+content.toString());
		//sha1加密
		String temp=getSha1(content.toString());
		log.info("加密之后的字符串:"+temp);
		//这边是用token,timestamp,nonce加密后和signature比较
		Boolean flag=temp.equals(signature);
		log.info("加密之后是否一致："+flag);
		return flag;
	}
	

	/**
	 * Sha1加密算法，微信公众号要求使用Sha1
	 * @param str
	 * @return
	 */
	 public static String getSha1(String str){
	        if(str==null||str.length()==0){
	            return null;
	        }
	        char hexDigits[] = {'0','1','2','3','4','5','6','7','8','9',
	                'a','b','c','d','e','f'};
	        try {
	            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
	            mdTemp.update(str.getBytes("UTF-8"));

	            byte[] md = mdTemp.digest();
	            int j = md.length;
	            char buf[] = new char[j*2];
	            int k = 0;
	            for (int i = 0; i < j; i++) {
	                byte byte0 = md[i];
	                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
	                buf[k++] = hexDigits[byte0 & 0xf];      
	            }
	            return new String(buf);
	        } catch (Exception e) {
	            // TODO: handle exception
	            return null;
	        }
	    }
}
