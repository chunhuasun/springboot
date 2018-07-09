package com.atguigu.springboot.servsub6;

import java.net.UnknownHostException;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.atguigu.springboot.serv.baiduApiImageCensor;
import com.atguigu.springboot.serv.baiduApiInfo;
import com.atguigu.springboot.servsub4.ImageDataManageInfoSub4;

@Component
public class SpringAsyncSub6 {
	
	private JSONObject jsonResult = new JSONObject(); 
	
	@Autowired
	private ImageDataManageInfoSub6 imageDataManageInfo;
	
	@Async
    public void imageCensor(String cos_url) throws InterruptedException {
        
		System.out.println("imageCheck begin");
        
		String image_text = baiduApiImageCensor.imageCensor(cos_url);
		if(!"合规".equals(image_text)){
			jsonResult.put("opertype", "1");
	    	jsonResult.put("operinfo", image_text);
   			image_text = jsonResult.toString();
        }
		try {
			imageDataManageInfo.changetxCOSData(cos_url, "imageCensor", image_text);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("imageCheck end .."+image_text);
    }
	
	@Async
    public void imagePick(String sendImageInfo,String cos_url) throws InterruptedException {
        
		System.out.println("imagePick begin");
         
		String image_text = baiduApiInfo.baiduocrAPiBase64(sendImageInfo);
		try {
			imageDataManageInfo.changetxCOSData(cos_url, "imagePick", image_text);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		System.out.println("imagePick end .."+image_text);
    }
	

}
