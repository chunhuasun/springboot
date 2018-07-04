package com.atguigu.springboot.entity;


import javax.persistence.*;

//使用JPA注解配置映射关系
@Entity //告诉JPA这是一个实体类（和数据表映射的类）
@Table(name = "train_item_musictimer") //@Table来指定和哪个数据表对应;
public class TrainItemMusictimer {

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
    private String itemInfo;
    
    @Column //省略默认列名就是属性名
    private String itemList;

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

	public String getItemInfo() {
		return itemInfo;
	}

	public void setItemInfo(String itemInfo) {
		this.itemInfo = itemInfo;
	}

	public String getItemList() {
		return itemList;
	}

	public void setItemList(String itemList) {
		this.itemList = itemList;
	}

	@Override
	public String toString() {
		return "TrainItemMusictimer [Id=" + Id + ", reqOpenId=" + reqOpenId
				+ ", operDayId=" + operDayId + ", operDay=" + operDay
				+ ", reqItemTimeId=" + reqItemTimeId + ", itemInfo=" + itemInfo
				+ ", itemList=" + itemList + "]";
	}
     	  
}
