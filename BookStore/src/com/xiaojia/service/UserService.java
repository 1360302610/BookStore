package com.xiaojia.service;

import javax.servlet.http.HttpServletRequest;

import com.xiaojia.domain.User;

/**
 * 用户service层接口
 * @author wu
 *
 */
public interface UserService {

	/**
	 * 用户注册
	 * @param user
	 */
	public String register(User user,HttpServletRequest request);

	/**
	 * 通过激活码判断用户是否存在
	 * @param token
	 * @return
	 */
	public User findUserByActivie(String token);

	/**
	 * 更新用户信息
	 * @param user
	 */
	public void updateUser(User user);

	/**
	 * 用户登陆
	 * @param username
	 * @param password
	 * @return
	 */
	public User login(String username, String password);
	/**
	 * 根据id查找用户
	 * @param id
	 * @return
	 */
	public User findUserById(Integer id);

	/**
	 * 更改用户信息
	 * @param user
	 */
	public void modifyUser(User user);

}
