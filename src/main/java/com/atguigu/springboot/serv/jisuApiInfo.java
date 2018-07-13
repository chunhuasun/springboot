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
  

public class jisuApiInfo {

	 //配置您申请的KEY
     public static final String API_KEY = "4bb25a5516d851bb";
     private static String recEncoding = "UTF-8"; 
	   
	  /**
	   * jisuQueryWord   极速数据网提供的成语词典查询API  根据成语查询详细信息
	   * 
	   *在线接口文档：http://www.juhe.cn/docs/157
	   * @param qryWord   查询词语
	   * @return 返回指定图片的所有文字内容信息，每段之间用“\n”分隔
	   */ 
	  public static String jisuQueryWord(String qryWord) {
	    
		 String returnText = "";
		 /*首先读取url文件转存为本地文件，再将图片文件发送到百度OCR进行文字识别文件，最后返回识别的文字内容*/
		 try {
			 
             String sendUrl ="http://api.jisuapi.com/chengyu/detail";//请求接口地址   
			 
             Map params = new HashMap();//请求参数
             params.put("chengyu",qryWord);//填写需要查询的汉字，UTF8 urlencode编码
             params.put("appkey",API_KEY);//应用APPKEY(应用详细页查询)
               
             /*发送请求获取成语查询详情*/
			 String translate = HttpRequestProxy.doPost(sendUrl, params, recEncoding);
			   
			/*解析返回结果*/
			JSONObject object = null;
			object = JSONObject.fromObject(translate);
			
			if(object.getInt("status")!=0){
				returnText = object.get("msg").toString();
            }else{
            	returnText = object.get("result").toString();
            }
			
			System.out.println("testGroupby returnText-->" + returnText);
			
			JSONArray jsonArray = JSONArray.fromObject(returnText);  //将字符串转换为JSON数组
	        //System.out.println("testGroupby jsonArray-->" + jsonArray.toString());
	 
			//后续的结果解析还未处理----------------------------
	         String ocrinfo = "";
			 String ocrinfosingle = "";
			 
			 int iSize = jsonArray.size();
			 for (int i = 0; i < iSize; i++) {   //循环获取识别的信息
				  JSONObject jsonObj = jsonArray.getJSONObject(i);
					 	
				  ocrinfosingle = jsonObj.get("words").toString();
				  //System.out.println("++++++++++++++++ocrinfosingle+++++in----"+ocrinfosingle);
				  ocrinfo += ocrinfosingle + " ";
			 } 
			 
			   
			 returnText = ocrinfo;
		      
		 } catch (Exception e) {  
		     e.printStackTrace();  
		 }  
		 return returnText;
	  }    
	    
	  
	  /**
	   * jisuQueryKeyword   极速数据网提供的成语词典查询API  成语收索
	   * 
	   *在线接口文档：http://www.juhe.cn/docs/157
	   * @param qryWord   查询词语
	   * @return 返回指定图片的所有文字内容信息，每段之间用“\n”分隔
	   */ 
	  public static String jisuQueryKeyword(String qryWord) {
	    
		 String returnText = "";
		 /*首先读取url文件转存为本地文件，再将图片文件发送到百度OCR进行文字识别文件，最后返回识别的文字内容*/
		 try {
			 
             String sendUrl ="http://api.jisuapi.com/chengyu/search";//请求接口地址   
			 
             Map params = new HashMap();//请求参数
             params.put("keyword",qryWord);//填写需要查询的汉字，UTF8 urlencode编码
             //params.put("keyword",URLEncoder.encode(qryWord,"utf-8"));//填写需要查询的汉字，UTF8 urlencode编码
             params.put("appkey",API_KEY);//应用APPKEY(应用详细页查询)
               
             /*发送请求获取成语查询详情*/
			 String translate = HttpRequestProxy.doPost(sendUrl, params, recEncoding);
			   
			/*解析返回结果*/
			JSONObject object = null;
			object = JSONObject.fromObject(translate);
			
			System.out.println("testGroupby object-->" + object.toString());
			//String url = URL + "?appkey=" + APPKEY + "&keyword=" + URLEncoder.encode(keyword,"utf-8");
			 
			if(object.getInt("status")!=0){
				returnText = object.get("msg").toString();
            }else{
            	returnText = object.get("result").toString();
            }
			
			System.out.println("testGroupby returnText-->" + returnText);
			
			JSONArray jsonArray = JSONArray.fromObject(returnText);  //将字符串转换为JSON数组
	        //System.out.println("testGroupby jsonArray-->" + jsonArray.toString());
	 
			//后续的结果解析还未处理----------------------------
	         String ocrinfo = "";
			 String ocrinfosingle = "";
			 
			 int iSize = jsonArray.size();
			 for (int i = 0; i < iSize; i++) {   //循环获取识别的信息
				  JSONObject jsonObj = jsonArray.getJSONObject(i);
					 	
				  ocrinfosingle = jsonObj.get("words").toString();
				  //System.out.println("++++++++++++++++ocrinfosingle+++++in----"+ocrinfosingle);
				  ocrinfo += ocrinfosingle + " ";
			 } 
			 
			   
			 returnText = ocrinfo;
		      
		 } catch (Exception e) {  
		     e.printStackTrace();  
		 }  
		 return returnText;
	  } 
	  
