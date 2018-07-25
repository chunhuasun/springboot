package com.atguigu.springboot.repository;

import java.util.List;

import com.atguigu.springboot.entity.ApiDataKeyword;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//继承JpaRepository来完成对数据库的操作
public interface ApiDataKeywordRepository extends JpaRepository<ApiDataKeyword,Integer> {
	
	@Query(value = "select * from api_data_keyword where keyword <> '，' order by api_flag asc , id desc limit :limitCount ", nativeQuery = true)
	List<ApiDataKeyword> findByQryOrder(@Param("limitCount") Integer limitCount);
	
	@Query(value = "select count(*) from api_data_keyword where keyword = :keyword ", nativeQuery = true)
	long queryByKeyword(@Param("keyword") String keyword);
	
}
