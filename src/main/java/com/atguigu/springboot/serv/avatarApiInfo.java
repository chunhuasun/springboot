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

public class avatarApiInfo {

	// 配置您申请的KEY
	public static final String API_KEY = "ae9102a7ec4c4c89b18c392110696c67";
	private static String recEncoding = "UTF-8";

	/**
	 * avatarQueryTangshi 阿凡达数据网提供的唐诗宋词API接口
	 * 1、根据关键字搜索唐诗宋词
	 * @param qryWord
	 *            查询词语
	 * @return 返回指定图片的所有文字内容信息，每段之间用“\n”分隔
	 */
	public static String avatarQueryTangshi(String qryWord) {

		String returnText = "";
		/* 首先读取url文件转存为本地文件，再将图片文件发送到百度OCR进行文字识别文件，最后返回识别的文字内容 */
		try {

			String sendUrl = "http://api.avatardata.cn/TangShiSongCi/Search";// 请求接口地址

			Map params = new HashMap();// 请求参数
			params.put("keyWord", qryWord);// 填写需要查询的汉字，UTF8 urlencode编码
			params.put("key", API_KEY);// 应用APPKEY(应用详细页查询)

			/* 1、根据关键字搜索唐诗宋词 */
			String translate = HttpRequestProxy.doPost(sendUrl, params,
					recEncoding);
			/*剔除\r\n*/
			translate = translate.replaceAll("\r|\n", "");
			returnText = translate;

			/*
			 * 解析返回结果 JSONObject object = null; object =
			 * JSONObject.fromObject(translate); if(object.getInt("total")!=0){
			 * returnText = object.get("error_code").toString(); }else{
			 * returnText = object.get("result").toString(); }
			 */
			System.out.println("avatarQueryTangshi returnText-->" + returnText);
			/*
			 * JSONArray jsonArray = JSONArray.fromObject(returnText);
			 * //将字符串转换为JSON数组 //System.out.println("testGroupby jsonArray-->" +
			 * jsonArray.toString());
			 * 
			 * //后续的结果解析还未处理---------------------------- String ocrinfo = "";
			 * String ocrinfosingle = "";
			 * 
			 * int iSize = jsonArray.size(); for (int i = 0; i < iSize; i++) {
			 * //循环获取识别的信息 JSONObject jsonObj = jsonArray.getJSONObject(i);
			 * 
			 * ocrinfosingle = jsonObj.get("words").toString();
			 * //System.out.println
			 * ("++++++++++++++++ocrinfosingle+++++in----"+ocrinfosingle);
			 * ocrinfo += ocrinfosingle + " "; }
			 * 
			 * 
			 * returnText = ocrinfo;
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnText;
	}

	/**
	 * avatarQueryId 阿凡达数据网提供的唐诗宋词API接口
	 * 2、单个唐诗宋词详细解释
	 * @param qryId
	 *            查询词语
	 * @return 返回指定图片的所有文字内容信息，每段之间用“\n”分隔
	 */
	public static String avatarQueryId(String qryId) {

		String returnText = "";
		/* 首先读取url文件转存为本地文件，再将图片文件发送到百度OCR进行文字识别文件，最后返回识别的文字内容 */
		try {

			String sendUrl = "http://api.avatardata.cn/TangShiSongCi/LookUp";// 请求接口地址

			Map params = new HashMap();// 请求参数
			params.put("id", qryId);// 填写需要查询的汉字，UTF8 urlencode编码
			params.put("key", API_KEY);// 应用APPKEY(应用详细页查询)

			/* 1、根据关键字搜索唐诗宋词 */
			String translate = HttpRequestProxy.doPost(sendUrl, params,
					recEncoding);
			/*剔除\r\n*/
			translate = translate.replaceAll("\r|\n", "");
			returnText = translate;

			/*
			 * 解析返回结果 JSONObject object = null; object =
			 * JSONObject.fromObject(translate); if(object.getInt("total")!=0){
			 * returnText = object.get("error_code").toString(); }else{
			 * returnText = object.get("result").toString(); }
			 */
			System.out.println("avatarQueryId returnText-->" + returnText);
			/*
			 * JSONArray jsonArray = JSONArray.fromObject(returnText);
			 * //将字符串转换为JSON数组 //System.out.println("testGroupby jsonArray-->" +
			 * jsonArray.toString());
			 * 
			 * //后续的结果解析还未处理---------------------------- String ocrinfo = "";
			 * String ocrinfosingle = "";
			 * 
			 * int iSize = jsonArray.size(); for (int i = 0; i < iSize; i++) {
			 * //循环获取识别的信息 JSONObject jsonObj = jsonArray.getJSONObject(i);
			 * 
			 * ocrinfosingle = jsonObj.get("words").toString();
			 * //System.out.println
			 * ("++++++++++++++++ocrinfosingle+++++in----"+ocrinfosingle);
			 * ocrinfo += ocrinfosingle + " "; }
			 * 
			 * 
			 * returnText = ocrinfo;
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnText;
	}
	
	
	/**
	 * avatarQueryRandom 阿凡达数据网提供的唐诗宋词API接口
	 * 3、随机返回一条
	 * @param qryId
	 *            查询词语
	 * @return 返回指定图片的所有文字内容信息，每段之间用“\n”分隔
	 */
	public static String avatarQueryRandom(String qryId) {

		String returnText = "";
		/* 首先读取url文件转存为本地文件，再将图片文件发送到百度OCR进行文字识别文件，最后返回识别的文字内容 */
		try {

			String sendUrl = "http://api.avatardata.cn/TangShiSongCi/Random";// 请求接口地址

			Map params = new HashMap();// 请求参数
			//params.put("id", qryId);// 填写需要查询的汉字，UTF8 urlencode编码
			params.put("key", API_KEY);// 应用APPKEY(应用详细页查询)

			/* 1、根据关键字搜索唐诗宋词 */
			String translate = HttpRequestProxy.doPost(sendUrl, params,
					recEncoding);
			/*剔除\r\n*/
			translate = translate.replaceAll("\r|\n", "");
			returnText = translate;

			/*
			 * 解析返回结果 JSONObject object = null; object =
			 * JSONObject.fromObject(translate); if(object.getInt("total")!=0){
			 * returnText = object.get("error_code").toString(); }else{
			 * returnText = object.get("result").toString(); }
			 */
			System.out.println("avatarQueryId returnText-->" + returnText);
			/*
			 * JSONArray jsonArray = JSONArray.fromObject(returnText);
			 * //将字符串转换为JSON数组 //System.out.println("testGroupby jsonArray-->" +
			 * jsonArray.toString());
			 * 
			 * //后续的结果解析还未处理---------------------------- String ocrinfo = "";
			 * String ocrinfosingle = "";
			 * 
			 * int iSize = jsonArray.size(); for (int i = 0; i < iSize; i++) {
			 * //循环获取识别的信息 JSONObject jsonObj = jsonArray.getJSONObject(i);
			 * 
			 * ocrinfosingle = jsonObj.get("words").toString();
			 * //System.out.println
			 * ("++++++++++++++++ocrinfosingle+++++in----"+ocrinfosingle);
			 * ocrinfo += ocrinfosingle + " "; }
			 * 
			 * 
			 * returnText = ocrinfo;
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnText;
	}
	
	/**
	 * avatarChengYuSearch 阿凡达数据网提供的唐诗宋词API接口
	 * 3、随机返回一条
	 * 根据关键字搜索成语
	 * @param qryId
	 *            查询词语
	 * @return 返回
	 */
	public static String avatarChengYuSearch(String qryWord) {

		String returnText = "";
		/* 首先读取url文件转存为本地文件，再将图片文件发送到百度OCR进行文字识别文件，最后返回识别的文字内容 */
		try {

			String sendUrl = "http://api.avatardata.cn/ChengYu/Search";// 请求接口地址

			Map params = new HashMap();// 请求参数
			//params.put("id", qryId);// 填写需要查询的汉字，UTF8 urlencode编码
			params.put("key", "9efa642e50bc4d8a9db5d361a19b699e");// 应用APPKEY(应用详细页查询)
			params.put("keyWord",qryWord);//填写需要查询的汉字，UTF8 urlencode编码

			  
			/* 1、根据关键字搜索唐诗宋词 */
			String translate = HttpRequestProxy.doPost(sendUrl, params,
					recEncoding);
			/*剔除\r\n*/
			//translate = translate.replaceAll("\r|\n", "");
			returnText = translate;
 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnText;
	}
	
}