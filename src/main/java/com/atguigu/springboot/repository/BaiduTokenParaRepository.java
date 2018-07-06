package com.atguigu.springboot.repository;

import java.util.List;

import com.atguigu.springboot.entity.BaiduTokenPara; 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//继承JpaRepository来完成对数据库的操作
public interface BaiduTokenParaRepository extends JpaRepository<BaiduTokenPara,Integer> {
	
	@Query(value = "select * from baidu_token_para where token_para = :tokenPara and oper_day_id >= :operDayId ", nativeQuery = true)
	List<BaiduTokenPara> findByOperDayId(@Param("tokenPara")String tokenPara,@Param("operDayId")String operDayId);
	 
}
