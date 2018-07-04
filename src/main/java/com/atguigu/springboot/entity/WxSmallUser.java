package com.atguigu.springboot.entity;


import javax.persistence.*;

//使用JPA注解配置映射关系
@Entity //告诉JPA这是一个实体类（和数据表映射的类）
@Table(name = "wx_small_user") //@Table来指定和哪个数据表对应;
public class WxSmallUser {

    @Id //这是一个主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private Integer Id;
      
    @Column //省略默认列名就是属性名
    private String openId;

    @Column //省略默认列名就是属性名
    private String city;
    
    @Column //省略默认列名就是属性名
    private String country;
    
    @Column //省略默认列名就是属性名
    private String gender;
    
    @Column //省略默认列名就是属性名
    private String nickName;
    
    @Column //省略默认列名就是属性名
    private String province;
    
    @Column //省略默认列名就是属性名
    private String avatarUrl;
    
    @Column //省略默认列名就是属性名
    private String cosUrl;
    
    @Column //省略默认列名就是属性名
    private String userGroupType;
    
    @Column //省略默认列名就是属性名
    private String userCareDate;
    
    @Column //省略默认列名就是属性名
    private String userOperType1;
    
    @Column //省略默认列名就是属性名
    private String userOperType2;
    
    @Column //省略默认列名就是属性名
    private String userOperType3;
    
    @Column //省略默认列名就是属性名
    private String userOperType4;
    
    @Column //省略默认列名就是属性名
    private String userOperType5;
    
    @Column //省略默认列名就是属性名
    private String userOperType6;
    
    @Column //省略默认列名就是属性名
    private String userOperType7;
    
    @Column //省略默认列名就是属性名
    private String userOperType8;
    
    @Column //省略默认列名就是属性名
    private String userOperType9;
    
    @Column //省略默认列名就是属性名
    private String timeStamp;
    
    @Column //省略默认列名就是属性名
    private String userSystemInfo;

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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public String getCosUrl() {
		return cosUrl;
	}

	public void setCosUrl(String cosUrl) {
		this.cosUrl = cosUrl;
	}

	public String getUserGroupType() {
		return userGroupType;
	}

	public void setUserGroupType(String userGroupType) {
		this.userGroupType = userGroupType;
	}

	public String getUserCareDate() {
		return userCareDate;
	}

	public void setUserCareDate(String userCareDate) {
		this.userCareDate = userCareDate;
	}

	public String getUserOperType1() {
		return userOperType1;
	}

	public void setUserOperType1(String userOperType1) {
		this.userOperType1 = userOperType1;
	}

	public String getUserOperType2() {
		return userOperType2;
	}

	public void setUserOperType2(String userOperType2) {
		this.userOperType2 = userOperType2;
	}

	public String getUserOperType3() {
		return userOperType3;
	}

	public void setUserOperType3(String userOperType3) {
		this.userOperType3 = userOperType3;
	}

	public String getUserOperType4() {
		return userOperType4;
	}

	public void setUserOperType4(String userOperType4) {
		this.userOperType4 = userOperType4;
	}

	public String getUserOperType5() {
		return userOperType5;
	}

	public void setUserOperType5(String userOperType5) {
		this.userOperType5 = userOperType5;
	}

	public String getUserOperType6() {
		return userOperType6;
	}

	public void setUserOperType6(String userOperType6) {
		this.userOperType6 = userOperType6;
	}

	public String getUserOperType7() {
		return userOperType7;
	}

	public void setUserOperType7(String userOperType7) {
		this.userOperType7 = userOperType7;
	}

	public String getUserOperType8() {
		return userOperType8;
	}

	public void setUserOperType8(String userOperType8) {
		this.userOperType8 = userOperType8;
	}

	public String getUserOperType9() {
		return userOperType9;
	}

	public void setUserOperType9(String userOperType9) {
		this.userOperType9 = userOperType9;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getUserSystemInfo() {
		return userSystemInfo;
	}

	public void setUserSystemInfo(String userSystemInfo) {
		this.userSystemInfo = userSystemInfo;
	}

	@Override
	public String toString() {
		return "WxSmallUser [Id=" + Id + ", openId=" + openId + ", city="
				+ city + ", country=" + country + ", gender=" + gender
				+ ", nickName=" + nickName + ", province=" + province
				+ ", avatarUrl=" + avatarUrl + ", cosUrl=" + cosUrl
				+ ", userGroupType=" + userGroupType + ", userCareDate="
				+ userCareDate + ", userOperType1=" + userOperType1
				+ ", userOperType2=" + userOperType2 + ", userOperType3="
				+ userOperType3 + ", userOperType4=" + userOperType4
				+ ", userOperType5=" + userOperType5 + ", userOperType6="
				+ userOperType6 + ", userOperType7=" + userOperType7
				+ ", userOperType8=" + userOperType8 + ", userOperType9="
				+ userOperType9 + ", timeStamp=" + timeStamp
				+ ", userSystemInfo=" + userSystemInfo + "]";
	}
    
	  
}
