package com.xiaojia.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class MyUtils {
	/**
	 * 校验邮箱是否正确
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		String reg = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$";
		boolean matches = Pattern.matches(reg, email);
		return matches;
	}
public static void main(String[] args){
	//String reg="^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,6}$";
	String reg = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$";
	boolean matches = Pattern.matches(reg, "postmaster@starcup.top");
	System.out.println(matches);
}
	/**
	 * 图片上传
	 * 
	 * @param upload
	 * @param request
	 * @return  返回相对路径
	 * @throws Exception
	 */
	public static String upload(MultipartFile upload, HttpServletRequest request)
			throws Exception {
		// 获取原始文件名
		String originalFilename = upload.getOriginalFilename();
		// 获取服务器端路径
		String basePath = request.getServletContext().getRealPath("/upload");
		// 把日期格式成字符串
		SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");
		String subPath = sdf.format(new Date());
		// 如果目录不存在，则新建
		File dir = new File(basePath + subPath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		// 获取后缀名
		String suffix = originalFilename.substring(originalFilename
				.lastIndexOf("."));
		String newFileName = UUID.randomUUID().toString().replace("-", "")
				+ suffix;
		// 相对路径
		String relativePath = "/upload" + subPath + newFileName;
		// 绝对路径
		String fullPath = basePath + subPath + newFileName;
		upload.transferTo(new File(fullPath));
		return relativePath;
	}

	public static boolean isEmpty(String str){
		if("".endsWith(str) || str==null){
			return true;
		}
		return false;
	}
}
