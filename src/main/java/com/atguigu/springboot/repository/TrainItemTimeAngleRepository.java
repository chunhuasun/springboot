package com.atguigu.springboot.repository;

import java.util.List;

import com.atguigu.springboot.entity.TrainItemTimeAngle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//继承JpaRepository来完成对数据库的操作
public interface TrainItemTimeAngleRepository extends JpaRepository<TrainItemTimeAngle,Integer> {
	
	@Query(value = "select * from train_item_time_angle where req_open_id = :reqOpenId and angle_id = :angleId order by req_item_time_id desc limit :limitCount ", nativeQuery = true)
	List<TrainItemTimeAngle> findByReqOpenIdAndOperDayId(@Param("reqOpenId") String reqOpenId,@Param("angleId") long angleId,@Param("limitCount") Integer limitCount);
	
}
