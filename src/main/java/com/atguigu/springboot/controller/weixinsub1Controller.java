package com.atguigu.springboot.controller;

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
import com.atguigu.springboot.serv.PubCheckServ;
import com.atguigu.springboot.serv.PubInfoCheckServ;
import com.atguigu.springboot.serv.baiduApiFaceCheck;

import net.sf.json.JSONObject;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class weixinsub1Controller {

    @Autowired
    RuleCheckSingleRepository ruleCheckSingleRepository;
    
    //private static baiduApiFaceCheck baiduApiFKInfo = new baiduApiFaceCheck();  //百度API相关处理--人脸识别
    
    @GetMapping("/wxsub1")
    public String wxsub1test(){
    	
    	//String checkType = "cust_name_include"; 
        String st_image_file_path = "";
        String image_text = "";
        String imageUrl = "http://mycos-1253822284.coscd.myqcloud.com/testfile/12345.jpg";
    	try { 
	    	st_image_file_path = this.getClass().getResource("/").getPath()+"file/";  //目录包含中文将导致(系统找不到指定的路径。)
	    	//st_image_file_path = "D:/";  //本机测试用
	    	System.out.println(st_image_file_path);
	    	//image_text = baiduApiFaceCheck.apifacedetect(imageUrl, st_image_file_path);  //将文件存入本地，然后使用本地图片路径调用方式
	    	
	    	System.out.println(image_text);
	    	
            image_text = baiduApiFaceCheck.baiduocr(imageUrl);   //将文件转换为Base64 再转换为图片二进制数组调用方式
	    	
	    	System.out.println(image_text);
	    	
	    	
	    } catch (Exception e) {
	        e.printStackTrace();
	    } 
    	
    	  
    	return "ok im goin wxsub1test";
    }
     
    
}
