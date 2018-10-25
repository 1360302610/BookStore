package com.xiaojia.utils;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
//必须重写getPasswordAuthentication
public class MyAuthenticator extends Authenticator{
	/**
	 * 发件人帐号密码
	 */
	 public static String userName;
	 public static String password;
	 public MyAuthenticator(){}
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		// TODO Auto-generated method stub
		return new PasswordAuthentication(userName, password);
	}

}
