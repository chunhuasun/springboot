package com.atguigu.springboot.entity;


import javax.persistence.*;

//使用JPA注解配置映射关系
@Entity //告诉JPA这是一个实体类（和数据表映射的类）
@Table(name = "wx_small_serv_info") //@Table来指定和哪个数据表对应;
public class WxSmallServInfo {

    @Id //这是一个主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private Integer Id;
      
    @Column //省略默认列名就是属性名
    private long servOrderId; 
    
    @Column //省略默认列名就是属性名
    private String useEffDayId; 
    
    @Column //省略默认列名就是属性名
    private String useEffFlag;
    
    @Column //省略默认列名就是属性名
    private long useServTimes;
    
    @Column //省略默认列名就是属性名
    private String userGroupType;
    
    @Column //省略默认列名就是属性名
    private String userServInfo;
    
    @Column //省略默认列名就是属性名
    private String userServType;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public long getServOrderId() {
		return servOrderId;
	}

	public void setServOrderId(long servOrderId) {
		this.servOrderId = servOrderId;
	}

	public String getUseEffDayId() {
		return useEffDayId;
	}

	public void setUseEffDayId(String useEffDayId) {
		this.useEffDayId = useEffDayId;
	}

	public String getUseEffFlag() {
		return useEffFlag;
	}

	public void setUseEffFlag(String useEffFlag) {
		this.useEffFlag = useEffFlag;
	}

	public long getUseServTimes() {
		return useServTimes;
	}

	public void setUseServTimes(long useServTimes) {
		this.useServTimes = useServTimes;
	}

	public String getUserGroupType() {
		return userGroupType;
	}

	public void setUserGroupType(String userGroupType) {
		this.userGroupType = userGroupType;
	}

	public String getUserServInfo() {
		return userServInfo;
	}

	public void setUserServInfo(String userServInfo) {
		this.userServInfo = userServInfo;
	}

	public String getUserServType() {
		return userServType;
	}

	public void setUserServType(String userServType) {
		this.userServType = userServType;
	}

	@Override
	public String toString() {
		return "WxSmallServInfo [Id=" + Id + ", servOrderId=" + servOrderId
				+ ", useEffDayId=" + useEffDayId + ", useEffFlag=" + useEffFlag
				+ ", useServTimes=" + useServTimes + ", userGroupType="
				+ userGroupType + ", userServInfo=" + userServInfo
				+ ", userServType=" + userServType + "]";
	}
    
}
