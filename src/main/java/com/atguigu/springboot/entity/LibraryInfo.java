package com.atguigu.springboot.entity;


import javax.persistence.*;

//使用JPA注解配置映射关系
@Entity //告诉JPA这是一个实体类（和数据表映射的类）
@Table(name = "library_info") //@Table来指定和哪个数据表对应;
public class LibraryInfo {

    @Id //这是一个主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private Integer Id;
      
    @Column //省略默认列名就是属性名
    private String problem;  //题目
    
    @Column //省略默认列名就是属性名
    private String answer;  //答案
    
    @Column //省略默认列名就是属性名
    private String confuse1;  //混淆选项1
    
    @Column //省略默认列名就是属性名
    private String confuse2;  //混淆选项2
    
    @Column //省略默认列名就是属性名
    private String confuse3;  //混淆选项3
    
    @Column //省略默认列名就是属性名
    private Integer bingoNum;  //回答正确数
    
    @Column //省略默认列名就是属性名
    private Integer missNum;  //回答错误数
    
    @Column //省略默认列名就是属性名
    private Integer collectNum;  //收藏次数
    
    @Column //省略默认列名就是属性名
    private Integer collectUserNum;  //收藏人数
    
	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getProblem() {
		return problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getConfuse1() {
		return confuse1;
	}

	public void setConfuse1(String confuse1) {
		this.confuse1 = confuse1;
	}

	public String getConfuse2() {
		return confuse2;
	}

	public void setConfuse2(String confuse2) {
		this.confuse2 = confuse2;
	}

	public String getConfuse3() {
		return confuse3;
	}

	public void setConfuse3(String confuse3) {
		this.confuse3 = confuse3;
	}

	public Integer getBingoNum() {
		return bingoNum;
	}

	public void setBingoNum(Integer bingoNum) {
		this.bingoNum = bingoNum;
	}

	public Integer getMissNum() {
		return missNum;
	}

	public void setMissNum(Integer missNum) {
		this.missNum = missNum;
	}

	public Integer getCollectNum() {
		return collectNum;
	}

	public void setCollectNum(Integer collectNum) {
		this.collectNum = collectNum;
	}

	public Integer getCollectUserNum() {
		return collectUserNum;
	}

	public void setCollectUserNum(Integer collectUserNum) {
		this.collectUserNum = collectUserNum;
	}

	@Override
	public String toString() {
		return "LibraryInfo [Id=" + Id + ", problem=" + problem + ", answer="
				+ answer + ", confuse1=" + confuse1 + ", confuse2=" + confuse2
				+ ", confuse3=" + confuse3 + ", bingoNum=" + bingoNum
				+ ", missNum=" + missNum + ", collectNum=" + collectNum
				+ ", collectUserNum=" + collectUserNum + "]";
	}
     
    
}
