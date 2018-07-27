package com.atguigu.springboot.serv.sub4;

import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; 
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.springboot.entity.ApiDataKeyword;
import com.atguigu.springboot.entity.ApiDataPhrase;
import com.atguigu.springboot.entity.ApiDataTangshi;
import com.atguigu.springboot.entity.LibraryIdeaInfo;
import com.atguigu.springboot.entity.LibraryInfo;
import com.atguigu.springboot.entity.UserCollectLibraryInfo;
import com.atguigu.springboot.entity.UserLibraryIdeaInfo;
import com.atguigu.springboot.entity.UserLibraryInfo;
import com.atguigu.springboot.repository.ApiDataKeywordRepository;
import com.atguigu.springboot.repository.ApiDataPhraseRepository;
import com.atguigu.springboot.repository.ApiDataTangshiRepository;
import com.atguigu.springboot.repository.LibraryIdeaInfoRepository;
import com.atguigu.springboot.repository.LibraryInfoRepository;
import com.atguigu.springboot.repository.UserCollectLibraryInfoRepository;
import com.atguigu.springboot.repository.UserLibraryIdeaInfoRepository;
import com.atguigu.springboot.repository.UserLibraryInfoRepository;
import com.atguigu.springboot.serv.avatarApiInfo;
import com.atguigu.springboot.serv.jisuApiInfo;
import com.atguigu.springboot.serv.juheApiInfo;


@Service
public class ApiDataManageInfoSub4 {
	
	@Autowired
	private ApiDataTangshiRepository apiDataTangshiRepository;
	@Autowired
	private ApiDataKeywordRepository apiDataKeywordRepository;
	@Autowired
	private ApiDataPhraseRepository apiDataPhraseRepository;
	@Autowired
	private LibraryInfoRepository libraryInfoRepository;
	@Autowired
	private UserLibraryInfoRepository userLibraryInfoRepository;
	@Autowired
	private UserCollectLibraryInfoRepository userCollectLibraryInfoRepository;
	@Autowired
	private LibraryIdeaInfoRepository libraryIdeaInfoRepository;
	@Autowired
	private UserLibraryIdeaInfoRepository userLibraryIdeaInfoRepository;
	
	
	
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
	
