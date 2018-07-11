package com.atguigu.springboot.repository;

import java.util.List;

import com.atguigu.springboot.entity.TrainDictationInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

//继承JpaRepository来完成对数据库的操作
public interface TrainDictationInfoRepository extends JpaRepository<TrainDictationInfo,Integer> {
	
	@Query(value = "select * from train_dictation_info where req_open_id = :reqOpenId order by dicta_id desc limit :limitCount ", nativeQuery = true)
	List<TrainDictationInfo> findByReqOpenId(@Param("reqOpenId") String reqOpenId,@Param("limitCount") Integer limitCount);
	
	@Query(value = "select * from train_dictation_info where req_open_id = :reqOpenId order by near_day_id desc limit :limitCount ", nativeQuery = true)
	List<TrainDictationInfo> findByReqOpenIdOrderDayId(@Param("reqOpenId") String reqOpenId,@Param("limitCount") Integer limitCount);
	
	@Query(value = "select count(*) from train_dictation_info where req_open_id = :reqOpenId and word_info = :wordInfo ", nativeQuery = true)
	long countByReqOpenId(@Param("reqOpenId") String reqOpenId,@Param("wordInfo") String wordInfo);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "update train_dictation_info set near_day_id = :nearDayId , error_times = error_times+1 where req_open_id = :reqOpenId and word_info = :wordInfo ", nativeQuery = true)
	int updateByReqOpenId(@Param("nearDayId") String nearDayId,
			@Param("reqOpenId") String reqOpenId,@Param("wordInfo") String wordInfo);
	 
}
