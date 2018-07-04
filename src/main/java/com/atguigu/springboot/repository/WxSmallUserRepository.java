package com.atguigu.springboot.repository;
 

import java.util.List;
 
import com.atguigu.springboot.entity.WxSmallUser;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository; 

//继承JpaRepository来完成对数据库的操作
public interface WxSmallUserRepository extends JpaRepository<WxSmallUser,Integer> {
	
	//List<WxSmallUser> findByCertTypeAndCheckGroupNo(String CertType,int CheckGroupNo,Pageable pageable);
	//List<WxSmallUser> findByCertTypeAndCheckGroupNo(String CertType,int CheckGroupNo);
	List<WxSmallUser> findByOpenId(String OpenId);
	 
}
