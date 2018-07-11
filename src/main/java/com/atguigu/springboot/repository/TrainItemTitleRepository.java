package com.atguigu.springboot.repository;

import java.util.List;

import com.atguigu.springboot.entity.TrainItemTitle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

//继承JpaRepository来完成对数据库的操作
public interface TrainItemTitleRepository extends JpaRepository<TrainItemTitle,Integer> {
	
	@Query(value = "select count(*) from train_item_title where req_open_id = :reqOpenId ", nativeQuery = true)
	long countByReqOpenId(@Param("reqOpenId") String reqOpenId);
	
	@Query(value = "select count(*) from train_item_title where req_open_id = :reqOpenId and title_info = :titleInfo and title_type = '4' ", nativeQuery = true)
	long countByReqOpenIdAndTitleInfo(@Param("reqOpenId") String reqOpenId,@Param("titleInfo") String titleInfo);
	
	@Query(value = "select * from train_item_title where req_open_id = :reqOpenId and title_type = '4' order by title_used desc limit :limitCount ", nativeQuery = true)
	List<TrainItemTitle> queryByReqOpenId(@Param("reqOpenId") String reqOpenId,@Param("limitCount") Integer limitCount);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "update train_item_title set title_used = title_used + 1 where req_open_id = :reqOpenId and title_info = :titleInfo and title_type = '4' ", nativeQuery = true)
	int updateByReqOpenIdAndTitleInfo(@Param("reqOpenId") String reqOpenId,@Param("titleInfo") String titleInfo);
	
	@Query(value = "select * from train_item_title where req_open_id in (:reqOpenIds) and title_type = :titleType order by title_used desc limit :limitCount ", nativeQuery = true)
	List<TrainItemTitle> findByReqOpenIdGroupAndTitleType(@Param("reqOpenIds") List<String> reqOpenIds,@Param("titleType") String titleType,@Param("limitCount") Integer limitCount);
	
}
