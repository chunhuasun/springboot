package com.atguigu.springboot.entity;


import javax.persistence.*;

//使用JPA注解配置映射关系
@Entity //告诉JPA这是一个实体类（和数据表映射的类）
@Table(name = "wx_small_serv_lock") //@Table来指定和哪个数据表对应;
public class WxSmallServLock {

    @Id //这是一个主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private Integer Id;
      
    @Column //省略默认列名就是属性名
    private String getType;
    
    @Column //省略默认列名就是属性名
    private String funOpType;
  
    @Column //省略默认列名就是属性名
    private String lockFlag;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getGetType() {
		return getType;
	}

	public void setGetType(String getType) {
		this.getType = getType;
	}

	public String getFunOpType() {
		return funOpType;
	}

	public void setFunOpType(String funOpType) {
		this.funOpType = funOpType;
	}

	public String getLockFlag() {
		return lockFlag;
	}

	public void setLockFlag(String lockFlag) {
		this.lockFlag = lockFlag;
	}

	@Override
	public String toString() {
		return "WxSmallServLock [Id=" + Id + ", getType=" + getType
				+ ", funOpType=" + funOpType + ", lockFlag=" + lockFlag + "]";
	} 
    		  
}
