package com.atguigu.springboot.repository;

import java.util.List;

import com.atguigu.springboot.entity.TrainItemKnowledge;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//继承JpaRepository来完成对数据库的操作
public interface TrainItemKnowledgeRepository extends JpaRepository<TrainItemKnowledge,Integer> {
	
	@Query(value = "select * from train_item_knowledge where req_open_id = :reqOpenId order by req_item_time_id desc limit :limitCount ", nativeQuery = true)
	List<TrainItemKnowledge> findByReqOpenId(@Param("reqOpenId") String reqOpenId,@Param("limitCount") Integer limitCount);
	
	@Query(value = "select * from train_item_knowledge where req_open_id = :reqOpenId and itemtitletype = :itemtitletype order by req_item_time_id desc limit :limitCount ", nativeQuery = true)
	List<TrainItemKnowledge> findByReqOpenIdAndtitletype(@Param("reqOpenId") String reqOpenId,@Param("itemtitletype") String itemtitletype,@Param("limitCount") Integer limitCount);
	
	@Query(value = "select * from train_item_knowledge where req_open_id = :reqOpenId and item_title like %:itemTitle% order by req_item_time_id desc limit :limitCount ", nativeQuery = true)
	List<TrainItemKnowledge> findByReqOpenIdAndItemTitle(@Param("reqOpenId") String reqOpenId,@Param("itemTitle") String itemTitle,@Param("limitCount") Integer limitCount);
	
	@Query(value = "select * from train_item_knowledge where req_open_id = :reqOpenId and req_item_time_id = :reqItemTimeId order by req_item_time_id desc limit :limitCount ", nativeQuery = true)
	List<TrainItemKnowledge> findByReqOpenIdAndItemId(@Param("reqOpenId") String reqOpenId,@Param("reqItemTimeId") long reqItemTimeId,@Param("limitCount") Integer limitCount);
	
}
