package com.atguigu.springboot.entity;


import javax.persistence.*;

//使用JPA注解配置映射关系
@Entity //告诉JPA这是一个实体类（和数据表映射的类）
@Table(name = "user_library_idea_info") //@Table来指定和哪个数据表对应;
public class UserLibraryIdeaInfo {

    @Id //这是一个主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private Integer Id;
      
    @Column //省略默认列名就是属性名
    private String openId;  //用户ID
    
    @Column //省略默认列名就是属性名
    private Integer libraryId;  //题目ID 

    @Column //省略默认列名就是属性名
    private String ideaInfo;  //用户对题目给出的不同答案

    @Column //省略默认列名就是属性名
    private Integer ideaInfoTimes;  //用户对题目给出的同一答案的次数

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

	public Integer getLibraryId() {
		return libraryId;
	}

	public void setLibraryId(Integer libraryId) {
		this.libraryId = libraryId;
	}

	public String getIdeaInfo() {
		return ideaInfo;
	}

	public void setIdeaInfo(String ideaInfo) {
		this.ideaInfo = ideaInfo;
	}

	public Integer getIdeaInfoTimes() {
		return ideaInfoTimes;
	}

	public void setIdeaInfoTimes(Integer ideaInfoTimes) {
		this.ideaInfoTimes = ideaInfoTimes;
	}

	@Override
	public String toString() {
		return "UserLibraryIdeaInfo [Id=" + Id + ", openId=" + openId
				+ ", libraryId=" + libraryId + ", ideaInfo=" + ideaInfo
				+ ", ideaInfoTimes=" + ideaInfoTimes + "]";
	}
     
}
