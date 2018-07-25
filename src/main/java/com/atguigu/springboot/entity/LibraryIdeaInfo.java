package com.atguigu.springboot.entity;


import javax.persistence.*;

//使用JPA注解配置映射关系
@Entity //告诉JPA这是一个实体类（和数据表映射的类）
@Table(name = "library_idea_info") //@Table来指定和哪个数据表对应;
public class LibraryIdeaInfo {

    @Id //这是一个主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private Integer Id;
      
    @Column //省略默认列名就是属性名
    private String problem;  //题目
    
    @Column //省略默认列名就是属性名
    private String thinkAnswer;  //参考答案
    
    @Column //省略默认列名就是属性名
    private Integer thinkRank;  //参考答案排名
    
    @Column //省略默认列名就是属性名
    private Integer useNum;  //使用人数
    
    @Column //省略默认列名就是属性名
    private Integer useCount;  //使用次数
    
    @Column //省略默认列名就是属性名
    private Integer collectNum;  //收藏次数
    
    @Column //省略默认列名就是属性名
    private Integer collectUserNum;  //收藏人数
    
    @Column //省略默认列名就是属性名
    private Integer thinkScore;  //参考积分

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

	public String getThinkAnswer() {
		return thinkAnswer;
	}

	public void setThinkAnswer(String thinkAnswer) {
		this.thinkAnswer = thinkAnswer;
	}

	public Integer getThinkRank() {
		return thinkRank;
	}

	public void setThinkRank(Integer thinkRank) {
		this.thinkRank = thinkRank;
	}

	public Integer getUseNum() {
		return useNum;
	}

	public void setUseNum(Integer useNum) {
		this.useNum = useNum;
	}

	public Integer getUseCount() {
		return useCount;
	}

	public void setUseCount(Integer useCount) {
		this.useCount = useCount;
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

	public Integer getThinkScore() {
		return thinkScore;
	}

	public void setThinkScore(Integer thinkScore) {
		this.thinkScore = thinkScore;
	}

	@Override
	public String toString() {
		return "LibraryIdeaInfo [Id=" + Id + ", problem=" + problem
				+ ", thinkAnswer=" + thinkAnswer + ", thinkRank=" + thinkRank
				+ ", useNum=" + useNum + ", useCount=" + useCount
				+ ", collectNum=" + collectNum + ", collectUserNum="
				+ collectUserNum + ", thinkScore=" + thinkScore + "]";
	}
    
      
}
