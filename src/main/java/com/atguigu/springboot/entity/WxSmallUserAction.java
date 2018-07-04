package com.atguigu.springboot.entity;


import javax.persistence.*;

//使用JPA注解配置映射关系
@Entity //告诉JPA这是一个实体类（和数据表映射的类）
@Table(name = "wx_small_user_action") //@Table来指定和哪个数据表对应;
public class WxSmallUserAction {

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
    private String operDate;
    
    @Column //省略默认列名就是属性名
    private String timeStamp;
    
    @Column //省略默认列名就是属性名
    private String getType;
    
    @Column //省略默认列名就是属性名
    private String funOpType;
    
    @Column //省略默认列名就是属性名
    private long smalluserCount;

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

	public String getOperDate() {
		return operDate;
	}

	public void setOperDate(String operDate) {
		this.operDate = operDate;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getGetType() {
		return getType;
	}

	public void setGetType(String getType) {
		this.getType = getType;
	}

	public String getFunOpType() {
		return funOpType;
	}

	public void setFunOpType(String funOpType) {
		this.funOpType = funOpType;
	}

	public long getSmalluserCount() {
		return smalluserCount;
	}

	public void setSmalluserCount(long smalluserCount) {
		this.smalluserCount = smalluserCount;
	}

	@Override
	public String toString() {
		return "WxSmallUserAction [Id=" + Id + ", reqOpenId=" + reqOpenId
				+ ", operDayId=" + operDayId + ", operDay=" + operDay
				+ ", operDate=" + operDate + ", timeStamp=" + timeStamp
				+ ", getType=" + getType + ", funOpType=" + funOpType
				+ ", smalluserCount=" + smalluserCount + "]";
	}
       
}
