<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<%String basePath=
request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
%>
<title>bookStore列表</title>
<%--导入css --%>
<link rel="stylesheet" href="css/main.css" type="text/css" />
</head>

<body class="main">

	<jsp:include page="head.jsp" />
	<jsp:include page="menu_search.jsp" />

	<div id="divpagecontent">
		<table width="100%" border="0" cellspacing="0">
			<tr>

				<td>
					<div style="text-align:right; margin:5px 10px 5px 0px">
						<a href="${pageContext.request.contextPath }/">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;<c:if test="${pb.category!=null && pb.category!='' }">&gt;</c:if>&nbsp;&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/showBookPage?category=${pb.category}">${pb.category }</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;图书列表
					</div>

					<table cellspacing="0" class="listcontent">
						<tr>
							<td>
								<h1>商品目录</h1>
								<hr />
								<h1>计算机</h1>&nbsp;&nbsp;&nbsp;&nbsp;共${pb.count }种商品
								<hr />
								<div style="margin-top:20px; margin-bottom:5px">
									<img src="images/productlist.gif" width="100%" height="38" />
								</div>

								<table cellspacing="0" class="booklist">
									<tr>
									<c:forEach items="${pb.books}" var="page">
										<td>
											<div class="divbookpic">
												<p>
													<a href="${pageContext.request.contextPath }/showBookInfo?id=${page.id}"><img src="<%=basePath %>${page.imgurl}" width="115" height="129"
														border="0" /> </a>
												</p>
											</div>

											<div class="divlisttitle">
												<a href="${pageContext.request.contextPath }/showBookInfo?id=${page.id}">书名:${page.name}<br />售价:${page.price} </a>
											</div></td>
										<td>
										
									</c:forEach>
									</tr>
								</table>
								<div class="pagination">
								<c:if test="${pb.books.size()!=0}">
									<ul>
										<li class="disablepage"><a href="${pageContext.request.contextPath}/showBookPage?name=${pb.name }&category=${pb.category}&currentPage=${pb.currentPage==1?1:pb.currentPage-1}">&lt;&lt;上一页</a></li>
										<li>第${pb.currentPage }页/共${pb.totallPage }页</li>
										<li class="nextPage"><a href="${pageContext.request.contextPath}/showBookPage?name=${pb.name }&category=${pb.category}&currentPage=${pb.currentPage==pb.totallPage?pb.totallPage:pb.currentPage+1}">&lt;&lt;下一页</a></li>
									</ul>
									</c:if>
								</div></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>



	<jsp:include page="foot.jsp" />


</body>
</html>
