<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
<title>电子书城</title>
<link rel="stylesheet" href="css/main.css" type="text/css" />
</head>


<body class="main">
	<jsp:include page="head.jsp" />
	<jsp:include page="menu_search.jsp" />

	<div id="divpagecontent">
		<table width="100%" border="0" cellspacing="0">
			<tr>

				<td><div style="text-align:right; margin:5px 10px 5px 0px">
						<a href="${pageContext.request.contextPath }/">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;订单详细信息
					</div>



					<table cellspacing="0" class="infocontent">
						<tr>
							<td>
								<table width="100%" border="0" cellspacing="0">
									<tr>
										<td>
											<p>订单编号:${order.id}</p></td>
									</tr>
									<tr>
										<td>
											<table cellspacing="1" class="carttable">
												<tr>
													<td width="10%">序号</td>
													<td width="40%">商品名称</td>
													<td width="10%">价格</td>
													<td width="10%">数量</td>
													<td width="10%">小计</td>

												</tr>
											</table>
											<c:set var="totalMoney" value="0"></c:set>
									<c:forEach items="${order.orderItems }" var="o" varStatus="vs">
											<table width="100%" border="0" cellspacing="0">
												<tr>
													<td width="10%">${vs.count }</td>
													<td width="40%">${o.book.name }</td>
													<td width="10%">${o.book.price }</td>
													<td width="10%">${o.buynum }</td>
													<td width="10%">${o.buynum * o.book.price }</td>
													<c:set var="totalMoney" value="${totalMoney+o.buynum*o.book.price }"></c:set>
												</tr>
											</table>
										</c:forEach>
											

											<table cellspacing="1" class="carttable">
												<tr>
													<td style="text-align:right; padding-right:40px;"><font
														style="color:#FF0000">合计：&nbsp;&nbsp;${totalMoney }</font></td>
												</tr>
											</table>

											<p>
												收货地址：${order.receiverAddress }&nbsp;&nbsp;&nbsp;&nbsp;<br />
												收货人：&nbsp;&nbsp;&nbsp;&nbsp;${order.receiverName }&nbsp;&nbsp;&nbsp;&nbsp;<br />
												联系方式：${order.receiverPhone }&nbsp;&nbsp;&nbsp;&nbsp;

											</p>
											<hr>
											<p style="text-align:right">
											<c:if test="${order.paystate==0 }">
												<a href="${pageContext.request.contextPath }/showPayUI?orderid=${order.id}&money=${totalMoney}"><img src="images/forPay.png" width="204"
													height="51" border="0" /> </a>
											</c:if>
											</p>
										</td>
									</tr>
								</table>
							</td>


						</tr>


					</table>
				</td>
			</tr>
		</table>
	</div>



	<jsp:include page="foot.jsp" />


</body>
</html>
