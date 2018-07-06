package com.atguigu.springboot.repository;
 

import com.atguigu.springboot.entity.TrainItemPublish; 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//继承JpaRepository来完成对数据库的操作
public interface TrainItemPublishRepository extends JpaRepository<TrainItemPublish,Integer> {
	
	@Query(value = "updaet train_item_publish set showallow = :showallow where req_open_id = :reqOpenId or add_open_id = :addOpenId ", nativeQuery = true)
	long updateByQuery(@Param("reqOpenId")String reqOpenId,@Param("addOpenId")String addOpenId,@Param("showallow")String showallow);
}
