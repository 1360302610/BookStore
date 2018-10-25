package com.xiaojia.domain;

import java.io.Serializable;

/**
 * 通过session保存购物车信息，必须实现Serializable接口，保证对象序列化，能够在网络上传输
 * @author wu
 *
 */
public class Book implements Serializable{
	public Book(){}
	public Book(String id, String name, double price, int pnum,
			String category, String description, String imgurl) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.pnum = pnum;
		this.category = category;
		this.description = description;
		this.imgurl = imgurl;
	}
	private String id;
	private String name;
	private double price;
	private int pnum;
	private String category;
	private String description;
	private String imgurl;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getPnum() {
		return pnum;
	}
	public void setPnum(int pnum) {
		this.pnum = pnum;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	//重写equals 告诉JVM根据什么来判断两个对象是否相等 
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	//修改购物车数量的时候
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
}