	/**
	 * 2018-07-18  成语大全API接口，先查本地数据库中是否存在，不存在再查询接口，并将数据存入本地数据库
	 *   随机返回一个词语的例句，并随机返回 另外3个成语。
	 * 
	 * @param qryWord 搜索词
	 *          
	 * 
	 * @return  
	 */
	public String jisuApiQueryPhrase(String qryWord)
			throws UnknownHostException {

		 
		//查询本地数据库中是否存在
		String returnTex = "";		
		JSONArray jsonarray;
		JSONObject object = null;
		int rint;
		Random r = new Random();
		List<ApiDataKeyword> apiDataKeywords = new ArrayList<ApiDataKeyword>();
		ApiDataKeyword apiDataKeyword = new ApiDataKeyword();
		List<ApiDataPhrase> apiDataPhrases = new ArrayList<ApiDataPhrase>();
		ApiDataPhrase apiDataPhrase = new ApiDataPhrase();
		List<ApiDataPhrase> reApiDataPhrases = new ArrayList<ApiDataPhrase>();
		LibraryInfo libraryInfo = new LibraryInfo();
		
		 
		//随机取一个字  如果这个字存在调用过API接口的打标 则直接从词库中取词，否则调用API接口取词
		apiDataKeywords = apiDataKeywordRepository.findByQryOrder(20);
		if(apiDataKeywords.size()>0){
			rint = r.nextInt(apiDataKeywords.size());
			apiDataKeyword = apiDataKeywords.get(rint);
		}else{
			apiDataKeyword.setKeyword("天");
			apiDataKeyword.setApiFlag("0");
		}
		
		//获取词语
		if("1".equals(apiDataKeyword.getApiFlag())){
			//直接从本地库中获取
			apiDataPhrases = apiDataPhraseRepository.findByPhraseRand(apiDataKeyword.getKeyword(), 10);
		}else{
			//调用API获取词语信息
			returnTex = jisuApiInfo.jisuQueryKeyword(apiDataKeyword.getKeyword()); 
			object = JSONObject.fromObject(returnTex);
			System.out.println("jisuQueryTangshi object-->" + object.toString());
			//解析接口返回值
			if(object.getInt("status")!=0){
				returnTex = object.get("msg").toString();
				//通过关键字查询无数据   --返回特定信息  
            }else{
            	returnTex = object.get("result").toString();
            	//获取到正确信息存入本地数据库
            	jsonarray = JSONArray.fromObject(returnTex);  //将字符串转换为JSON数组
            	for (int i = 0; i < jsonarray.size(); i++) {   //循环获取识别的信息
            		object = jsonarray.getJSONObject(i);
            		
            		apiDataPhrase = new ApiDataPhrase();
            		apiDataPhrase.setPhrase(object.get("name").toString());
            		apiDataPhrase.setApiFlag("0");
            		
            		//剔除重复
            		if(1>apiDataPhraseRepository.queryByPhrase(apiDataPhrase.getPhrase())){
                		apiDataPhrases.add(apiDataPhrase); 
                		String stPhrase = apiDataPhrase.getPhrase();
                		//拆分词语为单个汉字并存入关键字搜索表中
                		for(int ii=0;ii < stPhrase.length() ; ii++) {
                			String stKey = stPhrase.substring(ii,ii+1);
                			//查询是否已经存在
                			if(apiDataKeywordRepository.queryByKeyword(stKey)>0){
                				//已经存在，不做处理
                			}else{
                				//不存在加入
                				apiDataKeyword = new ApiDataKeyword();
                				apiDataKeyword.setKeyword(stKey);
                				apiDataKeyword.setApiFlag("0");
                				apiDataKeywordRepository.save(apiDataKeyword);
                			}
                		}
            		}
  			    }
            	if(apiDataPhrases.size()>0){
            		apiDataPhraseRepository.save(apiDataPhrases);
                }
            	apiDataKeyword.setApiFlag("1");
            	apiDataKeywordRepository.save(apiDataKeyword);            	
            }
		}
		
		//从词库中随机取4个词，然后从4个词中随机取一个词进行例句获取
		  //先取一个进行详情获取   --期望获取一个有例子的词语
		for(int i=0;i < apiDataPhrases.size() ; i++) {
			rint = r.nextInt(apiDataPhrases.size());
			apiDataPhrase = apiDataPhrases.get(rint);
			//查看是否存在例句 存在例句退出循环
			if(!"".equals(apiDataPhrase.getExample())&&apiDataPhrase.getExample()!=null){
				//加入到放回信息中
				reApiDataPhrases.add(apiDataPhrase);
			    //剔除数组中已取出的元素
				apiDataPhrases.remove(rint); 
				break;
			}
			//若存在例句则说明之前调用过API直接返回数据，否则调用API获取相关信息
			if("1".equals(apiDataPhrase.getApiFlag())){
				//调用过API但无例子
			}else{
				//通过接口获取词语的相关信息
				//调用API获取词语信息
				returnTex = jisuApiInfo.jisuQueryWord(apiDataPhrase.getPhrase()); 
				object = JSONObject.fromObject(returnTex);
				System.out.println("jisuQueryTangshi object-->" + object.toString());
				//解析接口返回值
				if(object.getInt("status")!=0){
					returnTex = object.get("msg").toString();
					//通过关键字查询无数据   --返回特定信息  
	            }else{
	            	returnTex = object.get("result").toString();
	            	object = JSONObject.fromObject(returnTex);
	            	
	            	apiDataPhrase.setPronounce(object.get("pronounce").toString());
	            	apiDataPhrase.setContent(object.get("content").toString());
	            	apiDataPhrase.setComefrom(object.get("comefrom").toString());
	            	apiDataPhrase.setAntonym(object.get("antonym").toString());
	            	apiDataPhrase.setThesaurus(object.get("thesaurus").toString());
	            	apiDataPhrase.setApiFlag("1");
	            	apiDataPhrase.setYufa("");
	            	//将例句中的词语替换为～
	            	apiDataPhrase.setExample(object.get("example").toString().replaceAll(apiDataPhrase.getPhrase(), "～")); 
	            	 
	            	apiDataPhraseRepository.save(apiDataPhrase);

                    //查看是否存在例句 存在例句退出循环
	    			if(!"".equals(apiDataPhrase.getExample())&&apiDataPhrase.getExample()!=null){
	    				//加入到放回信息中
	    				reApiDataPhrases.add(apiDataPhrase);
	    			    //剔除数组中已取出的元素
	    				apiDataPhrases.remove(rint); 
	    				break;
	    			}
	            }
			}
		}
		
		//从词库中随机取3个词作为选项
		for(int i=0;i < 3 ; i++){
			if(apiDataPhrases.size()>0){
				rint = r.nextInt(apiDataPhrases.size());
				apiDataPhrase = apiDataPhrases.get(rint);
				//加入到放回信息中
				reApiDataPhrases.add(apiDataPhrase);
			    //剔除数组中已取出的元素
				apiDataPhrases.remove(rint); 
			}
		}
		
		//将信息记录到题库中
		if(reApiDataPhrases.size()==4){
			libraryInfo.setProblem(reApiDataPhrases.get(0).getExample());
			libraryInfo.setAnswer(reApiDataPhrases.get(0).getPhrase());
			libraryInfo.setConfuse1(reApiDataPhrases.get(1).getPhrase());
			libraryInfo.setConfuse2(reApiDataPhrases.get(2).getPhrase());
			libraryInfo.setConfuse3(reApiDataPhrases.get(3).getPhrase());
			libraryInfo.setBingoNum(0);
			libraryInfo.setMissNum(0);
			libraryInfo.setCollectNum(0);
			libraryInfo.setCollectUserNum(0);
			
			libraryInfoRepository.save(libraryInfo);
		}
		
		//查询本条题目的编号并返回小程序
		reApiDataPhrases.get(0).setId(libraryInfoRepository.queryIdByAnswer(libraryInfo.getAnswer())); 
		 
		jsonarray = JSONArray.fromObject(reApiDataPhrases);
		returnTex = jsonarray.toString();
		return returnTex;
	} 
	
	
	/**使用阿凡达数据库查询成语 使用聚合数据查询成语详情
	 * 2018-07-20  成语大全API接口，先查本地数据库中是否存在，不存在再查询接口，并将数据存入本地数据库
	 *   随机返回一个词语的例句，并随机返回 另外3个成语。
	 * 
	 * @param qryWord 搜索词
	 *          
	 * 
	 * @return  
	 */
	public String unionApiQueryPhrase(String qryWord)
			throws UnknownHostException {

		 
		//查询本地数据库中是否存在
		String returnTex = "";		
		JSONArray jsonarray;
		JSONObject object = null;
		int rint;
		Random r = new Random();
		List<ApiDataKeyword> apiDataKeywords = new ArrayList<ApiDataKeyword>();
		ApiDataKeyword apiDataKeyword = new ApiDataKeyword();
		List<ApiDataPhrase> apiDataPhrases = new ArrayList<ApiDataPhrase>();
		ApiDataPhrase apiDataPhrase = new ApiDataPhrase();
		List<ApiDataPhrase> reApiDataPhrases = new ArrayList<ApiDataPhrase>();
		LibraryInfo libraryInfo = new LibraryInfo();
		
		 
		//随机取一个字  如果这个字存在调用过API接口的打标 则直接从词库中取词，否则调用API接口取词
		apiDataKeywords = apiDataKeywordRepository.findByQryOrder(20);
		if(apiDataKeywords.size()>0){
			rint = r.nextInt(apiDataKeywords.size());
			apiDataKeyword = apiDataKeywords.get(rint);
		}else{
			apiDataKeyword.setKeyword("天");
			apiDataKeyword.setApiFlag("0");
		}
		
		System.out.println("jisuQueryTangshi getApiFlag-->" + apiDataKeyword.getApiFlag());
		
		//获取词语
		if(!"1".equals(apiDataKeyword.getApiFlag())){
			//调用API获取词语信息 将词语加入词库中
			returnTex = avatarApiInfo.avatarChengYuSearch(apiDataKeyword.getKeyword()); 
			object = JSONObject.fromObject(returnTex);
			System.out.println("jisuQueryTangshi object-->" + object.toString());
			//解析接口返回值
			if(object.getInt("total")<1){
				returnTex = object.get("error_code").toString();
				//通过关键字查询无数据   --返回特定信息  
            }else{
            	returnTex = object.get("result").toString();
            	//获取到正确信息存入本地数据库
            	jsonarray = JSONArray.fromObject(returnTex);  //将字符串转换为JSON数组
            	for (int i = 0; i < jsonarray.size(); i++) {   //循环获取识别的信息
            		object = jsonarray.getJSONObject(i);
            		
            		apiDataPhrase = new ApiDataPhrase();
            		apiDataPhrase.setPhrase(object.get("name").toString());
            		apiDataPhrase.setApiFlag("0");
            		
            		//剔除重复
            		if(1>apiDataPhraseRepository.queryByPhrase(apiDataPhrase.getPhrase())){
                		apiDataPhrases.add(apiDataPhrase); 
                	}
  			    }
            	if(apiDataPhrases.size()>0){
            		apiDataPhraseRepository.save(apiDataPhrases);
                }
            	apiDataKeyword.setApiFlag("1");
            	apiDataKeywordRepository.save(apiDataKeyword);            	
            }
		}
		
		//从本地库中获取20个随机词语
		apiDataPhrases = apiDataPhraseRepository.findByPhraseRand(apiDataKeyword.getKeyword(), 20);
 		
		//从词库中随机取4个词，然后从4个词中随机取一个词进行例句获取
		  //先取一个进行详情获取   --期望获取一个有例子的词语
		for(int i=0;i < apiDataPhrases.size() ; i++) {
			rint = r.nextInt(apiDataPhrases.size());
			apiDataPhrase = apiDataPhrases.get(rint);
			//剔除数组中已取出的元素
			apiDataPhrases.remove(rint); 
			//查看是否存在例句 存在例句退出循环
			if(!"".equals(apiDataPhrase.getExample())&&apiDataPhrase.getExample()!=null){
				//加入到返回信息中
				reApiDataPhrases.add(apiDataPhrase);
				//将词语加入关键字中
				String stPhrase = apiDataPhrase.getPhrase();
        		//拆分词语为单个汉字并存入关键字搜索表中
        		for(int ii=0;ii < stPhrase.length() ; ii++) {
        			String stKey = stPhrase.substring(ii,ii+1);
        			//查询是否已经存在
        			if(apiDataKeywordRepository.queryByKeyword(stKey)>0){
        				//已经存在，不做处理
        			}else{
        				//不存在加入
        				apiDataKeyword = new ApiDataKeyword();
        				apiDataKeyword.setKeyword(stKey);
        				apiDataKeyword.setApiFlag("0");
        				apiDataKeywordRepository.save(apiDataKeyword);
        			}
        		}
				break;
			}
			//若存在API调用标示，则说明之前调用过API，但这个词语没有例句
			if("1".equals(apiDataPhrase.getApiFlag())){
				//调用过API但无例子
			}else{
				//通过接口获取词语的相关信息
				//调用API获取词语信息
				returnTex = juheApiInfo.juheQueryWord(apiDataPhrase.getPhrase()); 
				object = JSONObject.fromObject(returnTex);
				System.out.println("jisuQueryTangshi object-->" + object.toString());
				//解析接口返回值
				if(object.getInt("error_code")!=0){
					returnTex = object.get("reason").toString();
					//通过关键字查询无数据   --返回特定信息  
	            }else{
	            	returnTex = object.get("result").toString();
	            	object = JSONObject.fromObject(returnTex);
	            	
	            	apiDataPhrase.setPronounce(object.get("pinyin").toString());
	            	apiDataPhrase.setContent(object.get("chengyujs").toString());
	            	apiDataPhrase.setComefrom(object.get("from_").toString());
	            	apiDataPhrase.setAntonym(object.get("fanyi").toString());
	            	apiDataPhrase.setThesaurus(object.get("tongyi").toString());
	            	apiDataPhrase.setApiFlag("1");
	            	apiDataPhrase.setYufa(object.get("yufa").toString());
	            	//将例句中的词语替换为～
	            	apiDataPhrase.setExample(object.get("example").toString().replaceAll(apiDataPhrase.getPhrase(), "～")); 
	            	 
	            	
	            	if("null".equals(apiDataPhrase.getAntonym())){
	            		apiDataPhrase.setAntonym("");
	            	}
	            	if("null".equals(apiDataPhrase.getThesaurus())){
	            		apiDataPhrase.setThesaurus("");
	            	}
	            	if("null".equals(apiDataPhrase.getYufa())){
	            		apiDataPhrase.setYufa("");
	            	}
	            	if("null".equals(apiDataPhrase.getExample())){
	            		apiDataPhrase.setExample("");
	            	}
	            	
	            	apiDataPhraseRepository.save(apiDataPhrase);

                    //查看是否存在例句 存在例句退出循环
	    			if(!"".equals(apiDataPhrase.getExample())&&apiDataPhrase.getExample()!=null){
	    				//加入到放回信息中
	    				reApiDataPhrases.add(apiDataPhrase);
	    				break;
	    			}
	            }
			}
		}
		
		//若循环完成都没找到例句，则随机获取一条有例句的信息
		if(reApiDataPhrases.size()==0){
			apiDataPhrase = apiDataPhraseRepository.findByExampleRand(2).get(0);
			reApiDataPhrases.add(apiDataPhrase);
		}
		
		//再从本地获取10条随机词作为选项
		apiDataPhrases = apiDataPhraseRepository.findByNotPhraseRand(apiDataKeyword.getKeyword(), 10);
		
		//从词库中随机取3个词作为选项
		for(int i=0;i < 3 ; i++){
			if(apiDataPhrases.size()>0){
				rint = r.nextInt(apiDataPhrases.size());
				apiDataPhrase = apiDataPhrases.get(rint);
				//加入到放回信息中
				reApiDataPhrases.add(apiDataPhrase);
			    //剔除数组中已取出的元素
				apiDataPhrases.remove(rint); 
			}
		}
		
		//将信息记录到题库中
		if(reApiDataPhrases.size()==4){
			libraryInfo.setProblem(reApiDataPhrases.get(0).getExample());
			libraryInfo.setAnswer(reApiDataPhrases.get(0).getPhrase());
			libraryInfo.setConfuse1(reApiDataPhrases.get(1).getPhrase());
			libraryInfo.setConfuse2(reApiDataPhrases.get(2).getPhrase());
			libraryInfo.setConfuse3(reApiDataPhrases.get(3).getPhrase());
			libraryInfo.setBingoNum(0);
			libraryInfo.setMissNum(0);
			libraryInfo.setCollectNum(0);
			libraryInfo.setCollectUserNum(0);
			
			libraryInfoRepository.save(libraryInfo);
		}
		
		//查询本条题目的编号并返回小程序
		reApiDataPhrases.get(0).setId(libraryInfoRepository.queryIdByAnswer(libraryInfo.getAnswer())); 
		 
		jsonarray = JSONArray.fromObject(reApiDataPhrases);
		returnTex = jsonarray.toString();
		return returnTex;
	} 
	
	
	/**获取本地 奇思妙想 中的题目 
	 * 2018-07-24  从本地库中获取题目
	 * 
	 * @param qryWord 搜索词
	 *          
	 * 
	 * @return  
	 */
	public String getIdeaPhrase(String qryWord)
			throws UnknownHostException {

		 
		//查询本地数据库中是否存在
		String returnTex = "";		
		JSONArray jsonarray;
		int rint;
		Random r = new Random();
		List<LibraryIdeaInfo> libraryIdeaInfos = new ArrayList<LibraryIdeaInfo>();
		LibraryIdeaInfo libraryIdeaInfo;
 
		
		//随机取一个字  如果这个字存在调用过API接口的打标 则直接从词库中取词，否则调用API接口取词
		libraryIdeaInfos = libraryIdeaInfoRepository.findByRand(10);
		if(libraryIdeaInfos.size()>0){
			rint = r.nextInt(libraryIdeaInfos.size());
			libraryIdeaInfo = libraryIdeaInfos.get(rint);
		}else{
			libraryIdeaInfo = new LibraryIdeaInfo();
		}
		
		//后续进行题目筛选
		
		//取出题目的参考答案
		libraryIdeaInfos = libraryIdeaInfoRepository.findByProblem(libraryIdeaInfo.getProblem(), 30);
		   
		jsonarray = JSONArray.fromObject(libraryIdeaInfos);
		returnTex = jsonarray.toString();
		return returnTex;
	} 
	
