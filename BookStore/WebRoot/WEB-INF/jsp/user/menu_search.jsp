<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery3.3.1.js"></script>

<script type="text/javascript">
	/* $().ready(function(){
		$("#showSearchBooks").hide();
		//鼠标悬浮事件
		function changeBackground_Over(childDiv){
			childDiv.style.backgroundColor="gray";
		}
		//鼠标移开事件
		function changeBackground_Out(childDiv){
			childDiv.style.backgroundColor="white";
		}
		//填充搜索里面的文字
		function Go_Up(childDiv){
			var searchDiv=document.getElementById("name");
			searchDiv.value=childDiv.innerHTML;
			childDiv.parentNode.style.display="none"; //隐藏大的div
		} 
		$("#name").keyup(function(){//键盘弹起触发
			var bname=this.value;
			if(bname==""){
				$("#showSearchBooks").hide();
				return;
			}
			//发起请求
			$.ajax({
				url:"${pageContext.request.contextPath}/searchBookNameLike",
				data:{"name":bname},
				async:true,
				type:"POST",
				success:function(result){
					var books=result.split(",");		
					var childDiv="";
					for(var i=0;i<books.length;i++){
						childDiv+="<div onmouseover=changeBackground_Over(this) onmouseout=changeBackground_Out(this) onclick=Go_Up(this)>"+books[i]+"</div>";
					}	
					document.getElementById("showSearchBooks").innerHTML=childDiv;    //把小的div封装到大的div里面
					$("#showSearchBooks").show();
				},
				error: function(XMLHttpRequest, textStatus, errorThrown){
					console.log(errorThrown);
				}
			});
		});
		
	});
 */
</script>

<div id="divmenu">
	<a
		href="${pageContext.request.contextPath}/showBookPage?category=文学">文学</a>
	<a
		href="${pageContext.request.contextPath}/showBookPage?category=生活">生活</a>
	<a
		href="${pageContext.request.contextPath}/showBookPage?category=计算机">计算机</a>
	<a
		href="${pageContext.request.contextPath}/showBookPage?category=外语">外语</a>
	<a
		href="${pageContext.request.contextPath}/showBookPage?category=经营">经管</a>
	<a
		href="${pageContext.request.contextPath}/showBookPage?category=励志">励志</a>
	<a
		href="${pageContext.request.contextPath}/showBookPage?category=社科">社科</a>
	<a
		href="${pageContext.request.contextPath}/showBookPage?category=学术">学术</a>
	<a
		href="${pageContext.request.contextPath}/showBookPage?category=少儿">少儿</a>
	<a
		href="${pageContext.request.contextPath}/showBookPage?category=艺术">艺术</a>
	<a
		href="${pageContext.request.contextPath}/showBookPage?category=原版">原版</a>
	<a
		href="${pageContext.request.contextPath}/showBookPage?category=科技">科技</a>
	<a
		href="${pageContext.request.contextPath}/showBookPage?category=考试">考试</a>
	<a
		href="${pageContext.request.contextPath}/showBookPage?category=生活百科">生活百科</a>
	<a href="${pageContext.request.contextPath}/showBookPage"
		style="color:#FFFF00">全部商品目录</a>
</div>
<div id="divsearch">
	<form action="${pageContext.request.contextPath}/showBookPage"
		method="post">
		<table width="100%" border="0" cellspacing="0">
			<tr>
				<td style="text-align:right; padding-right:220px">
				Search <input type="text" name="name" class="inputtable" id="name" /> 
					<input type="image" src="images/serchbutton.gif"
					border="0" style="margin-bottom:-4px">
				</td>
			</tr>
		</table>
	</form>
	
</div>

<!-- <div id="showSearchBooks" style="border:1px solid;width:129px;position:absolute;left:900px;top:140px;background-color:white">

</div> -->



