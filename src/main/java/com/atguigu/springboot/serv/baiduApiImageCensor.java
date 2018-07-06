package com.atguigu.springboot.serv;
 
import net.sf.json.JSONArray;

import org.json.JSONException;
import org.json.JSONObject;

import com.baidu.aip.imagecensor.AipImageCensor;
import com.baidu.aip.imagecensor.EImgType;
 

public class baiduApiImageCensor {

	// 设置APPID/AK/SK
	public static final String APP_ID = "10794888";
	public static final String API_KEY = "ToBr7pwbQRE7imP0QrBHy2Cg";
	public static final String SECRET_KEY = "AGuGWPzHjpwBxa1FC4mp0PpxzR4LdBF6";
   
	private static volatile AipImageCensor client = null;  
    public static AipImageCensor getClient() {  
        if (client == null) {  
            synchronized (AipImageCensor.class) {  
                if (client == null) {  
                	client = new AipImageCensor(APP_ID, API_KEY, SECRET_KEY);
                }  
            }  
        }  
        return client;  
    }  
    
	/**
	 * imageCensor
	 * 
	 * @param imageUrl
	 *            图片URL地址
	 * 
	 * @return 返回指定图片的图像审核结果信息
	 */
	public static String imageCensor(String imageUrl) {

		String returnText = "";

		// System.out.println("+++++++++++++++++++++in----");

		// 初始化一个AipImageCensor
		//AipImageCensor client = new AipImageCensor(APP_ID, API_KEY, SECRET_KEY);
		getClient();
		// 可选：设置网络连接参数
		//client.setConnectionTimeoutInMillis(2000);
		//client.setSocketTimeoutInMillis(60000);
		// 可选：设置代理服务器地址, http和socket二选一，或者均不设置
		// client.setHttpProxy("proxy_host", proxy_port); // 设置http代理
		// client.setSocketProxy("proxy_host", proxy_port); // 设置socket代理
		// 调用接口
		/*
		// 参数为本地图片路径
	    String path = "test.jpg";
	    JSONObject response = client.imageCensorUserDefined(path, EimgType.FILE, null);
	    System.out.println(response.toString());

	    // 参数为url
	    String url = "http://testurl";
	    response = client.imageCensorUserDefined(url, EimgType.URL, null);
	    System.out.println(response.toString());

	    // 参数为本地图片文件二进制数组
	    byte[] file = readImageFile(imagePath);
	    response = client.imageCensorUserDefined(file, null);
	    System.out.println(response.toString());
	    */
		 
		//组合审核
		//JSONObject response = client.imageCensorComb(imageUrl, EImgType.URL, Arrays.asList("antiporn", "terror", "disgust"), null);
		//返回内容详细，取值方式需要重新调整	    
		//图像审核
		JSONObject response = client.imageCensorUserDefined(imageUrl, EImgType.URL, null);
		System.out.println(response.toString());
	    
	    /*解析返回结果*/
		try {
			returnText = response.get("conclusion").toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("++++++++++++++++returnText+++++in----"+returnText);
		
		if("不合规".equals(returnText)){
			try {
				returnText = response.get("data").toString();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 		JSONArray jsonArray = JSONArray.fromObject(returnText);  //将字符串转换为JSON数组
	        //System.out.println("testGroupby jsonArray-->" + jsonArray.toString()); 
	         String msginfo = ""; 
			 int iSize = jsonArray.size();
			 for (int i = 0; i < iSize; i++) {   //循环获取识别的信息
				  net.sf.json.JSONObject jsonObj = jsonArray.getJSONObject(i);  
				  msginfo += jsonObj.get("msg").toString(); 
			 } 
			 returnText = msginfo;
			 System.out.println("++++++++++++++++msg+++ ----"+returnText);
			
		}

		return returnText;
	}

}