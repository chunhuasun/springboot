package com.atguigu.springboot.entity;


import javax.persistence.*;

//使用JPA注解配置映射关系
@Entity //告诉JPA这是一个实体类（和数据表映射的类）
@Table(name = "user_library_info") //@Table来指定和哪个数据表对应;
public class UserLibraryInfo {

    @Id //这是一个主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private Integer Id;
      
    @Column //省略默认列名就是属性名
    private String openId;  //用户ID
    
    @Column //省略默认列名就是属性名
    private Integer libraryId;  //题目ID 

    @Column //省略默认列名就是属性名
    private String firstJudgeFlag;  //首次回答结果标示 1 正确 0 错误

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

	public String getFirstJudgeFlag() {
		return firstJudgeFlag;
	}

	public void setFirstJudgeFlag(String firstJudgeFlag) {
		this.firstJudgeFlag = firstJudgeFlag;
	}

	@Override
	public String toString() {
		return "UserLibraryInfo [Id=" + Id + ", openId=" + openId
				+ ", libraryId=" + libraryId + ", firstJudgeFlag="
				+ firstJudgeFlag + "]";
	}
     
}
