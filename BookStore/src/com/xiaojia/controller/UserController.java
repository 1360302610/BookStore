package com.xiaojia.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiaojia.domain.User;
import com.xiaojia.service.UserService;
import com.xiaojia.utils.MyUtils;

/**
 * 用户web层
 * @author wu
 *
 */
@Controller
public class UserController {
	//依赖注入
	@Autowired
	private UserService userService;
	
	//显示注册页面
	@RequestMapping("/showRegisterUI")
	public String showRegisterUI(){
		return "user/register";
	}
	//注册
	@RequestMapping("/register")
	public String register(User user,HttpServletRequest request,String repassword,String code){
		if(!MyUtils.isEmail(user.getEmail().trim())){
			request.setAttribute("emailError", "邮箱格式不正确");
			return "forward:/showRegisterUI";
		}
		if(user.getUsername()==null | user.getUsername().trim().length()<4){
			request.setAttribute("usernameError", "用户名不符合要求");
			return "forward:/showRegisterUI";
		}
		if(user.getPassword()==null | user.getPassword().trim().length()<6){
			request.setAttribute("passwordError", "密码不符合要求");
			return "forward:/showRegisterUI";
		}
		if(repassword==null | "".equals(repassword) | !user.getPassword().equals(repassword)){
			request.setAttribute("repasswordError", "两次密码不一致");
			return "forward:/showRegisterUI";
		}
		String checkcode_session=(String) request.getSession().getAttribute("checkcode_session");
		if(checkcode_session==null ||"".equals(checkcode_session)){
			return "redirect:/showRegisterUI";
		}
		if(!checkcode_session.equals(code)){
			request.setAttribute("codeError", "验证码错误");
			return "forward:/showRegisterUI";
		}
		String result = userService.register(user,request);
		if("success".equals(result)){
			request.setAttribute("sendEmail", "邮箱发送成功");
		}else{
			request.setAttribute("sendEmail", "邮箱发送失败");
		}
		return "user/registersuccess";
	}
	
