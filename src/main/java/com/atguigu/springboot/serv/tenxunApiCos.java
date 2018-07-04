package com.atguigu.springboot.serv;
 
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap; 

import org.json.JSONObject;  
  








import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.request.GetFileLocalRequest;
import com.qcloud.cos.request.UploadFileRequest;
import com.qcloud.cos.sign.Credentials;

public class tenxunApiCos {

	 //设置APPID/AK/SK
     public static final long appId = 1253822284;
     public static final String secretId = "AKIDnjYCfwN1qW0LbgqIpAKJ61q12GOOnrMf";
     public static final String secretKey = "GKI5g5IJA2nNlxypV2NYXM0yBD4jCTYV";
     // 设置要操作的bucket
     public static final String bucketName = "mycosgz";
      

	 //private static String recEncoding = "UTF-8"; 
	  /**
	   * getCos  下载文件
	   * 
	   * @param filePath 文件地址
	   *        
	   * @return 返回指定图片的首个文字内容信息
	   */ 
	  public static String getFileCos(String filePath,String fileName) {
	    
		 String returnText = "";
		
		 // 初始化秘钥信息
	     Credentials cred = new Credentials(appId, secretId, secretKey);

	     // 初始化客户端配置
	     ClientConfig clientConfig = new ClientConfig();
	     // 设置bucket所在的区域，比如华南园区：gz； 华北园区：tj；华东园区：sh ；
	     clientConfig.setRegion("gz");

	     // 初始化cosClient
	     COSClient cosClient = new COSClient(clientConfig, cred);

	     //filePath = filePath + "1man1.jpg";
	     //filePath = filePath + "17060222.avi";
	     //String localPathDown = "src/test/resources/local_file_down.txt";
	     String cosFilePath = "/mycosgz/" + fileName;
	     String localPathDown = filePath + fileName;
		 GetFileLocalRequest getFileLocalRequest =
		   new GetFileLocalRequest(bucketName, cosFilePath, localPathDown);
		 getFileLocalRequest.setUseCDN(false);
		 getFileLocalRequest.setReferer("*.myweb.cn");
		 String getFileResult = cosClient.getFileLocal(getFileLocalRequest);
 
         
        
		 return returnText;
	  }    
	  
	  /**
	   * sendFileCos 上传本地路径文件 
	   * 
	   * @param filePath 文件地址
	   *        
	   * @return 返回指定图片的首个文字内容信息
	   */ 
	  public static String sendFileCos(String sendPathName,String saveFileName) {
	    
		 String returnText = "";
		
		 // 初始化秘钥信息
	     Credentials cred = new Credentials(appId, secretId, secretKey);

	     // 初始化客户端配置
	     ClientConfig clientConfig = new ClientConfig();
	     // 设置bucket所在的区域，比如华南园区：gz； 华北园区：tj；华东园区：sh ；
	     clientConfig.setRegion("gz");

	     // 初始化cosClient
	     COSClient cosClient = new COSClient(clientConfig, cred);

	     //filePath = filePath + fileName;
	     String cosPath = "/mycosgz/"+saveFileName;
	     String localPath = sendPathName;
	     UploadFileRequest uploadFileRequest = 
	    		 new UploadFileRequest(bucketName,cosPath, localPath);
	     String uploadFileRet = cosClient.uploadFile(uploadFileRequest);

	     System.out.println(uploadFileRet);
	      
		 return uploadFileRet;
	  }    
	  
	  /**
	   * sendFileCos 上传流数据文件 
	   * 
	   * @param filePath 文件地址
	   *        
	   * @return 返回指定图片的首个文字内容信息
	   */ 
	  public static String sendFileCosBufer(byte[] sendFileBufer,String saveFileName) {
	    
		 String returnText = "";
		
		 // 初始化秘钥信息
	     Credentials cred = new Credentials(appId, secretId, secretKey);

	     // 初始化客户端配置
	     ClientConfig clientConfig = new ClientConfig();
	     // 设置bucket所在的区域，比如华南园区：gz； 华北园区：tj；华东园区：sh ；
	     clientConfig.setRegion("gz");

	     // 初始化cosClient
	     COSClient cosClient = new COSClient(clientConfig, cred);

	     //filePath = filePath + fileName;
	     String cosPath = "/mycosgz/"+saveFileName;
	      
	     UploadFileRequest uploadFileRequest = 
	    		 new UploadFileRequest(bucketName,cosPath, sendFileBufer);
	     String uploadFileRet = cosClient.uploadFile(uploadFileRequest);

	     System.out.println("cosClient.uploadFile="+uploadFileRet);
	      
		 return uploadFileRet;
	  }    
	  
	  
	  public static byte[] getByte(File file) throws Exception {
			byte[] bytes = null;
			if (file != null) {
				InputStream is = new FileInputStream(file);
				int length = (int) file.length();
				if (length > Integer.MAX_VALUE) // 当文件的长度超过了int的最大值
				{
					System.out.println("this file is max ");
					return null;
				}
				bytes = new byte[length];
				int offset = 0;
				int numRead = 0;
				while (offset < bytes.length
						&& (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
					offset += numRead;
				}
				// 如果得到的字节长度和file实际的长度不一致就可能出错了
				if (offset < bytes.length) {
					System.out.println("file length is error");
					return null;
				}
				is.close();
			}
			return bytes;
		} 
	  
}