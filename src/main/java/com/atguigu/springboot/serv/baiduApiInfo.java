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
  

public class baiduApiInfo {

	 //设置APPID/AK/SK
     public static final String APP_ID = "imageOcr";
     public static final String API_KEY = "PLBT4EL2374fL33sNPtaCZlb";
     public static final String SECRET_KEY = "wL3k2CLUN2a9lKXZe6gDUsVDbpjjd9Ro";
     
	 private static String recEncoding = "UTF-8"; 
	  /**
	   * baiduOCR
	   * 
	   * @param imageUrl 图片URL地址
	   *        
	   * @return 返回指定图片的首个文字内容信息
	   */ 
	  public static String baiduocr(String imageUrl) {
	    
		 String returnText = "";
		 
         //System.out.println("+++++++++++++++++++++in----");
		 
		 String url = "http://apistore.baidu.com/idlapi/"; 
		  
		 Map params = new HashMap(); 
		 params.put("r", "demo/ocrret");
		 params.put("type", "LocateRecognize");
		 params.put("md5", "");
		 params.put("txtLan", "");
		 params.put("objurl", imageUrl);    //图片URL地址
		  
		 //System.out.println("+++++++++++++++++++++params----"+params.toString());
		 String translate = HttpRequestProxy.doGet(url, params, recEncoding);
		 //System.out.println("+++++++++++++++++++++in----"+translate);
		 
		 int start_int = translate.indexOf("<td>0</td>")+10;  //出现识别内容的开始标志
		 //System.out.println("++++++++++++++++start_int+++++in----"+start_int);
		 int text_start = translate.indexOf("<td>",start_int)+4;   //出现首个具体内容的开始位置
		 //System.out.println("++++++++++++++++text_start+++++in----"+text_start);
		 int end_int = translate.indexOf("</td>",start_int);  //首次出现的内容结束标示
		 //System.out.println("++++++++++++++end_int+++++++in----"+end_int);
		 String text_st = translate.substring(text_start, end_int);  //开始标志和结束标志中间的就是首次识别的内容信息
		 //System.out.println("++++++++++++++++text_st+++++in----"+text_st);
		 
		 return text_st;
	  }  
	   
	  /**
	   * baidumusic
	   * 
	   * @param musicInfo 搜索内容
	   *        
	   * @return 返回指定图片的首个文字内容信息
	   */ 
	  public static String baidumusic(String musicInfo) {
	    
		 String returnText = "";
		 
         //System.out.println("+++++++++++++++++++++in----");
		 
		 String url = "http://tingapi.ting.baidu.com/v1/restserver/ting";  
		  
		 Map params = new HashMap(); 
		 params.put("from", "qianqian");
		 params.put("version", "2.1.0");
		 params.put("method", "baidu.ting.search.catalogSug");
		 params.put("format", "json");
		 params.put("query", musicInfo);    //图片URL地址
		  
		 System.out.println("+++++++++++++++++++++params----"+params.toString());
		 String translate = HttpRequestProxy.doGet(url, params, recEncoding);
		 System.out.println("+++++++++++++++++++++in----"+translate);
		  
		 String ocrinfo = "";
		 String ocrinfosingle = "";
		 
		
		 
		 try{
			 System.out.println("testGroupby jsonArray-11112221->" );
			 ocrinfo=new String(translate.getBytes("UTF-8"),"8859_1"); 
			 
			 System.out.println("testGroupby jsonArray-11111->" );
			 JSONObject jsonObjAll = JSONObject.fromObject(ocrinfo);
			 //JSONObject jsonObject = new JSONObject(translate);
			 System.out.println("testGroupby jsonArray-->" + jsonObjAll.toString());
			 
			 
			 System.out.println("testGroupby jsonArray-11111->" );
					 
			 ocrinfo = translate.substring(9,translate.length()-4);
			 System.out.println("testGroupby jsonArray-->" + ocrinfo);
			 JSONArray jsonArray = JSONArray.fromObject(ocrinfo);  //将字符串转换为JSON数组
	         System.out.println("testGroupby jsonArray-->" + jsonArray.toString());
	 
			 int iSize = jsonArray.size();
			 for (int i = 0; i < iSize; i++) {   //循环获取识别的信息
				  JSONObject jsonObj = jsonArray.getJSONObject(i);
					 	
				  ocrinfosingle = jsonObj.get("word").toString();
				  System.out.println("++++++++++++++++ocrinfosingle+++++in----"+ocrinfosingle);
				  ocrinfo += ocrinfosingle + "\n";
			 } 
			 //System.out.println("testGroupby jsonArray-->" + ocrinfo);
		 }catch(Exception e){
			 ocrinfo = "NULL";
		 }
		 
		 return returnText;
	  }  
	  
