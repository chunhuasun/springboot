package com.atguigu.springboot.controller;

import java.io.IOException;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.atguigu.springboot.entity.CheckAddress;
import com.atguigu.springboot.entity.InfoCheckCard;
import com.atguigu.springboot.entity.InfoCheckCardResult;
import com.atguigu.springboot.entity.RuleCheckEngine;
import com.atguigu.springboot.entity.RuleCheckSingle;
import com.atguigu.springboot.entity.User;
import com.atguigu.springboot.func.PubInfoCheckFunc;
import com.atguigu.springboot.func.PubInfoCheckInvoke;
import com.atguigu.springboot.func.ShowTimeFunc;
import com.atguigu.springboot.repository.CheckAddressRepository;
import com.atguigu.springboot.repository.InfoCheckCardRepository;
import com.atguigu.springboot.repository.InfoCheckCardResultRepository;
import com.atguigu.springboot.repository.RuleCheckEngineRepository;
import com.atguigu.springboot.repository.RuleCheckSingleRepository;
import com.atguigu.springboot.repository.UserRepository;
import com.atguigu.springboot.repository.WxSmallUserRepository;
import com.atguigu.springboot.serv.PubCheckServ;
import com.atguigu.springboot.serv.PubInfoCheckServ;
import com.atguigu.springboot.serv.baiduApiFaceCheck;
import com.atguigu.springboot.serv.baiduApiImageCensor;
import com.atguigu.springboot.serv.baiduApiInfo;

import net.sf.json.JSONObject;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import sun.misc.BASE64Encoder;

import com.atguigu.springboot.serv.tenxunApiCos;
import com.atguigu.springboot.serv.sub6.DataManageInfoSub6;
import com.atguigu.springboot.serv.sub6.SpringAsyncSub6;

//@RestController
@RequestMapping("/sub6")   //微信小程序  super画 画展  使用时候修改为  取消 @RestController 的屏蔽
public class weixinsub6Controller {
    
	public static String st_image_file_path ="";
	public static String st_respMessage ="";
	 
	@Autowired
	private DataManageInfoSub6 dataManageInfo;
	
	@Autowired
	private SpringAsyncSub6 springAsync;
	 
	// ---微信URL信息访问--返回小程序训练计划相关信息------------
	@RequestMapping(value="/weixinsub6GetOpenId", method= RequestMethod.POST)
    @ResponseBody
    public String wxGetOpenId(String requestInfo) throws UnknownHostException, NoSuchAlgorithmException {
      String response_info = "";
      try {
    	  
    	  System.out.println("requestInfo---"+requestInfo);
    	  
    	  //System.out.println("OpenId---"+wxSmallUserRepository.count());
    	  
    	  JSONObject object = JSONObject.fromObject(requestInfo);
    	  String appid = "wxe2c4e699f98185bf";
  		  String secret = "26115dae571e3128f4c696f4863fde10";
  		  String js_code = object.get("js_code").toString();
  		  // 获取用户 OpenId
  		  String OpenId = baiduApiInfo.wxsmallgetopenid(appid, secret, js_code);
  		  
  		  //修改流程请求时候建立DB连接 ，而不是在执行方式是建立连接
    	  st_image_file_path = this.getClass().getResource("/").getPath()+"file/";
    	  response_info = dataManageInfo.savewxsmallopenid(requestInfo,st_image_file_path,OpenId);
          st_respMessage = response_info;
      } catch (Exception e) {
          e.printStackTrace();
      }
      return response_info;
    }
    
	// ---微信URL信息访问--返回小程序训练计划相关信息------------
	@RequestMapping(value="/weixinsub6TrainPlan", method= RequestMethod.POST)
    @ResponseBody
    public String wxTrainPlanInfo(String requestInfo) throws UnknownHostException, NoSuchAlgorithmException {
      String response_info = "";
      JSONObject object = null;
      String reqOpenId = "";
      String funOpType = "";
      String EndFlag = "";
      try {
    	   
    	  //判断用户是否授权，未授权的用户不能进行后续操作，记录用户的操作行为
    	  response_info = dataManageInfo.loginTrainUserServInfo(requestInfo);
    	   
    	  object = JSONObject.fromObject(response_info);
    	  EndFlag = object.get("EndFlag").toString();
   		  if("1".equals(EndFlag)){
   			response_info = object.get("ServInfo").toString();
   			return response_info;
   		  }
    	  
    	  object = JSONObject.fromObject(requestInfo);
    	  reqOpenId = object.get("reqOpenId").toString();
    	  
    	  String getType = object.get("getType").toString();
    	  if("UserServLimit".equals(getType)){
    		  funOpType = object.get("funOpType").toString();
    	  }
    	  response_info = dataManageInfo.getTrainUserServLimit(reqOpenId,funOpType);
    	     
    	  
    	  object = JSONObject.fromObject(response_info);
    	  EndFlag = object.get("EndFlag").toString();
   		  if("1".equals(EndFlag)){
   			response_info = object.get("ServInfo").toString();
   		  }else{
   			response_info = dataManageInfo.getTrainPlanInfo(requestInfo);
   		  } 
   		  st_respMessage = response_info;
      } catch (IOException e) {
          e.printStackTrace();
      }
      return response_info;
    }
	
	// ---微信URL信息访问--返回小程序训练计划相关信息------------
	@RequestMapping(value="/weixinsub6GetBaiduToken", method= RequestMethod.POST)
    @ResponseBody
    public String wxGetBaiduToken(String requestInfo) throws UnknownHostException, NoSuchAlgorithmException {
      String response_info = "";
      
      JSONObject object = null;
	  object = JSONObject.fromObject(requestInfo);
	  String fromUser = object.get("fromUser").toString();
      try {
    	  //修改流程请求时候建立DB连接 ，而不是在执行方式是建立连接
    	  response_info = dataManageInfo.getAccessTokenInfo(fromUser);
          st_respMessage = response_info; 
      } catch (Exception e) {
          e.printStackTrace();
      }
      return response_info;
    }
	