	/**获取本地 奇思妙想 中的题目  指定ID的题目 
	 * 2018-07-27  从本地库中获取题目
	 * 
	 * @param qryWord 搜索词
	 *          
	 * 
	 * @return  
	 */
	public String getIdeaChoosePhrase(Integer libraryId)
			throws UnknownHostException {

		 
		//查询本地数据库中是否存在
		String returnTex = "";		
		JSONArray jsonarray;
		List<LibraryIdeaInfo> libraryIdeaInfos = new ArrayList<LibraryIdeaInfo>();
		LibraryIdeaInfo libraryIdeaInfo;
		
		//获取指定ID的数据
		libraryIdeaInfos = libraryIdeaInfoRepository.findById(libraryId);
		if(libraryIdeaInfos.size()>0){
			libraryIdeaInfo = libraryIdeaInfos.get(0);
		}else{
			libraryIdeaInfo = new LibraryIdeaInfo();
		}
		 
		//取出题目的参考答案
		libraryIdeaInfos = libraryIdeaInfoRepository.findByProblem(libraryIdeaInfo.getProblem(), 30);
		   
		jsonarray = JSONArray.fromObject(libraryIdeaInfos);
		returnTex = jsonarray.toString();
		return returnTex;
	} 
	
	/**
	 * 2018-07-16  唐诗API接口， 阿凡达数据
	 *    先查本地数据库中是否存在，不存在再查询接口，并将数据存入本地数据库
	 * 
	 * @param qryWord 搜索词
	 *          
	 * 
	 * @return  
	 */
	public String avatarApiQueryTangshi(String qryWord)
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
			returnTex = avatarApiInfo.avatarQueryTangshi(qryWord);
			
