package com.atguigu.springboot.repository;
 
import java.util.List;

import com.atguigu.springboot.entity.TenxunCosInfo;

import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//继承JpaRepository来完成对数据库的操作
public interface TenxunCosInfoRepository extends JpaRepository<TenxunCosInfo,Integer> {
    
	@Query(value = "select * from tenxun_cos_info where cos_url_info = :cosUrlInfo order by ingress_id desc limit 1 ", nativeQuery = true)
	List<TenxunCosInfo> findByCosUrlInfo(@Param("cosUrlInfo")String cosUrlInfo);
	
}
