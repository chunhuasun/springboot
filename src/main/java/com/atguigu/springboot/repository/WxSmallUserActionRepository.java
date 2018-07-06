package com.atguigu.springboot.repository;

import java.util.List;
 
import com.atguigu.springboot.entity.WxSmallUserAction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//继承JpaRepository来完成对数据库的操作
public interface WxSmallUserActionRepository extends JpaRepository<WxSmallUserAction,Integer> {
	
	@Query(value = "select * from wx_small_user_action where req_open_id = :reqOpenId and time_stamp < :timeStamp order by time_stamp desc limit :limitCount ", nativeQuery = true)
	List<WxSmallUserAction> queryByTimeStamp(@Param("reqOpenId") String reqOpenId,@Param("timeStamp") String timeStamp,@Param("limitCount") Integer limitCount);
	
}
