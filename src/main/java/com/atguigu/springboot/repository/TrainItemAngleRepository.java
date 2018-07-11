package com.atguigu.springboot.repository;

import java.util.List;

import com.atguigu.springboot.entity.TrainItemAngle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

//继承JpaRepository来完成对数据库的操作
public interface TrainItemAngleRepository extends JpaRepository<TrainItemAngle,Integer> {
	
	@Query(value = "select * from train_item_angle where req_open_id = :reqOpenId order by angle_id desc limit :limitCount ", nativeQuery = true)
	List<TrainItemAngle> findByReqOpenId(@Param("reqOpenId") String reqOpenId,@Param("limitCount") Integer limitCount);
	
	@Query(value = "select count(*) from train_item_angle where req_open_id = :reqOpenId and angle_info = :angleInfo ", nativeQuery = true)
	long countByReqOpenId(@Param("reqOpenId") String reqOpenId,@Param("angleInfo") String angleInfo);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "update train_item_angle set angle_type = :angleType,angle_type_name = :angleTypeName,angle_value = :angleValue, "
			+ "angle_time_value = :angleTimeValue where req_open_id = :reqOpenId and angle_info = :angleInfo ", nativeQuery = true)
	int updateByReqOpenId(@Param("angleType") String angleType,@Param("angleTypeName") String angleTypeName,
			@Param("angleValue") String angleValue, @Param("angleTimeValue") String angleTimeValue,
			@Param("reqOpenId") String reqOpenId,@Param("angleInfo") String angleInfo);
	   
}
