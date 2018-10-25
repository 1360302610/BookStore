package com.xiaojia.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaojia.dao.BookDao;
import com.xiaojia.domain.Book;
import com.xiaojia.domain.Order;
import com.xiaojia.page.PageBean;
import com.xiaojia.service.BookService;
/**
 * 图书service层接口的实现类
 * @author wu
 *
 */
@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookDao bookDao;
	@Override
	public PageBean showBookPage(int currentPage, int pageSize, String category,String name) {
		//创建PageBean对象,并封装条件
		PageBean pb=new PageBean();
		pb.setCurrentPage(currentPage);
		pb.setPageSize(pageSize);
		pb.setCategory(category);
		pb.setName(name);
		//计算 查询
		int count = bookDao.count(null, name,category, null,null);//得到总记录数
		int totallPage = (int)Math.ceil(count*1.0/pageSize); //求出总页数
		//条件查询数据
		List<Book> books=bookDao.findBooks((currentPage-1)*pageSize, pageSize, category, name, null,null,null);
		//封装PageBean
		pb.setBooks(books);
		pb.setCount(count);
		pb.setTotallPage(totallPage);
		
		return pb;
	}
	@Override
	public Book findBookById(String id) {
		return this.bookDao.findBookById(id);
	}

}
