package com.atguigu.springboot.servsub4;

import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

import javax.persistence.Column;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest; 
import org.springframework.stereotype.Service; 

import sun.misc.BASE64Decoder;
   




import com.atguigu.springboot.entity.BaiduTokenPara;
import com.atguigu.springboot.entity.TenxunCosInfo;
import com.atguigu.springboot.entity.TrainItemInfo;
import com.atguigu.springboot.entity.TrainItemOpus;
import com.atguigu.springboot.entity.TrainItemOpusComment;
import com.atguigu.springboot.entity.TrainItemOpusHome;
import com.atguigu.springboot.entity.TrainItemSaveInfo;
import com.atguigu.springboot.entity.TrainItemTimeInfo;
import com.atguigu.springboot.entity.TrainPlanInfo;
import com.atguigu.springboot.entity.WxSmallLogin;
import com.atguigu.springboot.entity.WxSmallServInfo;
import com.atguigu.springboot.entity.WxSmallServLock;
import com.atguigu.springboot.entity.WxSmallServLog;
import com.atguigu.springboot.entity.WxSmallUser;
import com.atguigu.springboot.entity.WxSmallUserAction; 
import com.atguigu.springboot.repository.BaiduTokenParaRepository;
import com.atguigu.springboot.repository.TenxunCosInfoRepository;
import com.atguigu.springboot.repository.TrainDictationInfoRepository;
import com.atguigu.springboot.repository.TrainDictationWordRepository;
import com.atguigu.springboot.repository.TrainItemAngleRepository;
import com.atguigu.springboot.repository.TrainItemInfoRepository;
import com.atguigu.springboot.repository.TrainItemKnowledgeRepository;
import com.atguigu.springboot.repository.TrainItemMusictimerRepository;
import com.atguigu.springboot.repository.TrainItemOpusCommentRepository;
import com.atguigu.springboot.repository.TrainItemOpusHomeRepository;
import com.atguigu.springboot.repository.TrainItemOpusRepository;
import com.atguigu.springboot.repository.TrainItemPublishRepository;
import com.atguigu.springboot.repository.TrainItemSaveInfoRepository;
import com.atguigu.springboot.repository.TrainItemTaskCommentRepository;
import com.atguigu.springboot.repository.TrainItemTaskRepository;
import com.atguigu.springboot.repository.TrainItemTimeAngleRepository;
import com.atguigu.springboot.repository.TrainItemTimeInfoRepository;
import com.atguigu.springboot.repository.TrainItemTitleRepository;
import com.atguigu.springboot.repository.TrainPlanInfoRepository;
import com.atguigu.springboot.repository.WxSmallLoginRepository;
import com.atguigu.springboot.repository.WxSmallServInfoRepository;
import com.atguigu.springboot.repository.WxSmallServLockRepository;
import com.atguigu.springboot.repository.WxSmallServLogRepository;
import com.atguigu.springboot.repository.WxSmallUserActionRepository;
import com.atguigu.springboot.repository.WxSmallUserRepository; 
import com.atguigu.springboot.serv.ImageUtils;
import com.atguigu.springboot.serv.baiduApiInfo;
import com.atguigu.springboot.serv.tenxunApiCos;

 

@Service
public class DataManageInfoSub4 {
	
	@Autowired
	private WxSmallUserRepository wxSmallUserRepository;
	@Autowired
	private WxSmallLoginRepository wxSmallLoginRepository;
	@Autowired
	private WxSmallUserActionRepository wxSmallUserActionRepository;
	@Autowired
	private WxSmallServLockRepository wxSmallServLockRepository;
	@Autowired
	private WxSmallServInfoRepository wxSmallServInfoRepository;
	@Autowired
	private WxSmallServLogRepository wxSmallServLogRepository;
	@Autowired
	private TrainPlanInfoRepository trainPlanInfoRepository;
	@Autowired
	private TrainItemInfoRepository trainItemInfoRepository;
	@Autowired
	private TrainItemSaveInfoRepository trainItemSaveInfoRepository;
	@Autowired
	private TrainItemTimeInfoRepository trainItemTimeInfoRepository;
	@Autowired
	private TrainItemTitleRepository trainItemTitleRepository;
	@Autowired
	private TrainItemAngleRepository trainItemAngleRepository;
	@Autowired
	private TrainItemTimeAngleRepository trainItemTimeAngleRepository;
	@Autowired
	private TrainDictationWordRepository trainDictationWordRepository;
	@Autowired
	private TrainDictationInfoRepository trainDictationInfoRepository;
	@Autowired
	private TrainItemKnowledgeRepository trainItemKnowledgeRepository;
	@Autowired
	private TrainItemMusictimerRepository trainItemMusictimerRepository;
	@Autowired
	private TrainItemPublishRepository trainItemPublishRepository;
	@Autowired
	private TrainItemOpusRepository trainItemOpusRepository;
	@Autowired
	private TrainItemOpusCommentRepository trainItemOpusCommentRepository;
	@Autowired
	private TrainItemOpusHomeRepository trainItemOpusHomeRepository;
	@Autowired
	private TrainItemTaskRepository trainItemTaskRepository;
	@Autowired
	private TrainItemTaskCommentRepository trainItemTaskCommentRepository;
	@Autowired
	private BaiduTokenParaRepository baiduTokenParaRepository;
	@Autowired
	private TenxunCosInfoRepository tenxunCosInfoRepository;
	
	/**
	 * 2017-09-08 存储小程序用户登录信息 获取指定用户特定观察项目的指标数据
	 * 
	 * @param requestInfo
	 *            传入JSON串进行控制 通过入参控制不同的查看方式 查询 计划列表 查询 项目列表 查询项目内容
	 * 
	 * @return 返回训练计划内容信息
	 */
	public String savewxsmallopenid(String requestInfo,String path,String OpenId)
			throws UnknownHostException {

		String returnTex = "OK";
		 
		// 获取当前日期
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss"); // 可以方便地修改日期格式
		String oper_info_date = dateFormat.format(now);
		SimpleDateFormat timeStampmId = new SimpleDateFormat("yyyyMMddHHmmssSSS"); // 可以方便地修改日期格式
		String timeStamp = timeStampmId.format(now);

		// 解析查询条件
		JSONObject object = null;
		object = JSONObject.fromObject(requestInfo);
		long queryCount = 0;
		 
		System.out.println("OpenId---"+OpenId);
		  
		// 查询用户ID是否存在，不存在则增加
		List<WxSmallUser> wxSmallUsers =wxSmallUserRepository.findByOpenId(OpenId);
		if(wxSmallUsers.size()<1){
			// 新用户增加用户信息  先增加信息再更新头像信息
			String imageInfo = "";
			String saveFileName = "trainPlan/userFile/"+timeStamp+".jpg";
			String cos_url = "";
			cos_url = "http://mycosgz-1253822284.cosgz.myqcloud.com/mycosgz/"+saveFileName;
			
			WxSmallUser wxSmallUser = new WxSmallUser();
			wxSmallUser.setOpenId(OpenId);
			wxSmallUser.setCity(object.get("city").toString());
			wxSmallUser.setCountry(object.get("country").toString());
			wxSmallUser.setGender(object.get("gender").toString());
			wxSmallUser.setNickName(object.get("nickName").toString());
			wxSmallUser.setProvince(object.get("province").toString());
			wxSmallUser.setAvatarUrl(imageInfo);
			wxSmallUser.setCosUrl(cos_url);
			wxSmallUser.setUserGroupType("Lvl0");
			wxSmallUser.setUserCareDate(oper_info_date);
			wxSmallUser.setTimeStamp(timeStamp);
			if(object.has("userSystemInfo")){
				wxSmallUser.setUserSystemInfo(object.get("userSystemInfo").toString());
			}else{
				wxSmallUser.setUserSystemInfo(""); 
			}
			wxSmallUserRepository.save(wxSmallUser); 
			
			try {
				URL url = new URL(object.get("avatarUrl").toString());
				imageInfo = ImageUtils.encodeImgageToBase64(url);
				
				//使用流数据方式存储文件 
				BASE64Decoder decoder = new BASE64Decoder();
				byte[] decoderBytes = decoder.decodeBuffer(imageInfo);
				  
		    	cos_url = tenxunApiCos.sendFileCosBufer(decoderBytes,saveFileName);
				 
				//数据优化将图片存储到COS中，只返回URL地址。减小传输量
				//将Base64位编码的图片进行解码，并保存到指定目录
				/*
				String imgName = "avatarUrl0001.jpg";
				ImageUtils.decodeBase64ToImage(imageInfo,path, imgName);
				  
				cos_url = tenxunApiCos.sendFileCos(path+imgName,saveFileName); 
				*/
				//object=JSONObject.fromObject(cos_url);
				//cos_url = object.get("data").toString(); 
				//object=JSONObject.fromObject(cos_url);
				//cos_url = object.get("access_url").toString();
				
			} catch (IOException e) {
				e.printStackTrace();
			} 
        }

		// 记录登录日志
		queryCount = wxSmallLoginRepository.count();
		queryCount += 1;
		// 添加数据
		WxSmallLogin wxSmallLogin = new WxSmallLogin();
		wxSmallLogin.setOpenId(OpenId);
		wxSmallLogin.setIngressId(queryCount);
		wxSmallLogin.setOperDate(oper_info_date);
		
		wxSmallLoginRepository.save(wxSmallLogin);
  
		JSONObject jsonmap = new JSONObject();
		jsonmap.element("expires_in", "7200");
		jsonmap.element("openid", OpenId);
		returnTex = jsonmap.toString();
	    return returnTex;
	}
	
	
	/**
	 * 2017-06-06 腾讯云COS对象存储 存储对象信息
	 * 
	 * @param fromUser
	 *            用户信息
	 * 
	 * @return 返回信息逻辑处理标志
	 */
	public String savetxCOSData(String fromUser, String cosInfo,
			String cosUrlInfo, String cosSize) throws UnknownHostException {

		String returnTex = "OK";
		
		 
		// 获取当日日期
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss"); // 可以方便地修改日期格式
		SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy-MM-dd"); // 可以方便地修改日期格式
		String operDay = dayFormat.format(now);
		SimpleDateFormat dayFormatId = new SimpleDateFormat("yyyyMMdd"); // 可以方便地修改日期格式
		String operDayId = dayFormatId.format(now);
		SimpleDateFormat daytFormatId = new SimpleDateFormat("yyyyMMddHHmmss"); // 可以方便地修改日期格式
		String operDayt = daytFormatId.format(now);

		long ingressIdCount = tenxunCosInfoRepository.count();

		ingressIdCount += Long.parseLong(operDayt) * 1000;

		// 添加数据
		TenxunCosInfo tenxunCosInfo = new TenxunCosInfo(); 
		
		tenxunCosInfo.setFromUser(fromUser);
		tenxunCosInfo.setCosInfo(cosInfo);
		tenxunCosInfo.setCosSize(cosSize);
		tenxunCosInfo.setCosUrlInfo(cosUrlInfo);
		tenxunCosInfo.setIngressId(ingressIdCount);
		tenxunCosInfo.setOperDate(operDay);
		tenxunCosInfo.setOperDateId(operDayId);
		tenxunCosInfo.setOperDateTime(operDayt);
		tenxunCosInfo.setCensorFlag("0");   //未进行图片审核
		tenxunCosInfo.setImagePickFlag("0");  //未进行图片文字识别
		tenxunCosInfo.setImageUseFlag("0");   //文件使用标示，0 未使用异步信息  1 同时使用审核和文字识别
		    
		tenxunCosInfoRepository.save(tenxunCosInfo);

        returnTex = Long.toString(ingressIdCount);
 
		return returnTex;
	}
	
	/**
	 * 2017-11-21 微信小程序前置校验
     *             校验用户的操作权限相关信息
	 * 
	 * @param requestInfo
	 *            传入JSON串进行控制 通过入参控制不同的查看方式 查询 计划列表 查询 项目列表 查询项目内容
	 * 
	 * @return 返回训练计划内容信息
	 */
	public String loginTrainUserServInfo(String requestInfo)
			throws UnknownHostException {

		String returnTex = "OK";
		 
		// 获取当日日期
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 可以方便地修改日期格式
		String operDate = dateFormat.format(now);
		SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy-MM-dd"); // 可以方便地修改日期格式
		String operDay = dayFormat.format(now);
		SimpleDateFormat dayFormatId = new SimpleDateFormat("yyyyMMdd"); // 可以方便地修改日期格式
		String operDayId = dayFormatId.format(now);
		SimpleDateFormat timeStampmId = new SimpleDateFormat("yyyyMMddHHmmssSSS"); // 可以方便地修改日期格式
		String timeStamp = timeStampmId.format(now);
		System.out.println(operDay);
 
		 
		// 解析查询条件
		JSONObject object = null;
		object = JSONObject.fromObject(requestInfo);
		String getType = object.get("getType").toString();
		String reqOpenId = object.get("reqOpenId").toString();
		String funOpType = "";
		//记录用户的本次操作信息
		if(object.has("funOpType")){
			funOpType = object.get("funOpType").toString();
		}else{
			funOpType = getType;
		}
		 
		JSONObject jsonServOne = new JSONObject(); 
		//获取用户权限等级 
		long queryCount = 0;
		List<WxSmallUser> wxSmallUsers =wxSmallUserRepository.findByOpenId(reqOpenId);
		queryCount = wxSmallUsers.size();
		
		WxSmallUserAction wxSmallUserAction  = new WxSmallUserAction();
		  
		wxSmallUserAction.setReqOpenId(reqOpenId);
		wxSmallUserAction.setOperDayId(operDayId);
		wxSmallUserAction.setOperDay(operDay);
		wxSmallUserAction.setOperDate(operDate);
		wxSmallUserAction.setTimeStamp(timeStamp);
		wxSmallUserAction.setGetType(getType);
		wxSmallUserAction.setFunOpType(funOpType);
		wxSmallUserAction.setSmalluserCount(queryCount);
		 
		wxSmallUserActionRepository.save(wxSmallUserAction);
		  
		if (queryCount > 0) {
			jsonServOne.element("EndFlag", "0"); 
		}else{
			//分业务进行授权
			List<WxSmallServLock> wxSmallServLocks =wxSmallServLockRepository.findByGetTypeAndFunOpTypeAndLockFlag(getType,funOpType,"1");
			queryCount = wxSmallServLocks.size();
			 
			if(queryCount > 0){
				//无操作权限
				jsonServOne.element("EndFlag", "1");
				jsonServOne.element("ServInfo", "请先授权");
			}else{
				jsonServOne.element("EndFlag", "0");
			} 
		} 
		   
		returnTex = jsonServOne.toString();  
   
		return returnTex;
	}
	
