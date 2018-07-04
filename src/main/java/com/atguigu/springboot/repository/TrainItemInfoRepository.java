package com.atguigu.springboot.repository;

import java.util.List;

import com.atguigu.springboot.entity.TrainItemInfo;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//继承JpaRepository来完成对数据库的操作
public interface TrainItemInfoRepository extends JpaRepository<TrainItemInfo,Integer> {
	
	List<TrainItemInfo> findByTrainPlanIdOrderByInfoGroupIdDesc(long trainPlanId,Pageable pageable);
	List<TrainItemInfo> findByTrainPlanIdAndInfoGroupId(long trainPlanId,long infoGroupId,Pageable pageable);
	
	@Query(value = "select * from TrainItemInfo where trainPlanId = :trainPlanId and infoGroupId < :infoGroupId order by operDayId desc , infoGroupId desc limit 1 ", nativeQuery = true)
	List<TrainItemInfo> findByTrainPlanIdLessInfoGroupId(@Param("trainPlanId") long trainPlanId,@Param("infoGroupId") long infoGroupId);
	  
}
