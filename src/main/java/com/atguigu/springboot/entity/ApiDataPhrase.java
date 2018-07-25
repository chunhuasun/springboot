package com.atguigu.springboot.entity;


import javax.persistence.*;

//使用JPA注解配置映射关系
@Entity //告诉JPA这是一个实体类（和数据表映射的类）
@Table(name = "api_data_phrase") //@Table来指定和哪个数据表对应;
public class ApiDataPhrase {

    @Id //这是一个主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private Integer Id;
      
    @Column //省略默认列名就是属性名
    private String phrase;  //成语
    
    @Column //省略默认列名就是属性名
    private String apiFlag;  //API调用标示 1标示已经调用过 
     
    @Column //省略默认列名就是属性名
    private String pronounce;  //拼音
    
    @Column //省略默认列名就是属性名
    private String content;  //成语解释
    
    @Column //省略默认列名就是属性名
    private String comefrom;  //成语出自
    
    @Column //省略默认列名就是属性名
    private String antonym;  //反义词
    
    @Column //省略默认列名就是属性名
    private String thesaurus;  //近义词
    
    @Column //省略默认列名就是属性名
    private String example;  //例子
    
    @Column //省略默认列名就是属性名
    private String yufa;  //例子

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getPhrase() {
		return phrase;
	}

	public void setPhrase(String phrase) {
		this.phrase = phrase;
	}

	public String getApiFlag() {
		return apiFlag;
	}

	public void setApiFlag(String apiFlag) {
		this.apiFlag = apiFlag;
	}

	public String getPronounce() {
		return pronounce;
	}

	public void setPronounce(String pronounce) {
		this.pronounce = pronounce;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getComefrom() {
		return comefrom;
	}

	public void setComefrom(String comefrom) {
		this.comefrom = comefrom;
	}

	public String getAntonym() {
		return antonym;
	}

	public void setAntonym(String antonym) {
		this.antonym = antonym;
	}

	public String getThesaurus() {
		return thesaurus;
	}

	public void setThesaurus(String thesaurus) {
		this.thesaurus = thesaurus;
	}

	public String getExample() {
		return example;
	}

	public void setExample(String example) {
		this.example = example;
	}

	public String getYufa() {
		return yufa;
	}

	public void setYufa(String yufa) {
		this.yufa = yufa;
	}

	@Override
	public String toString() {
		return "ApiDataPhrase [Id=" + Id + ", phrase=" + phrase + ", apiFlag="
				+ apiFlag + ", pronounce=" + pronounce + ", content=" + content
				+ ", comefrom=" + comefrom + ", antonym=" + antonym
				+ ", thesaurus=" + thesaurus + ", example=" + example
				+ ", yufa=" + yufa + "]";
	}
 
}