	  /**
	   * baiduOCRall    2016-07-08 demo停用了URL的方式 只能使用上传文件的方式进行识别， 
	   *                  转到  baiduocrurl 函数中进行处理
	   * 
	   * @param imageUrl 图片URL地址    
	   *        
	   * @return 返回指定图片的所有文字内容信息，每段之间用“\n”分隔
	   */ 
	  public static String baiduocrall(String imageUrl) {
	    
		 String returnText = "";
		 
		 String url = "http://apistore.baidu.com/idlapi/"; 
	    	
		 Map params = new HashMap(); 
		 params.put("r", "demo/ocrret");
		 params.put("type", "LocateRecognize");
		 ///*
		 params.put("md5", "");
		 params.put("txtLan", "");
		 params.put("objurl", imageUrl);    //图片URL地址
		 //*/
		 //params.put("image", "D:\\2016\\175269a7385b866afc51570cab1a26e0.jpg");    //服务器图片信息
		  
		 System.out.println("+++++++++++++++++++++params----"+params.toString());
		 String translate = HttpRequestProxy.doGet(url, params, recEncoding);
		 System.out.println("+++++++++++++++++++++in----"+translate);
		 
		 int start_int = translate.indexOf("<textarea")+10;  //出现识别内容的开始标志
		 int text_start = translate.indexOf("ret",start_int)+5;   //出现首个具体内容的开始位置
		 int end_int = translate.indexOf("</textarea>",start_int)-1;  //首次出现的内容结束标示
		 String text_st = translate.substring(text_start, end_int);  //返回的JSON数组字符串

		 String ocrinfo = "";
		 String ocrinfosingle = "";
		 
		 try{
			 JSONArray jsonArray = JSONArray.fromObject(text_st);  //将字符串转换为JSON数组
	         //System.out.println("testGroupby jsonArray-->" + jsonArray.toString());
	 
			 int iSize = jsonArray.size();
			 for (int i = 0; i < iSize; i++) {   //循环获取识别的信息
				  JSONObject jsonObj = jsonArray.getJSONObject(i);
					 	
				  ocrinfosingle = jsonObj.get("word").toString();
				  //System.out.println("++++++++++++++++ocrinfosingle+++++in----"+ocrinfosingle);
				  ocrinfo += ocrinfosingle + "\n";
			 } 
			 //System.out.println("testGroupby jsonArray-->" + ocrinfo);
		 }catch(Exception e){
			 ocrinfo = "NULL";
		 }
		 return ocrinfo;
	  }   
	  
	  /**
	   * baiduocrfileall
	   * 
	   * @param filereturn 图片文件通过百度ocrdemo解析后的返回内容
	   *        
	   * @return 返回指定图片的所有文字内容信息，每段之间用“\n”分隔
	   */ 
	  public static String baiduocrfileall(String filereturn) {
	    
		 String returnText = "";
		  
		 String translate = filereturn;
		 System.out.println("+++++++++++++++++++++in----"+translate);
		 
		 int start_int = translate.indexOf("<textarea")+10;  //出现识别内容的开始标志
		 int text_start = translate.indexOf("ret",start_int)+5;   //出现首个具体内容的开始位置
		 int end_int = translate.indexOf("</textarea>",start_int)-1;  //首次出现的内容结束标示
		 String text_st = translate.substring(text_start, end_int);  //返回的JSON数组字符串

		 String ocrinfo = "";
		 String ocrinfosingle = "";
		 
		 try{
			 JSONArray jsonArray = JSONArray.fromObject(text_st);  //将字符串转换为JSON数组
	         //System.out.println("testGroupby jsonArray-->" + jsonArray.toString());
	 
			 int iSize = jsonArray.size();
			 for (int i = 0; i < iSize; i++) {   //循环获取识别的信息
				  JSONObject jsonObj = jsonArray.getJSONObject(i);
					 	
				  ocrinfosingle = jsonObj.get("word").toString();
				  //System.out.println("++++++++++++++++ocrinfosingle+++++in----"+ocrinfosingle);
				  ocrinfo += ocrinfosingle + " ";
			 } 
			 //System.out.println("testGroupby jsonArray-->" + ocrinfo);
		 }catch(Exception e){
			 ocrinfo = "NULL";
		 }
		 return ocrinfo;
	  }   
	  
