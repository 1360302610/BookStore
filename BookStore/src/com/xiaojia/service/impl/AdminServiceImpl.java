package com.xiaojia.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaojia.dao.BookDao;
import com.xiaojia.dao.OrderDao;
import com.xiaojia.dao.OrderItemDao;
import com.xiaojia.domain.Book;
import com.xiaojia.page.PageBean;
import com.xiaojia.service.AdminService;
@Service
public class AdminServiceImpl implements AdminService {
	//依赖注入
	@Autowired
	private BookDao bookDao;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private OrderItemDao orderItemDao;
	
	
	@Override
	public Book findBookById(String id) {
		return this.bookDao.findBookById(id);
	}
	@Transactional
	@Override
	public void bookAdd(Book book) {
		 this.bookDao.bookAdd(book);
	}
	@Override
	public void bookEdit(Book book) {
		this.bookDao.bookEdit(book);
	}
	@Override
	public List<Book> showBookList(String id, String name, String category,
			String minprice, String maxprice) {
		List<Book> list=this.bookDao.showBookList(id,name,category,minprice,maxprice);
		return list;
	}
	@Override
	public PageBean showBookList(int cur, int pageSize, String id, String name,
			String category, String minprice, String maxprice) {
		//封装PageBean
		PageBean pb=new PageBean();
		pb.setCurrentPage(cur);
		pb.setPageSize(pageSize);
		pb.setId(id);
		pb.setName(name);
		pb.setCategory(category);
		pb.setMinprice(minprice);
		pb.setMaxprice(maxprice);
		
		//计算总记录数
		int count=this.bookDao.count(id,name,category,minprice,maxprice);
		int totallPage=(int) Math.ceil(count*1.0/pageSize);
		
		pb.setCount(count);
		pb.setTotallPage(totallPage);
		
		//查询数据
		List<Book> lists=this.bookDao.findBooks((cur-1)*pageSize, pageSize, category, name,id,minprice,maxprice);
		pb.setBooks(lists);
		
		return pb;
	}
	@Transactional//TODO 去掉事物估计就行了
	@Override
	public void deleteBook(String id) {
		//1 通过id查询其他表的外键引用  
		List<String> orderIds=this.bookDao.findOrderIdByBookId(id);
		//删除订单项
		this.orderItemDao.deleteOrderItem(id);
		//删除订单
		this.orderDao.deleteOrderById(orderIds);
		//删除图书
		this.bookDao.deleteBook(id);
	}

}