	/**
	 * 2017-11-21 微信小程序前置校验
     *             校验用户的操作权限相关信息
	 * 
	 * @param requestInfo
	 *            传入JSON串进行控制 通过入参控制不同的查看方式 查询 计划列表 查询 项目列表 查询项目内容
	 * 
	 * @return 返回训练计划内容信息
	 */
	public String getTrainUserServLimit(String reqOpenId,String funOpType)
			throws UnknownHostException {

		String returnTex = "OK";
		
		 
		// 获取当日日期
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 可以方便地修改日期格式
		String operDate = dateFormat.format(now);
		SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy-MM-dd"); // 可以方便地修改日期格式
		String operDay = dayFormat.format(now);
		SimpleDateFormat dayFormatId = new SimpleDateFormat("yyyyMMdd"); // 可以方便地修改日期格式
		String operDayId = dayFormatId.format(now);
		System.out.println(operDay);

		  
		// 解析查询条件
		JSONObject object = null;
		//获取用户权限等级
		long queryCount = 0;
		long useServTimes = 0;
		long count = 0;
		List<WxSmallServInfo> wxSmallServInfos;
		List<WxSmallServLog> wxSmallServLogs;
		
		//获取用户权限等级 
		List<WxSmallUser> wxSmallUsers =wxSmallUserRepository.findByOpenId(reqOpenId);
		queryCount = wxSmallUsers.size();
		String userGroupType = "Lvl0";
		if (queryCount > 0) {
			userGroupType = wxSmallUsers.get(0).getUserGroupType().toString();
		}
		JSONObject jsonServOne = new JSONObject(); 
		
		jsonServOne.element("userGroupType", userGroupType);
		
		if ("getObtain".equals(funOpType)) {
			jsonServOne.element("EndFlag", "1");  
			 
			PageRequest pr = new PageRequest(0, 40);
			wxSmallServInfos = wxSmallServInfoRepository.findByUserGroupTypeAndUserServTypeAndUseEffFlag(userGroupType, funOpType, "1");

			// 把结果集输出成list类型
			JSONArray jsonarray = JSONArray.fromObject(wxSmallServInfos); 
			
			jsonServOne.element("ServInfo", jsonarray.toString());
		}else if("imageOcr".equals(funOpType)||"imageSave".equals(funOpType)){
 
			wxSmallServInfos = wxSmallServInfoRepository.findByUserGroupTypeAndUserServTypeAndUseEffFlag(userGroupType, funOpType, "1");
			queryCount = wxSmallServInfos.size(); 
			if (queryCount > 0) {
				useServTimes = wxSmallServInfos.get(0).getUseServTimes();
				if(useServTimes>0){
					//存在日限制--
					//wxSmallServLogs = wxSmallServLogRepository.findByOpenIdAndUserServTypeAndOperDayId(reqOpenId,funOpType,operDayId);
					wxSmallServLogs = wxSmallServLogRepository.findByOpenIdAndUserServTypeOperDayId(reqOpenId,funOpType,operDayId);
					//wxSmallServLogs = wxSmallServLogRepository.findByUserServTypeAndOperDayId(funOpType,operDayId);
					queryCount = wxSmallServLogs.size(); 
					if(queryCount<useServTimes){
						//未达到上限--记录本次操作
						WxSmallServLog wxSmallServLog = new WxSmallServLog();
						 
						wxSmallServLog.setOpenId(reqOpenId);
						wxSmallServLog.setUserServType(funOpType);
						wxSmallServLog.setOperDayId(operDayId);
						wxSmallServLog.setOperDate(operDate);
						
						wxSmallServLogRepository.save(wxSmallServLog);
						jsonServOne.element("EndFlag", "0");
					}else{
						//超过上限不能继续操作
						jsonServOne.element("EndFlag", "1");
						jsonServOne.element("ServInfo", "图片处理张数达到当日上限，超出部分不进行处理");
					}
				}else{
					//无次数限制
					jsonServOne.element("EndFlag", "0");
				}
			}else{
				//无操作权限
				jsonServOne.element("EndFlag", "1");
				jsonServOne.element("ServInfo", "尚未获得图片操作权限");
			}
		}else{
			jsonServOne.element("EndFlag", "0");
		}
		  
		returnTex = jsonServOne.toString();   
		return returnTex;
	}
	 