	  /**
	   * jisuQueryTangshi   极速数据网提供的成语词典查询API   唐诗查询
	   * 
	   *在线接口文档：http://www.juhe.cn/docs/157
	   * @param qryWord   查询词语
	   * @return 返回指定图片的所有文字内容信息，每段之间用“\n”分隔
	   */ 
	  public static String jisuQueryTangshi(String qryWord) {
	    
		 String returnText = "";
		 /*首先读取url文件转存为本地文件，再将图片文件发送到百度OCR进行文字识别文件，最后返回识别的文字内容*/
		 try {
			 
             String sendUrl ="http://api.jisuapi.com/tangshi/search";//请求接口地址   
			 
             Map params = new HashMap();//请求参数
             params.put("keyword",qryWord);//填写需要查询的汉字，UTF8 urlencode编码
             //params.put("keyword",URLEncoder.encode(qryWord,"utf-8"));//填写需要查询的汉字，UTF8 urlencode编码
             params.put("appkey","6dd3c7b0db8da4fd");//应用APPKEY(应用详细页查询)
               
             /*发送请求获取成语查询详情*/
			 String translate = HttpRequestProxy.doPost(sendUrl, params, recEncoding);
			 
			 returnText = translate ;
			 
			/*解析返回结果*/
			 /*
			JSONObject object = null;
			object = JSONObject.fromObject(translate);
			
			System.out.println("testGroupby object-->" + object.toString());
			//String url = URL + "?appkey=" + APPKEY + "&keyword=" + URLEncoder.encode(keyword,"utf-8");
			 
			if(object.getInt("status")!=0){
				returnText = object.get("msg").toString();
            }else{
            	returnText = object.get("result").toString();
            }
			*/
			System.out.println("testGroupby returnText-->" + returnText);
			
			/*
			JSONArray jsonArray = JSONArray.fromObject(returnText);  //将字符串转换为JSON数组
	        //System.out.println("testGroupby jsonArray-->" + jsonArray.toString());
			//后续的结果解析还未处理----------------------------
	         String ocrinfo = "";
			 String ocrinfosingle = "";
			 int iSize = jsonArray.size();
			 for (int i = 0; i < iSize; i++) {   //循环获取识别的信息
				  JSONObject jsonObj = jsonArray.getJSONObject(i);
					 	
				  ocrinfosingle = jsonObj.get("words").toString();
				  //System.out.println("++++++++++++++++ocrinfosingle+++++in----"+ocrinfosingle);
				  ocrinfo += ocrinfosingle + " ";
			 }  
			 returnText = ocrinfo;
		     */ 
		 } catch (Exception e) {  
		     e.printStackTrace();  
		 }  
		 return returnText;
	  } 
}