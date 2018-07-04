package com.atguigu.springboot.entity;


import javax.persistence.*;

//使用JPA注解配置映射关系
@Entity //告诉JPA这是一个实体类（和数据表映射的类）
@Table(name = "train_item_time_info") //@Table来指定和哪个数据表对应;
public class TrainItemTimeInfo {

    @Id //这是一个主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private Integer Id;
      
    @Column //省略默认列名就是属性名
    private String reqOpenId;
    
    @Column //省略默认列名就是属性名
    private String operDayId;
    
    @Column //省略默认列名就是属性名
    private long reqItemTimeId;
    
    @Column //省略默认列名就是属性名
    private String itemTitle;
    
    @Column //省略默认列名就是属性名
    private String startTime;
    
    @Column //省略默认列名就是属性名
    private String endTime;
    
    @Column //省略默认列名就是属性名
    private String costTime;
     
    @Column //省略默认列名就是属性名
    private String itemInfo;
    
    @Column //省略默认列名就是属性名
    private String degrees;

    @Column //省略默认列名就是属性名
    private String degreeId;
    
    @Column //省略默认列名就是属性名
    private String opTypes;
    
    @Column //省略默认列名就是属性名
    private String opTypeId;
    
    @Column //省略默认列名就是属性名
    private String finishFlag;
    
    @Column //省略默认列名就是属性名
    private String cosimageurls;
    
    @Column //省略默认列名就是属性名
    private String scoreinfo;
    
    @Column //省略默认列名就是属性名
    private String scoretimeinfo;
    
    @Column //省略默认列名就是属性名
    private long timeAngleId;
    
    @Column //省略默认列名就是属性名
    private String recordType;
    
    @Column //省略默认列名就是属性名
    private String recordType1;
    
    @Column //省略默认列名就是属性名
    private String recordType2;
    
    @Column //省略默认列名就是属性名
    private String recordType3;

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

	public long getReqItemTimeId() {
		return reqItemTimeId;
	}

	public void setReqItemTimeId(long reqItemTimeId) {
		this.reqItemTimeId = reqItemTimeId;
	}

	public String getItemTitle() {
		return itemTitle;
	}

	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getCostTime() {
		return costTime;
	}

	public void setCostTime(String costTime) {
		this.costTime = costTime;
	}

	public String getItemInfo() {
		return itemInfo;
	}

	public void setItemInfo(String itemInfo) {
		this.itemInfo = itemInfo;
	}

	public String getDegrees() {
		return degrees;
	}

	public void setDegrees(String degrees) {
		this.degrees = degrees;
	}

	public String getDegreeId() {
		return degreeId;
	}

	public void setDegreeId(String degreeId) {
		this.degreeId = degreeId;
	}

	public String getOpTypes() {
		return opTypes;
	}

	public void setOpTypes(String opTypes) {
		this.opTypes = opTypes;
	}

	public String getOpTypeId() {
		return opTypeId;
	}

	public void setOpTypeId(String opTypeId) {
		this.opTypeId = opTypeId;
	}

	public String getFinishFlag() {
		return finishFlag;
	}

	public void setFinishFlag(String finishFlag) {
		this.finishFlag = finishFlag;
	}

	public String getCosimageurls() {
		return cosimageurls;
	}

	public void setCosimageurls(String cosimageurls) {
		this.cosimageurls = cosimageurls;
	}

	public String getScoreinfo() {
		return scoreinfo;
	}

	public void setScoreinfo(String scoreinfo) {
		this.scoreinfo = scoreinfo;
	}

	public String getScoretimeinfo() {
		return scoretimeinfo;
	}

	public void setScoretimeinfo(String scoretimeinfo) {
		this.scoretimeinfo = scoretimeinfo;
	}

	public long getTimeAngleId() {
		return timeAngleId;
	}

	public void setTimeAngleId(long timeAngleId) {
		this.timeAngleId = timeAngleId;
	}

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public String getRecordType1() {
		return recordType1;
	}

	public void setRecordType1(String recordType1) {
		this.recordType1 = recordType1;
	}

	public String getRecordType2() {
		return recordType2;
	}

	public void setRecordType2(String recordType2) {
		this.recordType2 = recordType2;
	}

	public String getRecordType3() {
		return recordType3;
	}

	public void setRecordType3(String recordType3) {
		this.recordType3 = recordType3;
	}

	@Override
	public String toString() {
		return "TrainItemTimeInfo [Id=" + Id + ", reqOpenId=" + reqOpenId
				+ ", operDayId=" + operDayId + ", reqItemTimeId="
				+ reqItemTimeId + ", itemTitle=" + itemTitle + ", startTime="
				+ startTime + ", endTime=" + endTime + ", costTime=" + costTime
				+ ", itemInfo=" + itemInfo + ", degrees=" + degrees
				+ ", degreeId=" + degreeId + ", opTypes=" + opTypes
				+ ", opTypeId=" + opTypeId + ", finishFlag=" + finishFlag
				+ ", cosimageurls=" + cosimageurls + ", scoreinfo=" + scoreinfo
				+ ", scoretimeinfo=" + scoretimeinfo + ", timeAngleId="
				+ timeAngleId + ", recordType=" + recordType + ", recordType1="
				+ recordType1 + ", recordType2=" + recordType2
				+ ", recordType3=" + recordType3 + "]";
	} 
    		  
}
