package com.xiaojia.service.impl;

import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiaojia.dao.UserDao;
import com.xiaojia.domain.User;
import com.xiaojia.service.UserService;
import com.xiaojia.utils.MD5Utils;
import com.xiaojia.utils.MailSenderInfo;
import com.xiaojia.utils.SimpleMailSender;
/**
 * 用户web层接口的实现类
 * @author wu
 *
 */
@Service
public class UserServiceImpl implements UserService{
	//依赖注入
	@Autowired
	private UserDao userDao;
	//用户注册
	@Override
	public String register(User user,HttpServletRequest request) {
		user.setEmail(user.getEmail().trim());
		user.setRegistTime(new Date());
		//激活码
		String activeCode = UUID.randomUUID().toString();
		//设置一些属性
		user.setActiveCode(activeCode);
		user.setRole("普通用户");
		user.setPassword(MD5Utils.md5(user.getPassword()));
		this.userDao.register(user);
		//注册成功，发送邮件
		
		// 这个类主要是设置邮件  
        MailSenderInfo mailInfo = new MailSenderInfo();  
        mailInfo.setMailServerHost("smtp.163.com");  
        mailInfo.setMailServerPort("465");  
        mailInfo.setValidate(true);  
        mailInfo.setUserName("15766105427@163.com"); // 实际发送者  
        mailInfo.setPassword("duanshi163");// 您的邮箱授权码
        mailInfo.setFromAddress("15766105427@163.com"); // 设置发送人邮箱地址  
        mailInfo.setToAddress(user.getEmail()); // 设置接受者邮箱地址  
        mailInfo.setSubject("激活账号");  
        //设置发送内容
        String path=request.getContextPath();  
		String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;  
		String activie = basePath + "/toActivieAccount?token="+activeCode;  
		String emailContent = "请勿回复本邮件.请点击<a href="+activie+">激活</a>"; 
		
        mailInfo.setContent(emailContent);  
        // 这个类主要来发送邮件  
        SimpleMailSender sms = new SimpleMailSender();  
        String result=sms.sendHtmlMail(mailInfo); // 发送html格式 
        return result;
	}
	@Override
	public User findUserByActivie(String code) {
		return this.userDao.findUserByActiveCode(code);
		
	}
	@Override
	public void updateUser(User user) {
		this.userDao.updateUser(user);
	}
	@Override
	public User login(String username, String password) {
		return this.userDao.login(username,MD5Utils.md5(password));
	}
	@Override
	public User findUserById(Integer id) {
		return this.userDao.findUserById(id);
	}
	@Override
	public void modifyUser(User user) {
		//先查询出用户
		User u = this.userDao.findUserById(user.getId());
		//判断用户是否修改了密码
		if(!u.getPassword().equals(user.getPassword())){
			u.setPassword(MD5Utils.md5(user.getPassword()));
		}
		u.setTelephone(user.getTelephone());
		u.setGender(user.getGender());
		this.userDao.updateUser(u);
	}

}
