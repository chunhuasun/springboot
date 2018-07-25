package com.atguigu.springboot.repository;

import java.util.List;

import com.atguigu.springboot.entity.ApiDataPhrase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//继承JpaRepository来完成对数据库的操作
public interface ApiDataPhraseRepository extends JpaRepository<ApiDataPhrase,Integer> {
	
	@Query(value = "select * from api_data_phrase order by api_flag asc, id desc limit :limitCount ", nativeQuery = true)
	List<ApiDataPhrase> findByQryOrder(@Param("limitCount") Integer limitCount);
	
	@Query(value = "select count(*) from api_data_phrase where phrase = :phrase ", nativeQuery = true)
	long queryByPhrase(@Param("phrase") String phrase);
	
	@Query(value = "select * from api_data_phrase where phrase like %:phrase%  order by api_flag asc, id desc limit :limitCount ", nativeQuery = true)
	List<ApiDataPhrase> findByPhrase(@Param("phrase") String phrase,@Param("limitCount") Integer limitCount);
	
	@Query(value = "select * from api_data_phrase where phrase like %:phrase%  order by rand() limit :limitCount ", nativeQuery = true)
	List<ApiDataPhrase> findByPhraseRand(@Param("phrase") String phrase,@Param("limitCount") Integer limitCount);
	
	@Query(value = "select * from api_data_phrase where length(example) > 7  order by rand() limit :limitCount ", nativeQuery = true)
	List<ApiDataPhrase> findByExampleRand(@Param("limitCount") Integer limitCount);
	
	@Query(value = "select * from api_data_phrase where phrase not like %:phrase%  order by rand() limit :limitCount ", nativeQuery = true)
	List<ApiDataPhrase> findByNotPhraseRand(@Param("phrase") String phrase,@Param("limitCount") Integer limitCount);
	
}
