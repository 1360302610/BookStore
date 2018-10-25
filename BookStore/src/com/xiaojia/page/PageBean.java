package com.xiaojia.page;

import java.util.List;

import com.xiaojia.domain.Book;

public class PageBean {

	private int currentPage; //当前页数
	private int pageSize;//每页显示多少
	private int count;//总记录数
	private int totallPage;//总页数
	private List<Book> books; //数据
	//条件
	private String category;//类别
	private String name;//图书名称
	private String id; //编号
	private String minprice;//最低价
	private String maxprice;//最高价
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMinprice() {
		return minprice;
	}
	public void setMinprice(String minprice) {
		this.minprice = minprice;
	}
	public String getMaxprice() {
		return maxprice;
	}
	public void setMaxprice(String maxprice) {
		this.maxprice = maxprice;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getTotallPage() {
		return totallPage;
	}
	public void setTotallPage(int totallPage) {
		this.totallPage = totallPage;
	}
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	
}
