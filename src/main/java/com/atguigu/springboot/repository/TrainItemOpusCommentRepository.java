package com.atguigu.springboot.repository;

import java.util.List;
 
import com.atguigu.springboot.entity.TrainItemOpusComment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//继承JpaRepository来完成对数据库的操作
public interface TrainItemOpusCommentRepository extends JpaRepository<TrainItemOpusComment,Integer> {
	List<TrainItemOpusComment> findByReqOpenId(String reqOpenId);
	
	@Query(value = "select * from train_item_opus_comment where opustime_stamp = :opustimeStamp and showallow = :showallow"
			+ " order by req_item_time_id desc,pub_flag asc,time_stamp desc limit :limitCount", nativeQuery = true)
	List<TrainItemOpusComment> queryByOpustimeStamp(@Param("opustimeStamp")String opustimeStamp,@Param("showallow")String showallow,@Param("limitCount") Integer limitCount);

	@Query(value = "select * from train_item_opus_comment where opustime_stamp = :opustimeStamp and showallow = :showallow and req_item_time_id < :reqItemTimeId"
			+ " order by req_item_time_id desc,pub_flag asc,time_stamp desc limit :limitCount", nativeQuery = true)
	List<TrainItemOpusComment> queryByReqItemTimeId(@Param("opustimeStamp")String opustimeStamp,@Param("showallow")String showallow,@Param("reqItemTimeId")String reqItemTimeId,@Param("limitCount") Integer limitCount);
  
	@Query(value = "select * from train_item_opus_comment where opustime_stamp = :opustimeStamp and pub_flag = :pubFlag"
			+ " order by req_item_time_id desc,pub_flag asc,time_stamp desc limit :limitCount", nativeQuery = true)
	List<TrainItemOpusComment> queryByOpustimeStampPubFlag(@Param("opustimeStamp")String opustimeStamp,@Param("pubFlag")String pubFlag,@Param("limitCount") Integer limitCount);

	@Query(value = "select max(req_item_time_id) from train_item_opus_comment where opustime_stamp = :opustimeStamp and pub_flag = :pubFlag" , nativeQuery = true)
	long queryByMaxOpustimeStampPubFlag(@Param("opustimeStamp")String opustimeStamp,@Param("pubFlag")String pubFlag);

	@Query(value = "select * from train_item_opus_comment where opustime_stamp = :opustimeStamp and time_stamp = :timeStamp and pub_flag = :pubFlag"
			+ " order by req_item_time_id desc,pub_flag asc,time_stamp desc limit :limitCount", nativeQuery = true)
	List<TrainItemOpusComment> queryBytimeStampPubFlag(@Param("opustimeStamp")String opustimeStamp,@Param("timeStamp")String timeStamp,@Param("pubFlag")String pubFlag,@Param("limitCount") Integer limitCount);
     
	@Query(value = "select * from train_item_opus_comment where opustime_stamp = :opustimeStamp and req_item_time_id = :reqItemTimeId ", nativeQuery = true)
	List<TrainItemOpusComment> queryByopusreqid(@Param("opustimeStamp")String opustimeStamp,@Param("reqItemTimeId")long reqItemTimeId);
   
}
