package com.atguigu.springboot.servsub6;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; 

import com.atguigu.springboot.entity.InfoCheckCardResult;
import com.atguigu.springboot.entity.TenxunCosInfo;
import com.atguigu.springboot.entity.TrainItemOpus;
import com.atguigu.springboot.repository.TenxunCosInfoRepository;
import com.atguigu.springboot.repository.TrainItemOpusRepository;


@Service
public class ImageDataManageInfoSub6 {
	
	@Autowired
	private TenxunCosInfoRepository tenxunCosInfoRepository;
	@Autowired
	private TrainItemOpusRepository trainItemOpusRepository;
	
	/**
	 * 2018-07-09  照片信息存储，用于异步检查图片的处理
	 * 
	 * @param cosUrl  图片地址信息
	 *        changeType  修改类别
	 *        changeInfo  修改信息
	 *          
	 * 
	 * @return 返回训练计划内容信息
	 */
	public void changetxCOSData(String cosUrl,String changeType,String changeInfo)
			throws UnknownHostException {

		 
		//查询传入的地址是否存在
		TenxunCosInfo tenxunCosInfo;
		List<TenxunCosInfo> tenxunCosInfos = tenxunCosInfoRepository.findByCosUrlInfo(cosUrl);
		if(tenxunCosInfos.size()>0){
			//存在数据修改信息
			tenxunCosInfo = tenxunCosInfos.get(0);
			//判断是否需要修改发布信息
			if("imageCensor".equals(changeType)){
				//修改图像审核信息
				tenxunCosInfo.setImageCensor(changeInfo);
				if(!"合规".equals(changeInfo)){
					tenxunCosInfo.setCensorFlag("2");  //不合规
				}else{
					tenxunCosInfo.setCensorFlag("1");  //审核合规
				}
			}else if("imagePick".equals(changeType)){
				//图片文字识别信息
				tenxunCosInfo.setImagePick(changeInfo);
				tenxunCosInfo.setImagePickFlag("1");
			}
			
			if("0".equals(tenxunCosInfo.getImageUseFlag())&&!"0".equals(tenxunCosInfo.getCensorFlag())&&"1".equals(tenxunCosInfo.getImagePickFlag())){
				//未使用信息，并且图像审核和文字识别信息均可使用，修改状态和发布信息
				tenxunCosInfo.setImageUseFlag("1");
			    
				//查询发布信息是否存在，存在则修改状态，
				TrainItemOpus trainItemOpus;
				List<TrainItemOpus> trainItemOpuss = trainItemOpusRepository.queryByOpusurl(cosUrl);
				List<TrainItemOpus> trainItemOpusList =new ArrayList<TrainItemOpus>();
				for(int i=0; i<trainItemOpuss.size(); i++){
					trainItemOpus = trainItemOpuss.get(i);
					
					if("2".equals(tenxunCosInfo.getCensorFlag())){
	  			    	//不合规的照片
	  			    	trainItemOpus.setCensorInfo(tenxunCosInfo.getImageCensor());
	  			    	trainItemOpus.setCensorUrl(trainItemOpus.getOpusurl());
	  			    	//提供固定的违规展示照片
	  			    	cosUrl = "http://mycos-1253822284.coscd.myqcloud.com/otherFile/18no.jpg";
	  			    	trainItemOpus.setOpusurl(cosUrl);
	  			    }
	  			    if("1".equals(tenxunCosInfo.getImagePickFlag())&&!"".equals(tenxunCosInfo.getImagePick())){
	  			    	trainItemOpus.setQueryinfo(trainItemOpus.getQueryinfo()+
				                   tenxunCosInfo.getImagePick()
				                   );
	  			    }
	  			    trainItemOpusList.add(trainItemOpus);
				}
				if(trainItemOpusList.size()>0){
					trainItemOpusRepository.save(trainItemOpusList);
				}
			}
			
			tenxunCosInfoRepository.save(tenxunCosInfo);
		}
	} 
}