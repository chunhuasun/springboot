package com.atguigu.springboot.entity;


import javax.persistence.*;

//使用JPA注解配置映射关系
@Entity //告诉JPA这是一个实体类（和数据表映射的类）
@Table(name = "train_item_knowledge") //@Table来指定和哪个数据表对应;
public class TrainItemKnowledge {

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
		return "TrainItemKnowledge [Id=" + Id + ", reqOpenId=" + reqOpenId
				+ ", operDayId=" + operDayId + ", operDay=" + operDay
				+ ", reqItemTimeId=" + reqItemTimeId + ", itemTitle="
				+ itemTitle + ", itemtitletype=" + itemtitletype
				+ ", itemInfo=" + itemInfo + ", itemaddinfo=" + itemaddinfo
				+ ", cosimageurls=" + cosimageurls + ", recordType="
				+ recordType + ", recordType1=" + recordType1
				+ ", recordType2=" + recordType2 + ", recordType3="
				+ recordType3 + "]";
	}
      
}
