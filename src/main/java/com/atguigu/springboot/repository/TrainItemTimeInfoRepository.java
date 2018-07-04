package com.atguigu.springboot.repository;

import java.util.List;

import com.atguigu.springboot.entity.TrainItemTimeInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//继承JpaRepository来完成对数据库的操作
public interface TrainItemTimeInfoRepository extends JpaRepository<TrainItemTimeInfo,Integer> {
	
	@Query(value = "select * from TrainItemTimeInfo where reqOpenId = :reqOpenId and operDayId = :operDayId order by reqItemTimeId desc limit 1 ", nativeQuery = true)
	List<TrainItemTimeInfo> findByReqOpenIdAndOperDayId(@Param("reqOpenId") String reqOpenId,@Param("operDayId") String operDayId);
	   
}
