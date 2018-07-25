package com.atguigu.springboot.entity;


import javax.persistence.*;

//使用JPA注解配置映射关系
@Entity //告诉JPA这是一个实体类（和数据表映射的类）
@Table(name = "user_collect_library_info") //@Table来指定和哪个数据表对应;
public class UserCollectLibraryInfo {

    @Id //这是一个主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private Integer Id;
      
    @Column //省略默认列名就是属性名
    private String openId;  //用户ID
    
    @Column //省略默认列名就是属性名
    private Integer libraryId;  //题目ID 

    @Column //省略默认列名就是属性名
    private String collectDate;  //首次收藏时间

    @Column //省略默认列名就是属性名
    private Integer collectNum;  //收藏次数
    
    @Column //省略默认列名就是属性名
    private String collectType;  //收藏题目类别
    
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

	public String getCollectDate() {
		return collectDate;
	}

	public void setCollectDate(String collectDate) {
		this.collectDate = collectDate;
	}

	public Integer getCollectNum() {
		return collectNum;
	}

	public void setCollectNum(Integer collectNum) {
		this.collectNum = collectNum;
	}

	public String getCollectType() {
		return collectType;
	}

	public void setCollectType(String collectType) {
		this.collectType = collectType;
	}

	@Override
	public String toString() {
		return "UserCollectLibraryInfo [Id=" + Id + ", openId=" + openId
				+ ", libraryId=" + libraryId + ", collectDate=" + collectDate
				+ ", collectNum=" + collectNum + ", collectType=" + collectType
				+ "]";
	}

	 
}
