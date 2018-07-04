package com.atguigu.springboot.entity;


import javax.persistence.*;

//使用JPA注解配置映射关系
@Entity //告诉JPA这是一个实体类（和数据表映射的类）
@Table(name = "train_item_title") //@Table来指定和哪个数据表对应;
public class TrainItemTitle {

    @Id //这是一个主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private Integer Id;
      
    @Column //省略默认列名就是属性名
    private String reqOpenId;
    
    @Column //省略默认列名就是属性名
    private long titleId;
    
    @Column //省略默认列名就是属性名
    private String titleInfo;
    
    @Column //省略默认列名就是属性名
    private String titleType;
    
    @Column //省略默认列名就是属性名
    private String titleTypeName;
    
    @Column //省略默认列名就是属性名
    private long titleUsed;

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

	public long getTitleId() {
		return titleId;
	}

	public void setTitleId(long titleId) {
		this.titleId = titleId;
	}

	public String getTitleInfo() {
		return titleInfo;
	}

	public void setTitleInfo(String titleInfo) {
		this.titleInfo = titleInfo;
	}

	public String getTitleType() {
		return titleType;
	}

	public void setTitleType(String titleType) {
		this.titleType = titleType;
	}

	public String getTitleTypeName() {
		return titleTypeName;
	}

	public void setTitleTypeName(String titleTypeName) {
		this.titleTypeName = titleTypeName;
	}

	public long getTitleUsed() {
		return titleUsed;
	}

	public void setTitleUsed(long titleUsed) {
		this.titleUsed = titleUsed;
	}

	@Override
	public String toString() {
		return "TrainItemTitle [Id=" + Id + ", reqOpenId=" + reqOpenId
				+ ", titleId=" + titleId + ", titleInfo=" + titleInfo
				+ ", titleType=" + titleType + ", titleTypeName="
				+ titleTypeName + ", titleUsed=" + titleUsed + "]";
	}
    
     
}
