package com.atguigu.springboot.repository;

import java.util.List;
import com.atguigu.springboot.entity.WxSmallServLock;
import org.springframework.data.jpa.repository.JpaRepository;

//继承JpaRepository来完成对数据库的操作
public interface WxSmallServLockRepository extends JpaRepository<WxSmallServLock,Integer> {
	
	List<WxSmallServLock> findByGetTypeAndFunOpTypeAndLockFlag(String getType,String funOpType,String lockFlag); 
}
