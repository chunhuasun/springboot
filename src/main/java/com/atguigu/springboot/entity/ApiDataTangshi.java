package com.atguigu.springboot.entity;


import javax.persistence.*;

//使用JPA注解配置映射关系
@Entity //告诉JPA这是一个实体类（和数据表映射的类）
@Table(name = "api_data_tangshi") //@Table来指定和哪个数据表对应;
public class ApiDataTangshi {

    @Id //这是一个主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private Integer Id;
      
    @Column //省略默认列名就是属性名
    private String title;  //标题
    
    @Column //省略默认列名就是属性名
    private String type;  //分类
     
    @Column //省略默认列名就是属性名
    private String content;  //内容
    	
    @Column //省略默认列名就是属性名
    private String explanation;  //解释
    
    @Column //省略默认列名就是属性名
    private String appreciation;  //赏析
    
    @Column //省略默认列名就是属性名
    private String author;  //作者

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public String getAppreciation() {
		return appreciation;
	}

	public void setAppreciation(String appreciation) {
		this.appreciation = appreciation;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "ApiDataTangshi [Id=" + Id + ", title=" + title + ", type="
				+ type + ", content=" + content + ", explanation="
				+ explanation + ", appreciation=" + appreciation + ", author="
				+ author + "]";
	}
    	   
}
