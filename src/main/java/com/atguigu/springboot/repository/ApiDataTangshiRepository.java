package com.atguigu.springboot.repository;

import java.util.List;

import com.atguigu.springboot.entity.ApiDataTangshi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//继承JpaRepository来完成对数据库的操作
public interface ApiDataTangshiRepository extends JpaRepository<ApiDataTangshi,Integer> {
	
	@Query(value = "select * from api_data_tangshi where title like %:qryword% or content like %:qryword% order by id desc limit :limitCount ", nativeQuery = true)
	List<ApiDataTangshi> findByQryword(@Param("qryword") String qryword,@Param("limitCount") Integer limitCount);
	 
}
