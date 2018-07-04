package com.atguigu.springboot.entity;


import javax.persistence.*;

//使用JPA注解配置映射关系
@Entity //告诉JPA这是一个实体类（和数据表映射的类）
@Table(name = "train_dictation_info") //@Table来指定和哪个数据表对应;
public class TrainDictationInfo {

    @Id //这是一个主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private Integer Id;
      
    @Column //省略默认列名就是属性名
    private String reqOpenId;
    
    @Column //省略默认列名就是属性名
    private long dictaId;
    
    @Column //省略默认列名就是属性名
    private String wordInfo;
    
    @Column //省略默认列名就是属性名
    private long errorTimes;
    
    @Column //省略默认列名就是属性名
    private String nearDayId;
    
    @Column //省略默认列名就是属性名
    private String done;
    
    @Column //省略默认列名就是属性名
    private String checkFlag;

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

	public long getDictaId() {
		return dictaId;
	}

	public void setDictaId(long dictaId) {
		this.dictaId = dictaId;
	}

	public String getWordInfo() {
		return wordInfo;
	}

	public void setWordInfo(String wordInfo) {
		this.wordInfo = wordInfo;
	}

	public long getErrorTimes() {
		return errorTimes;
	}

	public void setErrorTimes(long errorTimes) {
		this.errorTimes = errorTimes;
	}

	public String getNearDayId() {
		return nearDayId;
	}

	public void setNearDayId(String nearDayId) {
		this.nearDayId = nearDayId;
	}

	public String getDone() {
		return done;
	}

	public void setDone(String done) {
		this.done = done;
	}
 

	public String getCheckFlag() {
		return checkFlag;
	}

	public void setCheckFlag(String checkFlag) {
		this.checkFlag = checkFlag;
	}

	@Override
	public String toString() {
		return "TrainDictationInfo [Id=" + Id + ", reqOpenId=" + reqOpenId
				+ ", dictaId=" + dictaId + ", wordInfo=" + wordInfo
				+ ", errorTimes=" + errorTimes + ", nearDayId=" + nearDayId
				+ ", done=" + done + ", checkFlag=" + checkFlag + "]";
	}
      	  
}
