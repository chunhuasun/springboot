package com.atguigu.springboot.serv.sub4;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; 

import com.atguigu.springboot.entity.ApiDataTangshi;
import com.atguigu.springboot.entity.InfoCheckCardResult;
import com.atguigu.springboot.entity.TenxunCosInfo;
import com.atguigu.springboot.entity.TrainItemOpus;
import com.atguigu.springboot.repository.ApiDataTangshiRepository;
import com.atguigu.springboot.repository.TenxunCosInfoRepository;
import com.atguigu.springboot.repository.TrainItemOpusRepository;
import com.atguigu.springboot.serv.jisuApiInfo;


@Service
public class ApiDataManageInfoSub4 {
	
	@Autowired
	private ApiDataTangshiRepository apiDataTangshiRepository;
	
	/**
	 * 2018-07-13  唐诗API接口，先查本地数据库中是否存在，不存在再查询接口，并将数据存入本地数据库
	 * 
	 * @param qryWord 搜索词
	 *          
	 * 
	 * @return  
	 */
	public String jisuApiQueryTangshi(String qryWord)
			throws UnknownHostException {

		 
		//查询本地数据库中是否存在
		String returnTex = "";		
		JSONArray jsonarray;
		JSONObject object = null;
		List<ApiDataTangshi> apiDataTangshis = new ArrayList<ApiDataTangshi>();
		ApiDataTangshi apiDataTangshi;
		
		apiDataTangshis = apiDataTangshiRepository.findByQryword(qryWord, 10);
		if(apiDataTangshis.size()>0){
			//jsonarray = JSONArray.fromObject(apiDataTangshis);
			//returnTex = jsonarray.toString();
		}else{
			//调用接口查询
			returnTex = jisuApiInfo.jisuQueryTangshi(qryWord);
			
			object = JSONObject.fromObject(returnTex);
			System.out.println("jisuQueryTangshi object-->" + object.toString());
			//解析接口返回值
			if(object.getInt("status")!=0){
				returnTex = object.get("msg").toString();
				apiDataTangshi = new ApiDataTangshi() ;
				
				apiDataTangshi.setContent(returnTex);
				apiDataTangshis.add(apiDataTangshi); 
            }else{
            	returnTex = object.get("result").toString();
            	//获取到正确信息存入本地数据库
            	object = JSONObject.fromObject(returnTex);
            	returnTex = object.get("list").toString();
            	
            	//处理掉特殊符号
        		String checkRule = "[\\\\<p/br>&nbsp;]+";
        		
            	jsonarray = JSONArray.fromObject(returnTex);  //将字符串转换为JSON数组
            	for (int i = 0; i < jsonarray.size(); i++) {   //循环获取识别的信息
            		object = jsonarray.getJSONObject(i);
  					
            		apiDataTangshi = new ApiDataTangshi() ;
            		
            		apiDataTangshi.setTitle(object.get("title").toString().replaceAll(checkRule, ""));
            		apiDataTangshi.setType(object.get("type").toString().replaceAll(checkRule, ""));
            		apiDataTangshi.setContent(object.get("content").toString().replaceAll(checkRule, ""));
            		apiDataTangshi.setExplanation(object.get("explanation").toString().replaceAll(checkRule, ""));
            		apiDataTangshi.setAppreciation(object.get("appreciation").toString().replaceAll(checkRule, ""));
            		apiDataTangshi.setAuthor(object.get("author").toString().replaceAll(checkRule, ""));
            		 
            		apiDataTangshis.add(apiDataTangshi); 
  			    }
            	apiDataTangshiRepository.save(apiDataTangshis);
            } 
		}
		jsonarray = JSONArray.fromObject(apiDataTangshis);
		returnTex = jsonarray.toString();
		return returnTex;
	} 
}