<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<HTML>
<HEAD>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/css/Style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery3.3.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	function addProduct() {
		window.location.href = "${pageContext.request.contextPath}/admin/showBookAddUI";
	}
	function deleteBook(id,name){
		if(window.confirm("您确定要删除 《"+name+"》 吗?")){
			window.location.href="${pageContext.request.contextPath}/admin/deleteBookById?id="+id;
		}
	}
	$().ready(function(){
		$("#btn_pre").click(function(){
			window.location.href="${pageContext.request.contextPath}/admin/showBookList?id=${pb.id}&minprice=${pb.minprice}&maxprice=${maxprice}&name=${pb.name }&category=${pb.category}&currentPage=${pb.currentPage==1?1:pb.currentPage-1}";
		});
		$("#btn_next").click(function(){
			window.location.href="${pageContext.request.contextPath}/admin/showBookList?id=${pb.id}&minprice=${pb.minprice}&maxprice=${maxprice}&name=${pb.name }&category=${pb.category}&currentPage=${pb.currentPage==pb.totallPage?pb.totallPage:pb.currentPage+1}";
		});
		$("#search").click(function(){
			$("#Form1").submit();
		});
		
	});
</script>
</HEAD>
<body>
	<form id="Form1" name="Form1"
		action="${pageContext.request.contextPath}/admin/showBookList"
		method="post">
		<table cellSpacing="1" cellPadding="0" width="100%" align="center"
			bgColor="#f5fafe" border="0">
			<TBODY>
				<tr height="40">
					<td  class="ta_01" align="center" bgColor="#afd1f3"><strong style="color: #fff">查
							询 条 件</strong>
					</td>
				</tr>
				<tr>
					<td>
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
							<tr>
								<td height="22" align="center" bgColor="#f5fafe" class="ta_01">
									图书编号:</td>
								<td class="ta_01" bgColor="#ffffff"><input type="text"
									name="id" size="15" value="${pb.id }" id="Form1_userName" class="bg" />
								</td>
								<td height="22" align="center" bgColor="#f5fafe" class="ta_01">
									类别：</td>
								<td class="ta_01" bgColor="#ffffff"><select name="category"
									id="category">
										<option value="" selected="selected">--选择图书类别--</option>
										<option value="文学" <c:if test="${pb.category=='文学' }">selected</c:if>>文学</option>
										<option value="生活" <c:if test="${pb.category=='生活' }">selected</c:if>>生活</option>
										<option value="计算机" <c:if test="${pb.category=='计算机' }">selected</c:if>>计算机</option>
										<option value="外语" <c:if test="${pb.category=='外语' }">selected</c:if>>外语</option>
										<option value="经营" <c:if test="${pb.category=='经营' }">selected</c:if>>经营</option>
										<option value="励志" <c:if test="${pb.category=='励志' }">selected</c:if>>励志</option>
										<option value="社科" <c:if test="${pb.category=='社科' }">selected</c:if>>社科</option>
										<option value="学术" <c:if test="${pb.category=='学术' }">selected</c:if>>学术</option>
										<option value="少儿" <c:if test="${pb.category=='少儿' }">selected</c:if>>少儿</option>
										<option value="艺术" <c:if test="${pb.category=='艺术' }">selected</c:if>>艺术</option>
										<option value="原版" <c:if test="${pb.category=='原版' }">selected</c:if>>原版</option>
										<option value="科技" <c:if test="${pb.category=='科技' }">selected</c:if>>科技</option>
										<option value="考试" <c:if test="${pb.category=='考试' }">selected</c:if>>考试</option>
										<option value="生活百科" <c:if test="${pb.category=='生活百科' }">selected</c:if>>生活百科</option>
								</select></td>
							</tr>

							<tr>
								<td height="22" align="center" bgColor="#f5fafe" class="ta_01">
									图书名称:</td>
								<td class="ta_01" bgColor="#ffffff"><input type="text"
									name="name" size="15" value="${pb.name }" id="Form1_userName" class="bg" />
								</td>
								<td height="22" align="center" bgColor="#f5fafe" class="ta_01">
									价格区间(元)：</td>
								<td class="ta_01" bgColor="#ffffff"><input type="text"
									name="minprice" size="10" value="${pb.minprice }" oninput="value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')"/>- <input type="text"
									name="maxprice" size="10" value="${pb.maxprice }" oninput="value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')"/></td>
							</tr>

							<tr>
								<td width="100" height="22" align="center" bgColor="#f5fafe"
									class="ta_01"></td>
								<td class="ta_01" bgColor="#ffffff"><font face="宋体"
									color="red"> &nbsp;</font>
								</td>
								<td align="right" bgColor="#ffffff" class="ta_01"><br>
									<br></td>
								<td align="right" bgColor="#ffffff" class="ta_01">
										<button type="button" class="btn button_view" id="search">查询</button>
										 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
										 <button type="reset" class="btn button_view" id="reset">重置</button>
								</td>
							</tr>
						</table>
					</td>

				</tr>
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3"><strong>图书列表</strong>
					</TD>
				</tr>
				<tr>
					<td class="ta_01" align="right">
						<button type="button" class="btn button_add" id="add" onclick="addProduct()">添加</button>
					</td>
				</tr>
				<tr>
					<td class="ta_01" align="center" bgColor="#f5fafe">
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<tr
								style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
								<td align="center" width="20%">图书编号</td>
								<td align="center" width="15%">图书名称</td>
								<td align="center" width="10%">图书价格</td>
								<td align="center" width="10%">图书数量</td>
								<td width="10%" align="center">图书类别</td>
								<td width="10%" align="center">编辑</td>

								<td width="10%" align="center">删除</td>
							</tr>

						<c:forEach items="${pb.books}" var="book" varStatus="vs">
								<tr onmouseover="this.style.backgroundColor = 'white'"
									onmouseout="this.style.backgroundColor = '#F5FAFE';">
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="20">${book.id }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="18%">${book.name }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="8%">${book.price }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="8%">${book.pnum }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center">
										${book.category }</td>
									<td align="center" style="HEIGHT: 22px" width="8%">
									<a href="${pageContext.request.contextPath }/admin/showEditBookUI?id=${book.id}">
										<img src="${pageContext.request.contextPath}/images/admin/i_edit.gif"
											border="0" style="CURSOR: hand"> </a>
									</td>
									<td align="center" style="HEIGHT: 22px" width="8%">
									<a href="javascript:void(0)" onclick="deleteBook('${book.id}','${book.name }')">
										<img src="${pageContext.request.contextPath}/images/admin/i_del.gif"
											width="16" height="16" border="0" style="CURSOR: hand">
									</a>
									</td>
								</tr>
							</c:forEach>
						</table>
					</td>
				</tr>
				
				<c:if test="${pb.books.size()!=0 }">
					<tr>
					<td align="center" class="ta_01">
						<button type="button" class="btn" id="btn_pre">上一页</button>
						&nbsp;${pb.currentPage }&nbsp;/&nbsp;${pb.totallPage }&nbsp;
						<button type="button" class="btn" id="btn_next">下一页</button>
					</td>
				</tr>
				</c:if>
				
			</TBODY>
		</table>
	</form>
</body>
</HTML>

