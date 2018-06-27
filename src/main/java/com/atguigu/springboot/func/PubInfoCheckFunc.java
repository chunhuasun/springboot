package com.atguigu.springboot.func;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

public class PubInfoCheckFunc {
	
	/*根据类型校验字符串*/
	public static String checkInfoType(String checkStr , String checkType) {
	
		if("chinese".equals(checkType)){
			String reg = "[\\u4e00-\\u9fa5]+"  ;  //可区分 - ４１ 等双字节的非汉字
			String missStr = checkStr.replaceAll(reg, "");
			//System.out.println("reg-->"+remainStr);
		   // System.out.println(remainStr.length());
		    
		    String regfind = "[^\u4e00-\u9fa5]+"  ;  //可区分 - ４１ 等双字节的非汉字
			//String findStr = checkStr.replaceAll(regfind, "");
			//System.out.println("regfind-->"+chineseStr);
		   // System.out.println(chineseStr.length());
		    
		    JSONObject jsonObj = new JSONObject();
		    jsonObj.put("findlen", checkStr.length()-missStr.length());
		    jsonObj.put("misslen", missStr.length());
		    jsonObj.put("missstr", missStr);
		    //jsonObj.put("findstr", findStr);
			
		    return jsonObj.toString();
		}else if("number".equals(checkType)){
			String reg = "[\\d]+";  
			String missStr = checkStr.replaceAll(reg, "");
			//System.out.println("reg-->"+remainStr);
		   // System.out.println(remainStr.length());
		    
		    String regfind = "[^\\d]+"  ;  
			//String findStr = checkStr.replaceAll(regfind, "");
			//System.out.println("regfind-->"+chineseStr);
		   // System.out.println(chineseStr.length());
		    
		    JSONObject jsonObj = new JSONObject();
		    jsonObj.put("findlen", checkStr.length()-missStr.length());
		    jsonObj.put("misslen", missStr.length());
		    jsonObj.put("missstr", missStr);
		    //jsonObj.put("findstr", findStr);
			
		    return jsonObj.toString();
	    }else if("letter".equals(checkType)){
			String reg = "[a-zA-Z]+";  
			String missStr = checkStr.replaceAll(reg, "");
			//System.out.println("reg-->"+remainStr);
		   // System.out.println(remainStr.length());
		    
		    String regfind = "[^a-zA-Z]+"  ;  
			String findStr = checkStr.replaceAll(regfind, "");
			//System.out.println("regfind-->"+chineseStr);
		   // System.out.println(chineseStr.length());
		    
		    JSONObject jsonObj = new JSONObject();
		    jsonObj.put("findlen", checkStr.length()-missStr.length());
		    jsonObj.put("misslen", missStr.length());
		    jsonObj.put("missstr", missStr);
		    jsonObj.put("findstr", findStr);
			
		    return jsonObj.toString();
	    }else if("letterupper".equals(checkType)){
			String reg = "[A-Z]+";  
			String missStr = checkStr.replaceAll(reg, "");
			//System.out.println("reg-->"+remainStr);
		   // System.out.println(remainStr.length());
		    
		    String regfind = "[^A-Z]+"  ;  
			String findStr = checkStr.replaceAll(regfind, "");
			//System.out.println("regfind-->"+chineseStr);
		   // System.out.println(chineseStr.length());
		    
		    JSONObject jsonObj = new JSONObject();
		    jsonObj.put("findlen", checkStr.length()-missStr.length());
		    jsonObj.put("misslen", missStr.length());
		    jsonObj.put("missstr", missStr);
		    jsonObj.put("findstr", findStr);
			
		    return jsonObj.toString();
	    }else if("letterlower".equals(checkType)){
			String reg = "[a-z]+";  
			String missStr = checkStr.replaceAll(reg, "");
			//System.out.println("reg-->"+remainStr);
		   // System.out.println(remainStr.length());
		    
		    String regfind = "[^a-z]+"  ;  
			String findStr = checkStr.replaceAll(regfind, "");
			//System.out.println("regfind-->"+chineseStr);
		   // System.out.println(chineseStr.length());
		    
		    JSONObject jsonObj = new JSONObject();
		    jsonObj.put("findlen", checkStr.length()-missStr.length());
		    jsonObj.put("misslen", missStr.length());
		    jsonObj.put("missstr", missStr);
		    jsonObj.put("findstr", findStr);
			
		    return jsonObj.toString();
	    }else{
			return null;
		}
		//return null;
	}

