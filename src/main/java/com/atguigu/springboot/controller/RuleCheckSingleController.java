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

import net.sf.json.JSONObject;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RuleCheckSingleController {

    @Autowired
    RuleCheckSingleRepository ruleCheckSingleRepository;
    
    @Autowired
    CheckAddressRepository checkAddressRepository;
    
    @Autowired
    RuleCheckEngineRepository ruleCheckEngineRepository;
    
    @Autowired
    InfoCheckCardRepository infoCheckCardRepository;
    
    @Autowired
    InfoCheckCardResultRepository infoCheckCardResultRepository;
 
    
    @GetMapping("/ruletest")
    public String retest(){
    	
    	String checkType = "cust_name_include"; 
    	List<RuleCheckSingle> ruleCheckSingles =ruleCheckSingleRepository.findByCheckType(checkType);
    	   
    	System.out.println("ruleCheckSingle-->"+ruleCheckSingles.size());
    	
    	//打印开始时间
    	System.out.println(new Date(System.currentTimeMillis()));
    	long startTime=System.currentTimeMillis(); 
    	
    	System.out.println(new Date(System.nanoTime()));
    	long startTimenano=System.nanoTime();
    	
    	
    	RuleCheckSingle ruleCheckSingle = null;
    	String check_cardadd = "";
    	String check_errinfoString = "";
    	String check_val = "";
    	check_cardadd = "重庆市沙坪坝区虎财务部溪镇伍家沟村狮子湾组13号初级中学附1号";
    	 
    	//测试正则表达式的字符匹配
    	for (int i = 0; i < ruleCheckSingles.size(); i++) {
    		
    	    ruleCheckSingle = ruleCheckSingles.get(i); 
//    		System.out.println("ruleCheckSingle-->"+ruleCheckSingle.getCheckValue()+"no:"+ruleCheckSingle.getCheckNo());
    		 
			check_val = ".*("+ruleCheckSingle.getCheckValue()+").*";
//		    System.out.println(check_val);
			
			if(check_cardadd.matches(check_val)){
				check_errinfoString += ruleCheckSingle.getCheckNo() + "|";
			}
    		
    	}	
    	
    	//打印结束时间
    	System.out.println(new Date(System.currentTimeMillis()));
    	long endTime=System.currentTimeMillis(); //获取结束时间
    	
    	System.out.println(new Date(System.nanoTime()));
    	long endTimenano=System.nanoTime();
    	
    	
    	System.out.println("程序运行时间： "+(endTime-startTime)+"ms");
    	System.out.println("程序运行时间： "+(endTimenano-startTimenano)+"ns");
    	
    	//测试正则表达式的字符匹配
//    	String check_infoString = "";
//    	check_infoString ="[{'规则':'1','稽核值':'财务部'},{'规则':'2','稽核值':'测试卡'},{'规则':'3','稽核值':'初级中学'}]";
//    	
//    	String check_cardadd = "";
//    	check_cardadd = "重庆市沙坪坝区虎财务部溪镇伍家沟村狮子湾组13号初级中学附1号";
//    	
//    	String check_errinfoString = "";
//    	
//		JSONArray jsonParaInfo = JSONArray.fromObject(check_infoString);
//		JSONObject reJson = null;
//
//		for (int i = 0; i < jsonParaInfo.size(); i++) {
//			reJson = JSONObject.fromObject(jsonParaInfo.get(i));
//			System.out.println(reJson.toString()); 
//			//System.out.println(reJson.get("稽核值").toString());
//			String check_val = reJson.get("稽核值").toString();
//			System.out.println(check_val);
//			check_val = ".*("+check_val+").*";
//			System.out.println(check_val);
//			
//			if(check_cardadd.matches(check_val)){
//				check_errinfoString += reJson.get("规则").toString() + "|";
//			}
//			
//		}
		
		System.out.println("check_errinfoString-->"+check_errinfoString);
    	
      
    	
    	return "ok im goin 3311";
    }
    
    
    @GetMapping("/rulecheck")
    public String retestforfor(){
    	
    	String checkType = "cust_name_include"; 
    	List<RuleCheckSingle> ruleCheckSingles =ruleCheckSingleRepository.findByCheckType(checkType);
    	
    	List<CheckAddress> checkAddresss = checkAddressRepository.findAll();
    	 
    	System.out.println("ruleCheckSingle-->"+ruleCheckSingles.size());
    	
    	//打印开始时间
    	System.out.println(new Date(System.currentTimeMillis()));
    	long startTime=System.currentTimeMillis(); 
    	
    	System.out.println(new Date(System.nanoTime()));
    	long startTimenano=System.nanoTime();
    	
    	RuleCheckSingle ruleCheckSingle = null;
    	String check_cardadd = "";
    	String check_errinfoString = "";
    	String check_val = "";
    	check_cardadd = "重庆市沙坪坝区虎财务部溪镇伍家沟村狮子湾组13号初级中学附1号";
    	
    	JSONArray jsonAarr = new JSONArray();
    	JSONObject jsonOne = new JSONObject();
    	
    	//测试嵌套循环，测试效率
    	for (int ii = 0; ii < checkAddresss.size(); ii++) {
    		
			 check_cardadd = checkAddresss.get(ii).getCardAddress();
			 check_errinfoString = "";
    		//测试正则表达式的字符匹配
    	    for (int i = 0; i < ruleCheckSingles.size(); i++) {
    	    		
   	    	     ruleCheckSingle = ruleCheckSingles.get(i); 
    	    		 
    			  check_val = ".*("+ruleCheckSingle.getCheckValue()+").*";
    				
				 if(check_cardadd.matches(check_val)){
				  	 check_errinfoString += ruleCheckSingle.getCheckNo() + "|";
				 }
    	    }	
    	    
    	    if(!"".equals(check_errinfoString)){
    	    	//以JSON数组的方式记录错误信息
    	    	jsonOne = new JSONObject();
    	    	jsonOne.put("check_id", checkAddresss.get(ii).getId());
    	    	jsonOne.put("check_error_info", check_errinfoString);
				jsonAarr.add(jsonOne);
    	    }
    	}
    	
    	  
    	//打印结束时间
    	System.out.println(new Date(System.currentTimeMillis()));
    	long endTime=System.currentTimeMillis(); //获取结束时间
    	
    	System.out.println(new Date(System.nanoTime()));
    	long endTimenano=System.nanoTime();
    	 
    	System.out.println("程序运行时间： "+(endTime-startTime)+"ms");
    	System.out.println("程序运行时间： "+(endTimenano-startTimenano)+"ns");
    	 
    	//System.out.println("jsonAarr-->"+jsonAarr.toString());
    	
    	return "rulecheck ok";
    }
    
    @GetMapping("/rulecheckn")
    public String retestforforn(){
    	
    	String checkType = "cust_name_include"; 
    	List<RuleCheckSingle> ruleCheckSingles =ruleCheckSingleRepository.findByCheckType(checkType);
    	
    	List<CheckAddress> checkAddresss = checkAddressRepository.findAll();
    	 
    	System.out.println("ruleCheckSingle-->"+ruleCheckSingles.size());
    	
    	//打印开始时间
    	System.out.println(new Date(System.currentTimeMillis()));
    	long startTime=System.currentTimeMillis(); 
    	
    	System.out.println(new Date(System.nanoTime()));
    	long startTimenano=System.nanoTime();
    	
    	RuleCheckSingle ruleCheckSingle = null;
    	String check_cardadd = "";
    	String check_errinfoString = "";
    	String check_val = "";
    	check_cardadd = "重庆市沙坪坝区虎财务部溪镇伍家沟村狮子湾组13号初级中学附1号";
    	
    	JSONArray jsonAarr = new JSONArray();
    	JSONObject jsonOne = new JSONObject();
    	
    	//测试嵌套循环，测试效率 反向循环测试效率 将规则放到外层进行循环
    	//测试正则表达式的字符匹配
	    for (int i = 0; i < ruleCheckSingles.size(); i++) {
	    		
		     ruleCheckSingle = ruleCheckSingles.get(i); 
	    		 
			 check_val = ".*("+ruleCheckSingle.getCheckValue()+").*";
			
			 for (int ii = 0; ii < checkAddresss.size(); ii++) {
		      	  check_cardadd = checkAddresss.get(ii).getCardAddress();
		      	  check_errinfoString = "";
		      	  if(check_cardadd.matches(check_val)){
				   	  
		      		 jsonOne = new JSONObject();
	    	    	 jsonOne.put("check_id", checkAddresss.get(ii).getId());
	    	    	 jsonOne.put("check_error_info", ruleCheckSingle.getCheckNo());
					 jsonAarr.add(jsonOne);
					 
				  } 	  
			 }	 
	    }	
	     
    	  
    	//打印结束时间
    	System.out.println(new Date(System.currentTimeMillis()));
    	long endTime=System.currentTimeMillis(); //获取结束时间
    	
    	System.out.println(new Date(System.nanoTime()));
    	long endTimenano=System.nanoTime();
    	 
    	System.out.println("程序运行时间： "+(endTime-startTime)+"ms");
    	System.out.println("程序运行时间： "+(endTimenano-startTimenano)+"ns");
    	 
    	//System.out.println("jsonAarr-->"+jsonAarr.toString());
    	
    	return "rulechecknew ok";
    }


    @GetMapping("/rulechecka")
    public String retestforfora(){
    	
    	String checkType = "cust_name_include"; 
    	List<RuleCheckSingle> ruleCheckSingles =ruleCheckSingleRepository.findByCheckType(checkType);
    	
    	List<CheckAddress> checkAddresss = checkAddressRepository.findAll();
    	 
    	System.out.println("ruleCheckSingle-->"+ruleCheckSingles.size());
    	
    	//打印开始时间
    	System.out.println(new Date(System.currentTimeMillis()));
    	long startTime=System.currentTimeMillis(); 
    	
    	System.out.println(new Date(System.nanoTime()));
    	long startTimenano=System.nanoTime();
    	
    	RuleCheckSingle ruleCheckSingle = null;
    	String check_cardadd = "";
    	String check_errinfoString = "";
    	String check_val = "";
    	check_cardadd = "重庆市沙坪坝区虎财务部溪镇伍家沟村狮子湾组13号初级中学附1号";
    	
    	JSONArray jsonAarr = new JSONArray();
    	JSONObject jsonOne = new JSONObject();
    	
    	//测试嵌套循环，测试效率 反向循环测试效率 将规则放到外层进行循环
    	//测试正则表达式的字符匹配
    	check_val = ".*("+ruleCheckSingles.get(0).getCheckValue();
	    for (int i = 1; i < ruleCheckSingles.size(); i++) {
	    		
		     ruleCheckSingle = ruleCheckSingles.get(i); 
	    		 
			 //check_val = ".*("+ruleCheckSingle.getCheckValue()+").*";
		     check_val+="|"+ruleCheckSingle.getCheckValue();
	    }
	    check_val += ").*";
	    System.out.println("check_val-->"+check_val);
	    //循环建立规则后再进行调用
	    
			 for (int ii = 0; ii < checkAddresss.size(); ii++) {
		      	  check_cardadd = checkAddresss.get(ii).getCardAddress();
		      	  check_errinfoString = "";
		      	  if(check_cardadd.matches(check_val)){
				   	  
		      		 jsonOne = new JSONObject();
	    	    	 jsonOne.put("check_id", checkAddresss.get(ii).getId());
	    	    	 jsonOne.put("check_error_info", "2");
					 jsonAarr.add(jsonOne);
					 
				  } 	  
			 }	 
	     	
	     
    	  
    	//打印结束时间
    	System.out.println(new Date(System.currentTimeMillis()));
    	long endTime=System.currentTimeMillis(); //获取结束时间
    	
    	System.out.println(new Date(System.nanoTime()));
    	long endTimenano=System.nanoTime();
    	 
    	System.out.println("程序运行时间： "+(endTime-startTime)+"ms");
    	System.out.println("程序运行时间： "+(endTimenano-startTimenano)+"ns");
    	 
    	//System.out.println("jsonAarr-->"+jsonAarr.toString());
    	
    	return "rulechecknewa ok";
    }
    
    @GetMapping("/rulefun")
    public String retestfun(){
    	
    	String checkType = "cust_name_include"; 
    	List<RuleCheckSingle> ruleCheckSingles =ruleCheckSingleRepository.findByCheckType(checkType);
    	
    	List<CheckAddress> checkAddresss = checkAddressRepository.findAll();
    	
    	String check_cardadd = "";
    	String check_errinfoString = "";
    	
    	//打印开始时间
    	System.out.println(new Date(System.currentTimeMillis()));
    	long startTime=System.currentTimeMillis(); 
    	
    	System.out.println(new Date(System.nanoTime()));
    	long startTimenano=System.nanoTime();
    	
    	String check_val = "";
    	check_val = ".*("+ruleCheckSingles.get(0).getCheckValue();
    	
	    for (int i = 1; i < ruleCheckSingles.size(); i++) {
	    	   	 
			 //check_val = ".*("+ruleCheckSingle.getCheckValue()+").*";
		     check_val+="|"+ruleCheckSingles.get(i).getCheckValue();
	    }
	    check_val += ").*";
	    
    	
    	//汉字长度函数测试
    	//for (int ii = 0; ii < checkAddresss.size() ; ii++) {
    	for (int ii = 0; ii < 11 ; ii++) {	
	      	  check_cardadd = checkAddresss.get(ii).getCardAddress();
	      	  //System.out.println("check_cardadd： "+check_cardadd);
	      	  
	      	//汉字长度函数测试
	      	  check_errinfoString = PubInfoCheckFunc.checkInfoType(check_cardadd,"chinese");
	      	  System.out.println("chinese： "+check_errinfoString);
	      	//数字测试 
	      	  check_errinfoString = PubInfoCheckFunc.checkInfoType(check_cardadd,"number");
	      	  System.out.println("number： "+check_errinfoString);
	      	//字母测试  
	      	  check_errinfoString = PubInfoCheckFunc.checkInfoType(check_cardadd,"letter");
	      	  System.out.println("letter： "+check_errinfoString);
	      	//字母测试  大写字母
		      check_errinfoString = PubInfoCheckFunc.checkInfoType(check_cardadd,"letterupper");
		      System.out.println("letterupper： "+check_errinfoString);
		    //字母测试  小写字母
			  check_errinfoString = PubInfoCheckFunc.checkInfoType(check_cardadd,"letterlower");
			  System.out.println("letterlower： "+check_errinfoString); 
			//不含特殊字符 测试
			  check_errinfoString = PubInfoCheckFunc.checkInfoRule(check_cardadd,check_val,"notin");
			  System.out.println("notin： "+check_errinfoString); 
			//仅限特殊字符   测试 
			  
			//特殊字符开始 测试  
			  
			//特殊字符结束 测试    
			      	  
		 }	
    	 
    	
    	//固定内容函数测试
    	check_cardadd = "重市巫县溪通城重庆";
    	check_val = "公司|重庆";
    	//1、包含规则集合校验
    	check_errinfoString = PubInfoCheckServ.checkInclude(check_cardadd,check_val);
		System.out.println("checkInclude： "+check_errinfoString); 
		//2、以规则集合开头校验
    	check_errinfoString = PubInfoCheckServ.checkHeadInclude(check_cardadd,check_val);
		System.out.println("checkHeadInclude： "+check_errinfoString); 
		//3、以规则集合结束校验
    	check_errinfoString = PubInfoCheckServ.checkTailInclude(check_cardadd,check_val);
		System.out.println("checkTailInclude： "+check_errinfoString); 
		//4、非规则集合校验
    	check_errinfoString = PubInfoCheckServ.checkRepInclude(check_cardadd,check_val);
		System.out.println("checkNoInclude： "+check_errinfoString); 
    	
		
		//测试链式函数
		check_cardadd = "重市巫县12溪通城d重庆A";
    	check_val = "公司|重庆";
		PubCheckServ pubCheckServ1 = new PubCheckServ();
		pubCheckServ1.setStr(check_cardadd).checkChinese("").checkNumber("").checkNoNumLetter("").
		              checkInclude(check_val).checkHeadInclude(check_val).checkTailInclude(check_val);
		
		System.out.println("checkNoInclude： "+pubCheckServ1.toString()); 
		
    	//打印结束时间
    	System.out.println(new Date(System.currentTimeMillis()));
    	long endTime=System.currentTimeMillis(); //获取结束时间
    	
    	System.out.println(new Date(System.nanoTime()));
    	long endTimenano=System.nanoTime();
    	 
    	System.out.println("程序运行时间： "+(endTime-startTime)+"ms");
    	System.out.println("程序运行时间： "+(endTimenano-startTimenano)+"ns");
    	
    	return "retestfun ok";
    }
    
    
    @GetMapping("/rulefuna")
    public String retestfuna(){
    	
    	String check_cardadd = "";
    	String check_val="";
    	String check_errinfoString = "";
    	
    	check_val = "重庆|公司";  //测试不是按照的词组进行的替换而是按照的字进行的替换
    	//check_cardadd = "公司重庆公市联通分公司分";
    	check_cardadd = "重庆公司司庆";
    	//check_val = ".*("+check_val+").*";
    	check_errinfoString = PubInfoCheckFunc.checkInfoRuleIn(check_cardadd,check_val,"checkin");
		System.out.println("checkin： "+check_errinfoString); 
		check_errinfoString = PubInfoCheckFunc.checkInfoRuleIn(check_cardadd,check_val,"checkstar");
		System.out.println("checkstar： "+check_errinfoString); 
		check_errinfoString = PubInfoCheckFunc.checkInfoRuleIn(check_cardadd,check_val,"checkend");
		System.out.println("checkend： "+check_errinfoString); 
		check_errinfoString = PubInfoCheckFunc.checkInfoRuleIn(check_cardadd,check_val,"checkout");
		System.out.println("checkout： "+check_errinfoString); 
		check_errinfoString = PubInfoCheckFunc.checkInfoRuleIn(check_cardadd,check_val,"checktest");
		System.out.println("checktest： "+check_errinfoString); 
		
    	return "retestfuna ok";
    }
    
    @GetMapping("/ruleckinfo")
    public String retestckinfo(){
    	
    	/*测试资料稽核 
    	String card_type = "C02";            //证件类型
    	String card_place = "cust_name";     //校验位置
    	int check_group_no = 1;              //规则组号
    	String card_info = "测试库";               //校验内容
    
    	System.out.println("retestckinfo： in"); 
    	
    	Sort sort = new Sort(Sort.Direction.ASC,"checkNo"); 
    	List<RuleCheckEngine> ruleCheckEngines = ruleCheckEngineRepository.findByCardTypeAndCardPlace(card_type,card_place,sort);
    	
    	RuleCheckEngine ruleCheckEngine = new RuleCheckEngine();
    	PubCheckServ pubCheckServ = new PubCheckServ();
	//	pubCheckServ.setStr(check_cardadd).checkChinese().checkNumber().checkNoNumLetter().
	//	              checkInclude(check_val).checkHeadInclude(check_val).checkTailInclude(check_val);
	 
    	for (int i = 0; i < ruleCheckEngines.size(); i++) {
   	   	     
    		 ruleCheckEngine = ruleCheckEngines.get(i);
    		 System.out.println("ruleCheckEngine： "+ruleCheckEngine.toString()); 
    		 
    	 	 if("rule".equals(ruleCheckEngine.getEngineType())){
    			 
    			 
    			 pubCheckServ.setStr(card_info).checkChinese();
    		 }else if("engine".equals(ruleCheckEngine.getEngineType())){
    			 pubCheckServ.setStr(card_info).checkChinese();
    		 }
    		  
	    } 
   		*/
        
       // List<CheckAddress> checkAddresss = checkAddressRepository.findAll();
    	 
    	/*测试资料稽核*/
    	String card_type = "C02";            //证件类型
    	String card_place = "cust_name";     //校验位置
    	int check_group_no = 1;              //规则组号
    	//String card_info = "测试库";               //校验内容
    	String check_val = "";              //稽核规则
    	
        /*分组进行稽核，不同的稽核组分开处理*/
        /*按照证件类型和组别提取稽核规则和数据*/
    	/*稽核数据*/
    	PageRequest pr = new PageRequest(0, 14);
        List<InfoCheckCard> infoCheckCards = infoCheckCardRepository.findByCertTypeAndCheckGroupNo(card_type,check_group_no,pr);
        System.out.println("infoCheckCards： "+infoCheckCards.size()); 
        InfoCheckCard infoCheckCard = new InfoCheckCard();
        PubCheckServ pubCheckServ = new PubCheckServ();
        /*校验名称*/ /*稽核规则*/
    	card_place = "cust_name";  
        List<String> ruleCheckTypes =ruleCheckSingleRepository.findByDisCheckType(card_type,card_place,check_group_no);
        for (int i = 0; i < ruleCheckTypes.size(); i++) {
        	System.out.println("ruleCheckSingles： "+ruleCheckTypes.get(i).toString());
        	
        	if("cust_name_allow".equals(ruleCheckTypes.get(i).toString())){
        		/*生成稽核规则*/
        		List<RuleCheckSingle> ruleCheckSingles =ruleCheckSingleRepository.findByCheckType("cust_name_allow");
        		System.out.println("ruleCheckSingles： "+ruleCheckSingles.size()); 
        		check_val = ruleCheckSingles.get(0).getCheckValue();
        		for (int ii = 1; ii < ruleCheckSingles.size(); ii++) {
        			check_val+="|"+ruleCheckSingles.get(ii).getCheckValue();
        		}
        		
        		System.out.println("check_val： "+check_val); 
        		/*稽核数据*/
        		for (int ii = 0; ii < infoCheckCards.size(); ii++) {
        			 pubCheckServ = new PubCheckServ();
        			 infoCheckCard = infoCheckCards.get(ii);
        			 //校验规则：排除允许字符后，校验非全数字，仅包含字母和数字
        			 pubCheckServ.setStr(infoCheckCard.getCertName()).checkRepInclude(check_val).checkAllNumber("").checkNumLetter("");
        			 System.out.println("pubCheckServ： "+pubCheckServ.toString()); 
        			 if(pubCheckServ.getExceptReport().length()>0){
        				 /*存在错误记录*/
        				 InfoCheckCardResult infoCheckCardResult = infoCheckCardResultRepository.findById(infoCheckCard.getId());
        				 if(infoCheckCardResult==null){
        					 infoCheckCardResult = new InfoCheckCardResult();
        					 infoCheckCardResult.setCertAdd(infoCheckCard.getCertAdd());
            				 infoCheckCardResult.setCertName(infoCheckCard.getCertName());
            				 infoCheckCardResult.setCertNum(infoCheckCard.getCertNum());
            				 infoCheckCardResult.setCertType(infoCheckCard.getCertType()); 
            				 infoCheckCardResult.setCheckGroupNo(infoCheckCard.getCheckGroupNo());
            				 infoCheckCardResult.setId(infoCheckCard.getId());
        					 
            				 infoCheckCardResult.setCheckContentInfo(pubCheckServ.getExceptStr());
            				 infoCheckCardResult.setCheckRuleInfo(pubCheckServ.getExceptReport());
        				 }else{
        					 infoCheckCardResult.setCheckContentInfo(infoCheckCardResult.getCheckContentInfo() + pubCheckServ.getExceptStr());
            				 infoCheckCardResult.setCheckRuleInfo(infoCheckCardResult.getCheckRuleInfo() + pubCheckServ.getExceptReport());
        				 } 
        				 System.out.println("infoCheckCardResult： "+infoCheckCardResult.toString()); 
        				 infoCheckCardResultRepository.save(infoCheckCardResult); 
        			 }
        		}
        	}
        }
         
        
        /*校验地址*/
        
        /*校验号码*/
        
    	return "retestfuna ok";
    }
    
    @GetMapping("/rule627")
    public String retestfun627(){

    	/*拼装引擎和参数*/
    	/*稽核数据*/
    	String card_type = "C02";            //证件类型
    	String card_place = "cust_name";     //校验位置
    	int check_group_no = 1;              //规则组号
    	String check_val = "";              //稽核规则
    	PageRequest pr = new PageRequest(0, 16);
        List<InfoCheckCard> infoCheckCards = infoCheckCardRepository.findByCertTypeAndCheckGroupNo(card_type,check_group_no,pr);
        System.out.println("infoCheckCards： "+infoCheckCards.size()); 
        InfoCheckCard infoCheckCard = new InfoCheckCard();
         
        /*组装规则JSON数组*/
        JSONArray jsonRuleAarr = new JSONArray();
        JSONObject jsonOne = new JSONObject();
                 	 
        /*校验名称*/ /*单点稽核规则*/
    	card_place = "cust_name";  
        List<String> ruleCheckTypes =ruleCheckSingleRepository.findByDisCheckType(card_type,card_place,check_group_no);
        for (int i = 0; i < ruleCheckTypes.size(); i++) {
        	System.out.println("ruleCheckSingles： "+ruleCheckTypes.get(i).toString());
            /*单个引擎中存在多个稽核点，先组装成一条规则数据*/
        	/*生成稽核规则*/
    		List<RuleCheckSingle> ruleCheckSingles =ruleCheckSingleRepository.findByCheckType(ruleCheckTypes.get(i).toString());
    		System.out.println("ruleCheckSingles： "+ruleCheckSingles.size()); 
    		check_val = ruleCheckSingles.get(0).getCheckValue();
    		for (int ii = 1; ii < ruleCheckSingles.size(); ii++) {
    			check_val+="|"+ruleCheckSingles.get(ii).getCheckValue();
    		}
    		System.out.println("check_val： "+check_val); 
    		/*组装成Json数组，传入引擎进行稽核*/
    		jsonOne = new JSONObject();
	    	jsonOne.put("check_type", ruleCheckTypes.get(i).toString());
	    	jsonOne.put("check_value", check_val);
	    	jsonRuleAarr.add(jsonOne);
        }
        /*引擎稽核*/
        Sort sort = new Sort(Sort.Direction.ASC,"checkNo"); 
        List<RuleCheckEngine> ruleCheckEngines =ruleCheckEngineRepository.findByCardTypeAndCardPlaceAndEngineTypeAndCheckGroupNo(card_type,card_place,"engine",check_group_no,sort);
        for (int i = 0; i < ruleCheckEngines.size(); i++) {
        	System.out.println("ruleCheckEngines： "+ruleCheckEngines.get(i).toString());
            /*组装成Json数组，传入引擎进行稽核*/
    		jsonOne = new JSONObject();
	    	jsonOne.put("check_type", ruleCheckEngines.get(i).getCheckType().toString());
	    	jsonOne.put("check_value", "");
	    	jsonRuleAarr.add(jsonOne);
        }
        
        System.out.println("jsonRuleAarr： "+jsonRuleAarr.toString());
        /*循环稽核数据，进行稽核处理*/
        /*稽核数据*/
		for (int i = 0; i < infoCheckCards.size(); i++) {
			 infoCheckCard = infoCheckCards.get(i); 
			 String ckResult = "";
			 JSONObject reJson = null;
			 String checkContentInfo = "";
			 String checkRuleInfo = "";
			 try {
				  ckResult = PubInfoCheckInvoke.checkInvokeJson(jsonRuleAarr,infoCheckCard.getCertName());
				  reJson = JSONObject.fromObject(ckResult);
				  checkContentInfo = reJson.get("checkContentInfo").toString();
				  checkRuleInfo = reJson.get("checkRuleInfo").toString();
				  if(checkRuleInfo.length()>0){
					  /*存在违规信息，记录结果数据*/
					  InfoCheckCardResult infoCheckCardResult = infoCheckCardResultRepository.findByCheckNo(infoCheckCard.getCheckNo());
					  if(infoCheckCardResult==null){
						 infoCheckCardResult = new InfoCheckCardResult();
						 infoCheckCardResult.setCertAdd(infoCheckCard.getCertAdd());
	    				 infoCheckCardResult.setCertName(infoCheckCard.getCertName());
	    				 infoCheckCardResult.setCertNum(infoCheckCard.getCertNum());
	    				 infoCheckCardResult.setCertType(infoCheckCard.getCertType()); 
	    				 infoCheckCardResult.setCheckGroupNo(infoCheckCard.getCheckGroupNo());
	    				 infoCheckCardResult.setCheckNo(infoCheckCard.getCheckNo());
						 
	    				 infoCheckCardResult.setCheckContentInfo(checkContentInfo);
	    				 infoCheckCardResult.setCheckRuleInfo(checkRuleInfo);
					  }else{
						 infoCheckCardResult.setCheckContentInfo(infoCheckCardResult.getCheckContentInfo() + checkContentInfo);
	    				 infoCheckCardResult.setCheckRuleInfo(infoCheckCardResult.getCheckRuleInfo() + checkRuleInfo);
					  } 
					  System.out.println("infoCheckCardResult： "+infoCheckCardResult.toString()); 
					  infoCheckCardResultRepository.save(infoCheckCardResult); 
				  }
			 } catch (Exception e) {
			 		// TODO Auto-generated catch block
			 		e.printStackTrace();
		     }
		}
           
    	return "retestfuna ok";
    }
    
}
