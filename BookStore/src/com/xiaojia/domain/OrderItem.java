package com.xiaojia.domain;

public class OrderItem {
	private Order order;
	private Book book; // 购买那本书
	private int buynum; // 购买数量
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public int getBuynum() {
		return buynum;
	}
	public void setBuynum(int buynum) {
		this.buynum = buynum;
	}

	
	
}
