package com.atguigu.springboot.entity;


import javax.persistence.*;

//使用JPA注解配置映射关系
@Entity //告诉JPA这是一个实体类（和数据表映射的类）
@Table(name = "train_item_task") //@Table来指定和哪个数据表对应;
public class TrainItemTask {

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
    private String itemTitle;
    
    @Column //省略默认列名就是属性名
    private String itemtitletype;
    
    @Column //省略默认列名就是属性名
    private String itemInfo;
    
    @Column //省略默认列名就是属性名
    private String itemaddinfo;
    
    @Column //省略默认列名就是属性名
    private String cosimageurls;
    
    @Column //省略默认列名就是属性名
    private String finishFlag;
    
    @Column //省略默认列名就是属性名
    private String finishDayId;
    
    @Column //省略默认列名就是属性名
    private String finishDay;
    
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

	public String getItemTitle() {
		return itemTitle;
	}

	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}

	public String getItemtitletype() {
		return itemtitletype;
	}

	public void setItemtitletype(String itemtitletype) {
		this.itemtitletype = itemtitletype;
	}

	public String getItemInfo() {
		return itemInfo;
	}

	public void setItemInfo(String itemInfo) {
		this.itemInfo = itemInfo;
	}

	public String getItemaddinfo() {
		return itemaddinfo;
	}

	public void setItemaddinfo(String itemaddinfo) {
		this.itemaddinfo = itemaddinfo;
	}

	public String getCosimageurls() {
		return cosimageurls;
	}

	public void setCosimageurls(String cosimageurls) {
		this.cosimageurls = cosimageurls;
	}

	public String getFinishFlag() {
		return finishFlag;
	}

	public void setFinishFlag(String finishFlag) {
		this.finishFlag = finishFlag;
	}

	public String getFinishDayId() {
		return finishDayId;
	}

	public void setFinishDayId(String finishDayId) {
		this.finishDayId = finishDayId;
	}

	public String getFinishDay() {
		return finishDay;
	}

	public void setFinishDay(String finishDay) {
		this.finishDay = finishDay;
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
		return "TrainItemTask [Id=" + Id + ", reqOpenId=" + reqOpenId
				+ ", operDayId=" + operDayId + ", operDay=" + operDay
				+ ", reqItemTimeId=" + reqItemTimeId + ", timeStamp="
				+ timeStamp + ", itemTitle=" + itemTitle + ", itemtitletype="
				+ itemtitletype + ", itemInfo=" + itemInfo + ", itemaddinfo="
				+ itemaddinfo + ", cosimageurls=" + cosimageurls
				+ ", finishFlag=" + finishFlag + ", finishDayId=" + finishDayId
				+ ", finishDay=" + finishDay + ", recordType=" + recordType
				+ ", recordItem1=" + recordItem1 + ", recordItem2="
				+ recordItem2 + ", recordItem3=" + recordItem3 + "]";
	}
    
}
