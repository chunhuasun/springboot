package com.atguigu.springboot.serv;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

public class PubCheckServ {
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
	 *   
	 *   
	 * */
	
	private String checkStr = "";
	private String exceptStr = "";
	private String exceptReport = ""; 
	
	public String getCheckStr() {
		return checkStr;
	}

	public String getExceptStr() {
		return exceptStr;
	}
 	 
	public String getExceptReport() {
		return exceptReport;
	}
 

	public PubCheckServ setStr(String checkInfoStr) { 
        this.checkStr = checkInfoStr;
        return this;
    }
	
	/*1、全汉字校验*/
	public  PubCheckServ checkChinese(String checkRuleStr) {
		
		String checkRule = "[\\u4e00-\\u9fa5]+"  ;  //可区分 - ４１ 等双字节的非汉字
		String exceptStr = checkStr.replaceAll(checkRule, "");
		
		if(exceptStr.length()>0){
			this.exceptReport += "checkChinese"+"|";
			this.exceptStr += exceptStr+"|";
		} 
	    return this;
	}
	
	/*2、非汉字校验*/
	public  PubCheckServ checkNoChinese(String checkRuleStr) {
		
		String checkRule = "[^\u4e00-\u9fa5]+"  ;  //可区分 - ４１ 等双字节的非汉字
		String exceptStr = checkStr.replaceAll(checkRule, "");
		 
		if(exceptStr.length()>0){
			this.exceptReport += "checkNoChinese"+"|";
			this.exceptStr += exceptStr+"|";
		}
	    return this;
	}
	
	/*3、全数字校验*/
	public  PubCheckServ checkNumber(String checkRuleStr) {
		
		String checkRule = "[\\d]+"  ; 
		String exceptStr = checkStr.replaceAll(checkRule, "");
		 
		if(exceptStr.length()>0){
			this.exceptReport += "checkNumber"+"|";
			this.exceptStr += exceptStr+"|";
		}
	    return this;
	}
	
	/*3、全数字校验*/
	public  PubCheckServ checkAllNumber(String checkRuleStr) {
		
		String checkRule = "[\\d]+"  ; 
		String exceptStr = checkStr.replaceAll(checkRule, "");
		 
		if(exceptStr.length()==0){
			this.exceptReport += "checkAllNumber"+"|";
			this.exceptStr += checkStr+"|";
		}
	    return this;
	}
	
	/*4、非数字校验*/
	public  PubCheckServ checkNoNumber(String checkRuleStr) {
		
		String checkRule = "[^\\d]+"  ; 
		String exceptStr = checkStr.replaceAll(checkRule, "");
		 
		if(exceptStr.length()>0){
			this.exceptReport += "checkNoNumber"+"|";
			this.exceptStr += exceptStr+"|";
		}
	    return this;
	}
	
	/*5、全数字和字母校验*/
	public  PubCheckServ checkNumLetter(String checkRuleStr) {
		
		String checkRule = "[a-zA-Z0-9]+";
		String exceptStr = checkStr.replaceAll(checkRule, "");
		 
		if(exceptStr.length()>0){
			this.exceptReport += "checkNumLetter"+"|";
			this.exceptStr += exceptStr+"|";
		}
	    return this;
	}
	
	/*6、非数字和字母校验*/
	public  PubCheckServ checkNoNumLetter(String checkRuleStr) {
		
		String checkRule = "[^a-zA-Z0-9]+";
		String exceptStr = checkStr.replaceAll(checkRule, "");
		 
		if(exceptStr.length()>0){
			this.exceptReport += "checkNoNumLetter"+"|";
			this.exceptStr += exceptStr+"|";
		}
	    return this;
	}
	
	/*7、全大写字母校验*/
	public  PubCheckServ checkLetterUpper(String checkRuleStr) {
		
		String checkRule = "[A-Z]+";
		String exceptStr = checkStr.replaceAll(checkRule, "");
		 
		if(exceptStr.length()>0){
			this.exceptReport += "checkLetterUpper"+"|";
			this.exceptStr += exceptStr+"|";
		}
	    return this;
	}
	
