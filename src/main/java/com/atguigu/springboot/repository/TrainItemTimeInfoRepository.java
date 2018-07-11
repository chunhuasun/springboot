package com.atguigu.springboot.repository;

import java.util.List;

import com.atguigu.springboot.entity.TrainItemTimeInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//继承JpaRepository来完成对数据库的操作
public interface TrainItemTimeInfoRepository extends JpaRepository<TrainItemTimeInfo,Integer> {
	
	@Query(value = "select * from train_item_time_info where req_open_id = :reqOpenId and oper_day_id = :operDayId order by req_item_time_id desc limit :limitCount ", nativeQuery = true)
	List<TrainItemTimeInfo> findByReqOpenIdAndOperDayId(@Param("reqOpenId") String reqOpenId,@Param("operDayId") String operDayId,@Param("limitCount") Integer limitCount);
	
	@Query(value = "select * from train_item_time_info where req_open_id = :reqOpenId and oper_day_id = :operDayId and req_item_time_id = :reqItemTimeId order by req_item_time_id desc limit :limitCount ", nativeQuery = true)
	List<TrainItemTimeInfo> findByReqOpenIdAndTimeId(@Param("reqOpenId") String reqOpenId,@Param("operDayId") String operDayId,@Param("reqItemTimeId") long reqItemTimeId,@Param("limitCount") Integer limitCount);
	
	@Query(value = "select * from train_item_time_info where req_open_id = :reqOpenId and item_title = :itemTitle and finish_flag = '1' and oper_day_id <= :operDayId and req_item_time_id = :reqItemTimeId order by req_item_time_id desc limit :limitCount ", nativeQuery = true)
	List<TrainItemTimeInfo> findByReqOpenIdAndItemTitle(@Param("reqOpenId") String reqOpenId,@Param("itemTitle") String itemTitle,@Param("operDayId") String operDayId,@Param("limitCount") Integer limitCount);
	
	
}
