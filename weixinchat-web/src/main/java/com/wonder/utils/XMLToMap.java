package com.wonder.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XMLToMap {
	 /**
	   * xml转为Map,将Request请求参数解析放入Map容器  
	   * @param req
	   * @param resp
	   * @return
	 * @throws DocumentException 
	 * @throws IOException 
	   */
		public static Map<String,String> xmlToMap(HttpServletRequest req) throws DocumentException, IOException {
			Map<String,String> map=new HashMap<String,String>();
			SAXReader reader=new SAXReader();
			ServletInputStream ins=req.getInputStream();
			Document doc=reader.read(ins);
			Element root=doc.getRootElement();
			List<Element> list=root.elements();
			//将请求消息放到Map容器中
			for(Element e:list){
				map.put(e.getName(), e.getText());
			}
			ins.close();
			return map;
		}
}
