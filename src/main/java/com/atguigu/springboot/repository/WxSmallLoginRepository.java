package com.atguigu.springboot.repository;
 

import java.util.List;
 
import com.atguigu.springboot.entity.WxSmallLogin;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository; 

//继承JpaRepository来完成对数据库的操作
public interface WxSmallLoginRepository extends JpaRepository<WxSmallLogin,Integer> {
	
	//List<WxSmallUser> findByCertTypeAndCheckGroupNo(String CertType,int CheckGroupNo,Pageable pageable);
	//List<WxSmallUser> findByCertTypeAndCheckGroupNo(String CertType,int CheckGroupNo);
	 
}