	/**
	 * 2017-08-30 获取训练计划内容信息 获取指定用户特定观察项目的指标数据
	 * 
	 * @param requestInfo
	 *            传入JSON串进行控制 通过入参控制不同的查看方式 查询 计划列表 查询 项目列表 查询项目内容
	 * 
	 * @return 返回训练计划内容信息
	 */
	public String getTrainPlanInfo(String requestInfo)
			throws UnknownHostException {

		String returnTex = "OK";
		
		  
		// 获取当日日期
		Date now = new Date();
		SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy-MM-dd"); // 可以方便地修改日期格式
		String operDay = dayFormat.format(now);
		SimpleDateFormat dayFormatId = new SimpleDateFormat("yyyyMMdd"); // 可以方便地修改日期格式
		String operDayId = dayFormatId.format(now);
		System.out.println(operDay);
		SimpleDateFormat timeStampmId = new SimpleDateFormat("yyyyMMddHHmmssSSS"); // 可以方便地修改日期格式
		String timeStamp = timeStampmId.format(now);

		 
		// 解析查询条件
		JSONObject object = null;
		object = JSONObject.fromObject(requestInfo);
		String getType = object.get("getType").toString();
		String getPlanId = object.get("getPlanId").toString();
		String getItemId = object.get("getItemId").toString();
		String reqOpenId = object.get("reqOpenId").toString();
		PageRequest pr;
		List<TrainItemInfo> trainItemInfos;
		List<TrainItemSaveInfo> trainItemSaveInfos;

		if ("PlanList".equals(getType)) {
			pr = new PageRequest(0, 40);
			List<TrainPlanInfo> trainPlanInfos = trainPlanInfoRepository.findByOrderByTrainPlanIdDesc(pr); 
			JSONArray jsonarray = JSONArray.fromObject(trainPlanInfos);
			returnTex = jsonarray.toString();

		} else if ("ItemList".equals(getType)) {
			pr = new PageRequest(0, 40);
			trainItemInfos = trainItemInfoRepository.findByTrainPlanIdOrderByInfoGroupIdDesc(Long.parseLong(getPlanId),pr);
			 
			// 查询当日已经评论的项目，进行打标处理
			JSONArray jsonWatchAarr = new JSONArray();
			JSONObject jsonWatchOne = new JSONObject();
			for (int i = 0; i < trainItemInfos.size(); i++) {
				jsonWatchOne = new JSONObject();
				jsonWatchOne.put("itemName", trainItemInfos.get(i).getItemName());
				jsonWatchOne.put("infoGroupId", trainItemInfos.get(i).getInfoGroupId());
				jsonWatchOne.put("trainPlanId", trainItemInfos.get(i).getTrainPlanId());
				jsonWatchOne.put("recordType", trainItemInfos.get(i).getRecordType());
 
				trainItemSaveInfos = trainItemSaveInfoRepository.findByTrainPlanIdAndTrainItemIdAndReqOpenIdAndOperDayId(trainItemInfos.get(i).getTrainPlanId(), trainItemInfos.get(i).getInfoGroupId(), reqOpenId, operDayId);
				long queryItemSaves = trainItemSaveInfos.size();
				
				if (queryItemSaves > 0) {
					jsonWatchOne.put("itemSaveInfo", "1");
				} else {
					jsonWatchOne.put("itemSaveInfo", "");
				}
				jsonWatchAarr.add(jsonWatchOne);
			}

			returnTex = jsonWatchAarr.toString();

		} else if ("ItemInfo".equals(getType)) {
			// 查询计划项目内容
			pr = new PageRequest(0, 10);
			trainItemInfos = trainItemInfoRepository.findByTrainPlanIdAndInfoGroupId(Long.parseLong(getPlanId),Long.parseLong(getItemId),pr);
			 
			// 查询当日的评论信息
			JSONArray jsonWatchAarr = new JSONArray();
			JSONObject jsonWatchOne = new JSONObject();
			for (int i = 0; i < trainItemInfos.size(); i++) {
				jsonWatchOne = new JSONObject();
				jsonWatchOne.put("itemName", trainItemInfos.get(i).getItemName());
				jsonWatchOne.put("itemInfo", trainItemInfos.get(i).getItemInfo());
				jsonWatchOne.put("itemLevel", trainItemInfos.get(i).getItemLevel());
				jsonWatchOne.put("itemTrainTime",trainItemInfos.get(i).getItemTrainTime());
				jsonWatchOne.put("itemRestTime", trainItemInfos.get(i).getItemRestTime());
				jsonWatchOne.put("itemImageUrl", trainItemInfos.get(i).getItemImageUrl());
 
				
				trainItemSaveInfos = trainItemSaveInfoRepository.findByTrainPlanIdAndTrainItemIdAndReqOpenIdAndOperDayId(Long.parseLong(getPlanId),Long.parseLong(getItemId), reqOpenId, operDayId);
				 
				JSONArray jsonarray = JSONArray.fromObject(trainItemSaveInfos);
				jsonWatchOne.put("itemSaveInfo", jsonarray);
				jsonWatchAarr.add(jsonWatchOne);
			}

			returnTex = jsonWatchAarr.toString();

		} else if ("ItemNext".equals(getType)) {
			// 查询下一个计划项目内容
			// 目前使用顺序单循环的方式往后获取下一个计划内容， 后续可以扩展为获取下一个未执行的计划项目。
			trainItemInfos = trainItemInfoRepository.findByTrainPlanIdLessInfoGroupId(Long.parseLong(getPlanId),Long.parseLong(getItemId));
			  
			JSONArray jsonarray = JSONArray.fromObject(trainItemInfos);
			returnTex = jsonarray.toString();
		} else if ("SaveItemInfo".equals(getType)) {
			String saveDataInfo = object.get("SaveDataInfo").toString();
			object = JSONObject.fromObject(saveDataInfo);

			// 添加数据
			TrainItemSaveInfo trainItemSaveInfo = new TrainItemSaveInfo();
			  
			trainItemSaveInfo.setReqOpenId(reqOpenId);
			trainItemSaveInfo.setOperDayId(operDayId);
			trainItemSaveInfo.setTrainPlanId(Long.parseLong(getPlanId));
			trainItemSaveInfo.setTrainItemId(Long.parseLong(getItemId));
			trainItemSaveInfo.setTimePara(Long.parseLong(object.get("timePara").toString()));
			trainItemSaveInfo.setTimeParaDes(object.get("timeParaDes").toString());
			trainItemSaveInfo.setWritePara(Long.parseLong(object.get("writePara").toString()));
			trainItemSaveInfo.setWriteParaDes(object.get("writeParaDes").toString());
			trainItemSaveInfo.setContentInfo(object.get("contentInfo").toString());
			trainItemSaveInfo.setCosimageurls(object.get("cosimageurls").toString());
			 
			trainItemSaveInfoRepository.save(trainItemSaveInfo);
			
		} else if ("ItemTimeInfo".equals(getType)) {
			String funOpType = object.get("funOpType").toString();
			String saveDataInfo = object.get("SaveDataInfo").toString();
			object = JSONObject.fromObject(saveDataInfo);
 
			List<TrainItemTimeInfo> trainItemTimeInfos = trainItemTimeInfoRepository.findByReqOpenIdAndOperDayId(reqOpenId,operDayId);
			 
			long reqItemTimeId = 0;
			if (trainItemTimeInfos.size() > 0) {
				reqItemTimeId = (Long) trainItemTimeInfos.get(0).getReqItemTimeId() + 1;
			} else {
				reqItemTimeId = 1;
			}

			if ("begin".equals(funOpType)) // 传入为记录开始直接新增信息 将完成标示设置为 0 未完成
			{
				// 添加数据
				TrainItemTimeInfo trainItemTimeInfo = new TrainItemTimeInfo();

 
				trainItemTimeInfo.setReqOpenId(reqOpenId);
				trainItemTimeInfo.setOperDayId(operDayId);
				trainItemTimeInfo.setReqItemTimeId(reqItemTimeId);
 
				trainItemTimeInfo.setItemTitle(object.get("itemTitle").toString());
				trainItemTimeInfo.setStartTime(object.get("startTime").toString());
				trainItemTimeInfo.setEndTime(object.get("endTime").toString());
				trainItemTimeInfo.setCostTime(object.get("costTime").toString());
				trainItemTimeInfo.setItemInfo(object.get("itemInfo").toString());
				trainItemTimeInfo.setDegrees(object.get("degrees").toString());
				trainItemTimeInfo.setDegreeId(object.get("degreeIndex").toString());
				trainItemTimeInfo.setOpTypes(object.get("opTypes").toString());
				trainItemTimeInfo.setOpTypeId(object.get("opTypeIndex").toString());
				
				trainItemTimeInfo.setFinishFlag("0");
				trainItemTimeInfo.setCosimageurls(object.get("cosimageurls").toString());
				
				trainItemTimeInfoRepository.save(trainItemTimeInfo);
						
				  /*
				更新标题信息
				query = new BasicDBObject(); // 查询条件
				query.put("reqOpenId", reqOpenId);
				count = trainItemTitles.count(query);
				
				long titleId = count+1;
				query.put("titleInfo", object.get("itemTitle"));
				count = trainItemTitles.count(query);
				if(count>0){
					存在标题增加使用次数
					BasicDBObject update_object = new BasicDBObject();
					BasicDBObject set_object = new BasicDBObject();
					set_object.put("titleUsed", 1); 
					update_object.put("$inc", set_object);
					trainItemTitles.update(query, update_object, false, true);
				}else{
					不存在标题，增加标题
					userServInfo = new BasicDBObject();
					userServInfo.put("reqOpenId", reqOpenId);
					userServInfo.put("titleId", titleId);
					userServInfo.put("titleInfo", object.get("itemTitle"));
					userServInfo.put("titleType", object.get("titleType"));
					if("1".equals(object.get("titleType"))){
						userServInfo.put("titleTypeName","学习");
					}else if("2".equals(object.get("titleType"))){
						userServInfo.put("titleTypeName","生活");
					}else{
						userServInfo.put("titleTypeName","娱乐");
					}
					userServInfo.put("titleUsed", 1); 
					count = trainItemTitles.save(userServInfo).getN(); 
				} */
			}
			/*if ("end".equals(funOpType)) {
				// 判断是否存在数据ID itemTimeId 存在则只修改相关信息 否则为新增已经完成的记录
				String itemTimeId = object.get("itemTimeId").toString();
				String newangleFlag = "0";
				//先记录评价信息然后将评价关联到记录中
				JSONArray jsonarray;
				新增记录
			    query = new BasicDBObject(); // 查询条件
			    query.put("reqOpenId", reqOpenId);
			    DBCursor angleId = trainItemTimeAngles.find(query).limit(1)
						.sort(new BasicDBObject("angleId", -1));
			    DBObject objNewId = null;
			    long timeAngleId = 0;
			    try {
				 	while (angleId.hasNext()) {
				 		objNewId = angleId.next();
				 		timeAngleId = (Long) objNewId.get("angleId");
					}
				} finally {
					angleId.close();
				}
			    timeAngleId = timeAngleId + 1;
			   
				String saveDatas = object.get("keepanglechecked").toString();
				jsonarray = JSONArray.fromObject(saveDatas); 
		  		JSONObject jsonInfoOne = new JSONObject(); 
		  		for(int i=0; i<jsonarray.size(); i++){  
		  			jsonInfoOne = JSONObject.fromObject(jsonarray.get(i));   
			  	    // 添加数据
					DBObject newangleinfo = new BasicDBObject();
					
					newangleinfo.put("reqOpenId", reqOpenId);
					newangleinfo.put("angleId", timeAngleId);
					newangleinfo.put("angleInfo", jsonInfoOne.get("name").toString());
					newangleinfo.put("angleType", "1");
					newangleinfo.put("angleValue", jsonInfoOne.get("angleValue").toString());
					newangleinfo.put("angleTimeValue", jsonInfoOne.get("angleTimeValue").toString());
					    
					long count = trainItemTimeAngles.save(newangleinfo).getN(); 
					newangleFlag = "1";
		  		}
				
		  		saveDatas = object.get("throwanglechecked").toString();
		  		jsonarray = JSONArray.fromObject(saveDatas); 
		  		jsonInfoOne = new JSONObject(); 
		  		for(int i=0; i<jsonarray.size(); i++){  
		  			jsonInfoOne = JSONObject.fromObject(jsonarray.get(i));   
			  	    // 添加数据
					DBObject newangleinfo = new BasicDBObject();
					
					newangleinfo.put("reqOpenId", reqOpenId);
					newangleinfo.put("angleId", timeAngleId);
					newangleinfo.put("angleInfo", jsonInfoOne.get("name").toString());
					newangleinfo.put("angleType", "2");
					newangleinfo.put("angleValue", jsonInfoOne.get("angleValue").toString());
					newangleinfo.put("angleTimeValue", jsonInfoOne.get("angleTimeValue").toString());
					    
					long count = trainItemTimeAngles.save(newangleinfo).getN(); 
					newangleFlag = "1";
		  		}

		  		
		  		
				if (itemTimeId != null && !"".equals(itemTimeId)&& !"0".equals(itemTimeId)) {

					query.put("operDayId", operDayId);
					query.put("reqItemTimeId", Long.parseLong(itemTimeId));

					// 修改数据
					BasicDBObject update_object = new BasicDBObject();
					BasicDBObject set_object = new BasicDBObject();

					set_object.put("itemTitle", object.get("itemTitle"));
					set_object.put("startTime", object.get("startTime"));
					set_object.put("endTime", object.get("endTime"));
					set_object.put("costTime", object.get("costTime"));
					set_object.put("itemInfo", object.get("itemInfo"));
					set_object.put("degrees", object.get("degrees"));
					set_object.put("degreeId", object.get("degreeIndex"));
					set_object.put("opTypes", object.get("opTypes"));
					set_object.put("opTypeId", object.get("opTypeIndex"));
					 
					set_object.put("scoreinfo", object.get("scoreinfo"));
					set_object.put("scoretimeinfo", object.get("scoretimeinfo"));
					
					if ("1".equals(newangleFlag)) {
						set_object.put("timeAngleId", timeAngleId);
					}else{
						set_object.put("timeAngleId", 0);
					}

					set_object.put("finishFlag", "1");

					set_object.put("cosimageurls", object.get("cosimageurls"));

					update_object.put("$set", set_object);

					trainItemTimes.update(query, update_object, false, true);

				} else {
					// 添加数据
					DBObject userServInfo = new BasicDBObject();
					userServInfo.put("reqOpenId", reqOpenId);
					userServInfo.put("operDayId", operDayId);
					userServInfo.put("reqItemTimeId", reqItemTimeId);

					userServInfo.put("itemTitle", object.get("itemTitle"));
					userServInfo.put("startTime", object.get("startTime"));
					userServInfo.put("endTime", object.get("endTime"));
					userServInfo.put("costTime", object.get("costTime"));
					userServInfo.put("itemInfo", object.get("itemInfo"));
					userServInfo.put("degrees", object.get("degrees"));
					userServInfo.put("degreeId", object.get("degreeIndex"));
					userServInfo.put("opTypes", object.get("opTypes"));
					userServInfo.put("opTypeId", object.get("opTypeIndex"));

					userServInfo.put("finishFlag", "1");

					userServInfo
							.put("cosimageurls", object.get("cosimageurls"));
					
					userServInfo.put("scoreinfo", object.get("scoreinfo"));
					userServInfo.put("scoretimeinfo", object.get("scoretimeinfo"));
					
					if ("1".equals(newangleFlag)) {
						userServInfo.put("timeAngleId", timeAngleId);
					}else{
						userServInfo.put("timeAngleId", 0);
					}

					userServInfo.put("recordType", "");
					userServInfo.put("recordItem1", ""); // 备用字段
					userServInfo.put("recordItem2", ""); // 备用字段
					userServInfo.put("recordItem3", ""); // 备用字段

					long count = trainItemTimes.save(userServInfo).getN();
					
					更新标题信息
					query = new BasicDBObject(); // 查询条件
					query.put("reqOpenId", reqOpenId);
					count = trainItemTitles.count(query);
					
					long titleId = count+1;
					query.put("titleInfo", object.get("itemTitle"));
					count = trainItemTitles.count(query);
					if(count>0){
						存在标题增加使用次数
						BasicDBObject update_object = new BasicDBObject();
						BasicDBObject set_object = new BasicDBObject();
						set_object.put("titleUsed", 1); 
						update_object.put("$inc", set_object);
						trainItemTitles.update(query, update_object, false, true);
					}else{
						不存在标题，增加标题
						userServInfo = new BasicDBObject();
						userServInfo.put("reqOpenId", reqOpenId);
						userServInfo.put("titleId", titleId);
						userServInfo.put("titleInfo", object.get("itemTitle"));
						userServInfo.put("titleType", object.get("titleType"));
						if("1".equals(object.get("titleType"))){
							userServInfo.put("titleTypeName","学习");
						}else if("2".equals(object.get("titleType"))){
							userServInfo.put("titleTypeName","生活");
						}else{
							userServInfo.put("titleTypeName","娱乐");
						}
						userServInfo.put("titleUsed", 1); 
						count = trainItemTitles.save(userServInfo).getN(); 
					}
				}
			}*/
		} 
		/*
		else if ("ItemKnowledge".equals(getType)) {
			String funOpType = object.get("funOpType").toString(); 
			JSONArray jsonarray;
			DBCursor cursor ;
			List<DBObject> list;

			query = new BasicDBObject(); // 查询条件
			query.put("reqOpenId", reqOpenId);

			BasicDBObject filter_cloumn = new BasicDBObject(); // 查询字段
//			filter_cloumn.put("reqItemTimeId", 1);
			filter_cloumn.put("_id", 0); // 不显示ID字段

			BasicDBObject sort_cloumn = new BasicDBObject(); // 排序字段
			sort_cloumn.put("reqItemTimeId", -1); 

			if ("new".equals(funOpType))
			{
				String saveDataInfo = object.get("SaveDataInfo").toString();
				object = JSONObject.fromObject(saveDataInfo);
				
				
				cursor = trainItemKnowledges.find(query, filter_cloumn)
						.limit(1).sort(sort_cloumn);

				// 把结果集输出成list类型
				list = cursor.toArray();
				long reqItemTimeId = 0;
				if (list.size() > 0) {
					reqItemTimeId = (Long) list.get(0).get("reqItemTimeId") + 1;
				} else {
					reqItemTimeId = 1;
				}
				
				// 添加数据
				DBObject userServInfo = new BasicDBObject();
				userServInfo.put("reqOpenId", reqOpenId);
				userServInfo.put("operDayId", operDayId);
				userServInfo.put("operDay", operDay);
				userServInfo.put("reqItemTimeId", reqItemTimeId);

				userServInfo.put("itemTitle", object.get("itemTitle"));
				userServInfo.put("itemtitletype", object.get("itemtitletype"));
				userServInfo.put("itemInfo", object.get("itemInfo"));
				userServInfo.put("itemaddinfo", object.get("itemaddinfo"));
				userServInfo.put("cosimageurls", object.get("cosimageurls"));

				userServInfo.put("recordType", "");
				userServInfo.put("recordItem1", ""); // 备用字段
				userServInfo.put("recordItem2", ""); // 备用字段
				userServInfo.put("recordItem3", ""); // 备用字段

				long count = trainItemKnowledges.save(userServInfo).getN();
				
				更新标题大类信息
				query = new BasicDBObject(); // 查询条件
				query.put("reqOpenId", reqOpenId);
				count = trainItemTitles.count(query);
				
				long titleId = count+1;
				query.put("titleInfo", object.get("itemtitletype"));
				query.put("titleType", "4");
				count = trainItemTitles.count(query);
				if(count>0){
					存在标题增加使用次数
					BasicDBObject update_object = new BasicDBObject();
					BasicDBObject set_object = new BasicDBObject();
					set_object.put("titleUsed", 1); 
					update_object.put("$inc", set_object);
					trainItemTitles.update(query, update_object, false, true);
				}else{
					不存在标题，增加标题
					userServInfo = new BasicDBObject();
					userServInfo.put("reqOpenId", reqOpenId);
					userServInfo.put("titleId", titleId);
					userServInfo.put("titleInfo", object.get("itemtitletype"));
					userServInfo.put("titleType", "4");
					userServInfo.put("titleTypeName","知识点");
					userServInfo.put("titleUsed", 1); 
					count = trainItemTitles.save(userServInfo).getN(); 
				} 
			} else if ("queryList".equals(funOpType)) {
				查询类别的记录数
				query = new BasicDBObject(); // 查询条件
				query.put("reqOpenId", reqOpenId);
				query.put("titleType", "4");
				 
				cursor = trainItemTitles.find(query, filter_cloumn)
						.limit(40).sort(sort_cloumn);
				list = cursor.toArray();
				jsonarray = JSONArray.fromObject(list);
				returnTex = jsonarray.toString();
				
			}else if ("queryByType".equals(funOpType)) {
				String itemtitletype = object.get("itemtype").toString();
				按照类别查询知识条目
				query = new BasicDBObject(); // 查询条件
				query.put("reqOpenId", reqOpenId);
				query.put("itemtitletype", itemtitletype);
				
				filter_cloumn = new BasicDBObject(); // 查询字段
				filter_cloumn.put("operDay", 1);
				filter_cloumn.put("reqItemTimeId", 1);
				filter_cloumn.put("itemTitle", 1);
				filter_cloumn.put("_id", 0); // 不显示ID字段
				
				cursor = trainItemKnowledges.find(query, filter_cloumn)
						.limit(40).sort(sort_cloumn);
				list = cursor.toArray();
				jsonarray = JSONArray.fromObject(list);
				returnTex = jsonarray.toString();
			}else if ("queryBySearch".equals(funOpType)) {
				String itemtitletype = object.get("itemtype").toString();
				按照类别查询知识条目
				query = new BasicDBObject(); // 查询条件
				query.put("reqOpenId", reqOpenId);
				
				// 文件检索直接匹配文字即可
				Pattern pattern = Pattern.compile("^.*" + itemtitletype + ".*$", Pattern.CASE_INSENSITIVE);
				query.put("itemTitle", pattern); // 模糊查询处理
				
				filter_cloumn = new BasicDBObject(); // 查询字段
				filter_cloumn.put("operDay", 1);
				filter_cloumn.put("reqItemTimeId", 1);
				filter_cloumn.put("itemTitle", 1);
				filter_cloumn.put("_id", 0); // 不显示ID字段
				
				cursor = trainItemKnowledges.find(query, filter_cloumn)
						.limit(40).sort(sort_cloumn);
				list = cursor.toArray();
				jsonarray = JSONArray.fromObject(list);
				returnTex = jsonarray.toString();
			}else if ("byid".equals(funOpType)) {
				String itemTimeId = object.get("itemTimeId").toString();
				按照类别查询知识条目
				query = new BasicDBObject(); // 查询条件
				query.put("reqOpenId", reqOpenId);
				query.put("reqItemTimeId", Integer.parseInt(itemTimeId)); // 模糊查询处理
				
				filter_cloumn = new BasicDBObject(); // 查询字段
//				filter_cloumn.put("operDay", 1);
//				filter_cloumn.put("reqItemTimeId", 1);
//				filter_cloumn.put("itemTitle", 1);
				filter_cloumn.put("_id", 0); // 不显示ID字段
				
				cursor = trainItemKnowledges.find(query, filter_cloumn)
						.limit(40).sort(sort_cloumn);
				list = cursor.toArray();
				jsonarray = JSONArray.fromObject(list);
				returnTex = jsonarray.toString();
			}
		} else if ("ItemTask".equals(getType)) {
				String funOpType = object.get("funOpType").toString(); 
				JSONArray jsonarray;
				DBCursor cursor ;
				List<DBObject> list;
				List<DBObject> listDb;
				JSONArray jsonTaskAarr = new JSONArray();
				JSONObject jsonTaskOne = new JSONObject();
				

				query = new BasicDBObject(); // 查询条件
				//query.put("reqOpenId", reqOpenId);

				BasicDBObject filter_cloumn = new BasicDBObject(); // 查询字段
//				filter_cloumn.put("reqItemTimeId", 1);
				filter_cloumn.put("_id", 0); // 不显示ID字段

				BasicDBObject sort_cloumn = new BasicDBObject(); // 排序字段
				sort_cloumn.put("reqItemTimeId", -1); 

				if ("new".equals(funOpType))
				{
					String saveDataInfo = object.get("SaveDataInfo").toString();
					object = JSONObject.fromObject(saveDataInfo); 
					
					cursor = trainItemTasks.find(query, filter_cloumn)
							.limit(1).sort(sort_cloumn);
					// 把结果集输出成list类型
					list = cursor.toArray();
					long reqItemTimeId = 0;
					if (list.size() > 0) {
						reqItemTimeId = (Long) list.get(0).get("reqItemTimeId") + 1;
					} else {
						reqItemTimeId = 1;
					}
					
					// 添加数据
					DBObject userServInfo = new BasicDBObject();
					userServInfo.put("reqOpenId", reqOpenId);
					userServInfo.put("operDayId", operDayId);
					userServInfo.put("operDay", operDay);
					userServInfo.put("reqItemTimeId", reqItemTimeId);
					userServInfo.put("timeStamp", timeStamp);

					userServInfo.put("itemTitle", object.get("itemTitle"));
					userServInfo.put("itemtitletype", object.get("itemtitletype"));
					userServInfo.put("itemInfo", object.get("itemInfo"));
					userServInfo.put("itemaddinfo", object.get("itemaddinfo"));
					userServInfo.put("cosimageurls", object.get("cosimageurls"));
					
					userServInfo.put("finishFlag", "0");  //事项完结标示
					userServInfo.put("finishDayId", operDayId);
					userServInfo.put("finishDay", operDay);

					userServInfo.put("recordType", "");
					userServInfo.put("recordItem1", ""); // 备用字段
					userServInfo.put("recordItem2", ""); // 备用字段
					userServInfo.put("recordItem3", ""); // 备用字段

					long count = trainItemTasks.save(userServInfo).getN();
					 
				}else if ("end".equals(funOpType)) {
					// 判断是否存在数据ID itemTimeId 存在则只修改相关信息 否则为新增已经完成的记录
					String saveDataInfo = object.get("SaveDataInfo").toString();
					object = JSONObject.fromObject(saveDataInfo);
					
					查询是否存在事项ID
					query = new BasicDBObject(); // 查询条件
					query.put("timeStamp", object.get("timeStamp")); 
					 
					cursor = trainItemTasks.find(query, filter_cloumn)
							.limit(1).sort(sort_cloumn);
					list = cursor.toArray();
					if (list.size() > 0) {
						修改状态
						// 修改数据
						BasicDBObject update_object = new BasicDBObject();
						BasicDBObject set_object = new BasicDBObject();

						set_object.put("itemTitle", object.get("itemTitle"));
						set_object.put("itemtitletype", object.get("itemtitletype"));
						set_object.put("itemInfo", object.get("itemInfo"));
						set_object.put("itemaddinfo", object.get("itemaddinfo"));
						set_object.put("cosimageurls", object.get("cosimageurls"));
						
						set_object.put("finishFlag", "1");  //事项完结标示
						set_object.put("finishDayId", operDayId);
						set_object.put("finishDay", operDay);
						 
						update_object.put("$set", set_object);

						trainItemTasks.update(query, update_object, false, true);
					}else{
						直接新增信息
						query = new BasicDBObject();
						cursor = trainItemTasks.find(query, filter_cloumn)
								.limit(1).sort(sort_cloumn);
						// 把结果集输出成list类型
						list = cursor.toArray();
						long reqItemTimeId = 0;
						if (list.size() > 0) {
							reqItemTimeId = (Long) list.get(0).get("reqItemTimeId") + 1;
						} else {
							reqItemTimeId = 1;
						}
						
						// 添加数据
						DBObject userServInfo = new BasicDBObject();
						userServInfo.put("reqOpenId", reqOpenId);
						userServInfo.put("operDayId", operDayId);
						userServInfo.put("operDay", operDay);
						userServInfo.put("reqItemTimeId", reqItemTimeId);
						userServInfo.put("timeStamp", timeStamp);

						userServInfo.put("itemTitle", object.get("itemTitle"));
						userServInfo.put("itemtitletype", object.get("itemtitletype"));
						userServInfo.put("itemInfo", object.get("itemInfo"));
						userServInfo.put("itemaddinfo", object.get("itemaddinfo"));
						userServInfo.put("cosimageurls", object.get("cosimageurls"));
						
						userServInfo.put("finishFlag", "1");  //事项完结标示
						userServInfo.put("finishDayId", operDayId);
						userServInfo.put("finishDay", operDay);

						userServInfo.put("recordType", "");
						userServInfo.put("recordItem1", ""); // 备用字段
						userServInfo.put("recordItem2", ""); // 备用字段
						userServInfo.put("recordItem3", ""); // 备用字段

						long count = trainItemTasks.save(userServInfo).getN(); 
					}
					
					jsonarray = JSONArray.fromObject(list);
					returnTex = jsonarray.toString();
					
				}else if ("queryList".equals(funOpType)) {
					
					查询待完成事项和已完成事项
					query = new BasicDBObject(); // 查询条件
					query.put("reqOpenId", reqOpenId);
					query.put("finishFlag", "1");
					 
					cursor = trainItemTasks.find(query, filter_cloumn)
							.limit(10).sort(sort_cloumn);
					list = cursor.toArray();
					if(list.size() > 0){
						jsonarray = JSONArray.fromObject(list); 
						jsonTaskOne.put("finish", jsonarray);
					}

					query = new BasicDBObject(); // 查询条件
					query.put("reqOpenId", reqOpenId);
					query.put("finishFlag", "0");
					 
					cursor = trainItemTasks.find(query, filter_cloumn)
							.limit(10).sort(sort_cloumn);
					list = cursor.toArray();
					if(list.size() > 0){
						jsonarray = JSONArray.fromObject(list); 
						jsonTaskOne.put("action", jsonarray);
					}
					 
					jsonTaskAarr.add(jsonTaskOne);
					returnTex = jsonTaskAarr.toString(); 
					
				} else if ("queryBySearch".equals(funOpType)) {
					String itemtitletype = object.get("itemtype").toString();
					按照类别查询知识条目
					query = new BasicDBObject(); // 查询条件
					query.put("reqOpenId", reqOpenId);
					query.put("finishFlag", "1");
					
					// 文件检索直接匹配文字即可
					Pattern pattern = Pattern.compile("^.*" + itemtitletype + ".*$", Pattern.CASE_INSENSITIVE);
					query.put("itemTitle", pattern); // 模糊查询处理
					
					filter_cloumn = new BasicDBObject(); // 查询字段
					filter_cloumn.put("operDay", 1);
					filter_cloumn.put("reqItemTimeId", 1);
					filter_cloumn.put("itemTitle", 1);
					filter_cloumn.put("_id", 0); // 不显示ID字段
					  
					cursor = trainItemTasks.find(query, filter_cloumn)
							.limit(10).sort(sort_cloumn);
					list = cursor.toArray();
					if(list.size() > 0){
						jsonarray = JSONArray.fromObject(list); 
						jsonTaskOne.put("finish", jsonarray);
					}

					query = new BasicDBObject(); // 查询条件
					query.put("reqOpenId", reqOpenId);
					query.put("finishFlag", "0");
					query.put("itemTitle", pattern); 
					
					cursor = trainItemTasks.find(query, filter_cloumn)
							.limit(10).sort(sort_cloumn);
					list = cursor.toArray();
					if(list.size() > 0){
						jsonarray = JSONArray.fromObject(list); 
						jsonTaskOne.put("action", jsonarray);
					}
					 
					jsonTaskAarr.add(jsonTaskOne);
					returnTex = jsonTaskAarr.toString(); 
				}else if ("byid".equals(funOpType)) {
					按照类别查询知识条目
					query = new BasicDBObject(); // 查询条件
					query.put("reqOpenId", reqOpenId);
					query.put("timeStamp", object.get("timeStamp")); // 模糊查询处理
					
					filter_cloumn = new BasicDBObject(); // 查询字段
//					filter_cloumn.put("operDay", 1);
//					filter_cloumn.put("reqItemTimeId", 1);
//					filter_cloumn.put("itemTitle", 1);
					filter_cloumn.put("_id", 0); // 不显示ID字段
					
					cursor = trainItemTasks.find(query, filter_cloumn)
							.limit(40).sort(sort_cloumn);
					list = cursor.toArray();
					jsonarray = JSONArray.fromObject(list);
					returnTex = jsonarray.toString();
				} else if("opuspublish".equals(funOpType)||"opustask".equals(funOpType)){ //留言、评论信息处理
	                String querystamp = object.get("querystamp").toString();
	                
					//获取用户信息写入发布信息中
					query = new BasicDBObject(); // 查询条件
					query.put("OpenId", reqOpenId);
					cursor = smallusers.find(query, filter_cloumn)
							.limit(1);
					list = cursor.toArray();
					if("".equals(list.get(0).get("userOperType1"))||"allow".equals(list.get(0).get("userOperType1"))){	
						 
						now = new Date();
			  			timeStamp = timeStampmId.format(now);
				  	    // 添加数据
						DBObject newopus = new BasicDBObject();
						newopus.put("reqOpenId", reqOpenId);
						newopus.put("operDayId", operDayId);
						newopus.put("operDay", operDay); 
						newopus.put("timeStamp", timeStamp);
						newopus.put("tasktimeStamp", querystamp);  
						newopus.put("publishInfo", object.get("SaveDataInfo"));   
						//显示控制
						newopus.put("showallow", "allow");
						//用户信息 
						newopus.put("pubNickName", list.get(0).get("nickName"));  
						newopus.put("pubAvatarUrl", list.get(0).get("cosUrl"));
						
						if("opuspublish".equals(funOpType)){
							query = new BasicDBObject(); // 查询当前作品的留言记录数
			                query.put("opustimeStamp", querystamp); 
			                query.put("pubFlag", "publish"); 
			    			
						    cursor = trainItemOpusComments.find(query, filter_cloumn)
									.limit(1).sort(sort_cloumn); 
							// 把结果集输出成list类型
							list = cursor.toArray();
							long reqItemTimeId = 0;
							if (list.size() > 0) {
								reqItemTimeId = (Long) list.get(0).get("reqItemTimeId") + 1;
							} else {
								reqItemTimeId = 1;
							}   
							newopus.put("reqItemTimeId", reqItemTimeId);
							newopus.put("pubFlag", "publish");  
						}else if("opustask".equals(funOpType)){
							newopus.put("reqItemTimeId", Integer.parseInt(object.get("publishId").toString()));
							newopus.put("pubFlag", "task");  
						} 
						long count = trainItemTaskComments.save(newopus).getN();  
					}else{
						returnTex = "发言不合规，已被禁言";
					}
				} else if("killpublish".equals(funOpType)||"killtask".equals(funOpType)){ //删除留言、评论信息处理
	                String querystamp = object.get("querystamp").toString();
	                String killstamp = object.get("SaveDataInfo").toString();
	                String killflag = "0";
	                
	                //查询用户的权限信息
	                query = new BasicDBObject(); // 查询条件
	                query.put("opustimeStamp", querystamp); 
	                query.put("timeStamp", killstamp);
	                if("killpublish".equals(funOpType)){
	                	query.put("pubFlag", "publish");  
					}else if("killtask".equals(funOpType)){
						query.put("pubFlag", "task"); 
					}
	                cursor = trainItemOpusComments.find(query, filter_cloumn)
							.limit(1).sort(sort_cloumn); 
					// 把结果集输出成list类型
	                listDb = cursor.toArray();
					if (listDb.size() > 0) {
						String  pubOpenId = listDb.get(0).get("reqOpenId").toString() ;
						//检查当前的用户是否有操作权限
						if(reqOpenId.equals(pubOpenId)){
							//同一个用户随意操作
							killflag = "1";
						}else{
							query = new BasicDBObject(); // 查询条件
							query.put("OpenId", reqOpenId);
							cursor = smallusers.find(query, filter_cloumn)
									.limit(1);
							list = cursor.toArray();
							String reqOpGroup = list.get(0).get("userGroupType").toString();
							
							query = new BasicDBObject(); // 查询条件
							query.put("OpenId", pubOpenId);
							cursor = smallusers.find(query, filter_cloumn)
									.limit(1);
							list = cursor.toArray();
							String pubOpGroup = list.get(0).get("userGroupType").toString();
							
							if(Integer.parseInt(pubOpGroup.substring(3))>Integer.parseInt(reqOpGroup.substring(3))){
								killflag = "1";
							}else{
								killflag = "0";
							}
						}
					} else {
						killflag = "0";
					}
	                //删除数据
	    			if("1".equals(killflag)){
	    				query = new BasicDBObject(); // 查询条件
	    				query.put("opustimeStamp", querystamp); 
	                    if("killpublish".equals(funOpType)){
	                    	query.put("reqItemTimeId", listDb.get(0).get("reqItemTimeId"));  
	                    	trainItemOpusComments.remove(query);
	                    }else if("killtask".equals(funOpType)){
	                    	query.put("timeStamp", killstamp);
	    					query.put("pubFlag", "task");
	                        trainItemOpusComments.remove(query);
	    				}
	    			}else{
	    				returnTex = "不能越权操作";
	    			}
				}
		} else if ("ItemMusictimer".equals(getType)) {
			String funOpType = object.get("funOpType").toString(); 
			DBCursor cursor ;
			List<DBObject> list;
			JSONArray jsonarray;
			query = new BasicDBObject(); // 查询条件
			query.put("reqOpenId", reqOpenId); 
			BasicDBObject filter_cloumn = new BasicDBObject(); // 查询字段
//			filter_cloumn.put("reqItemTimeId", 1);
			filter_cloumn.put("_id", 0); // 不显示ID字段

			BasicDBObject sort_cloumn = new BasicDBObject(); // 排序字段
			sort_cloumn.put("reqItemTimeId", -1);
			if ("save".equals(funOpType))
			{
				String saveDataInfo = object.get("SaveDataInfo").toString();
				String itemInfo = object.get("itemInfo").toString();
				 
			    cursor = trainItemMusictimers.find(query, filter_cloumn)
						.limit(1).sort(sort_cloumn);

				// 把结果集输出成list类型
				list = cursor.toArray();
				long reqItemTimeId = 0;
				if (list.size() > 0) {
					reqItemTimeId = (Long) list.get(0).get("reqItemTimeId") + 1;
				} else {
					reqItemTimeId = 1;
				}
				
				// 添加数据
				DBObject userServInfo = new BasicDBObject();
				userServInfo.put("reqOpenId", reqOpenId);
				userServInfo.put("operDayId", operDayId);
				userServInfo.put("operDay", operDay);
				userServInfo.put("reqItemTimeId", reqItemTimeId);

				userServInfo.put("itemInfo", object.get("itemInfo"));
				userServInfo.put("itemList", object.get("SaveDataInfo"));  

				long count = trainItemMusictimers.save(userServInfo).getN();
				
			}else if("queryByid".equals(funOpType)){
				String itemTimeId = object.get("itemTimeId").toString();
				按照类别查询知识条目
				query = new BasicDBObject(); // 查询条件
				query.put("reqOpenId", reqOpenId);
				query.put("reqItemTimeId", Integer.parseInt(itemTimeId)); // 模糊查询处理
				
				filter_cloumn = new BasicDBObject(); // 查询字段
				filter_cloumn.put("_id", 0); // 不显示ID字段
				
				cursor = trainItemMusictimers.find(query, filter_cloumn)
						.limit(1).sort(sort_cloumn);
				list = cursor.toArray();
				jsonarray = JSONArray.fromObject(list);
				returnTex = jsonarray.toString();
			}else if ("queryByOrder".equals(funOpType)) {
				
				filter_cloumn = new BasicDBObject(); // 查询字段
				filter_cloumn.put("operDay", 1);
				filter_cloumn.put("reqItemTimeId", 1);
				filter_cloumn.put("itemInfo", 1);
				filter_cloumn.put("_id", 0); // 不显示ID字段
				
				cursor = trainItemMusictimers.find(query, filter_cloumn)
						.limit(10).sort(sort_cloumn);
				list = cursor.toArray();
				jsonarray = JSONArray.fromObject(list);
				returnTex = jsonarray.toString(); 
				
			}else if ("queryBySearch".equals(funOpType)) {
				String itemtitletype = object.get("itemtype").toString();
				按照类别查询知识条目
				query = new BasicDBObject(); // 查询条件
				query.put("reqOpenId", reqOpenId);
				
				// 文件检索直接匹配文字即可
				Pattern pattern = Pattern.compile("^.*" + itemtitletype + ".*$", Pattern.CASE_INSENSITIVE);
				query.put("itemInfo", pattern); // 模糊查询处理 
				
				filter_cloumn = new BasicDBObject(); // 查询字段
				filter_cloumn.put("operDay", 1);
				filter_cloumn.put("reqItemTimeId", 1);
				filter_cloumn.put("itemInfo", 1);
				filter_cloumn.put("_id", 0); // 不显示ID字段
				
				cursor = trainItemMusictimers.find(query, filter_cloumn)
						.limit(10).sort(sort_cloumn);
				list = cursor.toArray();
				jsonarray = JSONArray.fromObject(list);
				returnTex = jsonarray.toString(); 
				
			} 
		} else if ("ItemPublishInfo".equals(getType)) {
			String funOpType = object.get("funOpType").toString(); 
			DBCursor cursor ;
			List<DBObject> list;
			JSONArray jsonarray;
			query = new BasicDBObject(); // 查询条件
			query.put("reqOpenId", reqOpenId); 
			BasicDBObject filter_cloumn = new BasicDBObject(); // 查询字段
//			filter_cloumn.put("reqItemTimeId", 1);
			filter_cloumn.put("_id", 0); // 不显示ID字段

			BasicDBObject sort_cloumn = new BasicDBObject(); // 排序字段
			sort_cloumn.put("reqItemTimeId", -1);
			if ("savely".equals(funOpType)) //发布留言信息
			{ 
				String saveDataInfo = object.get("SaveDataInfo").toString(); 
				
				query = new BasicDBObject();
			    cursor = trainItemPublishs.find(query, filter_cloumn)
						.limit(1).sort(sort_cloumn);

				// 把结果集输出成list类型
				list = cursor.toArray();
				long reqItemTimeId = 0;
				if (list.size() > 0) {
					reqItemTimeId = (Long) list.get(0).get("reqItemTimeId") + 1;
				} else {
					reqItemTimeId = 1;
				}
				 
				//获取用户信息写入发布信息中
				query = new BasicDBObject(); // 查询条件
				query.put("OpenId", reqOpenId);
				cursor = smallusers.find(query, filter_cloumn)
						.limit(1);
				list = cursor.toArray();
				 
				// 添加数据
				DBObject userServInfo = new BasicDBObject();
				userServInfo.put("reqOpenId", reqOpenId);
				userServInfo.put("operDayId", operDayId);
				userServInfo.put("operDay", operDay);
				userServInfo.put("reqItemTimeId", reqItemTimeId);

				userServInfo.put("publishInfo", object.get("SaveDataInfo"));  
				userServInfo.put("addOperDay", operDay);
				userServInfo.put("timeStamp", timeStamp);
				userServInfo.put("addTaskInfo", "");  
				userServInfo.put("addOpenId", "");  
				
				//用户名称和头像 
				userServInfo.put("pubNickName", list.get(0).get("nickName"));  
				userServInfo.put("pubAvatarUrl", list.get(0).get("avatarUrl"));  
				userServInfo.put("pubFlag", "publish");  
				userServInfo.put("taskNickName", "");
				
				//显示控制
				userServInfo.put("showallow", "allow");

				if("".equals(list.get(0).get("userOperType1"))||"allow".equals(list.get(0).get("userOperType1"))){
					//默认使用 userOperType1 控制发布权限 后期可以考虑或者
					long count = trainItemPublishs.save(userServInfo).getN();
				}else{
					returnTex = "之前的发言不文明，已被禁言";
				}
			}else if("savepl".equals(funOpType)){ //发布评论信息
				String itemTimeId = object.get("itemTimeId").toString();
				String pubOpenId = object.get("pubOpenId").toString(); 
				
				按照类别查询知识条目
				query = new BasicDBObject(); // 查询条件
				query.put("reqOpenId", pubOpenId);
				query.put("reqItemTimeId", Integer.parseInt(itemTimeId)); // 模糊查询处理
				
				filter_cloumn = new BasicDBObject(); // 查询字段
				filter_cloumn.put("_id", 0); // 不显示ID字段
				
				cursor = trainItemPublishs.find(query, filter_cloumn)
						.limit(1).sort(sort_cloumn);
				list = cursor.toArray();
				 
				// 添加数据
				DBObject userServInfo = new BasicDBObject();
				userServInfo.put("reqOpenId", list.get(0).get("reqOpenId"));
				userServInfo.put("operDayId", list.get(0).get("operDayId"));
				userServInfo.put("operDay", list.get(0).get("operDay"));
				userServInfo.put("reqItemTimeId", list.get(0).get("reqItemTimeId"));
				//userServInfo.put("publishInfo", list.get(0).get("publishInfo"));  
				userServInfo.put("publishInfo", "");
				
				userServInfo.put("addOperDay", operDay);
				userServInfo.put("timeStamp", timeStamp);
				userServInfo.put("addTaskInfo", object.get("SaveDataInfo"));  
				userServInfo.put("addOpenId", reqOpenId);  
				
				//显示控制
				userServInfo.put("showallow", "allow");
				
				//获取用户信息写入发布信息中
				query = new BasicDBObject(); // 查询条件
				query.put("OpenId", reqOpenId);
				cursor = smallusers.find(query, filter_cloumn)
						.limit(1);
				list = cursor.toArray();
				  
				userServInfo.put("pubNickName", "");  
				userServInfo.put("pubAvatarUrl", "");  
				userServInfo.put("pubFlag", "task");
				userServInfo.put("taskNickName", list.get(0).get("nickName"));
				
				if("".equals(list.get(0).get("userOperType1"))||"allow".equals(list.get(0).get("userOperType1"))){
					//默认使用 userOperType1 控制发布权限 后期可以考虑或者
					long count = trainItemPublishs.save(userServInfo).getN();
				}else{
					returnTex = "之前的发言不文明，已被禁言";
				}
			}else if ("queryinfo".equals(funOpType)) { //支持下拉刷新获取后续信息
				//不允许查看被禁言的人员发布、评论的相关信息     考虑到效率问题 可以将禁言后的屏蔽言论功能放在禁言的时候去处理。
				
				query = new BasicDBObject(); // 查询条件
				query.put("userOperType1", "notallow");
				cursor = smallusers.find(query, filter_cloumn)
						.limit(30);
				list = cursor.toArray();
				
				BasicDBList userOps = new BasicDBList();
				String notallowId = "";

				for (int i = 0; i < list.size(); i++) {
					notallowId = list.get(i).get("OpenId").toString();
					userOps.add(notallowId);
				} 
				
				
				query = new BasicDBObject(); // 查询条件
				 
				按照类别查询知识条目
				BasicDBObject queryNow = new BasicDBObject(); // 查询条件
				queryNow.put("operDayId", object.get("queryDay"));
				queryNow.put("reqItemTimeId", new BasicDBObject("$lt", object.get("itemTimeId")));
				 
				BasicDBList values = new BasicDBList();
				values.add(queryNow);  //当日的数据
	 			values.add(new BasicDBObject("operDayId", new BasicDBObject("$lt", object.get("queryDay"))));  //历史数据
	 			query.put("$or", values);
	 			
	 			query.put("showallow", "allow");  //控制禁言的信息不展现
	 			//query.put("reqOpenId", new BasicDBObject("$nin", userOps));  //禁止显示发布信息
	 			//query.put("addOpenId", new BasicDBObject("$nin", userOps));  //禁止显示评论信息
	 			
	 			//sysDebugLog("ItemPublishInfo", "5756", "queryNow",query.toString());
	 			
				filter_cloumn = new BasicDBObject(); // 查询字段
				filter_cloumn.put("_id", 0); // 不显示ID字段
				
				sort_cloumn = new BasicDBObject(); // 排序字段
				sort_cloumn.put("operDayId", -1);
				sort_cloumn.put("reqItemTimeId", -1);
				sort_cloumn.put("pubFlag", 1);
				
				cursor = trainItemPublishs.find(query, filter_cloumn)
						.limit(30).sort(sort_cloumn);
				list = cursor.toArray();
				jsonarray = JSONArray.fromObject(list);
				returnTex = jsonarray.toString();  
				
			}else if ("querytask".equals(funOpType)) {  //查看指定内容的评论信息
				String itemTimeId = object.get("itemTimeId").toString();
				String pubOpenId = object.get("pubOpenId").toString(); 
				
				query = new BasicDBObject(); // 查询条件
				query.put("reqOpenId", pubOpenId);
				query.put("reqItemTimeId", Integer.parseInt(itemTimeId)); // 模糊查询处理
				
				query.put("showallow", "allow");  //控制禁言的信息不展现
				
				filter_cloumn = new BasicDBObject(); // 查询字段
				filter_cloumn.put("_id", 0); // 不显示ID字段
				
				sort_cloumn = new BasicDBObject(); // 排序字段
				sort_cloumn.put("timeStamp", -1);
				
				cursor = trainItemPublishs.find(query, filter_cloumn)
						.limit(40).sort(sort_cloumn);
				list = cursor.toArray();
				jsonarray = JSONArray.fromObject(list);
				returnTex = jsonarray.toString();   
			} 
		} */
		else if ("ItemOpusInfo".equals(getType)) {
			String funOpType = object.get("funOpType").toString(); 
			JSONArray jsonarray;
			WxSmallUser wxSmallUser ;
			if ("opusappear".equals(funOpType)) //发布作品信息
			{ 
				String saveDataInfo = object.get("SaveDataInfo").toString(); 
				wxSmallUser = wxSmallUserRepository.findByOpenId(reqOpenId).get(0);
				//获取用户信息写入发布信息中
				if(!"Lvl0".equals(wxSmallUser.getUserGroupType())){
					//默认使用 userGroupType 控制发布权限  
					jsonarray = JSONArray.fromObject(saveDataInfo); 
			  		JSONObject jsonInfoOne = new JSONObject(); 
			  		long reqItemTimeId = 0;
			  		String cosUrl = "";
			  		List<TenxunCosInfo> tenxunCosInfos;
			  		TenxunCosInfo tenxunCosInfo ;
			  		for(int i=0; i<jsonarray.size(); i++){ 
			  			reqItemTimeId = trainItemOpusRepository.queryMaxReqItemTimeId();
						reqItemTimeId += 1;
						
			  			jsonInfoOne = JSONObject.fromObject(jsonarray.get(i));  
			  			 
			  			now = new Date();
			  			timeStamp = timeStampmId.format(now);
				  	    // 添加数据
			  			TrainItemOpus trainItemOpus = new TrainItemOpus();
			  			 
						trainItemOpus.setReqOpenId(reqOpenId);
						trainItemOpus.setOperDayId(operDayId);
						trainItemOpus.setOperDay(operDay);
						trainItemOpus.setReqItemTimeId(reqItemTimeId);
						trainItemOpus.setTimeStamp(timeStamp);
						 
						trainItemOpus.setOpustitle(jsonInfoOne.get("opustitle").toString());     //标题
						trainItemOpus.setOpusauthor(jsonInfoOne.get("opusauthor").toString());   //作品人
						trainItemOpus.setOpusdate(jsonInfoOne.get("opusdate").toString());       //作品日期
						trainItemOpus.setOpusdepict(jsonInfoOne.get("opusdepict").toString());   //作品信息
						trainItemOpus.setOpustype(jsonInfoOne.get("opustype").toString());       //作品类别
						trainItemOpus.setQueryinfo(jsonInfoOne.get("opustitle").toString()+
								                   jsonInfoOne.get("opusauthor").toString()+
								                   jsonInfoOne.get("opusdepict").toString());    //搜索关键字
						
						cosUrl = jsonInfoOne.get("opusurl").toString();
						trainItemOpus.setOpusurl(cosUrl);         //图片地址
			  			
						//查询图片的审核信息
			  			tenxunCosInfos = tenxunCosInfoRepository.findByCosUrlInfo(cosUrl);
			  			if(tenxunCosInfos.size()>0){
			  				//存在数据修改信息
			  				tenxunCosInfo = tenxunCosInfos.get(0);
			  			    if("2".equals(tenxunCosInfo.getCensorFlag())){
			  			    	//不合规的照片
			  			    	trainItemOpus.setCensorInfo(tenxunCosInfo.getImageCensor());
			  			    	trainItemOpus.setCensorUrl(cosUrl);
			  			    	//提供固定的违规展示照片
			  			    	cosUrl = "http://mycos-1253822284.coscd.myqcloud.com/otherFile/18no.jpg";
			  			    	trainItemOpus.setOpusurl(cosUrl);
			  			    }
			  			    if("1".equals(tenxunCosInfo.getImagePickFlag())&&!"".equals(tenxunCosInfo.getImagePick())){
			  			    	trainItemOpus.setQueryinfo(jsonInfoOne.get("opustitle").toString()+
						                   jsonInfoOne.get("opusauthor").toString()+
						                   jsonInfoOne.get("opusdepict").toString()+
						                   tenxunCosInfo.getImagePick()
						                   );
			  			    }
			  			}
						
						trainItemOpusRepository.save(trainItemOpus);
			  		}
				}else{
					returnTex = "还未获得发表作品的权限。";
				}
			} else if("opuspublish".equals(funOpType)||"opustask".equals(funOpType)){ //留言、评论信息处理
                String querystamp = object.get("querystamp").toString();
                
				//获取用户信息写入发布信息中
				wxSmallUser = wxSmallUserRepository.findByOpenId(reqOpenId).get(0);
				if("".equals(wxSmallUser.getUserOperType1())||wxSmallUser.getUserOperType1()==null||"allow".equals(wxSmallUser.getUserOperType1())){	
					 
					now = new Date();
		  			timeStamp = timeStampmId.format(now);
			  	    // 添加数据
		  			TrainItemOpusComment trainItemOpusComment = new TrainItemOpusComment();
					trainItemOpusComment.setReqOpenId(reqOpenId);
					trainItemOpusComment.setOperDayId(operDayId);
					trainItemOpusComment.setOperDay(operDay);
					trainItemOpusComment.setTimeStamp(timeStamp);
					trainItemOpusComment.setOpustimeStamp(querystamp);
					trainItemOpusComment.setPublishInfo(object.get("SaveDataInfo").toString());
					
					//显示控制
					trainItemOpusComment.setShowallow("allow");
					//用户信息 
					trainItemOpusComment.setPubNickName(wxSmallUser.getNickName());
					trainItemOpusComment.setPubAvatarUrl(wxSmallUser.getCosUrl());
					
					if("opuspublish".equals(funOpType)){
						List<TrainItemOpusComment> trainItemOpusComments = 
		                		trainItemOpusCommentRepository.queryByOpustimeStampPubFlag(querystamp, "publish",3);
						// 把结果集输出成list类型
						long reqItemTimeId = 0;
						if (trainItemOpusComments.size() > 0) {
							reqItemTimeId = (Long) trainItemOpusComments.get(0).getReqItemTimeId() + 1;
						} else {
							reqItemTimeId = 1;
						}
						trainItemOpusComment.setReqItemTimeId(reqItemTimeId);
						trainItemOpusComment.setPubFlag("publish"); 
					}else if("opustask".equals(funOpType)){
						trainItemOpusComment.setReqItemTimeId(Long.parseLong(object.get("publishId").toString()));
						trainItemOpusComment.setPubFlag("task");  
					} 
					trainItemOpusCommentRepository.save(trainItemOpusComment);
				}else{
					returnTex = "发言不合规，已被禁言";
				}
			}else if("killpublish".equals(funOpType)||"killtask".equals(funOpType)){ //删除留言、评论信息处理
                String querystamp = object.get("querystamp").toString();
                String killstamp = object.get("SaveDataInfo").toString();
                String killflag = "0";
                String killPubFlag = "";
                long killReqId = 0;
                List<TrainItemOpusComment> trainItemOpusComments;
                //查询用户的权限信息
                if("killpublish".equals(funOpType)){
                	killPubFlag = "publish";  
				}else if("killtask".equals(funOpType)){
					killPubFlag = "task"; 
				}
                
                trainItemOpusComments = trainItemOpusCommentRepository.queryBytimeStampPubFlag(querystamp,killstamp,killPubFlag,3);
                if (trainItemOpusComments.size() > 0) {
					String  pubOpenId = trainItemOpusComments.get(0).getReqOpenId();
					killReqId = trainItemOpusComments.get(0).getReqItemTimeId();
					//检查当前的用户是否有操作权限
					if(reqOpenId.equals(pubOpenId)){
						//同一个用户随意操作
						killflag = "1";
					}else{
						wxSmallUser = wxSmallUserRepository.findByOpenId(reqOpenId).get(0);
						String reqOpGroup = wxSmallUser.getUserGroupType();
						
						wxSmallUser = wxSmallUserRepository.findByOpenId(pubOpenId).get(0);
						String pubOpGroup = wxSmallUser.getUserGroupType();
						 
						if(Integer.parseInt(pubOpGroup.substring(3))>Integer.parseInt(reqOpGroup.substring(3))){
							killflag = "1";
						}else{
							killflag = "0";
						}
					}
				} else {
					killflag = "0";
				}
                //删除数据
    			if("1".equals(killflag)){
    				if("killpublish".equals(funOpType)){
                    	
                    	trainItemOpusComments = trainItemOpusCommentRepository.queryByopusreqid(querystamp,killReqId);
                    	trainItemOpusCommentRepository.delete(trainItemOpusComments);
                    }else if("killtask".equals(funOpType)){
                    	trainItemOpusComments = trainItemOpusCommentRepository.queryBytimeStampPubFlag(querystamp,killstamp,killPubFlag,300);
                    	trainItemOpusCommentRepository.delete(trainItemOpusComments); 
    				}
    			}else{
    				returnTex = "不能越权操作";
    			}
			}else if("killopus".equals(funOpType)){ //删除作品信息处理
                String querystamp = object.get("querystamp").toString();
                String killflag = "0";
                List<TrainItemOpus> trainItemOpuss;
                //查询用户的权限信息
                trainItemOpuss = trainItemOpusRepository.queryByTimeStamp(querystamp);
                // 把结果集输出成list类型
				if (trainItemOpuss.size() > 0) {
					String  pubOpenId = trainItemOpuss.get(0).getReqOpenId();
					//检查当前的用户是否有操作权限
					if(reqOpenId.equals(pubOpenId)){
						//同一个用户随意操作
						killflag = "1";
					}else{
						wxSmallUser = wxSmallUserRepository.findByOpenId(reqOpenId).get(0);
						String reqOpGroup = wxSmallUser.getUserGroupType();
						
						wxSmallUser = wxSmallUserRepository.findByOpenId(pubOpenId).get(0);
						String pubOpGroup = wxSmallUser.getUserGroupType();
						
						if(Integer.parseInt(pubOpGroup.substring(3))>Integer.parseInt(reqOpGroup.substring(3))){
							killflag = "1";
						}else{
							killflag = "0";
						}
					}
				} else {
					killflag = "0";
				}
                //删除数据
    			if("1".equals(killflag)){
    				trainItemOpuss = trainItemOpusRepository.queryByTimeStamp(querystamp);
    				trainItemOpusRepository.delete(trainItemOpuss);
    			}else{
    				returnTex = "不能越权操作";
    			}
			}else if("queryComment".equals(funOpType)){ //查询留言、评论信息处理
                String querystamp = object.get("querystamp").toString();
                List<TrainItemOpusComment> trainItemOpusComments;
                 
				trainItemOpusComments = trainItemOpusCommentRepository.queryByOpustimeStamp(querystamp, "allow", 40);
				if(object.has("querymaxid")){
					String querymaxid = object.get("querymaxid").toString();
					if(Integer.parseInt(querymaxid)>0){
						trainItemOpusComments = trainItemOpusCommentRepository.queryByReqItemTimeId(querystamp, "allow",querymaxid, 40);
					}
				}  
				jsonarray = JSONArray.fromObject(trainItemOpusComments);
				returnTex = jsonarray.toString();  
				
			}else if ("queryList".equals(funOpType)) { //查询信息列表
				String queryinfo = object.get("queryinfo").toString();
				String querystamp = object.get("querystamp").toString();
				List<TrainItemOpus> trainItemOpuss;
				if("".equals(queryinfo)||queryinfo==null){
					queryinfo = "";
				}
				if("".equals(querystamp)||querystamp==null){
					querystamp = "99180123171240955";
				}
				 
				trainItemOpuss = trainItemOpusRepository.queryByQueryinfo(queryinfo,querystamp,10);
				jsonarray = JSONArray.fromObject(trainItemOpuss);
				returnTex = jsonarray.toString();
			}else if ("queryStamp".equals(funOpType)) {  //查看指定内容的评论信息
                String querystamp = object.get("querystamp").toString();
                List<TrainItemOpus> trainItemOpuss; 
				trainItemOpuss = trainItemOpusRepository.queryByTimeStamp(querystamp);
				jsonarray = JSONArray.fromObject(trainItemOpuss);
				//后续增加留言和评论信息的查询
				returnTex = jsonarray.toString();  
			}else if ("setHome".equals(funOpType)) {  //修改主页设置
				String saveDataInfo = object.get("SaveDataInfo").toString();
				object = JSONObject.fromObject(saveDataInfo);
				String cosimageurls = object.get("cosimageurls").toString();
				jsonarray = JSONArray.fromObject(cosimageurls); 
				TrainItemOpusHome trainItemOpusHome ;
				//记录是否存在
		  		List<TrainItemOpusHome> trainItemOpusHomes = trainItemOpusHomeRepository.queryByUseFlag("1");
		  		long queryCount = trainItemOpusHomes.size();
		  		if(queryCount>0){
		  			trainItemOpusHome = trainItemOpusHomes.get(0);
		  		}else{
		  			 // 添加数据
		  			trainItemOpusHome = new TrainItemOpusHome(); 
		  			trainItemOpusHome.setUseFlag("1");
		  		}
		  		trainItemOpusHome.setReqOpenId(reqOpenId);
				trainItemOpusHome.setTimeStamp(timeStamp);
				trainItemOpusHome.setBaseinfo(object.get("baseinfo").toString());
				trainItemOpusHome.setHeightscale(object.get("heightscale").toString());
				trainItemOpusHome.setImagemode(object.get("imagemode").toString());
				if(jsonarray.size()>0){
					//存在图片全量更新
					trainItemOpusHome.setCosimageurls(object.get("cosimageurls").toString());
				}
				trainItemOpusHomeRepository.save(trainItemOpusHome);
				returnTex = "OK";  
			} else if ("getHome".equals(funOpType)) {  //获取主页设置
				//记录是否存在
		  		List<TrainItemOpusHome> trainItemOpusHomes = trainItemOpusHomeRepository.queryByUseFlag("1");
				jsonarray = JSONArray.fromObject(trainItemOpusHomes.get(0));
				returnTex = jsonarray.toString(); 
			}
		}else if ("ItemUserInfo".equals(getType)) {
			String funOpType = object.get("funOpType").toString();
			JSONArray jsonarray;
			if ("setallow".equals(funOpType)) //发布留言信息
			{  
				String pubOpenId = object.get("pubOpenId").toString(); 
				long queryCount = 0;
				queryCount = wxSmallUserRepository.findByOpenId(pubOpenId).size();
				if (queryCount > 0 ) {
					WxSmallUser wxSmallUser = wxSmallUserRepository.findByOpenId(pubOpenId).get(0);
					wxSmallUser.setUserOperType1(object.get("SaveDataInfo").toString());
					wxSmallUserRepository.save(wxSmallUser);
				
					//作品的留言、评论信息的处理
					TrainItemOpusComment trainItemOpusComment = trainItemOpusCommentRepository.findByReqOpenId(pubOpenId).get(0);
					trainItemOpusComment.setShowallow(object.get("SaveDataInfo").toString());
					trainItemOpusCommentRepository.save(trainItemOpusComment);
					
				    //将已经发布的信息和评论信息设置为  禁言或者解禁状态
					trainItemPublishRepository.updateByQuery(pubOpenId, pubOpenId, object.get("SaveDataInfo").toString());
				}   
			} else if ("setlvl".equals(funOpType)) { //支持下拉刷新获取后续信息
				String pubOpenId = object.get("pubOpenId").toString(); 
				
				long queryCount = 0;
				queryCount = wxSmallUserRepository.findByOpenId(pubOpenId).size();;
				if (queryCount > 0 ) {
					WxSmallUser wxSmallUser = wxSmallUserRepository.findByOpenId(pubOpenId).get(0);
					wxSmallUser.setUserGroupType(object.get("SaveDataInfo").toString());
					wxSmallUserRepository.save(wxSmallUser);
				} 
			}else if ("query".equals(funOpType)) { //支持下拉刷新获取后续信息
				List<WxSmallUser> wxSmallUsers = wxSmallUserRepository.queryByTimeStamp(object.get("queryDay").toString(), 10);
				jsonarray = JSONArray.fromObject(wxSmallUsers);
				returnTex = jsonarray.toString(); 
			}else if ("queryaction".equals(funOpType)) { //支持下拉刷新获取后续信息 查询用户行为数据
				List<WxSmallUserAction> wxSmallUserActions = wxSmallUserActionRepository.queryByTimeStamp(object.get("pubOpenId").toString(), object.get("queryDay").toString(), 20);
				
				jsonarray = JSONArray.fromObject(wxSmallUserActions);
				returnTex = jsonarray.toString(); 
			}
		} 
		/*else if ("ItemTimeQuery".equals(getType)) {
			String funOpType = object.get("funOpType").toString();
			String queryDay = object.get("queryDay").toString();

			query = new BasicDBObject(); // 查询条件
			query.put("reqOpenId", reqOpenId);
//			query.put("operDayId", operDayId);
			query.put("operDayId", queryDay);

			BasicDBObject filter_cloumn = new BasicDBObject(); // 查询字段
			filter_cloumn.put("_id", 0); // 不显示ID字段
 
			if ("list".equals(funOpType)) {
				filter_cloumn.put("reqItemTimeId", 1);
				filter_cloumn.put("itemTitle", 1);
				filter_cloumn.put("startTime", 1);
				filter_cloumn.put("endTime", 1);
				filter_cloumn.put("costTime", 1);
				filter_cloumn.put("finishFlag", 1);
 
				BasicDBObject sort_cloumn = new BasicDBObject(); // 排序字段
				sort_cloumn.put("reqItemTimeId", -1);
				DBCursor cursor = trainItemTimes.find(query, filter_cloumn)
						.limit(10).sort(sort_cloumn);
				List<DBObject> list = cursor.toArray();
				JSONArray jsonarray = JSONArray.fromObject(list);
				returnTex = jsonarray.toString();
				
			} else if ("byid".equals(funOpType)) {
				String itemTimeId = object.get("itemTimeId").toString();
				if (itemTimeId != null && !"".equals(itemTimeId)) {
					query.put("reqItemTimeId", Long.parseLong(itemTimeId));
				} else {
					query.put("reqItemTimeId", 0);
				}
				filter_cloumn.put("reqItemTimeId", 1);
				filter_cloumn.put("itemTitle", 1);
				filter_cloumn.put("startTime", 1);
				filter_cloumn.put("endTime", 1);
				filter_cloumn.put("costTime", 1);
				filter_cloumn.put("finishFlag", 1);

				filter_cloumn.put("itemInfo", 1);
				filter_cloumn.put("degrees", 1);
				filter_cloumn.put("degreeId", 1);
				filter_cloumn.put("opTypes", 1);
				filter_cloumn.put("opTypeId", 1);
				filter_cloumn.put("cosimageurls", 1);
				
				filter_cloumn.put("scoreinfo", 1);
				filter_cloumn.put("scoretimeinfo", 1);
				filter_cloumn.put("timeAngleId", 1); 
				
				BasicDBObject sort_cloumn = new BasicDBObject(); // 排序字段
				sort_cloumn.put("reqItemTimeId", -1);
				DBCursor cursor = trainItemTimes.find(query, filter_cloumn)
						.limit(1).sort(sort_cloumn);
				List<DBObject> list = cursor.toArray();
				JSONObject jsonmap = new JSONObject();
				jsonmap.element("trainItem", JSONArray.fromObject(list).toString()); 
				if(list.size()>0){
					query = new BasicDBObject(); // 查询条件
					query.put("reqOpenId", reqOpenId);
					query.put("angleId", list.get(0).get("timeAngleId"));
					
					filter_cloumn = new BasicDBObject();
					filter_cloumn.put("_id", 0);
					filter_cloumn.put("angleInfo", 1);
					filter_cloumn.put("angleType", 1);
					filter_cloumn.put("angleValue", 1);
					filter_cloumn.put("angleTimeValue", 1);
					
					cursor = trainItemTimeAngles.find(query, filter_cloumn)
							.limit(70);
					List<DBObject> listAngle = cursor.toArray();
					jsonmap.element("trainItemAngle", JSONArray.fromObject(listAngle).toString());  
				}
				returnTex = jsonmap.toString();

			} else if ("hisbyid".equals(funOpType)) {
				// 通过名称查询历史数据信息
				String itemTimeId = object.get("itemTimeId").toString();
				if (itemTimeId != null && !"".equals(itemTimeId)) {
					query.put("reqItemTimeId", Long.parseLong(itemTimeId));
					// 查询记录名称
					DBObject objOne = trainItemTimes.findOne(query);

					query = new BasicDBObject(); // 查询条件
					query.put("reqOpenId", reqOpenId);
					query.put("itemTitle", objOne.get("itemTitle"));
					query.put("finishFlag", "1");
					
					query.put("operDayId", new BasicDBObject("$lte", queryDay));

					filter_cloumn.put("reqItemTimeId", 1);
					filter_cloumn.put("itemTitle", 1);
					filter_cloumn.put("startTime", 1);
					filter_cloumn.put("endTime", 1);
					filter_cloumn.put("costTime", 1);
					filter_cloumn.put("operDayId", 1); 

					filter_cloumn.put("itemInfo", 1);
					filter_cloumn.put("degrees", 1);
					filter_cloumn.put("degreeId", 1);
					filter_cloumn.put("opTypes", 1);
					filter_cloumn.put("opTypeId", 1);

					BasicDBObject sort_cloumn = new BasicDBObject(); // 排序字段
					sort_cloumn.put("reqItemTimeId", -1);

					DBCursor cursor = trainItemTimes.find(query, filter_cloumn)
							.limit(10).sort(sort_cloumn);
					List<DBObject> list = cursor.toArray();
					JSONArray jsonarray = JSONArray.fromObject(list);
					returnTex = jsonarray.toString();
				}
			}
		} else if ("ItemTitleQuery".equals(getType)) {
			long queryCount=0;
			DBCursor cursor ;
			List<DBObject> list;
			JSONArray jsonarray;
			JSONArray jsonTitleAarr = new JSONArray();
			JSONObject jsonTitleOne = new JSONObject();
			
			BasicDBObject filter_cloumn = new BasicDBObject(); // 查询字段
			filter_cloumn.put("_id", 0); // 不显示ID字段
			filter_cloumn.put("avatarUrl", 0); // 不显示ID字段
			
			// 增加权限组的区分 从userServLimit 表中获取用户拥有的权限
			BasicDBObject queryServLimit = new BasicDBObject(); // 查询条件
			BasicDBList values = new BasicDBList();
			values.add(new BasicDBObject("userGroupType", "Lvl9"));  //超级管理员
 			values.add(new BasicDBObject("userGroupType", "Lvl2"));  //授权发布人   后续考虑设置可查看的等级
 			queryServLimit.put("$or", values);
			  
			DBCursor cursorServLimit = smallusers.find(queryServLimit,filter_cloumn)
					.limit(200);

			List<DBObject> listServLimit = cursorServLimit.toArray();
			
			sysDebugLog("ItemTitleQuery", "5563", "listServLimit",listServLimit.toString());
			
			BasicDBList userValues = new BasicDBList();
			String listOpenId = "";

			for (int i = 0; i < listServLimit.size(); i++) {
				listOpenId = listServLimit.get(i).get("OpenId")
						.toString();
				userValues.add(listOpenId);
			}
			userValues.add(reqOpenId);
			
//			sysDebugLog("ItemTitleQuery", "5575", "userValues",userValues.toString());
			
			query = new BasicDBObject(); // 查询条件
//			query.put("reqOpenId", reqOpenId);
			query.put("reqOpenId", new BasicDBObject("$in", userValues));
			query.put("titleType", "1");
			 
//			sysDebugLog("ItemTitleQuery", "5575", "query",query.toString());
			
			filter_cloumn = new BasicDBObject(); // 查询字段
			filter_cloumn.put("_id", 0); // 不显示ID字段
			
			BasicDBObject sort_cloumn = new BasicDBObject(); // 排序字段
			sort_cloumn.put("titleUsed", -1);
 
			// 查询记录名称
			queryCount = trainItemTitles.count(query);
			if(queryCount>0){
				cursor = trainItemTitles.find(query, filter_cloumn)
						.limit(60).sort(sort_cloumn);
				list = cursor.toArray();
				jsonarray = JSONArray.fromObject(list); 
				jsonTitleOne.put("xxitemtitles", jsonarray);
//				jsonTitleAarr.add(jsonTitleOne); 
			}
			
			query = new BasicDBObject(); // 查询条件
//			query.put("reqOpenId", reqOpenId);
			query.put("reqOpenId", new BasicDBObject("$in", userValues));
			query.put("titleType", "2");
			 
			// 查询记录名称
			queryCount = trainItemTitles.count(query);
			if(queryCount>0){
				cursor = trainItemTitles.find(query, filter_cloumn)
						.limit(60).sort(sort_cloumn);
				list = cursor.toArray();
				jsonarray = JSONArray.fromObject(list); 
				jsonTitleOne.put("shitemtitles", jsonarray);
//				jsonTitleAarr.add(jsonTitleOne); 
			}
			
			query = new BasicDBObject(); // 查询条件
//			query.put("reqOpenId", reqOpenId);
			query.put("reqOpenId", new BasicDBObject("$in", userValues));
			query.put("titleType", "3");
			 
			// 查询记录名称
			queryCount = trainItemTitles.count(query);
			if(queryCount>0){
				cursor = trainItemTitles.find(query, filter_cloumn)
						.limit(60).sort(sort_cloumn);
				list = cursor.toArray();
				jsonarray = JSONArray.fromObject(list); 
				jsonTitleOne.put("ylitemtitles", jsonarray);
//				jsonTitleAarr.add(jsonTitleOne); 
			} 
			
			query = new BasicDBObject(); // 查询条件
//			query.put("reqOpenId", reqOpenId);
			query.put("reqOpenId", new BasicDBObject("$in", userValues));
			query.put("titleType", "4");
			 
			// 查询记录名称 --数学知识点
			queryCount = trainItemTitles.count(query);
			if(queryCount>0){
				cursor = trainItemTitles.find(query, filter_cloumn)
						.limit(60).sort(sort_cloumn);
				list = cursor.toArray();
				jsonarray = JSONArray.fromObject(list); 
				jsonTitleOne.put("mathsitemtitles", jsonarray);
//				jsonTitleAarr.add(jsonTitleOne); 
			} 
			
			jsonTitleAarr.add(jsonTitleOne);
			returnTex = jsonTitleAarr.toString(); 
		} else if ("ItemAngle".equals(getType)) {
			String funOpType = object.get("funOpType").toString(); 
  
			long queryCount=0;
			DBCursor cursor ;
			List<DBObject> list;
			JSONArray jsonarray;
			query = new BasicDBObject(); // 查询条件
			query.put("reqOpenId", reqOpenId);
			 
			BasicDBObject filter_cloumn = new BasicDBObject(); // 查询字段
			filter_cloumn.put("_id", 0); // 不显示ID字段
			filter_cloumn.put("reqOpenId", 0); // 不显示ID字段
			
			BasicDBObject sort_cloumn = new BasicDBObject(); // 排序字段
			sort_cloumn.put("angleId", -1);
			
			if ("getAngle".equals(funOpType)) {
				cursor = trainItemAngles.find(query, filter_cloumn)
						.limit(60).sort(sort_cloumn);
				list = cursor.toArray();
				jsonarray = JSONArray.fromObject(list);
				returnTex = jsonarray.toString();
			}else if("saveAngle".equals(funOpType)){
				存在则修改否则新增
				String saveDatas = object.get("SaveDataInfo").toString();
		  		jsonarray = JSONArray.fromObject(saveDatas); 
		  		JSONObject jsonInfoOne = new JSONObject(); 
		  		for(int i=0; i<jsonarray.size(); i++){  
		  		   jsonInfoOne = JSONObject.fromObject(jsonarray.get(i));   
		  		 
		  		   String angleInfo = jsonInfoOne.get("angleInfo").toString(); 
		  		   String angleType = jsonInfoOne.get("angleType").toString();
		  		   String angleTypeName = jsonInfoOne.get("angleTypeName").toString();
		  		   String angleValue = jsonInfoOne.get("angleValue").toString();
		  		   String angleTimeValue = jsonInfoOne.get("angleTimeValue").toString();
		  		   
		  		   记录是否存在
		  		   query = new BasicDBObject(); // 查询条件
				   query.put("reqOpenId", reqOpenId);
				   query.put("angleInfo", angleInfo);
				   
				   queryCount = trainItemAngles.count(query);
				   if(queryCount>0){
					   更新记录
					   BasicDBObject update_object = new BasicDBObject();
					   BasicDBObject set_object = new BasicDBObject();
					   set_object.put("angleType", angleType);
					   set_object.put("angleTypeName", angleTypeName);
					   set_object.put("angleValue", angleValue);
					   set_object.put("angleTimeValue", angleTimeValue);
					     
					   update_object.put("$set", set_object);
					   trainItemAngles.update(query, update_object, false, true);
				   }else{
					   新增记录
					   query = new BasicDBObject(); // 查询条件
					   query.put("reqOpenId", reqOpenId);
					   DBCursor angleId = trainItemAngles.find(query).limit(1)
								.sort(new BasicDBObject("angleId", -1));
					   DBObject objNewId = null;
					   long newangleId = 0;
					   try {
							while (angleId.hasNext()) {
								objNewId = angleId.next();
								newangleId = (Long) objNewId.get("angleId");
							}
						} finally {
							angleId.close();
						}
					   newangleId = newangleId + 1;
					  
					    // 添加数据
						DBObject newangleinfo = new BasicDBObject();
						
						newangleinfo.put("reqOpenId", reqOpenId);
						newangleinfo.put("angleId", newangleId);
						newangleinfo.put("angleInfo", angleInfo);
						newangleinfo.put("angleType", angleType);
						newangleinfo.put("angleTypeName", angleTypeName);
						newangleinfo.put("angleValue", angleValue);
						newangleinfo.put("angleTimeValue", angleTimeValue);
						    
						newangleId = trainItemAngles.save(newangleinfo).getN();
				   } 
		  		}  
			}else if("saveWord".equals(funOpType)){
				存在则修改否则新增
				String saveDatas = object.get("SaveDataInfo").toString();
				String[] save_data_all = saveDatas.split(",|，| ");
				String wordInfo = "";
				long newwordId = 0;
				for (int i = 0; i < save_data_all.length; i++) {
					wordInfo = save_data_all[i];
					if(wordInfo.length()<1){
						continue;
					}
					判断数据是否存在
					query = new BasicDBObject(); // 查询条件
				    query.put("reqOpenId", reqOpenId);
				    query.put("wordInfo", wordInfo);
				    queryCount = trainDictationWords.count(query);
				    if(queryCount>0){
				    	修改数据
				    	BasicDBObject update_object = new BasicDBObject();
					   BasicDBObject set_object = new BasicDBObject();
					   set_object.put("nearDayId", operDayId);
					     
					   update_object.put("$set", set_object);
					   trainItemAngles.update(query, update_object, false, true);
				    }else{
				    	新增数据
				    	query = new BasicDBObject(); // 查询条件
					   query.put("reqOpenId", reqOpenId);
					   DBCursor angleId = trainDictationWords.find(query).limit(1)
								.sort(new BasicDBObject("wordId", -1));
					   DBObject objNewId = null;
					   newwordId = 0;
					   try {
							while (angleId.hasNext()) {
								objNewId = angleId.next();
								newwordId = (Long) objNewId.get("wordId");
							}
						} finally {
							angleId.close();
						}
					   newwordId = newwordId + 1;
					  
					    // 添加数据
						DBObject newwordinfo = new BasicDBObject();
						
						newwordinfo.put("reqOpenId", reqOpenId);
						newwordinfo.put("wordId", newwordId);
						newwordinfo.put("wordInfo", wordInfo);
						newwordinfo.put("nearDayId", operDayId);
						newwordinfo.put("wordType", "1");
						newwordinfo.put("done", ""); 
						newwordinfo.put("check", "");  
						
						newwordId = trainDictationWords.save(newwordinfo).getN(); 
				    }
				}
			}else if("getWord".equals(funOpType)){
				
				query = new BasicDBObject(); // 查询条件
				query.put("reqOpenId", reqOpenId);
				 
				sort_cloumn = new BasicDBObject(); // 排序字段
				sort_cloumn.put("nearDayId", -1);
				
				cursor = trainDictationWords.find(query, filter_cloumn)
						.limit(60).sort(sort_cloumn);
				list = cursor.toArray();
				if(list.size()>0){
					
				}else{
					获取超级权限人员发布的信息
					query = new BasicDBObject(); // 查询条件
					
					 查询等级组对应的用户明细 
					BasicDBObject queryUserInfos = new BasicDBObject(); // 查询条件
					queryUserInfos.put("userGroupType", "Lvl9");
					DBCursor cursorUserInfo = smallusers.find(queryUserInfos).limit(
							200);
					List<DBObject> listUserInfo = cursorUserInfo.toArray();
					BasicDBList userValues = new BasicDBList();
					String listOpenId = "";

					for (int i = 0; i < listUserInfo.size(); i++) {
						listOpenId = listUserInfo.get(i).get("OpenId")
								.toString();
						userValues.add(listOpenId);
					}
					query.put("reqOpenId", new BasicDBObject("$in", userValues));
					 
					sort_cloumn = new BasicDBObject(); // 排序字段
					sort_cloumn.put("nearDayId", -1);
					
					cursor = trainDictationWords.find(query, filter_cloumn)
							.limit(60).sort(sort_cloumn);
					list = cursor.toArray();
				}
				jsonarray = JSONArray.fromObject(list);
				returnTex = jsonarray.toString();
				
			}else if("getWordHis".equals(funOpType)){
				
				query = new BasicDBObject(); // 查询条件
				query.put("reqOpenId", reqOpenId);
				 
				sort_cloumn = new BasicDBObject(); // 排序字段
				sort_cloumn.put("nearDayId", -1);
				         
				cursor = trainDictationInfos.find(query, filter_cloumn)
						.limit(60).sort(sort_cloumn);
				list = cursor.toArray();
				jsonarray = JSONArray.fromObject(list);
				returnTex = jsonarray.toString();
				
			}else if("saveDictation".equals(funOpType)){
				
				String saveDatas = object.get("SaveDataInfo").toString();
		  		jsonarray = JSONArray.fromObject(saveDatas); 
		  		JSONObject jsonInfoOne = new JSONObject(); 
		  		for(int i=0; i<jsonarray.size(); i++){  
		  		   jsonInfoOne = JSONObject.fromObject(jsonarray.get(i));   
		  		 
		  		   String wordInfo = jsonInfoOne.get("wordInfo").toString(); 
		  		   
		  		   记录是否存在
		  		   query = new BasicDBObject(); // 查询条件
				   query.put("reqOpenId", reqOpenId);
				   query.put("wordInfo", wordInfo);
				   
				   queryCount = trainDictationInfos.count(query);
				   if(queryCount>0){
					   更新记录
					   BasicDBObject update_object = new BasicDBObject();
					   BasicDBObject set_object = new BasicDBObject();
					   set_object.put("errorTimes", 1);
					   update_object.put("$inc", set_object);
					   set_object = new BasicDBObject();
					   set_object.put("nearDayId", operDayId);
					   update_object.put("$set", set_object);
					   trainDictationInfos.update(query, update_object, false, true);
				   }else{
					   新增记录
					   query = new BasicDBObject(); // 查询条件
					   query.put("reqOpenId", reqOpenId);
					   DBCursor angleId = trainDictationInfos.find(query).limit(1)
								.sort(new BasicDBObject("dictaId", -1));
					   DBObject objNewId = null;
					   long newId = 0;
					   long errorTimes = 1;
					   try {
							while (angleId.hasNext()) {
								objNewId = angleId.next();
								newId = (Long) objNewId.get("dictaId");
							}
						} finally {
							angleId.close();
						}
					    newId = newId + 1;
					  
					    // 添加数据
						DBObject newangleinfo = new BasicDBObject();
						
						newangleinfo.put("reqOpenId", reqOpenId);
						newangleinfo.put("dictaId", newId);
						newangleinfo.put("wordInfo", wordInfo);
						newangleinfo.put("errorTimes", errorTimes);
						newangleinfo.put("nearDayId", operDayId);
						newangleinfo.put("done", ""); 
						newangleinfo.put("check", "");
						    
						newId = trainDictationInfos.save(newangleinfo).getN();
				   } 
		  		}  
			}
		} */
		return returnTex;
	}
	
