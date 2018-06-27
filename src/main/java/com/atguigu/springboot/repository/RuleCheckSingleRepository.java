package com.atguigu.springboot.repository;

import java.util.List;

import com.atguigu.springboot.entity.RuleCheckEngine;
import com.atguigu.springboot.entity.RuleCheckSingle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//继承JpaRepository来完成对数据库的操作
public interface RuleCheckSingleRepository extends JpaRepository<RuleCheckSingle,Integer> {
	
	List<RuleCheckSingle> findByCheckType(String checkType);
	List<RuleCheckSingle> findByCardTypeAndCardPlace(String CardType,String CardPlace);
	 
	@Query("select distinct c.checkType from RuleCheckSingle c where c.cardType=:cardType and c.cardPlace=:cardPlace and c.checkGroupNo = :checkGroupNo") 
	List<String> findByDisCheckType(@Param("cardType")String CardType,@Param("cardPlace")String CardPlace,@Param("checkGroupNo")int CheckGroupNo);

}
