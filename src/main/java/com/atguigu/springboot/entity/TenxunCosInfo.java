package com.atguigu.springboot.entity;


import javax.persistence.*;

//使用JPA注解配置映射关系
@Entity //告诉JPA这是一个实体类（和数据表映射的类）
@Table(name = "tenxun_cos_info") //@Table来指定和哪个数据表对应;
public class TenxunCosInfo {

    @Id //这是一个主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private Integer Id;
      
    @Column //省略默认列名就是属性名
    private String fromUser;
    
    @Column //省略默认列名就是属性名
    private String cosInfo;
    
    @Column //省略默认列名就是属性名
    private String cosSize;
    
    @Column //省略默认列名就是属性名
    private String cosUrlInfo;
     
    @Column //省略默认列名就是属性名
    private long ingressId;
  
    @Column //省略默认列名就是属性名
    private String operDate;
    
    @Column //省略默认列名就是属性名
    private String operDateId;
    
    @Column //省略默认列名就是属性名
    private String operDateTime;

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

	public String getCosInfo() {
		return cosInfo;
	}

	public void setCosInfo(String cosInfo) {
		this.cosInfo = cosInfo;
	}

	public String getCosSize() {
		return cosSize;
	}

	public void setCosSize(String cosSize) {
		this.cosSize = cosSize;
	}

	public String getCosUrlInfo() {
		return cosUrlInfo;
	}

	public void setCosUrlInfo(String cosUrlInfo) {
		this.cosUrlInfo = cosUrlInfo;
	}

	public long getIngressId() {
		return ingressId;
	}

	public void setIngressId(long ingressId) {
		this.ingressId = ingressId;
	}

	public String getOperDate() {
		return operDate;
	}

	public void setOperDate(String operDate) {
		this.operDate = operDate;
	}

	public String getOperDateId() {
		return operDateId;
	}

	public void setOperDateId(String operDateId) {
		this.operDateId = operDateId;
	}

	public String getOperDateTime() {
		return operDateTime;
	}

	public void setOperDateTime(String operDateTime) {
		this.operDateTime = operDateTime;
	}

	@Override
	public String toString() {
		return "TenxunCosInfo [Id=" + Id + ", fromUser=" + fromUser
				+ ", cosInfo=" + cosInfo + ", cosSize=" + cosSize
				+ ", cosUrlInfo=" + cosUrlInfo + ", ingressId=" + ingressId
				+ ", operDate=" + operDate + ", operDateId=" + operDateId
				+ ", operDateTime=" + operDateTime + "]";
	}
 	  
}
