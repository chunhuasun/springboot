package com.atguigu.springboot.repository;
 

import java.util.List;
 
import com.atguigu.springboot.entity.WxSmallUser;
import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//继承JpaRepository来完成对数据库的操作
public interface WxSmallUserRepository extends JpaRepository<WxSmallUser,Integer> {
	
	//List<WxSmallUser> findByCertTypeAndCheckGroupNo(String CertType,int CheckGroupNo,Pageable pageable);
	//List<WxSmallUser> findByCertTypeAndCheckGroupNo(String CertType,int CheckGroupNo);
	List<WxSmallUser> findByOpenId(String OpenId);
	
	@Query(value = "select * from wx_small_user where time_stamp < :timeStamp order by time_stamp desc limit :limitCount ", nativeQuery = true)
	List<WxSmallUser> queryByTimeStamp(@Param("timeStamp") String timeStamp,@Param("limitCount") Integer limitCount);
	 
}
