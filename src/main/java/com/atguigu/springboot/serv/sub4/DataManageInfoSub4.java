package com.atguigu.springboot.serv.sub4;

import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.atguigu.springboot.entity.TrainDictationInfo;
import com.atguigu.springboot.entity.TrainDictationWord;
import com.atguigu.springboot.entity.TrainItemAngle;
import com.atguigu.springboot.entity.TrainItemInfo;
import com.atguigu.springboot.entity.TrainItemKnowledge;
import com.atguigu.springboot.entity.TrainItemMusictimer;
import com.atguigu.springboot.entity.TrainItemOpus;
import com.atguigu.springboot.entity.TrainItemOpusComment;
import com.atguigu.springboot.entity.TrainItemOpusHome;
import com.atguigu.springboot.entity.TrainItemPublish;
import com.atguigu.springboot.entity.TrainItemSaveInfo;
import com.atguigu.springboot.entity.TrainItemTask;
import com.atguigu.springboot.entity.TrainItemTaskComment;
import com.atguigu.springboot.entity.TrainItemTimeAngle;
import com.atguigu.springboot.entity.TrainItemTimeInfo;
import com.atguigu.springboot.entity.TrainItemTitle;
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
			//wxSmallUser.setAvatarUrl(imageInfo);
			wxSmallUser.setAvatarUrl(cos_url);
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

		  
		//获取用户权限等级
		long queryCount = 0;
		long useServTimes = 0;
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
 
			List<TrainItemTimeInfo> trainItemTimeInfos = trainItemTimeInfoRepository.findByReqOpenIdAndOperDayId(reqOpenId,operDayId,1);
			 
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
		}else if ("ItemKnowledge".equals(getType)) {
			String funOpType = object.get("funOpType").toString(); 
			JSONArray jsonarray;
			List<TrainItemKnowledge> trainItemKnowledges;
			TrainItemKnowledge trainItemKnowledge;
			TrainItemTitle trainItemTitle;
			List<TrainItemTitle> trainItemTitles;
			long count = 0 ;
			
			if ("new".equals(funOpType))
			{
				String saveDataInfo = object.get("SaveDataInfo").toString();
				object = JSONObject.fromObject(saveDataInfo);
				
				trainItemKnowledges = trainItemKnowledgeRepository.findByReqOpenId(reqOpenId, 1);
				long reqItemTimeId = 0;
				if (trainItemKnowledges.size() > 0) {
					reqItemTimeId = trainItemKnowledges.get(0).getReqItemTimeId() + 1;
				} else {
					reqItemTimeId = 1;
				}
				
				// 添加数据
				trainItemKnowledge = new TrainItemKnowledge();
 
				trainItemKnowledge.setReqOpenId(reqOpenId);
				trainItemKnowledge.setOperDayId(operDayId);
				trainItemKnowledge.setOperDay(operDay);
				trainItemKnowledge.setReqItemTimeId(reqItemTimeId);
				trainItemKnowledge.setItemTitle(object.get("itemTitle").toString());
				trainItemKnowledge.setItemtitletype(object.get("itemtitletype").toString());
				trainItemKnowledge.setItemInfo(object.get("itemInfo").toString());
				trainItemKnowledge.setItemaddinfo(object.get("itemaddinfo").toString());
				trainItemKnowledge.setCosimageurls(object.get("cosimageurls").toString());
				
				trainItemKnowledgeRepository.save(trainItemKnowledge); 
				
				//更新标题大类信息
				count = trainItemTitleRepository.countByReqOpenId(reqOpenId);
				
				long titleId = count+1;
				
				count = trainItemTitleRepository.countByReqOpenIdAndTitleInfo(reqOpenId, object.get("itemtitletype").toString());
				if(count>0){
					//存在标题增加使用次数
					trainItemTitleRepository.updateByReqOpenIdAndTitleInfo(reqOpenId, object.get("itemtitletype").toString());
				}else{
					//不存在标题，增加标题
					trainItemTitle = new TrainItemTitle();
 
					trainItemTitle.setReqOpenId(reqOpenId);
					trainItemTitle.setTitleId(titleId);
					trainItemTitle.setTitleInfo(object.get("itemtitletype").toString());
					trainItemTitle.setTitleType("4");
					trainItemTitle.setTitleTypeName("知识点");
					trainItemTitle.setTitleUsed(1); 
					
					trainItemTitleRepository.save(trainItemTitle); 
				} 
			} else if ("queryList".equals(funOpType)) {
				//查询类别的记录数
				trainItemTitles = trainItemTitleRepository.queryByReqOpenId(reqOpenId, 40);
				jsonarray = JSONArray.fromObject(trainItemTitles);
				returnTex = jsonarray.toString();
			
			}else if ("queryByType".equals(funOpType)) {
				String itemtitletype = object.get("itemtype").toString();
				//按照类别查询知识条目
				trainItemKnowledges = trainItemKnowledgeRepository.findByReqOpenIdAndtitletype(reqOpenId, itemtitletype, 40);
				jsonarray = JSONArray.fromObject(trainItemKnowledges);
				returnTex = jsonarray.toString();
			}else if ("queryBySearch".equals(funOpType)) {
				String itemtitletype = object.get("itemtype").toString();
				//按照类别查询知识条目
				trainItemKnowledges = trainItemKnowledgeRepository.findByReqOpenIdAndItemTitle(reqOpenId, itemtitletype, 40);
				jsonarray = JSONArray.fromObject(trainItemKnowledges);
				returnTex = jsonarray.toString();
			}else if ("byid".equals(funOpType)) {
				String itemTimeId = object.get("itemTimeId").toString();
				//按照类别查询知识条目
				trainItemKnowledges = trainItemKnowledgeRepository.findByReqOpenIdAndItemId(reqOpenId, Long.parseLong(itemTimeId), 40);
				jsonarray = JSONArray.fromObject(trainItemKnowledges);
				returnTex = jsonarray.toString();
			}
		} else if ("ItemTask".equals(getType)) {
				String funOpType = object.get("funOpType").toString(); 
				JSONArray jsonarray;
				JSONArray jsonTaskAarr = new JSONArray();
				JSONObject jsonTaskOne = new JSONObject();
				TrainItemTask trainItemTask; 
				List<TrainItemTask> trainItemTasks;
				WxSmallUser wxSmallUser;
				TrainItemTaskComment trainItemTaskComment;
				List<TrainItemOpusComment> trainItemOpusComments;

				if ("new".equals(funOpType))
				{
					String saveDataInfo = object.get("SaveDataInfo").toString();
					object = JSONObject.fromObject(saveDataInfo); 
					
					long reqItemTimeId = 0;
					reqItemTimeId = trainItemTaskRepository.findMaxReqItemTimeId()+1;
					
					// 添加数据
					trainItemTask = new TrainItemTask(); 
                    
					trainItemTask.setReqOpenId(reqOpenId);
					trainItemTask.setOperDayId(operDayId);
					trainItemTask.setOperDay(operDay);
					trainItemTask.setReqItemTimeId(reqItemTimeId);
					trainItemTask.setTimeStamp(timeStamp);
					trainItemTask.setItemTitle(object.get("itemTitle").toString());
					trainItemTask.setItemtitletype(object.get("itemtitletype").toString());
					trainItemTask.setItemInfo(object.get("itemInfo").toString());
					trainItemTask.setItemaddinfo(object.get("itemaddinfo").toString());
					trainItemTask.setCosimageurls(object.get("cosimageurls").toString());
					trainItemTask.setFinishFlag("0");  //事项完结标示
					trainItemTask.setFinishDayId(operDayId);
					trainItemTask.setFinishDay(operDay);
					
					trainItemTaskRepository.save(trainItemTask); 
				}else if ("end".equals(funOpType)) {
					// 判断是否存在数据ID itemTimeId 存在则只修改相关信息 否则为新增已经完成的记录
					String saveDataInfo = object.get("SaveDataInfo").toString();
					object = JSONObject.fromObject(saveDataInfo);
					//查询是否存在事项ID
					trainItemTasks = trainItemTaskRepository.queryByTimeStamp(object.get("timeStamp").toString() , 1);
					if (trainItemTasks.size() > 0) {
						// 修改数据
						trainItemTask = trainItemTasks.get(0);
						
						trainItemTask.setItemTitle(object.get("itemTitle").toString());
						trainItemTask.setItemtitletype(object.get("itemtitletype").toString());
						trainItemTask.setItemInfo(object.get("itemInfo").toString());
						trainItemTask.setItemaddinfo(object.get("itemaddinfo").toString());
						trainItemTask.setCosimageurls(object.get("cosimageurls").toString());
						trainItemTask.setFinishFlag("1");  //事项完结标示
						trainItemTask.setFinishDayId(operDayId);
						trainItemTask.setFinishDay(operDay);
						  
						trainItemTaskRepository.save(trainItemTask);
					}else{
						//直接新增信息
						long reqItemTimeId = 0;
						reqItemTimeId = trainItemTaskRepository.findMaxReqItemTimeId()+1;
						
						// 添加数据
						trainItemTask = new TrainItemTask(); 
	                    
						trainItemTask.setReqOpenId(reqOpenId);
						trainItemTask.setOperDayId(operDayId);
						trainItemTask.setOperDay(operDay);
						trainItemTask.setReqItemTimeId(reqItemTimeId);
						trainItemTask.setTimeStamp(timeStamp);
						trainItemTask.setItemTitle(object.get("itemTitle").toString());
						trainItemTask.setItemtitletype(object.get("itemtitletype").toString());
						trainItemTask.setItemInfo(object.get("itemInfo").toString());
						trainItemTask.setItemaddinfo(object.get("itemaddinfo").toString());
						trainItemTask.setCosimageurls(object.get("cosimageurls").toString());
						trainItemTask.setFinishFlag("1");  //事项完结标示
						trainItemTask.setFinishDayId(operDayId);
						trainItemTask.setFinishDay(operDay);
						
						trainItemTaskRepository.save(trainItemTask);
					}
					jsonarray = JSONArray.fromObject(trainItemTasks);
					returnTex = jsonarray.toString();
					
				}else if ("queryList".equals(funOpType)) {
					//查询待完成事项和已完成事项
					//已完成的事项列表
					trainItemTasks = trainItemTaskRepository.queryByReqOpenIdAndFinishFlag(reqOpenId,"1",10);
					if(trainItemTasks.size() > 0){
						jsonarray = JSONArray.fromObject(trainItemTasks); 
						jsonTaskOne.put("finish", jsonarray);
					}

					//未完成的事项列表
					trainItemTasks = trainItemTaskRepository.queryByReqOpenIdAndFinishFlag(reqOpenId,"0",10);
					if(trainItemTasks.size() > 0){
						jsonarray = JSONArray.fromObject(trainItemTasks); 
						jsonTaskOne.put("action", jsonarray);
					}
					jsonTaskAarr.add(jsonTaskOne);
					returnTex = jsonTaskAarr.toString(); 
				} else if ("queryBySearch".equals(funOpType)) {
					String itemtitletype = object.get("itemtype").toString();
					//按照类别查询知识条目
					trainItemTasks = trainItemTaskRepository.searchByReqOpenIdAndFinishFlag(reqOpenId, "1", itemtitletype, 10);
					if(trainItemTasks.size() > 0){
						jsonarray = JSONArray.fromObject(trainItemTasks); 
						jsonTaskOne.put("finish", jsonarray);
					}

					trainItemTasks = trainItemTaskRepository.searchByReqOpenIdAndFinishFlag(reqOpenId, "0", itemtitletype, 10);
					if(trainItemTasks.size() > 0){
						jsonarray = JSONArray.fromObject(trainItemTasks); 
						jsonTaskOne.put("action", jsonarray);
					}
					 
					jsonTaskAarr.add(jsonTaskOne);
					returnTex = jsonTaskAarr.toString(); 
				}else if ("byid".equals(funOpType)) {
					//按照类别查询知识条目
					trainItemTasks = trainItemTaskRepository.queryByReqOpenIdAndTimeStamp(reqOpenId, object.get("timeStamp").toString(), 40);
					jsonarray = JSONArray.fromObject(trainItemTasks);
					returnTex = jsonarray.toString();
				} else if("opuspublish".equals(funOpType)||"opustask".equals(funOpType)){ //留言、评论信息处理
	                String querystamp = object.get("querystamp").toString();
	                
					//获取用户信息写入发布信息中
					wxSmallUser = wxSmallUserRepository.findByOpenId(reqOpenId).get(0);
					if("".equals(wxSmallUser.getUserOperType1())||wxSmallUser.getUserOperType1()==null||"allow".equals(wxSmallUser.getUserOperType1())){	
						now = new Date();
			  			timeStamp = timeStampmId.format(now);
				  	    // 添加数据
			  			trainItemTaskComment = new TrainItemTaskComment();

			  			trainItemTaskComment.setReqOpenId(reqOpenId);
			  			trainItemTaskComment.setOperDayId(operDayId);
			  			trainItemTaskComment.setOperDay(operDay); 
			  			trainItemTaskComment.setTimeStamp(timeStamp);
			  			trainItemTaskComment.setTasktimeStamp(querystamp);  
			  			trainItemTaskComment.setPublishInfo(object.get("SaveDataInfo").toString());   
						//显示控制
			  			trainItemTaskComment.setShowallow("allow");
						//用户信息 
			  			trainItemTaskComment.setPubNickName(wxSmallUser.getNickName());  
			  			trainItemTaskComment.setPubAvatarUrl(wxSmallUser.getCosUrl());
						
						if("opuspublish".equals(funOpType)){
							long reqItemTimeId = 0;
			                reqItemTimeId = trainItemOpusCommentRepository.queryByMaxOpustimeStampPubFlag(querystamp, "publish")+1;
			                trainItemTaskComment.setReqItemTimeId(reqItemTimeId);
			                trainItemTaskComment.setPubFlag("publish");  
						}else if("opustask".equals(funOpType)){
							trainItemTaskComment.setReqItemTimeId(Long.parseLong(object.get("publishId").toString()));
							trainItemTaskComment.setPubFlag("task");  
						} 
						trainItemTaskCommentRepository.save(trainItemTaskComment);
					}else{
						returnTex = "发言不合规，已被禁言";
					}
				} else if("killpublish".equals(funOpType)||"killtask".equals(funOpType)){ //删除留言、评论信息处理
	                String querystamp = object.get("querystamp").toString();
	                String killstamp = object.get("SaveDataInfo").toString();
	                String killflag = "0";
	                String pubFlag = "";
	                long killReqId = 0;
	                //查询用户的权限信息
	                if("killpublish".equals(funOpType)){
	                	pubFlag = "publish"; 
					}else if("killtask".equals(funOpType)){
						pubFlag = "task";
					}
	                
	                trainItemOpusComments = trainItemOpusCommentRepository.queryBytimeStampPubFlag(querystamp, killstamp, pubFlag, 1);
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
	                    	trainItemOpusComments = trainItemOpusCommentRepository.queryBytimeStampPubFlag(querystamp,killstamp,"task",300);
	                    	trainItemOpusCommentRepository.delete(trainItemOpusComments);
	    				}
	    			}else{
	    				returnTex = "不能越权操作";
	    			}
				}
		} else if ("ItemMusictimer".equals(getType)) {
			String funOpType = object.get("funOpType").toString(); 
			JSONArray jsonarray;
			List<TrainItemMusictimer> trainItemMusictimers;
			TrainItemMusictimer trainItemMusictimer;
			
			if ("save".equals(funOpType))
			{
				long reqItemTimeId = 0;
				reqItemTimeId = trainItemMusictimerRepository.findMaxReqItemTimeId(reqOpenId)+1;
				
				// 添加数据
				trainItemMusictimer = new TrainItemMusictimer();

				trainItemMusictimer.setReqOpenId(reqOpenId);
				trainItemMusictimer.setOperDayId(operDayId);
				trainItemMusictimer.setOperDay(operDay);
				trainItemMusictimer.setReqItemTimeId(reqItemTimeId);
				trainItemMusictimer.setItemInfo(object.get("itemInfo").toString());
				trainItemMusictimer.setItemList(object.get("SaveDataInfo").toString());  

				trainItemMusictimerRepository.save(trainItemMusictimer);
				
			}else if("queryByid".equals(funOpType)){
				String itemTimeId = object.get("itemTimeId").toString();
				//按照类别查询知识条目
				trainItemMusictimers = trainItemMusictimerRepository.queryByReqOpenIdAndReqItemTimeId(reqOpenId, Long.parseLong(itemTimeId), 1);
				jsonarray = JSONArray.fromObject(trainItemMusictimers);
				returnTex = jsonarray.toString();
			}else if ("queryByOrder".equals(funOpType)) {
				trainItemMusictimers = trainItemMusictimerRepository.queryByReqOpenId(reqOpenId, 10);
				jsonarray = JSONArray.fromObject(trainItemMusictimers);
				returnTex = jsonarray.toString(); 
			}else if ("queryBySearch".equals(funOpType)) {
				String itemtitletype = object.get("itemtype").toString();
				//按照类别查询知识条目
				trainItemMusictimers = trainItemMusictimerRepository.queryByReqOpenIdAndItemInfo(reqOpenId, itemtitletype, 10);
				jsonarray = JSONArray.fromObject(trainItemMusictimers);
				returnTex = jsonarray.toString();
			} 
		} else if ("ItemPublishInfo".equals(getType)) {
			String funOpType = object.get("funOpType").toString(); 
			JSONArray jsonarray;
			WxSmallUser wxSmallUser;
			List<TrainItemPublish> trainItemPublishs;
			TrainItemPublish trainItemPublish;


			if ("savely".equals(funOpType)) //发布留言信息
			{ 
				long reqItemTimeId = 0;
				reqItemTimeId = trainItemPublishRepository.findMaxReqItemTimeId()+1;
				 
				//获取用户信息写入发布信息中
				wxSmallUser = wxSmallUserRepository.findByOpenId(reqOpenId).get(0);
				 
				// 添加数据
				trainItemPublish = new TrainItemPublish();

				trainItemPublish.setReqOpenId(reqOpenId);
				trainItemPublish.setOperDayId(operDayId);
				trainItemPublish.setOperDay(operDay);
				trainItemPublish.setReqItemTimeId(reqItemTimeId);
				trainItemPublish.setPublishInfo(object.get("SaveDataInfo").toString());  
				trainItemPublish.setAddOperDay(operDay);
				trainItemPublish.setTimeStamp(timeStamp);
				trainItemPublish.setAddTaskInfo("");  
				trainItemPublish.setAddOpenId("");  
				//用户名称和头像 
				trainItemPublish.setPubNickName(wxSmallUser.getNickName());  
				trainItemPublish.setPubAvatarUrl(wxSmallUser.getAvatarUrl());  
				trainItemPublish.setPubFlag("publish");  
				trainItemPublish.setTaskNickName("");
				//显示控制
				trainItemPublish.setShowallow("allow");

				if("".equals(wxSmallUser.getUserOperType1())||wxSmallUser.getUserOperType1()==null||"allow".equals(wxSmallUser.getUserOperType1())){
					//默认使用 userOperType1 控制发布权限 后期可以考虑或者
					trainItemPublishRepository.save(trainItemPublish);
				}else{
					returnTex = "之前的发言不文明，已被禁言";
				}
			}else if("savepl".equals(funOpType)){ //发布评论信息
				String itemTimeId = object.get("itemTimeId").toString();
				String pubOpenId = object.get("pubOpenId").toString(); 
				
				//按照类别查询知识条目
				trainItemPublishs = trainItemPublishRepository.queryByReqOpenIdAndReqItemTimeId(pubOpenId, Long.parseLong(itemTimeId), 1); 
				 
				// 添加数据
				trainItemPublish = new TrainItemPublish();

				trainItemPublish.setReqOpenId(trainItemPublishs.get(0).getReqOpenId());
				trainItemPublish.setOperDayId(trainItemPublishs.get(0).getOperDayId());
				trainItemPublish.setOperDay(trainItemPublishs.get(0).getOperDay());
				trainItemPublish.setReqItemTimeId(trainItemPublishs.get(0).getReqItemTimeId());
				trainItemPublish.setPublishInfo("");  
				trainItemPublish.setAddOperDay(operDay);
				trainItemPublish.setTimeStamp(timeStamp);
				trainItemPublish.setAddTaskInfo(object.get("SaveDataInfo").toString());  
				trainItemPublish.setAddOpenId(reqOpenId);  
				//显示控制
				trainItemPublish.setShowallow("allow");
				    
				//获取用户信息写入发布信息中
				wxSmallUser = wxSmallUserRepository.findByOpenId(reqOpenId).get(0);
				
				//用户名称和头像 
				trainItemPublish.setPubNickName("");  
				trainItemPublish.setPubAvatarUrl("");  
				trainItemPublish.setPubFlag("task");  
				trainItemPublish.setTaskNickName(wxSmallUser.getNickName());
				 
				if("".equals(wxSmallUser.getUserOperType1())||wxSmallUser.getUserOperType1()==null||"allow".equals(wxSmallUser.getUserOperType1())){
					//默认使用 userOperType1 控制发布权限 后期可以考虑或者
					trainItemPublishRepository.save(trainItemPublish);
				}else{
					returnTex = "之前的发言不文明，已被禁言";
				}
			}else if ("queryinfo".equals(funOpType)) { //支持下拉刷新获取后续信息
				//按照类别查询知识条目
				trainItemPublishs = trainItemPublishRepository.queryByOperDayIdAndReqItemTimeId(object.get("queryDay").toString(),Long.parseLong(object.get("itemTimeId").toString()), object.get("queryDay").toString(), 30);
				jsonarray = JSONArray.fromObject(trainItemPublishs);
				returnTex = jsonarray.toString();  
				
			}else if ("querytask".equals(funOpType)) {  //查看指定内容的评论信息
				String itemTimeId = object.get("itemTimeId").toString();
				String pubOpenId = object.get("pubOpenId").toString(); 
					
				trainItemPublishs = trainItemPublishRepository.queryByReqOpenIdAndReqItemTimeIdAllow(pubOpenId, Long.parseLong(itemTimeId), 40);
				jsonarray = JSONArray.fromObject(trainItemPublishs);
				returnTex = jsonarray.toString(); 
			} 
		}else if ("ItemOpusInfo".equals(getType)) {
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
		} else if ("ItemTimeQuery".equals(getType)) {
			String funOpType = object.get("funOpType").toString();
			String queryDay = object.get("queryDay").toString();

			List<TrainItemTimeInfo> trainItemTimeInfos;
			List<TrainItemTimeAngle> trainItemTimeAngles;
			
			if ("list".equals(funOpType)) {
				trainItemTimeInfos = trainItemTimeInfoRepository.findByReqOpenIdAndOperDayId(reqOpenId,queryDay,10);
				JSONArray jsonarray = JSONArray.fromObject(trainItemTimeInfos);
				returnTex = jsonarray.toString();
			} else if ("byid".equals(funOpType)) {
				String itemTimeId = object.get("itemTimeId").toString();
				long qryItemTimeId = 0;
				if (itemTimeId != null && !"".equals(itemTimeId)) {
					qryItemTimeId = Long.parseLong(itemTimeId);
				}
				
				trainItemTimeInfos = trainItemTimeInfoRepository.findByReqOpenIdAndTimeId(reqOpenId, queryDay, qryItemTimeId , 1);
				JSONObject jsonmap = new JSONObject();
				jsonmap.element("trainItem", JSONArray.fromObject(trainItemTimeInfos).toString()); 
				if(trainItemTimeInfos.size()>0){
					long angleId = trainItemTimeInfos.get(0).getTimeAngleId();
					
					trainItemTimeAngles = trainItemTimeAngleRepository.findByReqOpenIdAndOperDayId(reqOpenId, angleId, 70);
					jsonmap.element("trainItemAngle", JSONArray.fromObject(trainItemTimeAngles).toString());  
				}
				returnTex = jsonmap.toString();

			} else if ("hisbyid".equals(funOpType)) {
				// 通过名称查询历史数据信息
				String itemTimeId = object.get("itemTimeId").toString();
				if (itemTimeId != null && !"".equals(itemTimeId)) {
					// 查询记录名称
					trainItemTimeInfos = trainItemTimeInfoRepository.findByReqOpenIdAndTimeId(reqOpenId, queryDay, Long.parseLong(itemTimeId) , 1);
					
					String itemTitle =  trainItemTimeInfos.get(0).getItemTitle();

					trainItemTimeInfos = trainItemTimeInfoRepository.findByReqOpenIdAndItemTitle(reqOpenId, itemTitle, queryDay, 10);
					JSONArray jsonarray = JSONArray.fromObject(trainItemTimeInfos);
					returnTex = jsonarray.toString();
				}
			}
		} else if ("ItemTitleQuery".equals(getType)) {
			JSONArray jsonarray;
			JSONArray jsonTitleAarr = new JSONArray();
			JSONObject jsonTitleOne = new JSONObject();
			List<WxSmallUser> wxSmallUsers;
			List<String> listUserInfo = new ArrayList<String>();
			String listOpenId = "";
			List<TrainItemTitle> trainItemTitles;
			
			// 增加权限组的区分 从userServLimit 表中获取用户拥有的权限
			wxSmallUsers = wxSmallUserRepository.findByUserGroupType("Lvl9", 100);//超级管理员
			for (int i = 0; i < wxSmallUsers.size(); i++) {
				listOpenId = wxSmallUsers.get(i).getOpenId();
				listUserInfo.add(listOpenId);
			}
			
 			wxSmallUsers = wxSmallUserRepository.findByUserGroupType("Lvl2", 100);//授权发布人   后续考虑设置可查看的等级
 			for (int i = 0; i < wxSmallUsers.size(); i++) {
				listOpenId = wxSmallUsers.get(i).getOpenId();
				listUserInfo.add(listOpenId);
			}
 			listUserInfo.add(reqOpenId);
			 
			// 查询记录名称
			trainItemTitles = trainItemTitleRepository.findByReqOpenIdGroupAndTitleType(listUserInfo,"1", 60);
			if(trainItemTitles.size()>0){
				jsonarray = JSONArray.fromObject(trainItemTitles); 
				jsonTitleOne.put("xxitemtitles", jsonarray);
//				jsonTitleAarr.add(jsonTitleOne); 
			}
			 
			// 查询记录名称
			trainItemTitles = trainItemTitleRepository.findByReqOpenIdGroupAndTitleType(listUserInfo,"2", 60);
			if(trainItemTitles.size()>0){
				jsonarray = JSONArray.fromObject(trainItemTitles); 
				jsonTitleOne.put("shitemtitles", jsonarray);
//				jsonTitleAarr.add(jsonTitleOne); 
			}
			 
			// 查询记录名称
			trainItemTitles = trainItemTitleRepository.findByReqOpenIdGroupAndTitleType(listUserInfo,"3", 60);
			if(trainItemTitles.size()>0){
				jsonarray = JSONArray.fromObject(trainItemTitles); 
				jsonTitleOne.put("ylitemtitles", jsonarray);
//				jsonTitleAarr.add(jsonTitleOne); 
			} 
			 
			// 查询记录名称 --数学知识点
			trainItemTitles = trainItemTitleRepository.findByReqOpenIdGroupAndTitleType(listUserInfo,"4", 60);
			if(trainItemTitles.size()>0){
				jsonarray = JSONArray.fromObject(trainItemTitles); 
				jsonTitleOne.put("mathsitemtitles", jsonarray);
//				jsonTitleAarr.add(jsonTitleOne); 
			} 
			
			jsonTitleAarr.add(jsonTitleOne);
			returnTex = jsonTitleAarr.toString(); 
		} else if ("ItemAngle".equals(getType)) {
			String funOpType = object.get("funOpType").toString(); 
  
			long queryCount=0;
			JSONArray jsonarray;
			List<TrainItemAngle> trainItemAngles;
			TrainItemAngle trainItemAngle;
			List<TrainDictationWord> trainDictationWords ;
			TrainDictationWord trainDictationWord;
			List<TrainDictationInfo> trainDictationInfos ;
			TrainDictationInfo trainDictationInfo;
			
			if ("getAngle".equals(funOpType)) {
				trainItemAngles = trainItemAngleRepository.findByReqOpenId(reqOpenId, 60);
				jsonarray = JSONArray.fromObject(trainItemAngles);
				returnTex = jsonarray.toString();
			}else if("saveAngle".equals(funOpType)){
				//存在则修改否则新增
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
		  		   
		  		   //记录是否存在
				   queryCount = trainItemAngleRepository.countByReqOpenId(reqOpenId, angleInfo);
				   if(queryCount>0){
					   //更新记录
					   trainItemAngleRepository.updateByReqOpenId(angleType, angleTypeName, angleValue, angleTimeValue , reqOpenId, angleInfo);
				   }else{
					   //新增记录
					   long angleId = 0;
					   trainItemAngles = trainItemAngleRepository.findByReqOpenId(reqOpenId, 1);
					   if(trainItemAngles.size()>0){
						   angleId= trainItemAngles.get(0).getAngleId();
					   }
					   angleId = angleId + 1;
					   
					   // 添加数据
					   trainItemAngle = new TrainItemAngle();
					   
					   trainItemAngle.setReqOpenId(reqOpenId);
					   trainItemAngle.setAngleId(angleId);	
					   trainItemAngle.setAngleInfo(angleInfo);	
					   trainItemAngle.setAngleType(angleType);
					   trainItemAngle.setAngleTypeName(angleTypeName);
					   trainItemAngle.setAngleValue(angleValue);
					   trainItemAngle.setAngleTimeValue(angleTimeValue);
					   
					   trainItemAngleRepository.save(trainItemAngle);  
				   } 
		  		}  
			}else if("saveWord".equals(funOpType)){
				//存在则修改否则新增
				String saveDatas = object.get("SaveDataInfo").toString();
				String[] save_data_all = saveDatas.split(",|，| ");
				String wordInfo = "";
				long wordId = 0;
				for (int i = 0; i < save_data_all.length; i++) {
					wordInfo = save_data_all[i];
					if(wordInfo.length()<1){
						continue;
					}
					//判断数据是否存在
					queryCount = trainDictationWordRepository.countByReqOpenId(reqOpenId, wordInfo);
				    if(queryCount>0){
				    	//修改数据
					    trainDictationWordRepository.updateByReqOpenId(operDayId, reqOpenId, wordInfo);
				    }else{
				        //新增数据
				        trainDictationWords = trainDictationWordRepository.findByReqOpenId(reqOpenId, 1);
					    wordId = 0;
					    if(trainDictationWords.size()>0){
					    	wordId = trainDictationWords.get(0).getWordId();
					    }
					    wordId = wordId + 1;
					  
					    // 添加数据
					    trainDictationWord = new TrainDictationWord();
					    
					    trainDictationWord.setReqOpenId(reqOpenId);
					    trainDictationWord.setWordId(wordId);
					    trainDictationWord.setWordInfo(wordInfo);
					    trainDictationWord.setNearDayId(operDayId);
					    trainDictationWord.setWordType("1");
					    trainDictationWord.setDone("");
					    trainDictationWord.setCheckFlag("");
					    
					    trainDictationWordRepository.save(trainDictationWord);
				    }
				}
			}else if("getWord".equals(funOpType)){
				trainDictationWords = trainDictationWordRepository.findByReqOpenIdOrderDayId(reqOpenId, 60);
				if(trainDictationWords.size()>0){
				}else{
					//获取超级权限人员发布的信息
					List<WxSmallUser> wxSmallUsers = wxSmallUserRepository.findByUserGroupType("Lvl9",200);
					List<String> listUserInfo = new ArrayList<String>();
					String listOpenId = "";
					for (int i = 0; i < wxSmallUsers.size(); i++) {
						listOpenId = wxSmallUsers.get(i).getOpenId();
						listUserInfo.add(listOpenId);
					}
					trainDictationWords = trainDictationWordRepository.findByReqOpenIdGroup(listUserInfo, 60);
				}
				jsonarray = JSONArray.fromObject(trainDictationWords);
				returnTex = jsonarray.toString();
				
			}else if("getWordHis".equals(funOpType)){
				
				trainDictationInfos = trainDictationInfoRepository.findByReqOpenIdOrderDayId(reqOpenId, 60);
				jsonarray = JSONArray.fromObject(trainDictationInfos);
				returnTex = jsonarray.toString();
				
			}else if("saveDictation".equals(funOpType)){
				
				String saveDatas = object.get("SaveDataInfo").toString();
		  		jsonarray = JSONArray.fromObject(saveDatas); 
		  		JSONObject jsonInfoOne = new JSONObject(); 
		  		for(int i=0; i<jsonarray.size(); i++){  
		  		   jsonInfoOne = JSONObject.fromObject(jsonarray.get(i));   
		  		   String wordInfo = jsonInfoOne.get("wordInfo").toString(); 
		  		   //记录是否存在
		  		   queryCount = trainDictationInfoRepository.countByReqOpenId(reqOpenId, wordInfo);
				   if(queryCount>0){
					   //更新记录
					   trainDictationInfoRepository.updateByReqOpenId(operDayId, reqOpenId, wordInfo);
				   }else{
					   //新增记录
					   trainDictationInfos = trainDictationInfoRepository.findByReqOpenId(reqOpenId, 1);
					   long newId = 0;
					   long errorTimes = 1;
					   if(trainDictationInfos.size()>0){
						   newId = trainDictationInfos.get(0).getDictaId();
					   }
					   newId = newId + 1;
					   
					   trainDictationInfo = new TrainDictationInfo();
					   
					   trainDictationInfo.setReqOpenId(reqOpenId);
					   trainDictationInfo.setDictaId(newId);
					   trainDictationInfo.setWordInfo(wordInfo);
					   trainDictationInfo.setErrorTimes(errorTimes);
					   trainDictationInfo.setNearDayId(operDayId);
					   trainDictationInfo.setDone("");
					   trainDictationInfo.setCheckFlag("");	
					   
					   trainDictationInfoRepository.save(trainDictationInfo); 
				   } 
		  		}  
			}
		} 
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