	  /**
	   * baiduocrurl
	   * 
	   * @param imageUrl 图片URL地址
	   *        
	   * @return 返回指定图片的所有文字内容信息，每段之间用“\n”分隔
	   */ 
	  public static String baiduocrurl(String imageUrl,String path) {
	    
		 String returnText = "";
		 /*首先读取url文件转存为本地文件，再将图片文件发送到百度OCR进行文字识别文件，最后返回识别的文字内容*/
		 try {  
			 /*将url转为本地文件*/
			 URL url = new URL(imageUrl); 
			 String sendImageInfo= ""; 
			 sendImageInfo = ImageUtils.encodeImgageToBase64(url);  
			 //String path = "/";     //直接存放在根目录
			 String imgName = "1217BF990031.jpg";    //文件名称  后续需要考虑文件冲突的情况  避免同时写入的发送。
			 ImageUtils.decodeBase64ToImage(sendImageInfo,path, imgName);
		 
			 /*上传本地文件到百度OCR进行解析*/
			 String post_file = HttpPostUploadUtil.httppostimg(path+imgName);
			 System.out.println("++++++++++++++++post_file+++++in----"+post_file);
			 
			 /*解析返回结果*/
			 returnText = baiduocrfileall(post_file);
		      
		 } catch (IOException e) {  
		     e.printStackTrace();  
		 }  
		 return returnText;
	  }    
	  
	  /**
	   * baiduocrurl
	   * 
	   * @param imageUrl 图片URL地址
	   *          URLEncoder.encode(sendImageInfo);   转码
	   * @return 返回指定图片的所有文字内容信息，每段之间用“\n”分隔
	   */ 
	  public static String baiduocrAPiurl(String imageUrl) {
	    
		 String returnText = "";
		 /*首先读取url文件转存为本地文件，再将图片文件发送到百度OCR进行文字识别文件，最后返回识别的文字内容*/
		 try {
			 
			 String accessToken = baiduApiInfo.baidugettokenImage();
			  
			 URL url = new URL(imageUrl); 
			 String sendImageInfo= ""; 
			 sendImageInfo = ImageUtils.encodeImgageToBase64(url);  
 
//     		  dataManageInfo.sysDebugLog("baiduApiInfo","300","sendImageInfo",sendImageInfo);
			 //dataManageInfo.sysDebugLog("baiduApiInfo","301","imageUrl",imageUrl);
	/*		 
	try {
        Thread.sleep(1000);
    } catch (InterruptedException e) {
        e.printStackTrace(); 
    }  部分图片无法获取 考虑进行休眠处理 但又发现还是无法解决问题 估计是微信系统问题
    */
	//System.out.println("++++++++++++++++sendImageInfo+++++in-2---"); 	
	  		
	 		 sendImageInfo=sendImageInfo.replaceAll("[\\t\\n\\r]", "");
			 String sendUrl = "https://aip.baidubce.com/rest/2.0/ocr/v1/general";
			 Map params = new HashMap(); 
			 params.put("access_token", accessToken);
			 params.put("image", sendImageInfo);
			  
			 /*发送请求获取文字识别*/
			 String translate = HttpRequestProxy.doPost(sendUrl, params, recEncoding);
//			 System.out.println("++++++++++++++++translate+++++in----"+translate);
// 		 	 dataManageInfo.sysDebugLog("baiduApiInfo","317","translate",translate);
			
			/*解析返回结果*/
			JSONObject object = null;
			object = JSONObject.fromObject(translate);
				
			returnText = object.get("words_result").toString();
	 		//dataManageInfo.sysDebugLog("baiduApiInfo","326","returnText",returnText);
			
			JSONArray jsonArray = JSONArray.fromObject(returnText);  //将字符串转换为JSON数组
	        //System.out.println("testGroupby jsonArray-->" + jsonArray.toString());
	 
	         String ocrinfo = "";
			 String ocrinfosingle = "";
			 
			 int iSize = jsonArray.size();
			 for (int i = 0; i < iSize; i++) {   //循环获取识别的信息
				  JSONObject jsonObj = jsonArray.getJSONObject(i);
					 	
				  ocrinfosingle = jsonObj.get("words").toString();
				  //System.out.println("++++++++++++++++ocrinfosingle+++++in----"+ocrinfosingle);
				  ocrinfo += ocrinfosingle + " ";
			 } 
			 //System.out.println("++++++++++++++++ocrinfo+++++in----"+ocrinfo);
				  
//		 	  dataManageInfo.sysDebugLog("baiduApiInfo","343","ocrinfo",ocrinfo);
			   
			 returnText = ocrinfo;
		      
		 } catch (Exception e) {  
		     e.printStackTrace();  
		 }  
		 return returnText;
	  }    
	   
