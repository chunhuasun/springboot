package com.atguigu.springboot.repository;

import java.util.List;
 


import com.atguigu.springboot.entity.WxSmallServLog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//继承JpaRepository来完成对数据库的操作
public interface WxSmallServLogRepository extends JpaRepository<WxSmallServLog,Integer> {
	
	@Query(value = "select * from WxSmallServLog where openId = :openId and userServType = :userServType and operDayId = :operDayId ", nativeQuery = true)
	List<WxSmallServLog> findByOpenIdAndUserServTypeOperDayId(@Param("openId")String openId,@Param("userServType")String userServType,@Param("operDayId")String operDayId);
	//List<WxSmallServLog> findByUserServTypeAndOperDayId(String userServType,String operDayId);
	 
}
