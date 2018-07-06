package com.atguigu.springboot.func;

import java.util.Date;

public class ShowTimeFunc {
	
	private long startTime = 0;
	private long startTimenano = 0;
	private long endTime = 0;
	private long endTimenano = 0;
	
	/*打印开始时间*/
	public void setStartTime(){
		System.out.println(new Date(System.currentTimeMillis()));
		this.startTime=System.currentTimeMillis(); 
    	System.out.println(new Date(System.nanoTime()));
    	this.startTimenano=System.nanoTime();
	}
	
	/*打印结束时间*/
	public void setEndTime(){
		System.out.println(new Date(System.currentTimeMillis()));
		this.endTime=System.currentTimeMillis(); //获取结束时间
    	System.out.println(new Date(System.nanoTime()));
    	this.endTimenano=System.nanoTime();
    	this.showRunTime();
    	this.setStartTime();
	}
	
	/*打印总耗时*/
	public void showRunTime(){
		System.out.println("程序运行时间： "+(this.endTime-this.startTime)+"ms");
    	System.out.println("程序运行时间： "+(this.endTimenano-this.startTimenano)+"ns");
	}

}
