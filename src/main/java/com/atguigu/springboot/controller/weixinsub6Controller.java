package com.atguigu.springboot.controller;

import java.io.IOException;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.atguigu.springboot.serv.DataManageInfo;

@RestController
public class weixinsub6Controller {
    
	public static String st_image_file_path ="";
	public static String st_respMessage ="";
	 
	@Autowired
	private DataManageInfo dataManageInfo;
	 
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
    
}
