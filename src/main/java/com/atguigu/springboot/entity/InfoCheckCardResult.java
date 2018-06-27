package com.atguigu.springboot.entity;


import javax.persistence.*;

//使用JPA注解配置映射关系
@Entity //告诉JPA这是一个实体类（和数据表映射的类）
@Table(name = "info_check_card_result") //@Table来指定和哪个数据表对应;如果省略默认表名就是user；
public class InfoCheckCardResult {

    @Id //这是一个主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private Integer id;
    
    @Column
    private Integer checkNo;
    
    @Column
    private Integer checkGroupNo;
     
	@Column
    private String certType;
    @Column //省略默认列名就是属性名
    private String certName;
    
    @Column //省略默认列名就是属性名
    private String certNum;
    
    @Column //省略默认列名就是属性名
    private String certAdd;
 
    @Column //省略默认列名就是属性名
    private String checkRuleInfo;

    @Column //省略默认列名就是属性名
    private String checkContentInfo;
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCheckNo() {
		return checkNo;
	}

	public void setCheckNo(Integer checkNo) {
		this.checkNo = checkNo;
	}

	public Integer getCheckGroupNo() {
		return checkGroupNo;
	}

	public void setCheckGroupNo(Integer checkGroupNo) {
		this.checkGroupNo = checkGroupNo;
	}

	public String getCertType() {
		return certType;
	}

	public void setCertType(String certType) {
		this.certType = certType;
	}

	public String getCertName() {
		return certName;
	}

	public void setCertName(String certName) {
		this.certName = certName;
	}

	public String getCertNum() {
		return certNum;
	}

	public void setCertNum(String certNum) {
		this.certNum = certNum;
	}

	public String getCertAdd() {
		return certAdd;
	}

	public void setCertAdd(String certAdd) {
		this.certAdd = certAdd;
	}

	public String getCheckRuleInfo() {
		return checkRuleInfo;
	}

	public void setCheckRuleInfo(String checkRuleInfo) {
		this.checkRuleInfo = checkRuleInfo;
	}

	public String getCheckContentInfo() {
		return checkContentInfo;
	}

	public void setCheckContentInfo(String checkContentInfo) {
		this.checkContentInfo = checkContentInfo;
	}

	@Override
	public String toString() {
		return "InfoCheckCardResult [id=" + id + ", checkNo=" + checkNo
				+ ", checkGroupNo=" + checkGroupNo + ", certType=" + certType
				+ ", certName=" + certName + ", certNum=" + certNum
				+ ", certAdd=" + certAdd + ", checkRuleInfo=" + checkRuleInfo
				+ ", checkContentInfo=" + checkContentInfo + "]";
	}
     
}
