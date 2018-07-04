package com.atguigu.springboot.repository;

import java.util.List;

import com.atguigu.springboot.entity.TrainItemSaveInfo;

import org.springframework.data.jpa.repository.JpaRepository;

//继承JpaRepository来完成对数据库的操作
public interface TrainItemSaveInfoRepository extends JpaRepository<TrainItemSaveInfo,Integer> {

	List<TrainItemSaveInfo> findByTrainPlanIdAndTrainItemIdAndReqOpenIdAndOperDayId(long trainPlanId,long trainItemId,String reqOpenId,String operDayId);
	 
}

