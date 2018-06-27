package com.atguigu.springboot.serv;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

public class PubInfoCheckServ {
	/*函数细分，每个功能都独立实现，有利于后续的服务拆分，
	 *  统一入参和出参
	 *  分两大类校验：
	 *      第一类：固定规则校验
	 *      入参： checkStr   校验字符串
	 *      出参： checkLen   满足规则的字符串长度
	 *          exceptLen  规则之外的字符串长度
	 *          exceptStr  规则之外的字符串
	 *      1、全汉字校验
	 *      2、非汉字校验
	 *      3、全数字校验
	 *      4、非数字校验
	 *      5、全数字和字母校验
	 *      6、非数字和字母校验
	 *      7、全大写字母校验
	 *      8、非大写字母校验
	 *      9、全小写字母校验
	 *      10、非小写字母校验
	 *      
	 *      第二类：自定义规则校验
	 *      入参： checkStr   校验字符串
	 *          checkRule  规则集合 格式     规则1|规则2|规则3|规则4
	 *      出参： checkLen   满足规则的字符串长度
	 *          exceptLen  规则之外的字符串长度
	 *          exceptStr  规则之外的字符串
	 *      1、包含规则集合校验
	 *      2、以规则集合开头校验
	 *      3、以规则集合结束校验
	 *      4、可包含规则集合信息   需要将可包含的集合进行替换，并将结果返回进行后续稽核
	 *   
	 *   
	 * */
	
	/*1、全汉字校验*/
	public static String checkChinese(String checkStr) {
		
		String checkRule = "[\\u4e00-\\u9fa5]+"  ;  //可区分 - ４１ 等双字节的非汉字
		String exceptStr = checkStr.replaceAll(checkRule, "");
		 
	    JSONObject jsonObj = new JSONObject();
	    jsonObj.put("checkLen", checkStr.length()-exceptStr.length());
	    jsonObj.put("exceptLen", exceptStr.length());
	    jsonObj.put("exceptStr", exceptStr);
		
	    return jsonObj.toString();
	}
	
	/*2、非汉字校验*/
	public static String checkNoChinese(String checkStr) {
		
		String checkRule = "[^\u4e00-\u9fa5]+"  ;  //可区分 - ４１ 等双字节的非汉字
		String exceptStr = checkStr.replaceAll(checkRule, "");
		 
	    JSONObject jsonObj = new JSONObject();
	    jsonObj.put("checkLen", checkStr.length()-exceptStr.length());
	    jsonObj.put("exceptLen", exceptStr.length());
	    jsonObj.put("exceptStr", exceptStr);
		
	    return jsonObj.toString();
	}
	
	/*3、全数字校验*/
	public static String checkNumber(String checkStr) {
		
		String checkRule = "[\\d]+"  ; 
		String exceptStr = checkStr.replaceAll(checkRule, "");
		 
	    JSONObject jsonObj = new JSONObject();
	    jsonObj.put("checkLen", checkStr.length()-exceptStr.length());
	    jsonObj.put("exceptLen", exceptStr.length());
	    jsonObj.put("exceptStr", exceptStr);
		
	    return jsonObj.toString();
	}
	
	/*4、非数字校验*/
	public static String checkNoNumber(String checkStr) {
		
		String checkRule = "[^\\d]+"  ; 
		String exceptStr = checkStr.replaceAll(checkRule, "");
		 
	    JSONObject jsonObj = new JSONObject();
	    jsonObj.put("checkLen", checkStr.length()-exceptStr.length());
	    jsonObj.put("exceptLen", exceptStr.length());
	    jsonObj.put("exceptStr", exceptStr);
		
	    return jsonObj.toString();
	}
	
	/*5、全数字和字母校验*/
	public static String checkNumLetter(String checkStr) {
		
		String checkRule = "[a-zA-Z0-9]+";
		String exceptStr = checkStr.replaceAll(checkRule, "");
		 
	    JSONObject jsonObj = new JSONObject();
	    jsonObj.put("checkLen", checkStr.length()-exceptStr.length());
	    jsonObj.put("exceptLen", exceptStr.length());
	    jsonObj.put("exceptStr", exceptStr);
		
	    return jsonObj.toString();
	}
	
	/*6、非数字和字母校验*/
	public static String checkNoNumLetter(String checkStr) {
		
		String checkRule = "[^a-zA-Z0-9]+";
		String exceptStr = checkStr.replaceAll(checkRule, "");
		 
	    JSONObject jsonObj = new JSONObject();
	    jsonObj.put("checkLen", checkStr.length()-exceptStr.length());
	    jsonObj.put("exceptLen", exceptStr.length());
	    jsonObj.put("exceptStr", exceptStr);
		
	    return jsonObj.toString();
	}
	
	/*7、全大写字母校验*/
	public static String checkLetterUpper(String checkStr) {
		
		String checkRule = "[A-Z]+";
		String exceptStr = checkStr.replaceAll(checkRule, "");
		 
	    JSONObject jsonObj = new JSONObject();
	    jsonObj.put("checkLen", checkStr.length()-exceptStr.length());
	    jsonObj.put("exceptLen", exceptStr.length());
	    jsonObj.put("exceptStr", exceptStr);
		
	    return jsonObj.toString();
	}
	
	/*8、非大写字母校验*/
	public static String checkNoLetterUpper(String checkStr) {
		
		String checkRule = "[^A-Z]+";
		String exceptStr = checkStr.replaceAll(checkRule, "");
		 
	    JSONObject jsonObj = new JSONObject();
	    jsonObj.put("checkLen", checkStr.length()-exceptStr.length());
	    jsonObj.put("exceptLen", exceptStr.length());
	    jsonObj.put("exceptStr", exceptStr);
		
	    return jsonObj.toString();
	}
	
