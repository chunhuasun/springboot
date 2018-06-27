package com.atguigu.springboot.repository;
 

import java.util.List;

import com.atguigu.springboot.entity.InfoCheckCard; 

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository; 

//继承JpaRepository来完成对数据库的操作
public interface InfoCheckCardRepository extends JpaRepository<InfoCheckCard,Integer> {
	
	List<InfoCheckCard> findByCertTypeAndCheckGroupNo(String CertType,int CheckGroupNo,Pageable pageable);
	List<InfoCheckCard> findByCertTypeAndCheckGroupNo(String CertType,int CheckGroupNo);
	 
}
