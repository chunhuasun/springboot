package com.atguigu.springboot.repository;

import java.util.List;
 
import com.atguigu.springboot.entity.TrainItemOpusHome;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//继承JpaRepository来完成对数据库的操作
public interface TrainItemOpusHomeRepository extends JpaRepository<TrainItemOpusHome,Integer> {
	
	@Query(value = "select * from train_item_opus_home where use_flag = :useFlag order by time_stamp desc ", nativeQuery = true)
	List<TrainItemOpusHome> queryByUseFlag(@Param("useFlag")String useFlag);
    
}
