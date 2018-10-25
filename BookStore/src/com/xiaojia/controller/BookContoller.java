 package com.xiaojia.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaojia.domain.Book;
import com.xiaojia.domain.User;
import com.xiaojia.page.PageBean;
import com.xiaojia.service.BookService;

/**
 * 图书web层接口
 * @author wu
 *
 */
@Controller
public class BookContoller {
	//依赖注入
	@Autowired
	private BookService bookService;
	//首页
	@RequestMapping("/")
	public String index(){
		return "user/index";
	}
	
	//导航栏分页查询
	@RequestMapping("/showBookPage")
	public String showBookPage(PageBean pageBean,HttpServletRequest request){
		//初始化
		int cur=1; //当前页
		int pageSize=4; //每页显示4本书
		if(pageBean.getCurrentPage()!=0){
			cur=pageBean.getCurrentPage();
		}
		//分页查询，并返回PageBean对象
		PageBean pb = bookService.showBookPage(cur,pageSize,pageBean.getCategory(),pageBean.getName());
		request.setAttribute("pb", pb);
		return "user/book_list";
	}
	//显示书的详情
	@RequestMapping("/showBookInfo")
	public String showBookInfo(String id,HttpServletRequest request){
		Book book=this.bookService.findBookById(id);
		request.setAttribute("book",book);
		return "user/book_info";
	}
	//将图书添加到购物车
	@RequestMapping("/addCart")
	public String addCart(String id,HttpServletRequest request){
		User user=(User) request.getSession().getAttribute("user");
		if(user==null){
			request.setAttribute("loginMsg","请先登录");
			return "forward:/showLoginUI";
		}
		//先查询图书
		Book book = this.bookService.findBookById(id);
		
		//从session中的购物车取出 来
		HttpSession session = request.getSession();
		Map<Book, String> cart = (Map<Book, String>) session.getAttribute("cart");
		int num = 1;
		//如何是第一次访问，没有购物车对象，我们就创建 一个购物车对象
		if(cart==null){
				cart = new HashMap<Book, String>();	
			}
		//查看当前集合中是否存在b这本书,如果有就把数据取出来加1;
		if(cart.containsKey(book)){
				num=Integer.parseInt(cart.get(book))+1;
			}
		//把图书放入购物车
		cart.put(book, num+"");
				
		//把cart对象放回到session作用域中
		session.setAttribute("cart", cart);
		
		return "redirect:/showCart";
	}

	//显示购物车
	@RequestMapping("/showCart")
	public String showCard(HttpServletRequest request){
		User user=(User) request.getSession().getAttribute("user");
		if(user==null){
			request.setAttribute("loginMsg","请先登录");
			return "forward:/showLoginUI";
		}
		return "user/cart";
	}
	
	//显示结账页面
	@RequestMapping("/showOrderUI")
	public String showOrderUI(){
		return "user/order";
	}
	//修改图书的库存
	@RequestMapping("/changebookpnum")
	public String changebookpnum(String id,String num,HttpServletRequest request){
		//重写id的hashcode
		Book book=new Book();
		book.setId(id);
		//得到购物车
		HttpSession session = request.getSession();
		Map<Book,String> map=(Map<Book, String>) session.getAttribute("cart");
		//当图书数量为0，则从购物车中删除掉
		if("0".equals(num)){
			map.remove(book);
		}
		//如果找到与id相同的书
		if(map.containsKey(book)){
			map.put(book, num);
		}
		return "redirect:/showCart";
	}
}
