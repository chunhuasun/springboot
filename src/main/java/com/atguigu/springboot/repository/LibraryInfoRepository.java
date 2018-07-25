package com.atguigu.springboot.repository;

import java.util.List;

import com.atguigu.springboot.entity.LibraryInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//继承JpaRepository来完成对数据库的操作
public interface LibraryInfoRepository extends JpaRepository<LibraryInfo,Integer> {
	
	@Query(value = "select * from library_info where answer = :answer order by id desc limit :limitCount ", nativeQuery = true)
	List<LibraryInfo> findByAnswer(@Param("answer") String answer,@Param("limitCount") Integer limitCount);
	
	@Query(value = "select max(id) from library_info where answer = :answer ", nativeQuery = true)
	Integer queryIdByAnswer(@Param("answer") String answer);
	
	@Query(value = "select * from library_info where id = :id ", nativeQuery = true)
	List<LibraryInfo> findById(@Param("id") Integer id);
}
