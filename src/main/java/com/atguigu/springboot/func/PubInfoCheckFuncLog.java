package com.atguigu.springboot.func;

import net.sf.json.JSONObject;

public class PubInfoCheckFuncLog {
	
	/*根据类型校验字符串*/
	public static String checkInfoType(String checkStr , String checkType) {
	
		if("chinese".equals(checkType)){
			//https://blog.csdn.net/kongjing0815/article/details/5751852
			checkStr = checkStr.replaceAll("[^x00-xff]*", "");  //只能识别双字节，无法准确定位汉字
		    System.out.println("chinese-->"+checkStr);
		    System.out.println(checkStr.length());
		    return checkStr;
		}else{
			String reg = "[\\u4e00-\\u9fa5]+"  ;  //可区分 - ４１ 等双字节的非汉字
			String remainStr = checkStr.replaceAll(reg, "");
			System.out.println("reg-->"+remainStr);
		    System.out.println(remainStr.length());
		    
		    String regchin = "[^\u4e00-\u9fa5]+"  ;  //可区分 - ４１ 等双字节的非汉字
			String chineseStr = checkStr.replaceAll(regchin, "");
			System.out.println("regchin-->"+chineseStr);
		    System.out.println(chineseStr.length());
		    
		    JSONObject jsonObj = new JSONObject();
		    jsonObj.put("chinlen", checkStr.length()-remainStr.length());
		    jsonObj.put("remainlen", remainStr.length());
		    jsonObj.put("remain", remainStr);
		    jsonObj.put("chinese", chineseStr);
			
		    return jsonObj.toString();
		}
		//return null;
	}

	/*根据规则串校验字符串*/
    public static String checkInfoRule(String checkStr , String checkRuleStr , String checkType) {
	
		
		
		return null;
	}

    /*根据规则JSON串校验字符串*/
    public static String checkInfoRuleJson(String checkStr , String checkRuleStr , String checkType) {
	
		
		
		return null;
	}
}
