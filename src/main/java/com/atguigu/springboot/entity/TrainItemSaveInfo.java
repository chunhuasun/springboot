package com.atguigu.springboot.entity;


import javax.persistence.*;

//使用JPA注解配置映射关系
@Entity //告诉JPA这是一个实体类（和数据表映射的类）
@Table(name = "train_item_save_info") //@Table来指定和哪个数据表对应;
public class TrainItemSaveInfo {

    @Id //这是一个主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private Integer Id;
      
    @Column //省略默认列名就是属性名
    private String reqOpenId;
    
    @Column //省略默认列名就是属性名
    private String operDayId;
    
    @Column //省略默认列名就是属性名
    private long trainPlanId;
    
    @Column //省略默认列名就是属性名
    private long trainItemId;
    
    @Column //省略默认列名就是属性名
    private long timePara;
    
    @Column //省略默认列名就是属性名
    private String timeParaDes;
    		   
    @Column //省略默认列名就是属性名
    private long writePara;
    
    @Column //省略默认列名就是属性名
    private String writeParaDes;
    
    @Column //省略默认列名就是属性名
    private String contentInfo;
    
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

	public long getTrainPlanId() {
		return trainPlanId;
	}

	public void setTrainPlanId(long trainPlanId) {
		this.trainPlanId = trainPlanId;
	}

	public long getTrainItemId() {
		return trainItemId;
	}

	public void setTrainItemId(long trainItemId) {
		this.trainItemId = trainItemId;
	}

	public long getTimePara() {
		return timePara;
	}

	public void setTimePara(long timePara) {
		this.timePara = timePara;
	}

	public String getTimeParaDes() {
		return timeParaDes;
	}

	public void setTimeParaDes(String timeParaDes) {
		this.timeParaDes = timeParaDes;
	}

	public long getWritePara() {
		return writePara;
	}

	public void setWritePara(long writePara) {
		this.writePara = writePara;
	}

	public String getWriteParaDes() {
		return writeParaDes;
	}

	public void setWriteParaDes(String writeParaDes) {
		this.writeParaDes = writeParaDes;
	}

	public String getContentInfo() {
		return contentInfo;
	}

	public void setContentInfo(String contentInfo) {
		this.contentInfo = contentInfo;
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
		return "TrainItemSaveInfo [Id=" + Id + ", reqOpenId=" + reqOpenId
				+ ", operDayId=" + operDayId + ", trainPlanId=" + trainPlanId
				+ ", trainItemId=" + trainItemId + ", timePara=" + timePara
				+ ", timeParaDes=" + timeParaDes + ", writePara=" + writePara
				+ ", writeParaDes=" + writeParaDes + ", contentInfo="
				+ contentInfo + ", cosimageurls=" + cosimageurls
				+ ", recordType=" + recordType + ", recordType1=" + recordType1
				+ ", recordType2=" + recordType2 + ", recordType3="
				+ recordType3 + "]";
	}
     
}