	/*保存信息 --微信小程序上传文件保存 --直接保存并返回保存的地址信息*/
	@RequestMapping(value="/weixinsub6FileSaveCos",method = RequestMethod.POST)
	@ResponseBody
	public String wxFileSaveCos(@RequestParam("fromUser") String fromUser ,@RequestParam("tcontent") String tcontent ,
			@RequestParam("send_file") MultipartFile file){
		
		  
		String cos_url = "cosUrlInfo";
		String image_text = "wxTenXunCosSend";
		String cos_size = "";
		String serv_limit = "";
		String funOpType = "";
		String sendImageInfo= ""; 
		JSONObject object = null;
		JSONObject jsonResult = new JSONObject(); 
		
		ShowTimeFunc showTimeFunc = new ShowTimeFunc();
		showTimeFunc.setStartTime();
		try {
			//增加权限控制，限制不同等级的用户图片的处理次数
			if("baiduocrAPi".equals(tcontent)||"baiduopusAPi".equals(tcontent)){
	    		funOpType = "imageOcr";
	    	}else{
	    		funOpType = "imageSave";
	    	}
			serv_limit = dataManageInfo.getTrainUserServLimit(fromUser,funOpType);
			System.out.println("180---getTrainUserServLimit:");
			showTimeFunc.setEndTime();
			
			object = JSONObject.fromObject(serv_limit);
	    	String EndFlag = object.get("EndFlag").toString();
	  		    
	   		if("1".equals(EndFlag)){
		    	jsonResult.put("opertype", "1");
		    	jsonResult.put("operinfo", object.get("ServInfo").toString());
	   			image_text = jsonResult.toString();
	   		}else{
	   			jsonResult.put("opertype", "0");
	   			
		    	String sendPathName= "";
		    	String saveFileName= "000001";
		    	
		    	Date now = new Date();
				SimpleDateFormat dayFormatId = new SimpleDateFormat("yyyyMMddHHmmss"); // 可以方便地修改日期格式
				saveFileName = dayFormatId.format(now);
				System.out.println("saveFileName:"+saveFileName);
		    	if (!file.isEmpty()) {
		    	 	sendPathName = file.getOriginalFilename();//
		    	 	saveFileName += sendPathName.substring(sendPathName.length()-6);  //从后向前取6位
		    	 	
		    	 	double length = (double)file.getSize();	
		    	 	cos_size = Math.ceil(length/1024/1024*100)/100+"M";
		    	} 
		    	byte[] sendFileBufer = file.getBytes(); 
		    	
		    	
		    	System.out.println("210---sendFileBufer:");
				showTimeFunc.setEndTime();
				
		    	//1、存储图片
		    	if("baiduocrAPi".equals(tcontent)){
					//只解析图片文字信息不存储图片
	            	saveFileName = "trainPlan/ocrFile/"+saveFileName;
		    	}else if("baiduopusAPi".equals(tcontent)){
					//解析图片文字信息并存储图片信息
	            	saveFileName = "trainPlan/opusFile/"+saveFileName;
		    	}else{
					//先存储上传信息  在线程中处理文件COS存储，存储完成后再修改 数据信息，传入COS文件编号
			    	saveFileName = "trainPlan/uploadFile/"+saveFileName;
		    	}
		    	cos_url = tenxunApiCos.sendFileCosBufer(sendFileBufer,saveFileName);
		    	object=JSONObject.fromObject(cos_url);
				cos_url = object.get("data").toString(); 
				object=JSONObject.fromObject(cos_url);
				cos_url = object.get("access_url").toString();
				
				System.out.println("230---sendFileCosBufer:");
				showTimeFunc.setEndTime();
				
				//2、存储图片地址信息  先存储再异步调用审核 缩短前台的等待时间
			    image_text = dataManageInfo.savetxCOSData(fromUser,tcontent,cos_url,cos_size);
			    System.out.println("239---savetxCOSData:");
				showTimeFunc.setEndTime();
				
			    
		    	//2、对图片进行图像审核   考虑两种方式实现：1、修改权限对高权限人员不审核，2、修改审核的环节，可以在最后的提交环节建立单独的线程去审核，审不过则不展现。
				/*
				image_text = baiduApiImageCensor.imageCensor(cos_url);
				if(!"合规".equals(image_text)){
					jsonResult.put("opertype", "1");
			    	jsonResult.put("operinfo", image_text);
		   			image_text = jsonResult.toString();
		   			return image_text; 
				}
				*/
				springAsync.imageCensor(cos_url);
				System.out.println("242---imageCensor:");
				showTimeFunc.setEndTime();
				
				
				jsonResult.put("cos_url", cos_url);
		    	//3、对图片进行文字识别
				BASE64Encoder encoder = new BASE64Encoder();
				sendImageInfo = encoder.encode(sendFileBufer); 
				/*
				image_text = baiduApiInfo.baiduocrAPiBase64(sendImageInfo);
			    */
				springAsync.imagePick(sendImageInfo,cos_url);
		    	System.out.println("252---baiduocrAPiBase64:");
				showTimeFunc.setEndTime();
				
			    
                jsonResult.put("operinfo", "");
   			    image_text = jsonResult.toString();
	   		}
			st_respMessage = image_text;
	    } catch (Exception e) {
	        image_text = "e error";
	        return image_text; 
	    } 
        return image_text; 
    }  
    
}
