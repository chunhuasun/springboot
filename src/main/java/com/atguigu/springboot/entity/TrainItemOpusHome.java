package com.atguigu.springboot.entity;


import javax.persistence.*;

//使用JPA注解配置映射关系
@Entity //告诉JPA这是一个实体类（和数据表映射的类）
@Table(name = "train_item_opus_home") //@Table来指定和哪个数据表对应;
public class TrainItemOpusHome {

    @Id //这是一个主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private Integer Id;
      
    @Column //省略默认列名就是属性名
    private String baseinfo;
    
    @Column //省略默认列名就是属性名
    private String cosimageurls;
    
    @Column //省略默认列名就是属性名
    private String heightscale;
    
    @Column //省略默认列名就是属性名
    private String imagemode;
    
    @Column //省略默认列名就是属性名
    private String reqOpenId;
    
    @Column //省略默认列名就是属性名
    private String timeStamp;
    
    @Column //省略默认列名就是属性名
    private String useFlag;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getBaseinfo() {
		return baseinfo;
	}

	public void setBaseinfo(String baseinfo) {
		this.baseinfo = baseinfo;
	}

	public String getCosimageurls() {
		return cosimageurls;
	}

	public void setCosimageurls(String cosimageurls) {
		this.cosimageurls = cosimageurls;
	}

	public String getHeightscale() {
		return heightscale;
	}

	public void setHeightscale(String heightscale) {
		this.heightscale = heightscale;
	}

	public String getImagemode() {
		return imagemode;
	}

	public void setImagemode(String imagemode) {
		this.imagemode = imagemode;
	}

	public String getReqOpenId() {
		return reqOpenId;
	}

	public void setReqOpenId(String reqOpenId) {
		this.reqOpenId = reqOpenId;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getUseFlag() {
		return useFlag;
	}

	public void setUseFlag(String useFlag) {
		this.useFlag = useFlag;
	}

	@Override
	public String toString() {
		return "TrainItemOpusHome [Id=" + Id + ", baseinfo=" + baseinfo
				+ ", cosimageurls=" + cosimageurls + ", heightscale="
				+ heightscale + ", imagemode=" + imagemode + ", reqOpenId="
				+ reqOpenId + ", timeStamp=" + timeStamp + ", useFlag="
				+ useFlag + "]";
	}
     
}