	/*9、全小写字母校验*/
	public static String checkLetterLower(String checkStr) {
		
		String checkRule = "[a-z]+";
		String exceptStr = checkStr.replaceAll(checkRule, "");
		 
	    JSONObject jsonObj = new JSONObject();
	    jsonObj.put("checkLen", checkStr.length()-exceptStr.length());
	    jsonObj.put("exceptLen", exceptStr.length());
	    jsonObj.put("exceptStr", exceptStr);
		
	    return jsonObj.toString();
	}
	
	/*10、非小写字母校验*/
	public static String checkNoLetterLower(String checkStr) {
		
		String checkRule = "[^a-z]+";
		String exceptStr = checkStr.replaceAll(checkRule, "");
		 
	    JSONObject jsonObj = new JSONObject();
	    jsonObj.put("checkLen", checkStr.length()-exceptStr.length());
	    jsonObj.put("exceptLen", exceptStr.length());
	    jsonObj.put("exceptStr", exceptStr);
		
	    return jsonObj.toString();
	}
	
	/*1、包含规则集合校验*/ 
	public static String checkInclude(String checkStr , String checkRule) {
		
		String exceptStr = "";
		int errcount = 0;
		checkRule = "("+checkRule+")"; 
		System.out.println("checkRule---"+checkRule);    
		Pattern pattern = Pattern.compile(checkRule);  
	    Matcher matcher = pattern.matcher(checkStr); 
	    System.out.println("matcher.matches()---"+matcher.matches());     
	    //查找符合规则的子串   
	    while(matcher.find()){
	    	//获取 字符串     
	    	if("".equals(exceptStr)){
		    	exceptStr = matcher.group();
	    	}else{
		    	exceptStr = exceptStr + "|" + matcher.group();
		    	errcount++;
	    	}
	    } 
	    JSONObject jsonObj = new JSONObject();
	    jsonObj.put("checkLen", checkStr.length()-(exceptStr.length()-errcount));
	    jsonObj.put("exceptLen", exceptStr.length()-errcount);
	    jsonObj.put("exceptStr", exceptStr);
		
	    return jsonObj.toString(); 
	}
	
	/*2、以规则集合开头校验*/ 
    public static String checkHeadInclude(String checkStr , String checkRule) {
		
		String exceptStr = "";
		int errcount = 0;
		checkRule = checkRule.replaceAll("\\|", "\\|^");  // 双斜杠进行转义
		checkRule = "(^"+checkRule+")"; 
		System.out.println("checkRule---"+checkRule);
		 
		Pattern pattern = Pattern.compile(checkRule);  
	    Matcher matcher = pattern.matcher(checkStr); 
	    System.out.println("matcher.matches()---"+matcher.matches());     
	    //查找符合规则的子串   
	    while(matcher.find()){
	    	//获取 字符串     
	    	if("".equals(exceptStr)){
		    	exceptStr = matcher.group();
	    	}else{
		    	exceptStr = exceptStr + "|" + matcher.group();
		    	errcount++;
	    	}
	    } 
	    JSONObject jsonObj = new JSONObject();
	    jsonObj.put("checkLen", checkStr.length()-(exceptStr.length()-errcount));
	    jsonObj.put("exceptLen", exceptStr.length()-errcount);
	    jsonObj.put("exceptStr", exceptStr);
		
	    return jsonObj.toString(); 
	}

	/*3、以规则集合结束校验*/ 
    public static String checkTailInclude(String checkStr , String checkRule) {
		
		String exceptStr = "";
		int errcount = 0;
		checkRule = checkRule.replaceAll("\\|", "\\$|");  // 双斜杠进行转义
		checkRule = "("+checkRule+"$)"; 
		System.out.println("checkRule---"+checkRule);  
		Pattern pattern = Pattern.compile(checkRule);  
	    Matcher matcher = pattern.matcher(checkStr); 
	    System.out.println("matcher.matches()---"+matcher.matches());     
	    //查找符合规则的子串   
	    while(matcher.find()){
	    	//获取 字符串     
	    	if("".equals(exceptStr)){
		    	exceptStr = matcher.group();
	    	}else{
		    	exceptStr = exceptStr + "|" + matcher.group();
		    	errcount++;
	    	}
	    } 
	    JSONObject jsonObj = new JSONObject();
	    jsonObj.put("checkLen", checkStr.length()-(exceptStr.length()-errcount));
	    jsonObj.put("exceptLen", exceptStr.length()-errcount);
	    jsonObj.put("exceptStr", exceptStr);
		
	    return jsonObj.toString(); 
	} 
    
    /*4、可包含规则集合信息   需要将可包含的集合进行替换，并将结果返回进行后续稽核   只能是单字符，需要在配置界面进行校验 --若要支持词组需要进行循环处理*/ 
    public static String checkRepInclude(String checkStr , String checkRule) {
		
		String exceptStr = "";
		int errcount = 0;
		//checkRule = checkRule.replaceAll("\\|", "\\$|");  // 双斜杠进行转义
		checkRule = "["+checkRule+"]+"; 
		exceptStr = checkStr.replaceAll(checkRule, "");
		 
	    JSONObject jsonObj = new JSONObject();
	    jsonObj.put("checkLen", checkStr.length()-(exceptStr.length()-errcount));
	    jsonObj.put("exceptLen", exceptStr.length()-errcount);
	    jsonObj.put("exceptStr", exceptStr);
		
	    return jsonObj.toString(); 
	} 
	 
}
