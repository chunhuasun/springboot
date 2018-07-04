package com.atguigu.springboot.repository;

import com.atguigu.springboot.entity.TrainItemMusictimer;
import org.springframework.data.jpa.repository.JpaRepository;

//继承JpaRepository来完成对数据库的操作
public interface TrainItemMusictimerRepository extends JpaRepository<TrainItemMusictimer,Integer> {
}
