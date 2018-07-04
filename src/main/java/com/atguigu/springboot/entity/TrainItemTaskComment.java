package com.atguigu.springboot.entity;


import javax.persistence.*;

//使用JPA注解配置映射关系
@Entity //告诉JPA这是一个实体类（和数据表映射的类）
@Table(name = "train_item_task_comment") //@Table来指定和哪个数据表对应;
public class TrainItemTaskComment {

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
    private String timeStamp;
    
    @Column //省略默认列名就是属性名
    private String tasktimeStamp;
    
    @Column //省略默认列名就是属性名
    private String publishInfo;
    
    @Column //省略默认列名就是属性名
    private String showallow;
    
    @Column //省略默认列名就是属性名
    private String pubNickName;
    
    @Column //省略默认列名就是属性名
    private String pubAvatarUrl;
     
    @Column //省略默认列名就是属性名
    private String pubFlag;

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

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getTasktimeStamp() {
		return tasktimeStamp;
	}

	public void setTasktimeStamp(String tasktimeStamp) {
		this.tasktimeStamp = tasktimeStamp;
	}

	public String getPublishInfo() {
		return publishInfo;
	}

	public void setPublishInfo(String publishInfo) {
		this.publishInfo = publishInfo;
	}

	public String getShowallow() {
		return showallow;
	}

	public void setShowallow(String showallow) {
		this.showallow = showallow;
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

	@Override
	public String toString() {
		return "TrainItemTaskComment [Id=" + Id + ", reqOpenId=" + reqOpenId
				+ ", operDayId=" + operDayId + ", operDay=" + operDay
				+ ", reqItemTimeId=" + reqItemTimeId + ", timeStamp="
				+ timeStamp + ", tasktimeStamp=" + tasktimeStamp
				+ ", publishInfo=" + publishInfo + ", showallow=" + showallow
				+ ", pubNickName=" + pubNickName + ", pubAvatarUrl="
				+ pubAvatarUrl + ", pubFlag=" + pubFlag + "]";
	}
     
}
