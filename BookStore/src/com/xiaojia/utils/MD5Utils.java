package com.xiaojia.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class MD5Utils {
	
	public static String md5(String value){
		try {
			//获取jdk提供的消息摘要工具类对象
			MessageDigest messageDigest=MessageDigest.getInstance("MD5");
			//得到加密的结果(十进制)
			byte[] digest = messageDigest.digest(value.getBytes());
			//将10进制转换为16进制
			BigInteger bigInteger=new BigInteger(1,digest);//1表示正数
			return bigInteger.toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return value;
	
	}
	public static  void main(String [] args){
		System.out.println(md5("1234"));
	}
}