	  /**
	   * baiduocrurl
	   * 
	   * @param imageUrl 图片URL地址
	   *          URLEncoder.encode(sendImageInfo);   转码
	   * @return 返回指定图片的所有文字内容信息，每段之间用“\n”分隔
	   */ 
	  public static String baiduocrAPiBase64(String sendImageInfo) {
	    
		 String returnText = "";
		 /*首先读取url文件转存为本地文件，再将图片文件发送到百度OCR进行文字识别文件，最后返回识别的文字内容*/
		 try {  
			  
			  String accessToken = baiduApiInfo.baidugettokenImage();
//     		  dataManageInfo.sysDebugLog("baiduocrAPiBase64","378","sendImageInfo",sendImageInfo); 
	/*		 
	try {
        Thread.sleep(1000);
    } catch (InterruptedException e) {
        e.printStackTrace(); 
    }  部分图片无法获取 考虑进行休眠处理 但又发现还是无法解决问题 估计是微信系统问题
    */
	//System.out.println("++++++++++++++++sendImageInfo+++++in-2---"); 	
	  		
	 		 sendImageInfo=sendImageInfo.replaceAll("[\\t\\n\\r]", "");
			 String sendUrl = "https://aip.baidubce.com/rest/2.0/ocr/v1/general";
			 Map params = new HashMap(); 
			 params.put("access_token", accessToken);
			 params.put("image", sendImageInfo);
			  
			 /*发送请求获取文字识别*/
			 String translate = HttpRequestProxy.doPost(sendUrl, params, recEncoding);
//			 System.out.println("++++++++++++++++translate+++++in----"+translate);
// 		 	 dataManageInfo.sysDebugLog("baiduApiInfo","317","translate",translate);
			
			/*解析返回结果*/
			JSONObject object = null;
			object = JSONObject.fromObject(translate);
				
			returnText = object.get("words_result").toString();
	 		//dataManageInfo.sysDebugLog("baiduApiInfo","326","returnText",returnText);
			
			JSONArray jsonArray = JSONArray.fromObject(returnText);  //将字符串转换为JSON数组
	        //System.out.println("testGroupby jsonArray-->" + jsonArray.toString());
	 
	         String ocrinfo = "";
			 String ocrinfosingle = "";
			 String addSeat = " ";
			 
			 long definLower = 0;
			 long objTop = 0;
			 long objHeight = 0;
			 int iSize = jsonArray.size();
			 for (int i = 0; i < iSize; i++) {   //循环获取识别的信息
				  JSONObject jsonObj = jsonArray.getJSONObject(i);
					 	 
				  ocrinfosingle = jsonObj.get("words").toString();
				  //调整位置结构
				  objTop = Long.parseLong(JSONObject.fromObject(jsonObj.get("location").toString()).get("top").toString());
				  objHeight = Long.parseLong(JSONObject.fromObject(jsonObj.get("location").toString()).get("height").toString());
				  if(definLower<=0){
					  definLower = objTop + (objHeight/2);
				  } 
				  
				  if(objTop>definLower){
                       //高于之前的虚高底，增加换行标示，并重设虚高的判断方式		
					  definLower = objTop + (objHeight/2);
					  addSeat = "↓";
				  }else{
					  //增加空格区分间隔
					  addSeat = "    ";
				  }
				  //System.out.println("++++++++++++++++ocrinfosingle+++++in----"+ocrinfosingle);
				  if(ocrinfo.length()>0){
					  ocrinfo += addSeat + ocrinfosingle ;
				  }else{
					  ocrinfo = ocrinfosingle ;
				  }
			 } 
			 //System.out.println("++++++++++++++++ocrinfo+++++in----"+ocrinfo);
				  
//		 	  dataManageInfo.sysDebugLog("baiduApiInfo","343","ocrinfo",ocrinfo);
			   
			 returnText = ocrinfo;
		      
		 } catch (Exception e) {  
		     e.printStackTrace();  
		 }  
		 return returnText;
	  }    
	  