	/*8、非大写字母校验*/
	public  PubCheckServ checkNoLetterUpper(String checkRuleStr) {
		
		String checkRule = "[^A-Z]+";
		String exceptStr = checkStr.replaceAll(checkRule, "");
		 
		if(exceptStr.length()>0){
			this.exceptReport += "checkNoLetterUpper"+"|";
			this.exceptStr += exceptStr+"|";
		}
	    return this;
	}
	
	/*9、全小写字母校验*/
	public  PubCheckServ checkLetterLower(String checkRuleStr) {
		
		String checkRule = "[a-z]+";
		String exceptStr = checkStr.replaceAll(checkRule, "");
		 
		if(exceptStr.length()>0){
			this.exceptReport += "checkLetterLower"+"|";
			this.exceptStr += exceptStr+"|";
		}
	    return this;
	}
	
	/*10、非小写字母校验*/
	public  PubCheckServ checkNoLetterLower(String checkRuleStr) {
		
		String checkRule = "[^a-z]+";
		String exceptStr = checkStr.replaceAll(checkRule, "");
		 
		if(exceptStr.length()>0){
			this.exceptReport += "checkNoLetterLower"+"|";
			this.exceptStr += exceptStr+"|";
		}
	    return this;
	}
	
	/*1、包含规则集合校验*/ 
	public  PubCheckServ checkInclude(String checkRuleStr) {
		
		String exceptStr = "";
		int errcount = 0;
		checkRuleStr = "("+checkRuleStr+")"; 
		System.out.println("checkRule---"+checkRuleStr);    
		Pattern pattern = Pattern.compile(checkRuleStr);  
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
	    if(exceptStr.length()>0){
			this.exceptReport += "checkInclude"+"|";
			this.exceptStr += exceptStr+"|";
		}
	    return this;
	}
	
	/*2、以规则集合开头校验*/ 
    public  PubCheckServ checkHeadInclude(String checkRuleStr) {
		
		String exceptStr = "";
		int errcount = 0;
		checkRuleStr = checkRuleStr.replaceAll("\\|", "\\|^");  // 双斜杠进行转义
		checkRuleStr = "(^"+checkRuleStr+")"; 
		System.out.println("checkRule---"+checkRuleStr);
		 
		Pattern pattern = Pattern.compile(checkRuleStr);  
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
	    if(exceptStr.length()>0){
			this.exceptReport += "checkHeadInclude"+"|";
			this.exceptStr += exceptStr+"|";
		}
	    return this;
	}

	/*3、以规则集合结束校验*/ 
    public  PubCheckServ checkTailInclude(String checkRuleStr) {
		
		String exceptStr = "";
		int errcount = 0;
		checkRuleStr = checkRuleStr.replaceAll("\\|", "\\$|");  // 双斜杠进行转义
		checkRuleStr = "("+checkRuleStr+"$)"; 
		System.out.println("checkRule---"+checkRuleStr);  
		Pattern pattern = Pattern.compile(checkRuleStr);  
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
	    if(exceptStr.length()>0){
			this.exceptReport += "checkTailInclude"+"|";
			this.exceptStr += exceptStr+"|";
		}
	    return this;
	}

    /*4、可包含规则集合信息   需要将可包含的集合进行替换，并将结果返回进行后续稽核   只能是单字符，需要在配置界面进行校验 --若要支持词组需要进行循环处理*/ 
    public  PubCheckServ checkRepInclude(String checkRuleStr) {
		 
    	checkRuleStr = "["+checkRuleStr+"]+"; 
		this.checkStr = checkStr.replaceAll(checkRuleStr, ""); 
	    return this;
	}
      
	@Override
	public String toString() {
		return "PubCheckServ [checkStr=" + checkStr + ", exceptStr="
				+ exceptStr + ", exceptReport=" + exceptReport + "]";
	} 
	
	public String getCheckResult() {
		
		JSONObject reJson = new JSONObject();
		reJson.put("checkContentInfo", this.exceptStr.toString());
		reJson.put("checkRuleInfo", this.exceptReport.toString());
    	
		return reJson.toString();
	}
}
