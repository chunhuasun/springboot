package com.atguigu.springboot.serv;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject; 
  

public class juheApiInfo {

	 //配置您申请的KEY
     public static final String API_KEY = "bfe0c6b8d2a2a70ddefc13d343dd20fa";
     private static String recEncoding = "UTF-8"; 
	   
	  /**
	   * juheQueryWord   聚合数据网提供的成语词典查询API  根据成语查询详细信息
	   * 
	   *在线接口文档：http://www.juhe.cn/docs/157
	   * @param qryWord   查询词语
	   * @return 返回指定图片的所有文字内容信息，每段之间用“\n”分隔
	   */ 
	  public static String juheQueryWord(String qryWord) {
	    
		 String returnText = "";
		 /*首先读取url文件转存为本地文件，再将图片文件发送到百度OCR进行文字识别文件，最后返回识别的文字内容*/
		 try {
			 
             String sendUrl ="http://v.juhe.cn/chengyu/query";//请求接口地址 
			 
             Map params = new HashMap();//请求参数
             params.put("word",qryWord);//填写需要查询的汉字，UTF8 urlencode编码
             params.put("key",API_KEY);//应用APPKEY(应用详细页查询)
             params.put("dtype","");//返回数据的格式,xml或json，默认json
              
             /*发送请求获取成语查询详情*/
			 String translate = HttpRequestProxy.doPost(sendUrl, params, recEncoding);
			  
			 returnText = translate;
			   
		 } catch (Exception e) {  
		     e.printStackTrace();  
		 }  
		 return returnText;
	  }    
	    
}