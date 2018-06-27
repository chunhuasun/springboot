package com.atguigu.springboot.func;

import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.atguigu.springboot.serv.PubCheckServ;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class PubInfoCheckInvoke {
	
	/*根据类型校验字符串*/
	public static void  checkInvoke(String checkStr) throws Exception {
	
		Class<?> userClass = Class.forName("com.atguigu.springboot.serv.PubCheckServ");
		PubCheckServ checkServ = (PubCheckServ) userClass.newInstance();
		
		 
		Method setStr = userClass.getMethod("setStr",String.class);//得到方法对象,有参的方法需要指定参数类型
		setStr.invoke(checkServ,checkStr);//执行还钱方法，有参传参
        System.out.println("------------------------分割线------------------------");
        
        Method checkChinese = userClass.getMethod("checkChinese");
        checkChinese.invoke(checkServ);
        
        Method toString = userClass.getMethod("toString");//得到方法对象
        Object money2 = toString.invoke(checkServ);//调用借钱方法，得到返回值
        System.out.println("输出结果：" + money2);
		
	}
	
	/*根据类型校验字符串*/
	public static String checkInvokeJson(JSONArray jsonRuleAarr,String checkStr) throws Exception {
	
		Method checkRule = null;
		JSONObject ckJson = null;
		String check_type = "";
		String check_value = "";
		
		Class<?> userClass = Class.forName("com.atguigu.springboot.serv.PubCheckServ");
		PubCheckServ checkServ = (PubCheckServ) userClass.newInstance();
		  
		checkRule = userClass.getMethod("setStr",String.class);  //设置校验串
		checkRule.invoke(checkServ,checkStr); 

        //循环调用稽核规则
		for (int i = 0; i < jsonRuleAarr.size(); i++) {
			ckJson = JSONObject.fromObject(jsonRuleAarr.get(i));
			check_type = ckJson.get("check_type").toString();
			check_value = ckJson.get("check_value").toString();
			checkRule = userClass.getMethod(check_type,String.class);  //循环调用规则
			checkRule.invoke(checkServ,check_value); 
		}
		
		
		//获取结果Json串
		checkRule = userClass.getMethod("getCheckResult");  //获取结果
		Object stResult = checkRule.invoke(checkServ);  
        System.out.println("输出结果：" + stResult);
		
        return stResult.toString();
	}
}
