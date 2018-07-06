package com.atguigu.springboot.repository;

import java.util.List;

import com.atguigu.springboot.entity.WxSmallServInfo; 

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//继承JpaRepository来完成对数据库的操作
public interface WxSmallServInfoRepository extends JpaRepository<WxSmallServInfo,Integer> {
	
	//List<WxSmallServInfo> findByGetTypeAndFunOpTypeAndLockFlag(String getType,String funOpType,String lockFlag); 
	
	@Query(value = "select * from wx_small_serv_info where user_group_type = :userGroupType and user_serv_type = :userServType and use_eff_flag = :useEffFlag order by serv_order_id asc limit 40 ", nativeQuery = true)
	List<WxSmallServInfo> findByUserGroupTypeAndUserServTypeAndUseEffFlag(@Param("userGroupType")String userGroupType,@Param("userServType")String userServType,@Param("useEffFlag")String useEffFlag);
	
	//List<WxSmallServInfo> findByUserGroupTypeAndUserServTypeAndUseEffFlag(String userGroupType,String userServType,String useEffFlag);
	 
}