	/**
	 * 获取百度语音合成的Token
	 * 
	 * @param fromUser
	 *            用户信息
	 * 
	 * @return 返回百度语音合成的Token
	 */
	public String getAccessTokenInfo(String fromUser)
			throws UnknownHostException {

		String returnTex = "OK";
		

        // 获取当日日期
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss"); // 可以方便地修改日期格式
		SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy-MM-dd"); // 可以方便地修改日期格式
		String operDay = dayFormat.format(now);
		SimpleDateFormat dayFormatId = new SimpleDateFormat("yyyyMMdd"); // 可以方便地修改日期格式
		String operDayId = dayFormatId.format(now);

		Calendar c = Calendar.getInstance();
		c.setTime(now);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day - 27);
		String overDayId = new SimpleDateFormat("yyyyMMdd").format(c.getTime());

		 
		// ----------------查询指定编码的唯一信息内容---------------------
		long ingressIdCount = baiduTokenParaRepository.findByOperDayId("text2audio", overDayId).size();
		if (ingressIdCount > 0) {
			/* 存在记录获取信息 */
			returnTex = baiduTokenParaRepository.findByOperDayId("text2audio", overDayId).get(0).getAccessToken().toString();
		} else {
			// 重新获取accessToken并记录到数据中再返回
			String accessToken = baiduApiInfo.baidugettoken();

			// 添加数据
			BaiduTokenPara baiduTokenPara = new BaiduTokenPara();
			baiduTokenPara.setOperDate(dateFormat.format(now).toString());
			baiduTokenPara.setOperDay(operDay);
			baiduTokenPara.setOperDayId(operDayId);
			baiduTokenPara.setAccessToken(accessToken);
			baiduTokenPara.setTokenPara("text2audio");
			
			baiduTokenParaRepository.save(baiduTokenPara);
			returnTex = accessToken;
		}
		System.out.println(returnTex);
 
		return returnTex;
	}
	
}