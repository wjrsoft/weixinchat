package com.wonder.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
/**
 * 因为没有数据库存储Token暂时先用文件存储，后期应该会用redis或者数据库存储
 * @author 王锦荣
 *
 */
public class IoUtil {
	/**
	 * 因为没有数据库，暂时用文本存储Token信息
	 * @return
	 * @throws Exception
	 */
	public static String getToken() throws Exception{
		FileReader fr = new FileReader("token.txt");  
		BufferedReader bufr = new BufferedReader(fr); 
		String token=bufr.readLine();
		bufr.close();  
		return token;
	}
	/**
	 * 因为没有数据库，暂时用文本存储
	 * @return
	 * @throws Exception
	 */
	public static boolean setToken(String token) throws Exception{
		FileWriter fw = new FileWriter("token.txt");  
		BufferedWriter bufw = new BufferedWriter(fw);
		bufw.write(token);
		bufw.flush();  
		bufw.close();  
		return false;
	}
	
}
