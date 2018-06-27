package com.atguigu.springboot.controller;

import com.atguigu.springboot.entity.User;
import com.atguigu.springboot.repository.UserRepository;

import net.sf.json.JSONObject;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") Integer id){
        User user = userRepository.findOne(id);
        return user;
    }

    @GetMapping("/user")
    public User insertUser(User user){
        User save = userRepository.save(user);
        return save;
    }
    
    @GetMapping("/test")
    public String retest(){
    	
    	//测试正则表达式的字符匹配
    	String check_infoString = "";
    	check_infoString ="[{'规则':'1','稽核值':'财务部'},{'规则':'2','稽核值':'测试卡'},{'规则':'3','稽核值':'初级中学'}]";
    	
    	String check_cardadd = "";
    	check_cardadd = "重庆市沙坪坝区虎财务部溪镇伍家沟村狮子湾组13号初级中学附1号";
    	
    	String check_errinfoString = "";
    	
		JSONArray jsonParaInfo = JSONArray.fromObject(check_infoString);
		JSONObject reJson = null;

		for (int i = 0; i < jsonParaInfo.size(); i++) {
			reJson = JSONObject.fromObject(jsonParaInfo.get(i));
			System.out.println(reJson.toString()); 
			//System.out.println(reJson.get("稽核值").toString());
			String check_val = reJson.get("稽核值").toString();
			System.out.println(check_val);
			check_val = ".*("+check_val+").*";
			System.out.println(check_val);
			
			if(check_cardadd.matches(check_val)){
				check_errinfoString += reJson.get("规则").toString() + "|";
			}
			
		}
		
		System.out.println("check_errinfoString-->"+check_errinfoString);
    	
     
    	
    	
    	
    	return "ok im goin 3311";
    }
    

}
