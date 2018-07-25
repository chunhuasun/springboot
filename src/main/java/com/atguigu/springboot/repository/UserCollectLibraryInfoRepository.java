package com.atguigu.springboot.repository;

import java.util.List;

import com.atguigu.springboot.entity.UserCollectLibraryInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//继承JpaRepository来完成对数据库的操作
public interface UserCollectLibraryInfoRepository extends JpaRepository<UserCollectLibraryInfo,Integer> {
	
	@Query(value = "select * from user_collect_library_info where open_id = :openId and library_id = :libraryId and collect_type = :collectType order by id desc limit :limitCount ", nativeQuery = true)
	List<UserCollectLibraryInfo> findByIdAndLibraryType(@Param("openId") String openId,@Param("libraryId") Integer libraryId,@Param("collectType") String collectType,@Param("limitCount") Integer limitCount);
	
	@Query(value = "select count(*) from user_collect_library_info where open_id = :openId and library_id = :libraryId and collect_type = :collectType ", nativeQuery = true)
	long queryByIdAndLibraryType(@Param("openId") String openId,@Param("libraryId") Integer libraryId,@Param("collectType") String collectType);
	 
}
