package com.atguigu.springboot.repository;
 

import java.util.List;

import com.atguigu.springboot.entity.InfoCheckCard; 
import com.atguigu.springboot.entity.InfoCheckCardResult;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository; 

//继承JpaRepository来完成对数据库的操作
public interface InfoCheckCardResultRepository extends JpaRepository<InfoCheckCardResult,Integer> {
	
	InfoCheckCardResult findById(Integer id);
	InfoCheckCardResult findByCheckNo(Integer checkNo);
	 
}