	//激活
	@RequestMapping("/toActivieAccount")
	public String toActivieAccound(String token,HttpServletRequest request) throws IOException{
		//判断激活码是否存在
		User user=this.userService.findUserByActivie(token);
		if(user!=null){
			//更新用户状态
			user.setState(1);
			this.userService.updateUser(user);
			request.setAttribute("message", "激活成功!");
			return "user/activeresult";
		}else{
			request.setAttribute("message", "激活失败!");
			return "user/activeresult";
		}
	}
	//显示登陆页面
	@RequestMapping("/showLoginUI")
	public String showLoginUI(HttpServletRequest request){
		String username="";
		String password="";
		Cookie[] cookies = request.getCookies();
		if(cookies!=null){
			for(Cookie c:cookies){
				//是否自动登陆
				if("name".equals(c.getName())){
					//是否记住用户名  回显
					request.setAttribute("name",c.getValue());
					username=c.getValue();
				}
				if("pwd".equals(c.getName())){
					password=c.getValue();
				}
			}
		}
		if(!"".equals(username) && !"".equals(password)){
			//自动登陆
			User user=this.userService.login(username,password);
			if(user==null){
				request.setAttribute("loginMsg", "用户名或密码错误");
				return "forward:/showLoginUI";
			}
			if(user.getState()!=1){
				request.setAttribute("loginMsg", "该账号还未激活!");
				return "forward:/showLoginUI";
			}
			request.getSession().setAttribute("user", user);
			//判断是否为管理员
			if("管理员".equals(user.getRole())){
				return "admin/home";
			}
			return "user/myAccount";
		}
		return "user/login";
	}
	//登陆
	@RequestMapping("/login")
	public String login(String username,String password,HttpServletRequest request,HttpServletResponse response,String remName,String autoLogin){
		User user=this.userService.login(username,password);
		if(user==null){
			request.setAttribute("loginMsg", "用户名或密码错误");
			return "forward:/showLoginUI";
		}
		if(user.getState()!=1){
			request.setAttribute("loginMsg", "该账号还未激活!");
			return "forward:/showLoginUI";
		}
		//判断是否勾选了记住用户名
		if(remName!=null){
			Cookie cookie=new Cookie("name",username);
			//设置Cookie存活时间
			cookie.setMaxAge(60*60*24*3);//三天
			cookie.setPath("/"); //根据个人的不用，在不同功能的路径下创建 
			response.addCookie(cookie);
		}else{
			//清除该Cookie
			Cookie[] cookies = request.getCookies();
			if(cookies!=null){
				for(Cookie c:cookies){
					if("name".equals(c.getName())){
						c.setMaxAge(0);
						c.setPath("/");
						response.addCookie(c);//必须添加进去
					}
				}
			}
		}
		//判断用户是否勾选了自动登陆
		if(autoLogin!=null){
			Cookie cName=new Cookie("name",username);
			Cookie cpwd=new Cookie("pwd",password);
			//设置过期时间
			cName.setMaxAge(60*60*24*3);
			cpwd.setMaxAge(60*60*24*3);
			cName.setPath("/");
			cpwd.setPath("/");
			//添加Cookie
			response.addCookie(cName);
			response.addCookie(cpwd);
		}else{
			Cookie[] cookies = request.getCookies();
			for(Cookie c:cookies){
				if("name".equals(c.getName())){
					c.setMaxAge(0);
					c.setPath("/");
					response.addCookie(c);//必须添加进去
				}
				if("pwd".equals(c.getName())){
					c.setMaxAge(0);
					c.setPath("/");
					response.addCookie(c);//必须添加进去
				}
			}
		}
		
		request.getSession().setAttribute("user", user);
		//判断是否为管理员
		if("管理员".equals(user.getRole())){
			return "admin/home";
		}
		return "redirect:/";
	}
	//显示我的账号页面
	@RequestMapping("/showMyAccountUI")
	public String showMyAccountUI(HttpServletRequest request){
		User user=(User) request.getSession().getAttribute("user");
		if(user==null){
			request.setAttribute("loginMsg","请先登录");
			return "forward:/showLoginUI";
		}
		//判断是否为管理员
		if("管理员".equals(user.getRole())){
				return "admin/home";
		}
		return "user/myAccount";
	}
	//显示用户信息修改页面
	@RequestMapping("/showUserModifyUI")
	public String showUserModifyUI(Integer id,HttpServletRequest request){
		if(id==null){
			request.setAttribute("loginMsg","请先登录");
			return "forward:/showLoginUI";
		}
		User user=this.userService.findUserById(id);
		request.setAttribute("u", user);
		return "user/modifyuserinfo";
	}
	//修改用户信息
	@RequestMapping("/modifyUser")
	public String modifyUser(User user,HttpServletRequest request,String repassword){
		if(user.getPassword()==null | user.getPassword().length()<6){
			request.setAttribute("passwordError", "密码不符合要求");
			request.setAttribute("id", user.getId());
			return "forward:/showUserModifyUI";
		}
		if(repassword==null | "".equals(repassword) | !user.getPassword().equals(repassword)){
			request.setAttribute("repasswordError", "两次密码不一致");
			request.setAttribute("id", user.getId());
			return "forward:/showUserModifyUI";
		}
		this.userService.modifyUser(user);
		return "user/modifyUserInfoSuccess";
	}
	//退出系统
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request,HttpServletResponse response){
		//清除Cookie
		Cookie[] cookies = request.getCookies();
		for(Cookie c:cookies){
			if("name".equals(c.getName())){
				c.setMaxAge(0);
				c.setPath("/");
				response.addCookie(c);
			}
			if("pwd".equals(c.getName())){
				c.setMaxAge(0);
				c.setPath("/");
				response.addCookie(c);
			}
			
		}
		request.getSession().invalidate();
		return "redirect:/";
	}
}
