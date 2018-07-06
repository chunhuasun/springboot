package com.atguigu.springboot.repository;

import java.util.List;

import com.atguigu.springboot.entity.TrainItemOpus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//继承JpaRepository来完成对数据库的操作
public interface TrainItemOpusRepository extends JpaRepository<TrainItemOpus,Integer> {

	@Query(value = "select * from train_item_opus where queryinfo like %:queryinfo% and time_stamp < :timeStamp order by req_item_time_id desc limit :limitCount", nativeQuery = true)
	List<TrainItemOpus> queryByQueryinfo(@Param("queryinfo")String queryinfo,@Param("timeStamp")String timeStamp,@Param("limitCount") Integer limitCount);
	
	@Query(value = "select * from train_item_opus where time_stamp < :timeStamp order by req_item_time_id desc limit :limitCount", nativeQuery = true)
	List<TrainItemOpus> queryByLessTimeStamp(@Param("timeStamp")String timeStamp,@Param("limitCount") Integer limitCount);
	
	@Query(value = "select * from train_item_opus where time_stamp = :timeStamp order by time_stamp desc ", nativeQuery = true)
	List<TrainItemOpus> queryByTimeStamp(@Param("timeStamp")String timeStamp);
	
	@Query(value = "select max(req_item_time_id) from train_item_opus ", nativeQuery = true)
	long queryMaxReqItemTimeId();
}


