package com.atguigu.springboot.serv;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import org.json.JSONObject;

import sun.misc.BASE64Decoder;

import com.baidu.aip.face.*;

public class baiduApiFaceCheck {

	// 设置APPID/AK/SK
	public static final String APP_ID = "9673426";
	public static final String API_KEY = "QGAXib3y9BbsVLlMhMitnGxV";
	public static final String SECRET_KEY = "b4iu41KvvVhOYLkxNYg7v3uhGXfcrRIb";

	// private static dataManageInfoBaidusub3 dataManageInfo = new
	// dataManageInfoBaidusub3();

	// private static String recEncoding = "UTF-8";
	/**
	 * baiduOCR
	 * 
	 * @param imageUrl
	 *            图片URL地址
	 * 
	 * @return 返回指定图片的首个文字内容信息
	 */
	public static String baiduocr(String imageUrl) {

		String returnText = "";
		try {
			URL url = new URL(imageUrl);
			String sendImageInfo = "";
			sendImageInfo = ImageUtils.encodeImgageToBase64(url);
			BASE64Decoder decoder = new BASE64Decoder();
			byte[] decoderBytes = decoder.decodeBuffer(sendImageInfo);

			// 初始化一个FaceClient
			AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);

			// 可选：设置网络连接参数
			client.setConnectionTimeoutInMillis(2000);
			client.setSocketTimeoutInMillis(60000);
 
			// 自定义参数定义
			HashMap<String, String> options = new HashMap<String, String>();
			options.put("max_face_num", "3");
			options.put("face_fields", "expression,age,beauty");

			// 调用API
			JSONObject res = client.detect(decoderBytes, options);
			System.out.println(res.toString());

		} catch (IOException e) {
			e.printStackTrace();
		}

		return returnText;
	}

	/**
	 * apifacedetect 人脸检测接口
	 * 
	 * @param imageUrl
	 *            图片URL地址 path 图片存放路径
	 * @return 返回百度API的 人脸检测 返回值
	 */
	public static String apifacedetect(String imageUrl, String path) {

		String returnText = "";
		try {
			/* 将url转为本地文件 */
			URL url = new URL(imageUrl);
			String sendImageInfo = "";
			sendImageInfo = ImageUtils.encodeImgageToBase64(url);

			String imgName = "FK20170522DT0001.jpg"; // 文件名称 后续需要考虑文件冲突的情况
														// 避免同时写入的发送。
			// 将Base64位编码的图片进行解码，并保存到指定目录

			ImageUtils.decodeBase64ToImage(sendImageInfo, path, imgName);

			/* 传入本地本地文件到百度API进行解析 */
			String imagePath = path + imgName;
			AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);

			// 可选：设置网络连接参数
			client.setConnectionTimeoutInMillis(2000);
			client.setSocketTimeoutInMillis(60000);

			// 自定义参数定义
			HashMap<String, String> options = new HashMap<String, String>();
			options.put("max_face_num", "12");
			options.put("face_fields", "gender,age,beauty");

			// 调用API
			JSONObject res = client.detect(imagePath, options);
			System.out.println(res.toString());

			returnText = res.toString();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return returnText;
	}

}