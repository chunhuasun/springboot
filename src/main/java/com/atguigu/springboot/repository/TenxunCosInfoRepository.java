package com.atguigu.springboot.repository;
 
import com.atguigu.springboot.entity.TenxunCosInfo;

import org.springframework.data.jpa.repository.JpaRepository; 

//继承JpaRepository来完成对数据库的操作
public interface TenxunCosInfoRepository extends JpaRepository<TenxunCosInfo,Integer> {
	
}
