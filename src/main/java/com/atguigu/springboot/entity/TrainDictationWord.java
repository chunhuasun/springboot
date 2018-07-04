package com.atguigu.springboot.entity;


import javax.persistence.*;

//使用JPA注解配置映射关系
@Entity //告诉JPA这是一个实体类（和数据表映射的类）
@Table(name = "train_dictation_word") //@Table来指定和哪个数据表对应;
public class TrainDictationWord {

    @Id //这是一个主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private Integer Id;
      
    @Column //省略默认列名就是属性名
    private String reqOpenId;
    
    @Column //省略默认列名就是属性名
    private long wordId;
    
    @Column //省略默认列名就是属性名
    private String wordInfo;
    
    @Column //省略默认列名就是属性名
    private String nearDayId;
    
    @Column //省略默认列名就是属性名
    private String wordType;
    
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

	public long getWordId() {
		return wordId;
	}

	public void setWordId(long wordId) {
		this.wordId = wordId;
	}

	public String getWordInfo() {
		return wordInfo;
	}

	public void setWordInfo(String wordInfo) {
		this.wordInfo = wordInfo;
	}

	public String getNearDayId() {
		return nearDayId;
	}

	public void setNearDayId(String nearDayId) {
		this.nearDayId = nearDayId;
	}

	public String getWordType() {
		return wordType;
	}

	public void setWordType(String wordType) {
		this.wordType = wordType;
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
		return "TrainDictationWord [Id=" + Id + ", reqOpenId=" + reqOpenId
				+ ", wordId=" + wordId + ", wordInfo=" + wordInfo
				+ ", nearDayId=" + nearDayId + ", wordType=" + wordType
				+ ", done=" + done + ", checkFlag=" + checkFlag + "]";
	}
 
}
