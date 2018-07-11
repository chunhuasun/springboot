package com.atguigu.springboot.repository;
 

import java.util.List;

import com.atguigu.springboot.entity.TrainItemPublish;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

//继承JpaRepository来完成对数据库的操作
public interface TrainItemPublishRepository extends JpaRepository<TrainItemPublish,Integer> {
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "updaet train_item_publish set showallow = :showallow where req_open_id = :reqOpenId or add_open_id = :addOpenId ", nativeQuery = true)
	int updateByQuery(@Param("reqOpenId")String reqOpenId,@Param("addOpenId")String addOpenId,@Param("showallow")String showallow);

	@Query(value = "select max(req_item_time_id) from train_item_publish ", nativeQuery = true)
	long findMaxReqItemTimeId();
	
	@Query(value = "select * from train_item_publish where req_open_id = :reqOpenId and req_item_time_id = :reqItemTimeId order by req_item_time_id desc limit :limitCount ", nativeQuery = true)
	List<TrainItemPublish> queryByReqOpenIdAndReqItemTimeId(@Param("reqOpenId") String reqOpenId,@Param("reqItemTimeId") long reqItemTimeId,@Param("limitCount") Integer limitCount);
	
	@Query(value = "select * from train_item_publish where showallow = 'allow' and ((oper_day_id = :operDayId and req_item_time_id < :reqItemTimeId ) or oper_day_id < :laseDayId )  "
			+ "order by oper_day_id desc , req_item_time_id desc , pub_flag desc limit :limitCount ", nativeQuery = true)
	List<TrainItemPublish> queryByOperDayIdAndReqItemTimeId(@Param("operDayId") String operDayId,@Param("reqItemTimeId") long reqItemTimeId,@Param("laseDayId") String laseDayId,@Param("limitCount") Integer limitCount);
	
	@Query(value = "select * from train_item_publish where showallow = 'allow' and req_open_id = :reqOpenId and req_item_time_id = :reqItemTimeId order by time_stamp desc limit :limitCount ", nativeQuery = true)
	List<TrainItemPublish> queryByReqOpenIdAndReqItemTimeIdAllow(@Param("reqOpenId") String reqOpenId,@Param("reqItemTimeId") long reqItemTimeId,@Param("limitCount") Integer limitCount);
	
}
