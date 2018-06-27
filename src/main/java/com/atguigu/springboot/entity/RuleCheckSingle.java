package com.atguigu.springboot.entity;


import javax.persistence.*;

//使用JPA注解配置映射关系
@Entity //告诉JPA这是一个实体类（和数据表映射的类）
@Table(name = "rule_check_single") //@Table来指定和哪个数据表对应;如果省略默认表名就是user；
public class RuleCheckSingle {

    @Id //这是一个主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private Integer checkNo;
      
    @Column
    private Integer checkGroupNo;
     
	@Column(name = "cardType",length = 40) //这是和数据表对应的一个列
    private String cardType;
    @Column //省略默认列名就是属性名
    private String cardPlace;
    
    @Column //省略默认列名就是属性名
    private String checkType;
    
    @Column //省略默认列名就是属性名
    private String checkValue;
    
    @Column //省略默认列名就是属性名
    private Integer checkEngineNo;

	public Integer getCheckNo() {
		return checkNo;
	}

	public void setCheckNo(Integer checkNo) {
		this.checkNo = checkNo;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardPlace() {
		return cardPlace;
	}

	public void setCardPlace(String cardPlace) {
		this.cardPlace = cardPlace;
	}

	public String getCheckType() {
		return checkType;
	}

	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}

	public String getCheckValue() {
		return checkValue;
	}

	public void setCheckValue(String checkValue) {
		this.checkValue = checkValue;
	}

	public Integer getCheckEngineNo() {
		return checkEngineNo;
	}

	public void setCheckEngineNo(Integer checkEngineNo) {
		this.checkEngineNo = checkEngineNo;
	}
	 
	public Integer getCheckGroupNo() {
		return checkGroupNo;
	}

	public void setCheckGroupNo(Integer checkGroupNo) {
		this.checkGroupNo = checkGroupNo;
	}
	
	@Override
	public String toString() {
		return "RuleCheckSingle [checkNo=" + checkNo + ", checkGroupNo="
				+ checkGroupNo + ", cardType=" + cardType + ", cardPlace="
				+ cardPlace + ", checkType=" + checkType + ", checkValue="
				+ checkValue + ", checkEngineNo=" + checkEngineNo + "]";
	}
 
}
