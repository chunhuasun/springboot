package com.atguigu.springboot.entity;


import javax.persistence.*;

//使用JPA注解配置映射关系
@Entity //告诉JPA这是一个实体类（和数据表映射的类）
@Table(name = "train_item_publish") //@Table来指定和哪个数据表对应;
public class TrainItemPublish {

    @Id //这是一个主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private Integer Id;
      
    @Column //省略默认列名就是属性名
    private String reqOpenId;
    
    @Column //省略默认列名就是属性名
    private String operDayId;
    
    @Column //省略默认列名就是属性名
    private String operDay;
    
    @Column //省略默认列名就是属性名
    private long reqItemTimeId;

    @Column //省略默认列名就是属性名
    private String publishInfo;
    
    @Column //省略默认列名就是属性名
    private String addOperDay;
    
    @Column //省略默认列名就是属性名
    private String timeStamp;
    
    @Column //省略默认列名就是属性名
    private String addTaskInfo;
    
    @Column //省略默认列名就是属性名
    private String addOpenId;
    
    @Column //省略默认列名就是属性名
    private String pubNickName;

    @Column //省略默认列名就是属性名
    private String pubAvatarUrl;
    
    @Column //省略默认列名就是属性名
    private String pubFlag;
    
    @Column //省略默认列名就是属性名
    private String taskNickName;
    
    @Column //省略默认列名就是属性名
    private String showallow;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getReqOpenId() {
		return reqOpenId;
	}

	public void setReqOpenId(String reqOpenId) {
		this.reqOpenId = reqOpenId;
	}

	public String getOperDayId() {
		return operDayId;
	}

	public void setOperDayId(String operDayId) {
		this.operDayId = operDayId;
	}

	public String getOperDay() {
		return operDay;
	}

	public void setOperDay(String operDay) {
		this.operDay = operDay;
	}

	public long getReqItemTimeId() {
		return reqItemTimeId;
	}

	public void setReqItemTimeId(long reqItemTimeId) {
		this.reqItemTimeId = reqItemTimeId;
	}

	public String getPublishInfo() {
		return publishInfo;
	}

	public void setPublishInfo(String publishInfo) {
		this.publishInfo = publishInfo;
	}

	public String getAddOperDay() {
		return addOperDay;
	}

	public void setAddOperDay(String addOperDay) {
		this.addOperDay = addOperDay;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getAddTaskInfo() {
		return addTaskInfo;
	}

	public void setAddTaskInfo(String addTaskInfo) {
		this.addTaskInfo = addTaskInfo;
	}

	public String getAddOpenId() {
		return addOpenId;
	}

	public void setAddOpenId(String addOpenId) {
		this.addOpenId = addOpenId;
	}

	public String getPubNickName() {
		return pubNickName;
	}

	public void setPubNickName(String pubNickName) {
		this.pubNickName = pubNickName;
	}

	public String getPubAvatarUrl() {
		return pubAvatarUrl;
	}

	public void setPubAvatarUrl(String pubAvatarUrl) {
		this.pubAvatarUrl = pubAvatarUrl;
	}

	public String getPubFlag() {
		return pubFlag;
	}

	public void setPubFlag(String pubFlag) {
		this.pubFlag = pubFlag;
	}

	public String getTaskNickName() {
		return taskNickName;
	}

	public void setTaskNickName(String taskNickName) {
		this.taskNickName = taskNickName;
	}

	public String getShowallow() {
		return showallow;
	}

	public void setShowallow(String showallow) {
		this.showallow = showallow;
	}

	@Override
	public String toString() {
		return "TrainItemPublish [Id=" + Id + ", reqOpenId=" + reqOpenId
				+ ", operDayId=" + operDayId + ", operDay=" + operDay
				+ ", reqItemTimeId=" + reqItemTimeId + ", publishInfo="
				+ publishInfo + ", addOperDay=" + addOperDay + ", timeStamp="
				+ timeStamp + ", addTaskInfo=" + addTaskInfo + ", addOpenId="
				+ addOpenId + ", pubNickName=" + pubNickName
				+ ", pubAvatarUrl=" + pubAvatarUrl + ", pubFlag=" + pubFlag
				+ ", taskNickName=" + taskNickName + ", showallow=" + showallow
				+ "]";
	}
    	 	  
}
