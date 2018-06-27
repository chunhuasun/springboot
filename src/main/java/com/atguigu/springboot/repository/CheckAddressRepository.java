package com.atguigu.springboot.repository;

import java.util.List;

import com.atguigu.springboot.entity.CheckAddress;

import org.springframework.data.jpa.repository.JpaRepository;

//继承JpaRepository来完成对数据库的操作
public interface CheckAddressRepository extends JpaRepository<CheckAddress,Integer> {
	 
}
