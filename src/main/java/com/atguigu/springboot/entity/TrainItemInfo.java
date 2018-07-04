package com.atguigu.springboot.entity;


import javax.persistence.*;

//使用JPA注解配置映射关系
@Entity //告诉JPA这是一个实体类（和数据表映射的类）
@Table(name = "train_item_info") //@Table来指定和哪个数据表对应;
public class TrainItemInfo {

    @Id //这是一个主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private Integer Id;
      
    @Column //省略默认列名就是属性名
    private String fromUser;
    
    @Column //省略默认列名就是属性名
    private long infoGroupId;
    
    @Column //省略默认列名就是属性名
    private String itemName;
    
    @Column //省略默认列名就是属性名
    private String itemInfo;
    
    @Column //省略默认列名就是属性名
    private String itemLevel;
    
    @Column //省略默认列名就是属性名
    private String itemTrainTime;
    
    @Column //省略默认列名就是属性名
    private String itemRestTime; 
    
    @Column //省略默认列名就是属性名
    private String userGroupType; 
    
    @Column //省略默认列名就是属性名
    private String operDayId; 
    
    @Column //省略默认列名就是属性名
    private long trainPlanId; 
    		   
    @Column //省略默认列名就是属性名
    private String itemImageUrl; 	
    
    @Column //省略默认列名就是属性名
    private String recordType; 

    @Column //省略默认列名就是属性名
    private String recordItem1;
    
    @Column //省略默认列名就是属性名
    private String recordItem2;

    @Column //省略默认列名就是属性名
    private String recordItem3;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getFromUser() {
		return fromUser;
	}

	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}

	public long getInfoGroupId() {
		return infoGroupId;
	}

	public void setInfoGroupId(long infoGroupId) {
		this.infoGroupId = infoGroupId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemInfo() {
		return itemInfo;
	}

	public void setItemInfo(String itemInfo) {
		this.itemInfo = itemInfo;
	}

	public String getItemLevel() {
		return itemLevel;
	}

	public void setItemLevel(String itemLevel) {
		this.itemLevel = itemLevel;
	}

	public String getItemTrainTime() {
		return itemTrainTime;
	}

	public void setItemTrainTime(String itemTrainTime) {
		this.itemTrainTime = itemTrainTime;
	}

	public String getItemRestTime() {
		return itemRestTime;
	}

	public void setItemRestTime(String itemRestTime) {
		this.itemRestTime = itemRestTime;
	}

	public String getUserGroupType() {
		return userGroupType;
	}

	public void setUserGroupType(String userGroupType) {
		this.userGroupType = userGroupType;
	}

	public String getOperDayId() {
		return operDayId;
	}

	public void setOperDayId(String operDayId) {
		this.operDayId = operDayId;
	}

	public long getTrainPlanId() {
		return trainPlanId;
	}

	public void setTrainPlanId(long trainPlanId) {
		this.trainPlanId = trainPlanId;
	}

	public String getItemImageUrl() {
		return itemImageUrl;
	}

	public void setItemImageUrl(String itemImageUrl) {
		this.itemImageUrl = itemImageUrl;
	}

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public String getRecordItem1() {
		return recordItem1;
	}

	public void setRecordItem1(String recordItem1) {
		this.recordItem1 = recordItem1;
	}

	public String getRecordItem2() {
		return recordItem2;
	}

	public void setRecordItem2(String recordItem2) {
		this.recordItem2 = recordItem2;
	}

	public String getRecordItem3() {
		return recordItem3;
	}

	public void setRecordItem3(String recordItem3) {
		this.recordItem3 = recordItem3;
	}

	@Override
	public String toString() {
		return "TrainItemInfo [Id=" + Id + ", fromUser=" + fromUser
				+ ", infoGroupId=" + infoGroupId + ", itemName=" + itemName
				+ ", itemInfo=" + itemInfo + ", itemLevel=" + itemLevel
				+ ", itemTrainTime=" + itemTrainTime + ", itemRestTime="
				+ itemRestTime + ", userGroupType=" + userGroupType
				+ ", operDayId=" + operDayId + ", trainPlanId=" + trainPlanId
				+ ", itemImageUrl=" + itemImageUrl + ", recordType="
				+ recordType + ", recordItem1=" + recordItem1
				+ ", recordItem2=" + recordItem2 + ", recordItem3="
				+ recordItem3 + "]";
	} 
    
}
