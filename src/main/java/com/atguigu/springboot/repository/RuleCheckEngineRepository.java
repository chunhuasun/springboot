package com.atguigu.springboot.repository;

import java.util.List;

import com.atguigu.springboot.entity.RuleCheckEngine;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

//继承JpaRepository来完成对数据库的操作
public interface RuleCheckEngineRepository extends JpaRepository<RuleCheckEngine,Integer> {
	
	List<RuleCheckEngine> findByCheckType(String checkType);
	List<RuleCheckEngine> findByCardTypeAndCardPlaceAndEngineTypeAndCheckGroupNo(String CardType,String CardPlace,String EngineType,int CheckGroupNo,Sort sort);
}
