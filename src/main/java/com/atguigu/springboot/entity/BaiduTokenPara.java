package com.atguigu.springboot.entity;


import javax.persistence.*;

//使用JPA注解配置映射关系
@Entity //告诉JPA这是一个实体类（和数据表映射的类）
@Table(name = "baidu_token_para") //@Table来指定和哪个数据表对应;
public class BaiduTokenPara {

    @Id //这是一个主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private Integer Id;
      
    @Column //省略默认列名就是属性名
    private String operDate;
    
    @Column //省略默认列名就是属性名
    private String operDay;
    
    @Column //省略默认列名就是属性名
    private String operDayId;
    
    @Column //省略默认列名就是属性名
    private String accessToken;
    
    @Column //省略默认列名就是属性名
    private String tokenPara;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getOperDate() {
		return operDate;
	}

	public void setOperDate(String operDate) {
		this.operDate = operDate;
	}

	public String getOperDay() {
		return operDay;
	}

	public void setOperDay(String operDay) {
		this.operDay = operDay;
	}

	public String getOperDayId() {
		return operDayId;
	}

	public void setOperDayId(String operDayId) {
		this.operDayId = operDayId;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getTokenPara() {
		return tokenPara;
	}

	public void setTokenPara(String tokenPara) {
		this.tokenPara = tokenPara;
	}

	@Override
	public String toString() {
		return "BaiduTokenPara [Id=" + Id + ", operDate=" + operDate
				+ ", operDay=" + operDay + ", operDayId=" + operDayId
				+ ", accessToken=" + accessToken + ", tokenPara=" + tokenPara
				+ "]";
	}
    
}