			object = JSONObject.fromObject(returnTex);
			System.out.println("avatarApiInfo object-->" + object.toString());
			//解析接口返回值
			if(object.getInt("total")==0){
				returnTex = object.get("error_code").toString();
				if("0".equals(returnTex)){
					returnTex = "未找到诗句";
				}
				apiDataTangshi = new ApiDataTangshi() ;
				
				apiDataTangshi.setContent(returnTex);
				apiDataTangshis.add(apiDataTangshi); 
            }else{
            	returnTex = object.get("result").toString();
            	String searchId = "";
            	//String searchName = "";
            	//处理掉特殊符号
        		String checkRule = "[\\\\<p/br>&nbsp;\\[1234567890\\]]+";
            	jsonarray = JSONArray.fromObject(returnTex);  //将字符串转换为JSON数组
            	for (int i = 0; i < jsonarray.size(); i++) {   //循环获取识别的信息
            		object = jsonarray.getJSONObject(i);
  					searchId = object.get("id").toString();
            		//searchName = object.get("name").toString();
            		/*通过id获取单个唐诗宋词详细解释*/
            		returnTex = avatarApiInfo.avatarQueryId(searchId);
            		object = JSONObject.fromObject(returnTex);
            		returnTex = object.get("result").toString();
            		object = JSONObject.fromObject(returnTex); 
            		
                    apiDataTangshi = new ApiDataTangshi() ;
            		
            		apiDataTangshi.setTitle(object.get("biaoti").toString().replaceAll(checkRule, ""));
            		apiDataTangshi.setType("");
            		apiDataTangshi.setContent(object.get("neirong").toString().replaceAll(checkRule, ""));
            		apiDataTangshi.setExplanation(object.get("jieshao").toString().replaceAll(checkRule, ""));
            		apiDataTangshi.setAppreciation("");
            		apiDataTangshi.setAuthor(object.get("zuozhe").toString().replaceAll(checkRule, ""));
            		 
            		apiDataTangshis.add(apiDataTangshi);
            	}
            	apiDataTangshiRepository.save(apiDataTangshis);
            } 
		}
		jsonarray = JSONArray.fromObject(apiDataTangshis);
		returnTex = jsonarray.toString();
		return returnTex;
	} 
	
	/**
	 * 2018-07-19  玩家答题信息记录
	 * 
	 * @param reqOpenId 玩家ID
	 *        libraryId 题目ID
	 *        judgeFlag 回答标志  1 正确 0 错误
	 * 
	 * @return  
	 */
	@Transactional()
	public String savePlayerAnswerInfo(String reqOpenId,Integer libraryId,String judgeFlag)
			throws UnknownHostException {

		 
		//查询本地数据库中是否存在
		String returnTex = "";		
		UserLibraryInfo userLibraryInfo;
		LibraryInfo libraryInfo = new LibraryInfo();
		
		
		//查询本题是否为用户的初试
		if(userLibraryInfoRepository.queryByIdAndLibrary(reqOpenId, libraryId)>0){
			
		}else{
			//之前无记录，用户首次回答本题，记录本次的作答情况，并增加题目的评价体系
			userLibraryInfo = new UserLibraryInfo();
			
			userLibraryInfo.setOpenId(reqOpenId);
			userLibraryInfo.setLibraryId(libraryId);
			userLibraryInfo.setFirstJudgeFlag(judgeFlag);
			
			userLibraryInfoRepository.save(userLibraryInfo);
			
			//只有使用首次答题的数据作为题目难易的依据
			libraryInfo = libraryInfoRepository.findById(libraryId).get(0);
			if("1".equals(judgeFlag)){
				libraryInfo.setBingoNum(libraryInfo.getBingoNum()+1);
			}else{
				libraryInfo.setMissNum(libraryInfo.getMissNum()+1);
			}
			libraryInfoRepository.save(libraryInfo);
		}
		 
		returnTex = "OK";
		return returnTex;
	} 
	
	/**
	 * 2018-07-25  奇思妙想玩家答题信息记录
	 * 
	 * @param reqOpenId 玩家ID
	 *        libraryId 题目ID
	 *        ideaInfo 回答标志  1 正确 0 错误
	 * 
	 * @return  
	 */
	@Transactional()
	public String savePlayerIdeaInfo(String reqOpenId,Integer libraryId,String ideaInfo)
			throws UnknownHostException {

		 
		//查询本地数据库中是否存在
		String returnTex = "";		
		JSONObject object = null;
		JSONArray jsonarray;
		Random r = new Random();
		LibraryIdeaInfo libraryIdeaInfo = new LibraryIdeaInfo();
		LibraryIdeaInfo libraryIdeaInfoNew = new LibraryIdeaInfo();
		List<LibraryIdeaInfo> libraryIdeaInfos = new ArrayList<LibraryIdeaInfo>();
		UserLibraryIdeaInfo userLibraryIdeaInfo = new UserLibraryIdeaInfo();
		List<UserLibraryIdeaInfo> userLibraryIdeaInfos = new ArrayList<UserLibraryIdeaInfo>();
		ApiDataPhrase apiDataPhrase = new ApiDataPhrase();
		List<ApiDataPhrase> apiDataPhrases = new ArrayList<ApiDataPhrase>();
		
		
		//判断答案是否为成语
		//从本地库中查找是否存在输入得成语 
		if(apiDataPhraseRepository.queryByPhrase(ideaInfo)<1){
			//本地库中不存在调用 API进行查询
			//调用API获取词语信息 将词语加入词库中
			returnTex = avatarApiInfo.avatarChengYuSearch(ideaInfo); 
			object = JSONObject.fromObject(returnTex);
			//解析接口返回值
			if(object.getInt("total")<1){
				returnTex = object.get("error_code").toString();
				//接口中不存在，视为输入不合规，返回提示信息
				returnTex = "ERROR";
				return returnTex;
			}else{
	            returnTex = object.get("result").toString();
	            //获取到正确信息存入本地数据库
	            jsonarray = JSONArray.fromObject(returnTex);  //将字符串转换为JSON数组
	            for (int i = 0; i < jsonarray.size(); i++) {   //循环获取识别的信息
	             	object = jsonarray.getJSONObject(i);
	            		
	            	apiDataPhrase = new ApiDataPhrase();
	            	apiDataPhrase.setPhrase(object.get("name").toString());
	            	apiDataPhrase.setApiFlag("0");
	            	apiDataPhrases.add(apiDataPhrase); 
	  		    }
	            if(apiDataPhrases.size()>0){
	             	apiDataPhraseRepository.save(apiDataPhrases);
	            }
	        }
		} 
		
		
		//记录用户答题信息
		userLibraryIdeaInfos = userLibraryIdeaInfoRepository.findByIdAndLibrary(reqOpenId, libraryId);
        if(userLibraryIdeaInfos.size()>0){
			//用户对本题给出过相同的答案增加答案次数
        	userLibraryIdeaInfo = userLibraryIdeaInfos.get(0);
        	
        	userLibraryIdeaInfo.setIdeaInfoTimes(userLibraryIdeaInfo.getIdeaInfoTimes()+1);
        	userLibraryIdeaInfoRepository.save(userLibraryIdeaInfo);
		}else{
			//增加记录
			userLibraryIdeaInfo = new UserLibraryIdeaInfo();
			
			userLibraryIdeaInfo.setOpenId(reqOpenId);
			userLibraryIdeaInfo.setLibraryId(libraryId);
			userLibraryIdeaInfo.setIdeaInfo(ideaInfo);
			userLibraryIdeaInfo.setIdeaInfoTimes(1);
			
			userLibraryIdeaInfoRepository.save(userLibraryIdeaInfo);
		}
		//判断是否已经存在相同的答案
		libraryIdeaInfo = libraryIdeaInfoRepository.findById(libraryId).get(0);
		libraryIdeaInfos = libraryIdeaInfoRepository.findByProblemAndAnswer(libraryIdeaInfo.getProblem(),ideaInfo, 3);
		if(libraryIdeaInfos.size()>0){
			//已有相同的答案，增加次数
			libraryIdeaInfoNew = libraryIdeaInfos.get(0);
			//用户作答给出的已有答案排名+1
			libraryIdeaInfoNew.setThinkRank(libraryIdeaInfoNew.getThinkRank()+1); 
			if(userLibraryIdeaInfo.getIdeaInfoTimes()==1){
				//用户首次作答给出的已有答案排名再+2
				libraryIdeaInfoNew.setThinkRank(libraryIdeaInfoNew.getThinkRank()+2);  
			}
			
			if(userLibraryIdeaInfo.getIdeaInfoTimes()==1){
				//用户首次使用答案
				libraryIdeaInfoNew.setUseNum(libraryIdeaInfoNew.getUseNum()+1);
			}
			libraryIdeaInfoNew.setUseCount(libraryIdeaInfoNew.getUseCount()+1);
		}else{
			//新答案增加记录
			libraryIdeaInfoNew = new LibraryIdeaInfo();
			
			libraryIdeaInfoNew.setProblem(libraryIdeaInfo.getProblem());
			libraryIdeaInfoNew.setThinkAnswer(ideaInfo);
			libraryIdeaInfoNew.setUseCount(1);
			libraryIdeaInfoNew.setUseNum(1);
			libraryIdeaInfoNew.setThinkRank(2);  //新答案排名为2
			
			libraryIdeaInfoRepository.save(libraryIdeaInfoNew);
		}
		
		//计算积分,先使用随机值，后续再研究积分规则 
		returnTex = Integer.toString(r.nextInt(10)+libraryIdeaInfoNew.getThinkRank());
		
		
		//returnTex = "OK";
		return returnTex;
	} 
	
	/**
	 * 2018-07-25  获取奇思妙想答案排名信息
	 * 
	 * @param reqOpenId 玩家ID
	 *        libraryId 题目ID
	 * 
	 * @return  
	 */
	@Transactional()
	public String getIdeaRankInfo(String reqOpenId,Integer libraryId)
			throws UnknownHostException {

		 
		//查询本地数据库中是否存在
		String returnTex = "";
		JSONArray jsonarray;
		LibraryIdeaInfo libraryIdeaInfo;
		List<LibraryIdeaInfo> libraryIdeaInfos = new ArrayList<LibraryIdeaInfo>();
		
		
		libraryIdeaInfo = libraryIdeaInfoRepository.findById(libraryId).get(0);
		
		libraryIdeaInfos = libraryIdeaInfoRepository.findByProblemOrder(libraryIdeaInfo.getProblem(), 20);
		
		jsonarray = JSONArray.fromObject(libraryIdeaInfos);
		returnTex = jsonarray.toString();
		return returnTex;
	} 
	
	/**
	 * 2018-07-25  奇思妙想答案赞赏
	 * 
	 * @param reqOpenId 玩家ID
	 *        libraryId 题目答案ID
	 * 
	 * @return  
	 */
	@Transactional()
	public String saveIdeaCollectInfo(String reqOpenId,Integer libraryId)
			throws UnknownHostException {

		
		// 获取当日日期
		Date now = new Date();
		SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy-MM-dd"); // 可以方便地修改日期格式
		String operDay = dayFormat.format(now);
		
		//查询本地数据库中是否存在
		String returnTex = "";
		LibraryIdeaInfo libraryIdeaInfo;
		UserCollectLibraryInfo userCollectLibraryInfo;
		String collectType = "idea";
		
		
		libraryIdeaInfo = libraryIdeaInfoRepository.findById(libraryId).get(0);
		//查询用户是否已经收藏过本题
		if(userCollectLibraryInfoRepository.queryByIdAndLibraryType(reqOpenId, libraryId,collectType)>0){
			//收藏过则增加收藏次数
			userCollectLibraryInfo = userCollectLibraryInfoRepository.findByIdAndLibraryType(reqOpenId, libraryId,collectType, 2).get(0);
			userCollectLibraryInfo.setCollectNum(userCollectLibraryInfo.getCollectNum()+1);
			 
		}else{
			//首次收藏，记录用户收藏信息
			userCollectLibraryInfo = new UserCollectLibraryInfo();
			
			userCollectLibraryInfo.setOpenId(reqOpenId);
			userCollectLibraryInfo.setLibraryId(libraryId);
			userCollectLibraryInfo.setCollectNum(1);
			userCollectLibraryInfo.setCollectDate(operDay); 
			userCollectLibraryInfo.setCollectType(collectType);
			
			//增肌题库中题目的收藏人数。
			libraryIdeaInfo.setCollectUserNum(libraryIdeaInfo.getCollectUserNum()+1);
			//用户首次赞赏已有答案排名+4
			libraryIdeaInfo.setThinkRank(libraryIdeaInfo.getThinkRank()+4);
		}
		//增加题库中题目的收藏次数
		libraryIdeaInfo.setCollectNum(libraryIdeaInfo.getCollectNum()+1);
		//用户重复赞赏已有答案排名+1   后续考虑重复赞赏的处理
		libraryIdeaInfo.setThinkRank(libraryIdeaInfo.getThinkRank()+1);
		
		
		//记录用户收藏信息
		userCollectLibraryInfoRepository.save(userCollectLibraryInfo);
		//修改题库收藏信息
		libraryIdeaInfoRepository.save(libraryIdeaInfo);
		 
		returnTex = "OK";
		return returnTex;
	} 
	
	/**
	 * 2018-07-20  收藏题目信息
	 * 
	 * @param reqOpenId 玩家ID
	 *        libraryId 题目ID
	 * 
	 * @return  
	 */
	@Transactional()
	public String savePlayerCollectInfo(String reqOpenId,Integer libraryId)
			throws UnknownHostException {

		
		// 获取当日日期
		Date now = new Date();
		SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy-MM-dd"); // 可以方便地修改日期格式
		String operDay = dayFormat.format(now);
		
		//查询本地数据库中是否存在
		String returnTex = "";
		LibraryInfo libraryInfo;
		UserCollectLibraryInfo userCollectLibraryInfo;
		String collectType = "phrase";
		
		
		libraryInfo = libraryInfoRepository.findById(libraryId).get(0);
		//查询用户是否已经收藏过本题
		if(userCollectLibraryInfoRepository.queryByIdAndLibraryType(reqOpenId, libraryId,collectType)>0){
			//收藏过则增加收藏次数
			userCollectLibraryInfo = userCollectLibraryInfoRepository.findByIdAndLibraryType(reqOpenId, libraryId,collectType, 2).get(0);
			userCollectLibraryInfo.setCollectNum(userCollectLibraryInfo.getCollectNum()+1);
			 
		}else{
			//首次收藏，记录用户收藏信息
			userCollectLibraryInfo = new UserCollectLibraryInfo();
			
			userCollectLibraryInfo.setOpenId(reqOpenId);
			userCollectLibraryInfo.setLibraryId(libraryId);
			userCollectLibraryInfo.setCollectNum(1);
			userCollectLibraryInfo.setCollectDate(operDay); 
			userCollectLibraryInfo.setCollectType(collectType);
			
			//增肌题库中题目的收藏人数。
			libraryInfo.setCollectUserNum(libraryInfo.getCollectUserNum()+1);
		}
		//增加题库中题目的收藏次数
		libraryInfo.setCollectNum(libraryInfo.getCollectNum()+1);
		
		//记录用户收藏信息
		userCollectLibraryInfoRepository.save(userCollectLibraryInfo);
		//修改题库收藏信息
		libraryInfoRepository.save(libraryInfo);
		 
		returnTex = "OK";
		return returnTex;
	} 
	
}