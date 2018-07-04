package com.atguigu.springboot.entity;


import javax.persistence.*;

//使用JPA注解配置映射关系
@Entity //告诉JPA这是一个实体类（和数据表映射的类）
@Table(name = "train_plan_info") //@Table来指定和哪个数据表对应;
public class TrainPlanInfo {

    @Id //这是一个主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private Integer Id;
      
    @Column //省略默认列名就是属性名
    private String fromUser;
    
    @Column //省略默认列名就是属性名
    private long infoGroupId;
    
    @Column //省略默认列名就是属性名
    private String planName;
    
    @Column //省略默认列名就是属性名
    private String planInfo;
    
    @Column //省略默认列名就是属性名
    private String userGroupType;
    
    @Column //省略默认列名就是属性名
    private String operDayId;
    
    @Column //省略默认列名就是属性名
    private long trainPlanId;
    
    @Column //省略默认列名就是属性名
    private String trainActiveFlag;

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

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public String getPlanInfo() {
		return planInfo;
	}

	public void setPlanInfo(String planInfo) {
		this.planInfo = planInfo;
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

	public String getTrainActiveFlag() {
		return trainActiveFlag;
	}

	public void setTrainActiveFlag(String trainActiveFlag) {
		this.trainActiveFlag = trainActiveFlag;
	}

	@Override
	public String toString() {
		return "TrainPlanInfo [Id=" + Id + ", fromUser=" + fromUser
				+ ", infoGroupId=" + infoGroupId + ", planName=" + planName
				+ ", planInfo=" + planInfo + ", userGroupType=" + userGroupType
				+ ", operDayId=" + operDayId + ", trainPlanId=" + trainPlanId
				+ ", trainActiveFlag=" + trainActiveFlag + "]";
	}
      
}