	/*根据规则串校验字符串*/
    public static String checkInfoRule(String checkStr , String checkRuleStr , String checkType) {
	
    	if("notin".equals(checkType)){
			String reg = "["+checkRuleStr+"]+"  ;  
			System.out.println("reg-->"+reg);
			String missStr = checkStr.replaceAll(reg, "");
			System.out.println("missStr-->"+missStr);
		   // System.out.println(remainStr.length());
		    
		    String regfind = "[^"+checkRuleStr+"]+"  ;  //可区分 - ４１ 等双字节的非汉字
		    System.out.println("regfind-->"+regfind);
			String findStr = checkStr.replaceAll(regfind, "");
			System.out.println("regfind-->"+findStr);
		   // System.out.println(findStr.length());
		    
		    JSONObject jsonObj = new JSONObject();
		    jsonObj.put("findlen", checkStr.length()-missStr.length());
		    jsonObj.put("misslen", missStr.length());
		    jsonObj.put("missstr", missStr);
		    jsonObj.put("findstr", findStr);
			
		    return jsonObj.toString();
		}else if("allin".equals(checkType)){
			String reg = checkRuleStr  ;  //可区分 - ４１ 等双字节的非汉字
			String missStr = checkStr.replaceAll(reg, "");
			//System.out.println("reg-->"+remainStr);
		   // System.out.println(remainStr.length());
		    
		    String regfind = "[^\u4e00-\u9fa5]+"  ;  //可区分 - ４１ 等双字节的非汉字
			//String findStr = checkStr.replaceAll(regfind, "");
			//System.out.println("regfind-->"+chineseStr);
		   // System.out.println(chineseStr.length());
		    
		    JSONObject jsonObj = new JSONObject();
		    jsonObj.put("findlen", checkStr.length()-missStr.length());
		    jsonObj.put("misslen", missStr.length());
		    jsonObj.put("missstr", missStr);
		    //jsonObj.put("findstr", findStr);
			
		    return jsonObj.toString();
			
		}else{
		   return null;
		}
	}
    
