package com.atguigu.springboot.entity;


import javax.persistence.*;

//使用JPA注解配置映射关系
@Entity //告诉JPA这是一个实体类（和数据表映射的类）
@Table(name = "wx_small_login") //@Table来指定和哪个数据表对应;
public class WxSmallLogin {

    @Id //这是一个主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private Integer Id;
      
    @Column //省略默认列名就是属性名
    private String openId;
    
    @Column //省略默认列名就是属性名
    private long ingressId;
    
    @Column //省略默认列名就是属性名
    private String operDate;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
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

	@Override
	public String toString() {
		return "WxSmallLogin [Id=" + Id + ", openId=" + openId + ", ingressId="
				+ ingressId + ", operDate=" + operDate + "]";
	}
 
}
