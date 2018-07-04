package com.atguigu.springboot.entity;


import javax.persistence.*;

//使用JPA注解配置映射关系
@Entity //告诉JPA这是一个实体类（和数据表映射的类）
@Table(name = "train_item_time_angle") //@Table来指定和哪个数据表对应;
public class TrainItemTimeAngle {

    @Id //这是一个主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private Integer Id;
      
    @Column //省略默认列名就是属性名
    private String reqOpenId;
    
    @Column //省略默认列名就是属性名
    private long angleId;
    
    @Column //省略默认列名就是属性名
    private String angleInfo;
    
    @Column //省略默认列名就是属性名
    private String angleType;
    
    @Column //省略默认列名就是属性名
    private String angleValue;
    
    @Column //省略默认列名就是属性名
    private String angleTimeValue;

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

	public long getAngleId() {
		return angleId;
	}

	public void setAngleId(long angleId) {
		this.angleId = angleId;
	}

	public String getAngleInfo() {
		return angleInfo;
	}

	public void setAngleInfo(String angleInfo) {
		this.angleInfo = angleInfo;
	}

	public String getAngleType() {
		return angleType;
	}

	public void setAngleType(String angleType) {
		this.angleType = angleType;
	}

	public String getAngleValue() {
		return angleValue;
	}

	public void setAngleValue(String angleValue) {
		this.angleValue = angleValue;
	}

	public String getAngleTimeValue() {
		return angleTimeValue;
	}

	public void setAngleTimeValue(String angleTimeValue) {
		this.angleTimeValue = angleTimeValue;
	}

	@Override
	public String toString() {
		return "TrainItemTimeAngle [Id=" + Id + ", reqOpenId=" + reqOpenId
				+ ", angleId=" + angleId + ", angleInfo=" + angleInfo
				+ ", angleType=" + angleType + ", angleValue=" + angleValue
				+ ", angleTimeValue=" + angleTimeValue + "]";
	}
      	  
}
