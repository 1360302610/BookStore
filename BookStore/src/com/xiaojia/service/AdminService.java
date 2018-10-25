package com.xiaojia.service;

import java.util.List;

import com.xiaojia.domain.Book;
import com.xiaojia.page.PageBean;

public interface AdminService {
	
	/**
	 * 显示图书列表
	 * @return
	 */
	List<Book> showBookList(String id,String name,String category,String minprice,String maxprice);
	/**
	 * 查询图书
	 * @param id
	 * @return
	 */
	Book findBookById(String id);
	/**
	 * 添加图书
	 * @param book
	 */
	void bookAdd(Book book);
	/**
	 * 编辑图书
	 * @param book
	 */
	void bookEdit(Book book);
	/**
	 * 分页条件查询
	 * @param cur
	 * @param pageSize
	 * @param id
	 * @param name
	 * @param category
	 * @param minprice
	 * @param maxprice
	 * @return
	 */
	PageBean showBookList(int cur, int pageSize, String id, String name,
			String category, String minprice, String maxprice);
	/**
	 * 删除图书
	 * @param id
	 */
	void deleteBook(String id);

}
