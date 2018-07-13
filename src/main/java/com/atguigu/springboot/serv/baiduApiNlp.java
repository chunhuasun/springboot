package com.atguigu.springboot.serv;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import org.json.JSONObject;

import sun.misc.BASE64Decoder;

import com.baidu.aip.face.*;
import com.baidu.aip.nlp.AipNlp;

public class baiduApiNlp {

	// 设置APPID/AK/SK
	public static final String APP_ID = "11527022";
	public static final String API_KEY = "50iGjGGbqw9cycH5sguIHOtM";
	public static final String SECRET_KEY = "Zj3kgiNCY5UAvhbMICChwAAzkCwLOKyx";
	
	private static String recEncoding = "UTF-8";
	 
	/**
	 * apiNlp 自然语言处理接口-- 词法分析
	 * 
	 * @param imageUrl
	 *            图片URL地址 path 图片存放路径
	 * @return 返回百度API的 人脸检测 返回值
	 */
	public static String apiNlp(String nlpWord) {

		String returnText = "";
		try {
			// 初始化一个AipNlp
	        AipNlp client = new AipNlp(APP_ID, API_KEY, SECRET_KEY);
			
			// 可选：设置网络连接参数
			client.setConnectionTimeoutInMillis(2000);
			client.setSocketTimeoutInMillis(60000);

			// 调用接口
			//nlpWord = "百度是一家高科技公司";
	        JSONObject res = client.lexer(nlpWord, null);
			System.out.println(res.toString());

			returnText = res.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnText;
	}
	
	/**
	 * apiNlp 自然语言处理接口-- 情感倾向分析
	 * 
	 * @param imageUrl
	 *            图片URL地址 path 图片存放路径
	 * @return 返回百度API的 人脸检测 返回值
	 */
	public static String apiNlpSentiment(String nlpWord) {

		String returnText = "";
		try {
			// 初始化一个AipNlp
	        AipNlp client = new AipNlp(APP_ID, API_KEY, SECRET_KEY);
			
			// 可选：设置网络连接参数
			client.setConnectionTimeoutInMillis(2000);
			client.setSocketTimeoutInMillis(60000);

			// 传入可选参数调用接口
		    HashMap<String, Object> options = new HashMap<String, Object>();

		    // 情感倾向分析
		    JSONObject res = client.sentimentClassify(nlpWord, options);
		    System.out.println(res.toString());

			returnText = res.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnText;
	}

}