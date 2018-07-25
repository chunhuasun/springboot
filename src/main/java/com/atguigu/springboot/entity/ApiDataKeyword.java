package com.atguigu.springboot.entity;


import javax.persistence.*;

//使用JPA注解配置映射关系
@Entity //告诉JPA这是一个实体类（和数据表映射的类）
@Table(name = "api_data_keyword") //@Table来指定和哪个数据表对应;
public class ApiDataKeyword {

    @Id //这是一个主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private Integer Id;
      
    @Column //省略默认列名就是属性名
    private String keyword;  //标题
    
    @Column //省略默认列名就是属性名
    private String apiFlag;  //标题 

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getApiFlag() {
		return apiFlag;
	}

	public void setApiFlag(String apiFlag) {
		this.apiFlag = apiFlag;
	}

	@Override
	public String toString() {
		return "ApiDataKeyword [Id=" + Id + ", keyword=" + keyword
				+ ", apiFlag=" + apiFlag + "]";
	}
    
}
