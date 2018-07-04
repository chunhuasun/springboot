package com.atguigu.springboot.entity;


import javax.persistence.*;

//使用JPA注解配置映射关系
@Entity //告诉JPA这是一个实体类（和数据表映射的类）
@Table(name = "wx_small_serv_log") //@Table来指定和哪个数据表对应;
public class WxSmallServLog {

    @Id //这是一个主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private Integer Id;
      
    @Column //省略默认列名就是属性名
    private String OpenId; 
    
    @Column //省略默认列名就是属性名
    private String userServType; 
    
    @Column //省略默认列名就是属性名
    private String operDayId; 
    
    @Column //省略默认列名就是属性名
    private String operDate;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getOpenId() {
		return OpenId;
	}

	public void setOpenId(String openId) {
		OpenId = openId;
	}

	public String getUserServType() {
		return userServType;
	}

	public void setUserServType(String userServType) {
		this.userServType = userServType;
	}

	public String getOperDayId() {
		return operDayId;
	}

	public void setOperDayId(String operDayId) {
		this.operDayId = operDayId;
	}

	public String getOperDate() {
		return operDate;
	}

	public void setOperDate(String operDate) {
		this.operDate = operDate;
	}

	@Override
	public String toString() {
		return "WxSmallServLog [Id=" + Id + ", OpenId=" + OpenId
				+ ", userServType=" + userServType + ", operDayId=" + operDayId
				+ ", operDate=" + operDate + "]";
	}  
    		  
}
