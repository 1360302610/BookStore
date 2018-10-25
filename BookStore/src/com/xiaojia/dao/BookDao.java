package com.xiaojia.dao;

import java.util.List;

import javax.validation.constraints.Past;

import org.apache.ibatis.annotations.Param;

import com.xiaojia.domain.Book;
import com.xiaojia.domain.Order;

/**
 * 图书 dao mapper接口
 * @author wu
 *
 */
public interface BookDao {
	//查询总记录数id,name,category,minprice,maxprice
	
	int count(@Param("id") String id,@Param("name") String name,@Param ("category") String category,
	@Param("minPrice") String minprice,@Param("maxPrice")String maxprice);
	//条件查询 
	List<Book> findBooks(@Param ("currentPage")int currentPage,@Param ("pageSize") int pageSize
			,@Param ("category") String category, @Param("name") String name
			,@Param("id") String id,@Param("minPrice") String minprice
			,@Param("maxPrice") String maxprice);
	//通过id查询图书
	Book findBookById(String id);
	//更新书的库存
	void updateBookNum(Order order);
	
	/**
	 * 管理员
	 */
	//显示图书列表
	List<Book> showBookList(@Param("id")String id,@Param("name") String name,
			@Param("category")String category,@Param("minPrice")String minprice, @Param("maxPrice")String maxprice);
	//添加图书
	void bookAdd(Book book);
	//编辑图书
	void bookEdit(Book book);
	//通过图书id查询订单id
	List<String> findOrderIdByBookId(@Param("id") String id);
	//删除图书
	void deleteBook(String id);
}