	  /**
	   * baidugettoken
	   * 
	   * @param 
	   *        
	   * @return 返回 百度语音合成 access_token 
	   */ 
	  public static String baidugettoken() {
	    
		 String returnText = "";
		 String tokenurl = "";
		 
		 tokenurl  = "https://openapi.baidu.com/oauth/2.0/token?";
		 tokenurl += "grant_type=client_credentials&";
		 tokenurl += "client_id="+"TIZFxZRmyPmIZgEb5nhx1mCf";
		 tokenurl += "client_secret="+"GTmvsMVAhNKRaPz4OGT0dOu5qTNZGOgu";
		 
		 String url = "https://openapi.baidu.com/oauth/2.0/token";
		 
		 Map params = new HashMap(); 
		 params.put("grant_type", "client_credentials");
		 params.put("client_id", "TIZFxZRmyPmIZgEb5nhx1mCf");
		 params.put("client_secret", "GTmvsMVAhNKRaPz4OGT0dOu5qTNZGOgu");
		 

		 try {  
			 /*发送请求获取access_token*/
			 String translate = HttpRequestProxy.doPost(url, params, recEncoding);
			 System.out.println("++++++++++++++++baidugettoken+++++in----"+translate);
			 
			 /*解析返回结果*/
			 JSONObject object = null;
			 object = JSONObject.fromObject(translate);
			
			 returnText = object.get("access_token").toString();
			 System.out.println("++++++++++++++++returnText+++++in----"+returnText);
		      
		 } catch (Exception e) {  
		     e.printStackTrace();  
		 }  
		 return returnText;
	  }    
	  
	  /**
	   * baidugettokenImage
	   * 
	   * @param 
	   *        
	   * @return 返回 百度语音合成 access_token 文字识别token
	   */ 
	  public static String baidugettokenImage() {
	    
		 String returnText = "";
		 String tokenurl = "";
		 
		 tokenurl  = "https://openapi.baidu.com/oauth/2.0/token?";
		 tokenurl += "grant_type=client_credentials&";
		 tokenurl += "client_id="+"TIZFxZRmyPmIZgEb5nhx1mCf";
		 tokenurl += "client_secret="+"GTmvsMVAhNKRaPz4OGT0dOu5qTNZGOgu";
		 
		 String url = "https://openapi.baidu.com/oauth/2.0/token";
		        url = "https://aip.baidubce.com/oauth/2.0/token";
		 
		 Map params = new HashMap(); 
		 params.put("grant_type", "client_credentials");
		 params.put("client_id", "PLBT4EL2374fL33sNPtaCZlb");
		 params.put("client_secret", "wL3k2CLUN2a9lKXZe6gDUsVDbpjjd9Ro");
		 

		 try {  
			 /*发送请求获取access_token*/
			 String translate = HttpRequestProxy.doPost(url, params, recEncoding);
			 System.out.println("++++++++++++++++baidugettokenImage+++++in----"+translate);
			 
			 /*解析返回结果*/
			 JSONObject object = null;
			 object = JSONObject.fromObject(translate);
			
			 returnText = object.get("access_token").toString();
			 System.out.println("++++++++++++++++returnText+++++in----"+returnText);
		      
		 } catch (Exception e) {  
		     e.printStackTrace();  
		 }  
		 return returnText;
	  }    
	  
	  /**
	   * wxsmallgetopenid
	   * 
	   * @param 
	   *        
	   * @return 返回 百度语音合成 access_token 
	   */ 
	  public static String wxsmallgetopenid(String appid ,String secret ,String js_code) {
	    
		 String returnText = ""; 
		 String url = "https://api.weixin.qq.com/sns/jscode2session";
		  
		 Map params = new HashMap(); 
		 params.put("appid",appid);
		 params.put("secret",secret);
		 params.put("js_code", js_code);
		 params.put("grant_type", "authorization_code");
		   
		 try {  
			 /*发送请求获取access_token*/
			 String res = HttpRequestProxy.doGet(url, params, recEncoding);
			 System.out.println("++++++++++++++++res+++++in----"+res);
			 
			 /*解析返回结果*/
			 JSONObject object = null;
			 object = JSONObject.fromObject(res);
			
			 returnText = object.get("openid").toString();
			 System.out.println("++++++++++++++++returnText+++++in----"+returnText);
		      
		 } catch (Exception e) {  
		     e.printStackTrace();  
		 }  
		 return returnText;
	  }    
}