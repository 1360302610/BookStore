package com.xiaojia.service;

import java.util.List;

import com.xiaojia.domain.Book;
import com.xiaojia.domain.Order;
import com.xiaojia.page.PageBean;


/**
 * 图书service层接口
 * @author wu
 *
 */
public interface BookService {

	/**
	 * 条件分页查询
	 * @param currentPage
	 * @param pageSize
	 * @param category
	 * @return
	 */
	PageBean showBookPage(int currentPage, int pageSize, String category,String name);

	/**
	 * 通过id查询图书
	 * @param id
	 * @return
	 */
	Book findBookById(String id);
	

}
