<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<HTML>
<HEAD>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%-- <LINK href="${pageContext.request.contextPath}/admin/css/Style.css"
	type="text/css" rel="stylesheet"> --%>
	<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery3.3.1.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<script language="javascript"
	src="${pageContext.request.contextPath}/js/public.js"></script>
<script language="javascript"
	src="${pageContext.request.contextPath}/js/check.js"></script>
<%String basePath=
request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
%>
</HEAD>
<body>
	<form id="userAction_save_do" name="Form1"
		action="${pageContext.request.contextPath }/admin/editBook" method="post"
		enctype="multipart/form-data">
		<input type="hidden" name="id" value="${book.id }"/>
		<table cellSpacing="1" cellPadding="5" width="100%" align="center"
			bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
			<tr>
				<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4"
					height="26"><strong><STRONG style="color: #fff">图书编辑</STRONG> </strong></td>
			</tr>


			<tr>
				<td align="center" bgColor="#B0E2FF" class="ta_01">图书名称：</td>
				<td class="ta_01" bgColor="#ffffff"><input type="text"
					name="name" class="bg" value="${book.name }" />${nameEmpty }</td>
				<td align="center" bgColor="#B0E2FF" class="ta_01">图书价格：</td>
				<td class="ta_01" bgColor="#ffffff"><input type="text"
					name="price" class="bg" value="${book.price }" oninput="value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')"/>${priceEmpty }</td>
			</tr>
			<tr>
				<td align="center" bgColor="#B0E2FF" class="ta_01">图书数量：</td>
				<td class="ta_01" bgColor="#ffffff"><input type="text"
					name="pnum" class="bg" value="${book.pnum }" oninput="value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')"/>${pnumEmpty }</td>
				<td align="center" bgColor="#B0E2FF" class="ta_01">图书类别：</td>
				<td class="ta_01" bgColor="#ffffff">
				
				<select name="category" 
					id="category">
						<option value="">--选择图书类别--</option>
						<option value="文学" <c:if test="${book.category=='文学' }">selected</c:if>>文学</option>
						<option value="生活" <c:if test="${book.category=='生活' }">selected</c:if>>生活</option>
						<option value="计算机" <c:if test="${book.category=='计算机' }">selected</c:if>>计算机</option>
						<option value="外语" <c:if test="${book.category=='外语' }">selected</c:if>>外语</option>
						<option value="经营" <c:if test="${book.category=='经营' }">selected</c:if>>经营</option>
						<option value="励志" <c:if test="${book.category=='励志' }">selected</c:if>>励志</option>
						<option value="社科" <c:if test="${book.category=='社科' }">selected</c:if>>社科</option>
						<option value="学术" <c:if test="${book.category=='学术' }">selected</c:if>>学术</option>
						<option value="少儿" <c:if test="${book.category=='少儿' }">selected</c:if>>少儿</option>
						<option value="艺术" <c:if test="${book.category=='艺术' }">selected</c:if>>艺术</option>
						<option value="原版" <c:if test="${book.category=='原版' }">selected</c:if>>原版</option>
						<option value="科技" <c:if test="${book.category=='科技' }">selected</c:if>>科技</option>
						<option value="考试" <c:if test="${book.category=='考试' }">selected</c:if>>考试</option>
						<option value="生活百科" <c:if test="${book.category=='生活百科' }">selected</c:if>>生活百科</option>
				</select>${categoryEmpty }</td>
			</tr>


			<tr>
				<td align="center" bgColor="#B0E2FF" class="ta_01">图书图片：</td>
				<td class="ta_01" bgColor="#ffffff" colSpan="3">
				<img alt="" src="<%=basePath%>${book.imgurl}"/>
				<input type="file" name="upload" size="30" value="" /></td>
			</tr>
			<TR>
				<TD class="ta_01" align="center" bgColor="#B0E2FF">图书描述：</TD>
				<TD class="ta_01" bgColor="#ffffff" colSpan="3"><textarea
						name="description" cols="30" rows="3" style="WIDTH: 96%">${book.description}</textarea>
				</TD>
			</TR>
			<TR>
				<td align="center" colSpan="4" class="sep1"><img
					src="${pageContext.request.contextPath}/images/admin/shim.gif">
				</td>
			</TR>


			<tr>
				<td class="ta_01" style="WIDTH: 100%" align="center"
					bgColor="#f5fafe" colSpan="4">
					
					<button type="submit" class="btn button_ok">确定</button> <FONT
					face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
					<button type="reset" class="btn button_cancel">重置</button> <FONT
					face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
					<button type="button" class="btn" onclick="history.go(-1)">返回</button>
					
				</td>
			</tr>
		</table>
	</form>




</body>
</HTML>