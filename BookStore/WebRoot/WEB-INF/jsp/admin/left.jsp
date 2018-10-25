<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>菜单</title>
<link href="${pageContext.request.contextPath}/css/left.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery3.3.1.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
	<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" type="text/css">
</head>
<body>
	<ul class="nav nav-pills nav-stacked"><!-- style="background-color: #AFD1F3" -->
 		<li role="presentation" ><a href="${pageContext.request.contextPath }/admin/showBookList" target="mainFrame" style="background-color: #AFD1F3;color: #fff" >图书管理</a></li>
  		<li role="presentation"><a href="#" style="background-color: #AFD1F3;color: #fff">销售榜单</a></li>
  		<li role="presentation"><a href="#" style="background-color: #AFD1F3;color: #fff">订单查看</a></li>
 		
	</ul>
</body>
</html>
