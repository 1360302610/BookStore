package com.xiaojia.dao;

import org.apache.ibatis.annotations.Param;

import com.xiaojia.domain.User;

public interface UserDao {

	//用户注册
	void register(User user);
	//通过激活码查询用户
	User findUserByActiveCode(String code);
	//更新用户信息
	void updateUser(User user);
	//登陆
	User login(@Param ("username") String username, @Param ("password")String password);
	//通过id查询用户
	User findUserById(Integer id);

}
