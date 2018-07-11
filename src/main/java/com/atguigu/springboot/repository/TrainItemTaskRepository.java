package com.atguigu.springboot.repository;

import java.util.List;

import com.atguigu.springboot.entity.TrainItemTask;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//继承JpaRepository来完成对数据库的操作
public interface TrainItemTaskRepository extends JpaRepository<TrainItemTask,Integer> {
	
	@Query(value = "select max(req_item_time_id) from train_item_task ", nativeQuery = true)
	long findMaxReqItemTimeId();
	
	@Query(value = "select * from train_item_task where time_stamp = :timeStamp order by req_item_time_id desc limit :limitCount ", nativeQuery = true)
	List<TrainItemTask> queryByTimeStamp(@Param("timeStamp") String timeStamp,@Param("limitCount") Integer limitCount);
	
	@Query(value = "select * from train_item_task where req_open_id = :reqOpenId and time_stamp = :timeStamp order by req_item_time_id desc limit :limitCount ", nativeQuery = true)
	List<TrainItemTask> queryByReqOpenIdAndTimeStamp(@Param("reqOpenId") String reqOpenId,@Param("timeStamp") String timeStamp,@Param("limitCount") Integer limitCount);
	
	@Query(value = "select * from train_item_task where req_open_id = :reqOpenId and finish_flag = :finishFlag order by req_item_time_id desc limit :limitCount ", nativeQuery = true)
	List<TrainItemTask> queryByReqOpenIdAndFinishFlag(@Param("reqOpenId") String reqOpenId,@Param("finishFlag") String finishFlag,@Param("limitCount") Integer limitCount);
	
	@Query(value = "select * from train_item_task where req_open_id = :reqOpenId and finish_flag = :finishFlag and item_title like %:itemTitle% order by req_item_time_id desc limit :limitCount ", nativeQuery = true)
	List<TrainItemTask> searchByReqOpenIdAndFinishFlag(@Param("reqOpenId") String reqOpenId,@Param("finishFlag") String finishFlag,@Param("itemTitle") String itemTitle,@Param("limitCount") Integer limitCount);
	
}
