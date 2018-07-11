package com.atguigu.springboot.repository;

import java.util.List;

import com.atguigu.springboot.entity.TrainItemMusictimer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//继承JpaRepository来完成对数据库的操作
public interface TrainItemMusictimerRepository extends JpaRepository<TrainItemMusictimer,Integer> {
	
	@Query(value = "select max(req_item_time_id) from train_item_musictimer where req_open_id = :reqOpenId ", nativeQuery = true)
	long findMaxReqItemTimeId(@Param("reqOpenId") String reqOpenId);
	
	@Query(value = "select * from train_item_musictimer where time_stamp = :timeStamp order by req_item_time_id desc limit :limitCount ", nativeQuery = true)
	List<TrainItemMusictimer> queryByTimeStamp(@Param("timeStamp") String timeStamp,@Param("limitCount") Integer limitCount);
	
	@Query(value = "select * from train_item_musictimer where req_open_id = :reqOpenId and req_item_time_id = :reqItemTimeId order by req_item_time_id desc limit :limitCount ", nativeQuery = true)
	List<TrainItemMusictimer> queryByReqOpenIdAndReqItemTimeId(@Param("reqOpenId") String reqOpenId,@Param("reqItemTimeId") long reqItemTimeId,@Param("limitCount") Integer limitCount);
	
	@Query(value = "select * from train_item_musictimer where req_open_id = :reqOpenId order by req_item_time_id desc limit :limitCount ", nativeQuery = true)
	List<TrainItemMusictimer> queryByReqOpenId(@Param("reqOpenId") String reqOpenId,@Param("limitCount") Integer limitCount);
	
	@Query(value = "select * from train_item_musictimer where req_open_id = :reqOpenId and item_info like %:itemInfo% order by req_item_time_id desc limit :limitCount ", nativeQuery = true)
	List<TrainItemMusictimer> queryByReqOpenIdAndItemInfo(@Param("reqOpenId") String reqOpenId,@Param("itemInfo") String itemInfo,@Param("limitCount") Integer limitCount);
	
}
