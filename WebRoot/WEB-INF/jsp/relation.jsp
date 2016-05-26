<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
   "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath }/css/relation.css" />
<script type="text/javascript">
	function altRows(id) {
		if (document.getElementsByTagName) {
			var table = document.getElementById(id);
			var rows = table.getElementsByTagName("tr");
			for (i = 0; i < rows.length; i++) {
				if (i % 2 == 0) {
					rows[i].className = "evenrowcolor";
				} else {
					rows[i].className = "oddrowcolor";
				}
			}
		}
	}

	window.onload = function() {
		altRows('alternatecolor');
	}
</script>
<script type="text/javascript">
	function del(mc, mode) {
		if (window.confirm("您确认删除与 " + mc + " 的关系吗？")) {
			location.href = '${pageContext.request.contextPath}/servlet/DelEnterpriseRelationServlet?mc='
					+ mc + '&mode=' + mode;
		}
	}
</script>
<script type="text/javascript">
	function gotopage(mode, page, totalpage) {
		if (page < 1 || page > totalpage) {
			alert("无效的请求页码");
		} else {
			window.location.href = '${pageContext.request.contextPath}/servlet/EnterpriseRelationServlet?method=getAllRelation&mode='
					+ mode + '&page=' + page;
		}
	}
</script>
</head>
<body>
	<div id="container">
		<div id="table">
			<table class="altrowstable" id="alternatecolor">
				<tr>
					<th width="170px">企业名称</th>
					<th width="170px">企业关系</th>
					<th width="170px">企业地址</th>
					<th width="170px">主要业务</th>
					<th width="170px">操作</th>
				</tr>
				<c:forEach var="c" items="${requestScope.list }">
					<tr>
						<td>${c.mc }</td>
						<c:if test="${requestScope.mode==1 }">
							<td>供应商</td>
						</c:if>
						<c:if test="${requestScope.mode==2 }">
							<td>供应接受商</td>
						</c:if>
						<c:if test="${requestScope.mode==3 }">
							<td>子企业</td>
						</c:if>
						<c:if test="${requestScope.mode==4 }">
							<td>上级企业</td>
						</c:if>
						<td>${c.address }</td>
						<td>${c.business }</td>
						<td><a href="javascript:void(0)"
							onclick="del('${c.mc }','${requestScope.mode }')">删除企业关系</a></td>
					</tr>
				</c:forEach>
				<tr>
					<td id="add" colspan="5"><a
						href="${pageContext.request.contextPath }/servlet/EnterpriseRelationServlet?method=getAllRelation&mode=1">查看供应商</a>&nbsp;&nbsp;<a
						href="${pageContext.request.contextPath }/servlet/EnterpriseRelationServlet?method=getAllRelation&mode=2">查看供应接受商</a>&nbsp;&nbsp;<a
						href="${pageContext.request.contextPath }/servlet/EnterpriseRelationServlet?method=getAllRelation&mode=3">查看子企业</a>&nbsp;&nbsp;<a
						href="${pageContext.request.contextPath }/servlet/EnterpriseRelationServlet?method=getAllRelation&mode=4">查看上级企业</a></td>
				</tr>
			</table>
			<c:choose>
				<c:when test="${empty total }"></c:when>
				<c:otherwise>
					<div id="page">
						共[${total }]条记录, 共[${totalpage }]页, 当前第[${page }]页
						&nbsp;&nbsp;&nbsp; <a href="javascript:void(0)"
							onclick="gotopage(${mode },${page-1 },${totalpage })">上一页</a>
						<c:forEach var="pagenum" items="${pagebar}">
							<c:if test="${pagenum==page}">
								<font color="#0066CC">${pagenum }</font>
							</c:if>

							<c:if test="${pagenum!=page}">
								<a href="javascript:void(0)"
									onclick="gotopage(${mode},${pagenum },${totalpage })">${pagenum }</a>
							</c:if>
						</c:forEach>
						<a href="javascript:void(0)"
							onclick="gotopage(${mode } ,${page+1 },${totalpage })">下一页</a> <input
							type="text" id="pagenum" style="width: 30px"> <input
							type="button" value=" GO "
							onclick="gotopage(${mode},document.getElementById('pagenum').value,${totalpage })">
					</div>
				</c:otherwise>
			</c:choose>
			<div id="alert">
				<span>${alert }</span>
			</div>
		</div>
	</div>
</body>
</html>