    /*根据规则串校验字符串*/
    public static String checkInfoRuleIn(String checkStr , String checkRuleStr , String checkType) {
	
    	//checkRuleStr = "重庆|公司"; 
    	if("checkin".equals(checkType)){
    		
    		checkRuleStr = "(^重庆|^公司)"; 
		    Pattern pattern = Pattern.compile(checkRuleStr);   
		    //2.将字符串和正则表达式相关联      
		    Matcher matcher = pattern.matcher(checkStr);      
		    //3.String 对象中的matches 方法就是通过这个Matcher和pattern来实现的。       
		    System.out.println("matcher.matches()---"+matcher.matches());     
		    //查找符合规则的子串   
		    while(matcher.find()){
		    	//获取 字符串     
		    	System.out.println(matcher.group());  
		    	//获取的字符串的首位置和末位置   
		    	System.out.println(matcher.start()+"--"+matcher.end());   
		    } 
    	
    		checkRuleStr = "("+checkRuleStr+")";  
			String missStr = checkStr.replaceAll(checkRuleStr, "");
			System.out.println("missStr-->"+missStr);
		    System.out.println(missStr.length());
		     
		    String regfind = "^("+checkRuleStr+")" ;  
			String findStr = checkStr.replaceAll(regfind, "");
			System.out.println("regfind-->"+findStr);
		    System.out.println(findStr.length());
		    
		    JSONObject jsonObj = new JSONObject();
		    jsonObj.put("findlen", checkStr.length()-missStr.length());
		    jsonObj.put("misslen", missStr.length());
		    jsonObj.put("missstr", missStr);
		    jsonObj.put("findstr", findStr);
			
		    return jsonObj.toString(); 
		}else if("checkstar".equals(checkType)){
			//checkRuleStr = "^.*("+checkRuleStr+").*";
			checkRuleStr = ".*("+"^重庆|^公司"+").*";
			JSONObject jsonObj = new JSONObject();
    		if(checkStr.matches(checkRuleStr)){
    			System.out.println("checkRuleStr-->"+checkRuleStr);
    			
    			jsonObj.put("findlen", checkStr.length());
    		    jsonObj.put("misslen", 0);
    		    jsonObj.put("missstr", 0);
    		    jsonObj.put("findstr", 0);
    		}else{
    			jsonObj.put("findlen", 0);
    		    jsonObj.put("misslen", 2);
    		    jsonObj.put("missstr", 2);
    		    jsonObj.put("findstr", 2);
    		} 
    		
		    return jsonObj.toString();
			
		}else if("checkend".equals(checkType)){
			//checkRuleStr = "^.*("+checkRuleStr+").*";
			checkRuleStr = ".*("+"重庆$|公司$"+").*";
			JSONObject jsonObj = new JSONObject();
    		if(checkStr.matches(checkRuleStr)){
    			System.out.println("checkRuleStr-->"+checkRuleStr);
    			
    			jsonObj.put("findlen", checkStr.length());
    		    jsonObj.put("misslen", 0);
    		    jsonObj.put("missstr", 0);
    		    jsonObj.put("findstr", 0);
    		}else{
    			jsonObj.put("findlen", 0);
    		    jsonObj.put("misslen", 2);
    		    jsonObj.put("missstr", 2);
    		    jsonObj.put("findstr", 2);
    		} 
    		
		    return jsonObj.toString();
			
		}else if("checkout".equals(checkType)){
			checkRuleStr = ".*([^"+"重庆|公司"+"]).*";
			JSONObject jsonObj = new JSONObject();
    		if(checkStr.matches(checkRuleStr)){
    			System.out.println("checkRuleStr-->"+checkRuleStr);
    			
    			jsonObj.put("findlen", checkStr.length());
    		    jsonObj.put("misslen", 0);
    		    jsonObj.put("missstr", 0);
    		    jsonObj.put("findstr", 0);
    		}else{
    			jsonObj.put("findlen", 0);
    		    jsonObj.put("misslen", 2);
    		    jsonObj.put("missstr", 2);
    		    jsonObj.put("findstr", 2);
    		} 
    		
		    return jsonObj.toString();
			
		}else if("checktest".equals(checkType)){

			String reg = "(重庆|公司)";  
			String missStr = checkStr.replaceAll(reg, "");
			System.out.println("reg-->"+missStr);
		    System.out.println(missStr.length());
			
			
			checkRuleStr = ".*([^"+"重庆|公司"+"]).*";
			JSONObject jsonObj = new JSONObject();
    		if(checkStr.matches(checkRuleStr)){
    			System.out.println("checkRuleStr-->"+checkRuleStr);
    			
    			jsonObj.put("findlen", checkStr.length());
    		    jsonObj.put("misslen", 0);
    		    jsonObj.put("missstr", 0);
    		    jsonObj.put("findstr", 0);
    		}else{
    			jsonObj.put("findlen", 0);
    		    jsonObj.put("misslen", 2);
    		    jsonObj.put("missstr", 2);
    		    jsonObj.put("findstr", 2);
    		} 
    		
		    return jsonObj.toString();
			
		}else{
			
		   return null;
		}
	}

    /*根据规则JSON串校验字符串*/
    public static String checkInfoRuleJson(String checkStr , String checkRuleStr , String checkRuleJson , String checkType) {
	
		
		
		return null;
	}
}
