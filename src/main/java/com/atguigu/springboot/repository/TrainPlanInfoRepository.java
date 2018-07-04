package com.atguigu.springboot.repository;

import java.util.List;

import com.atguigu.springboot.entity.TrainPlanInfo;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

//继承JpaRepository来完成对数据库的操作
public interface TrainPlanInfoRepository extends JpaRepository<TrainPlanInfo,Integer> {

	List<TrainPlanInfo> findByOrderByTrainPlanIdDesc(Pageable pageable);
	
}

