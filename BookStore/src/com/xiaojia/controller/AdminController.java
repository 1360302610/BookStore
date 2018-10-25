package com.xiaojia.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.xiaojia.domain.Book;
import com.xiaojia.page.PageBean;
import com.xiaojia.service.AdminService;
import com.xiaojia.utils.MyUtils;

@Controller
public class AdminController {
	// 依赖注入
	@Autowired
	private AdminService adminService;
	@Autowired
	private HttpServletRequest request;

	// 显示页面
	@RequestMapping("/admin/showpage/top")
	public String showtop() {
		return "admin/top";
	}

	@RequestMapping("/admin/showpage/left")
	public String showleft() {
		return "admin/left";
	}

	@RequestMapping("/admin/showpage/welcome")
	public String showwelcome() {
		return "admin/welcome";
	}

	@RequestMapping("/admin/showpage/bottom")
	public String showpage() {
		return "admin/bottom";
	}

	// 显示图书列表  （带有条件并分页）
	@RequestMapping("/admin/showBookList")
	public String showBookList(PageBean pageBean) {
		//初始化
		int cur=1;
		int pageSize=6;
		if(pageBean.getCurrentPage()!=0){
			cur=pageBean.getCurrentPage();
		}
		
		PageBean pb = this.adminService.showBookList(cur,pageSize,pageBean.getId()
				,pageBean.getName(),pageBean.getCategory()
				,pageBean.getMinprice(),pageBean.getMaxprice());
		request.setAttribute("pb",pb);
		return "admin/bookList";
	}

	// 显示修改图书页面
	@RequestMapping("/admin/showEditBookUI")
	public String showEditBookUI(String id) {
		Book book = this.adminService.findBookById(id);
		request.setAttribute("book", book);
		return "admin/bookEdit";
	}

	// 显示图书添加页面
	@RequestMapping("/admin/showBookAddUI")
	public String showBookAddUI() {
		return "admin/bookAdd";
	}

	// 图书添加
	@RequestMapping(value = "/admin/bookAdd", method = RequestMethod.POST)
	public String bookAdd(MultipartFile upload, String name, String price,
			String pnum, String category, String description) throws Exception {
		// 校验
		if (MyUtils.isEmpty(name)) {
			request.setAttribute("nameEmpty", "图书名称不能为空!");
			return "forward:/admin/showBookAddUI";
		}
		if (MyUtils.isEmpty(price)) {
			request.setAttribute("priceEmpty", "图书价格不能为空!");
			request.setAttribute("name",name);
			
			return "forward:/admin/showBookAddUI";
		}
		if (MyUtils.isEmpty(pnum)) {
			request.setAttribute("pnumEmpty", "图书数量不能为空!");
			request.setAttribute("price",price);
			
			request.setAttribute("name",name);
			return "forward:/admin/showBookAddUI";
		}
		if (MyUtils.isEmpty(category)) {
			request.setAttribute("categoryEmpty", "请选择图书类别");
			request.setAttribute("pnum",pnum);
			request.setAttribute("price",price);
			request.setAttribute("name",name);
			return "forward:/admin/showBookAddUI";
		}
		if (upload.getSize() == 0) {
			request.setAttribute("imgEmpty", "请选择图书图片");
			request.setAttribute("category",category);
			request.setAttribute("pnum",pnum);
			request.setAttribute("price",price);
			request.setAttribute("name",name);
			return "forward:/admin/showBookAddUI";
		}
		// 封装Book
		String relativePath = MyUtils.upload(upload, request);
		String id = UUID.randomUUID().toString().replace("-", "");
		Book book = new Book(id, name, Double.valueOf(price),
				Integer.valueOf(pnum), category, description, relativePath);
		this.adminService.bookAdd(book);
		return "redirect:/admin/showBookList";
	}

	// 编辑图书
	@RequestMapping("/admin/editBook")
	public String editBook(MultipartFile upload, String name, String price,
			String pnum, String category, String description,String id) throws Exception {
		// 校验
		if (MyUtils.isEmpty(name)) {
			request.setAttribute("nameEmpty", "图书名称不能为空!");
			request.setAttribute("id", id);
			return "forward:/admin/showEditBookUI";
		}
		if (MyUtils.isEmpty(price)) {
			request.setAttribute("priceEmpty", "图书价格不能为空!");
			request.setAttribute("id", id);
			return "forward:/admin/showEditBookUI";
		}
		if (MyUtils.isEmpty(pnum)) {
			request.setAttribute("pnumEmpty", "图书数量不能为空!");
			request.setAttribute("id", id);
			return "forward:/admin/showEditBookUI";
		}
		if (MyUtils.isEmpty(category)) {
			request.setAttribute("categoryEmpty", "请选择图书类别");
			request.setAttribute("id", id);
			return "forward:/admin/showEditBookUI";
		}
		String imgurl="";
		if (upload.getSize() != 0) {
			//修改了图片
			 imgurl= MyUtils.upload(upload, request);
			
		}
		//封装Book
		Book book=new Book(id, name, Double.valueOf(price),Integer.valueOf(pnum), category, description, imgurl);
		this.adminService.bookEdit(book);
		return "redirect:/admin/showBookList";
	}
	
	//删除图书
	@RequestMapping("/admin/deleteBookById")
	public String deleteBook(String id){
		this.adminService.deleteBook(id);
		return "redirect:/admin/showBookList";
	}
}
