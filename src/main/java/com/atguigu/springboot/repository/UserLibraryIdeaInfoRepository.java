package com.atguigu.springboot.repository;

import java.util.List;

import com.atguigu.springboot.entity.UserLibraryIdeaInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//继承JpaRepository来完成对数据库的操作
public interface UserLibraryIdeaInfoRepository extends JpaRepository<UserLibraryIdeaInfo,Integer> {
	
	@Query(value = "select * from user_library_idea_info where answer = :answer order by id desc limit :limitCount ", nativeQuery = true)
	List<UserLibraryIdeaInfo> findByAnswer(@Param("answer") String answer,@Param("limitCount") Integer limitCount);
	
	@Query(value = "select count(*) from user_library_idea_info where open_id = :openId and library_id = :libraryId ", nativeQuery = true)
	long queryByIdAndLibrary(@Param("openId") String openId,@Param("libraryId") Integer libraryId);
	
	@Query(value = "select * from user_library_idea_info where open_id = :openId and library_id = :libraryId limit 3 ", nativeQuery = true)
	List<UserLibraryIdeaInfo> findByIdAndLibrary(@Param("openId") String openId,@Param("libraryId") Integer libraryId);
}
