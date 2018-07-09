package com.atguigu.springboot.entity;


import javax.persistence.*;

//使用JPA注解配置映射关系
@Entity //告诉JPA这是一个实体类（和数据表映射的类）
@Table(name = "train_item_opus") //@Table来指定和哪个数据表对应;
public class TrainItemOpus {

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
    private String opustitle;
    
    @Column //省略默认列名就是属性名
    private String opusurl;
    
    @Column //省略默认列名就是属性名
    private String opusauthor;
    
    @Column //省略默认列名就是属性名
    private String opusdate;
    
    @Column //省略默认列名就是属性名
    private String opusdepict;
    
    @Column //省略默认列名就是属性名
    private String opustype;
    
    @Column //省略默认列名就是属性名
    private String queryinfo;

    @Column //省略默认列名就是属性名
    private String censorUrl;
    
    @Column //省略默认列名就是属性名
    private String censorInfo;
    
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

	public String getOpustitle() {
		return opustitle;
	}

	public void setOpustitle(String opustitle) {
		this.opustitle = opustitle;
	}

	public String getOpusurl() {
		return opusurl;
	}

	public void setOpusurl(String opusurl) {
		this.opusurl = opusurl;
	}

	public String getOpusauthor() {
		return opusauthor;
	}

	public void setOpusauthor(String opusauthor) {
		this.opusauthor = opusauthor;
	}

	public String getOpusdate() {
		return opusdate;
	}

	public void setOpusdate(String opusdate) {
		this.opusdate = opusdate;
	}

	public String getOpusdepict() {
		return opusdepict;
	}

	public void setOpusdepict(String opusdepict) {
		this.opusdepict = opusdepict;
	}

	public String getOpustype() {
		return opustype;
	}

	public void setOpustype(String opustype) {
		this.opustype = opustype;
	}

	public String getQueryinfo() {
		return queryinfo;
	}

	public void setQueryinfo(String queryinfo) {
		this.queryinfo = queryinfo;
	}

	public String getCensorUrl() {
		return censorUrl;
	}

	public void setCensorUrl(String censorUrl) {
		this.censorUrl = censorUrl;
	}

	public String getCensorInfo() {
		return censorInfo;
	}

	public void setCensorInfo(String censorInfo) {
		this.censorInfo = censorInfo;
	}

	@Override
	public String toString() {
		return "TrainItemOpus [Id=" + Id + ", reqOpenId=" + reqOpenId
				+ ", operDayId=" + operDayId + ", operDay=" + operDay
				+ ", reqItemTimeId=" + reqItemTimeId + ", timeStamp="
				+ timeStamp + ", opustitle=" + opustitle + ", opusurl="
				+ opusurl + ", opusauthor=" + opusauthor + ", opusdate="
				+ opusdate + ", opusdepict=" + opusdepict + ", opustype="
				+ opustype + ", queryinfo=" + queryinfo + ", censorUrl="
				+ censorUrl + ", censorInfo=" + censorInfo + "]";
	}
     
}
