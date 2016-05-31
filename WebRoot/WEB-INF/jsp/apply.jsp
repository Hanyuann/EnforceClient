<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
	function update(mc, mode) {
		location.href = '${pageContext.request.contextPath}/servlet/UpdateRelationServlet?mc2='
				+ encodeURI(encodeURI(mc)) + '&mode=' + mode;
	}
	function del(mc, mode) {
		if (window.confirm("您确认删除与 " + mc + " 的请求吗？")) {
			location.href = '${pageContext.request.contextPath}/servlet/DelRelationServlet?mc2='
					+ encodeURI(encodeURI(mc))
					+ '&mode='
					+ mode
					+ '&from=apply';
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
					<th width="170px">期望关系</th>
					<th width="170px">企业地址</th>
					<th width="170px">主要业务</th>
					<th width="170px">操作</th>
				</tr>
				<c:forEach var="c" items="${requestScope.supplyFrom }">
					<tr>
						<td>${c.mc }</td>
						<td>供应商</td>
						<td>${c.address }</td>
						<td>${c.business }</td>
						<td><a href="javascript:void(0)"
							onclick="update('${c.mc }','1')">同意</a>&nbsp&nbsp<a
							href="javascript:void(0)" onclick="del('${c.mc }','1')">删除</a></td>
					</tr>
				</c:forEach>
				<c:forEach var="c" items="${requestScope.supplyTo }">
					<tr>
						<td>${c.mc }</td>
						<td>供应接受商</td>
						<td>${c.address }</td>
						<td>${c.business }</td>
						<td><a href="javascript:void(0)"
							onclick="update('${c.mc }','2')">同意</a>&nbsp&nbsp<a
							href="javascript:void(0)" onclick="del('${c.mc }','2')">删除</a></td>
					</tr>
				</c:forEach>
				<c:forEach var="c" items="${requestScope.follower }">
					<tr>
						<td>${c.mc }</td>
						<td>子企业</td>
						<td>${c.address }</td>
						<td>${c.business }</td>
						<td><a href="javascript:void(0)"
							onclick="update('${c.mc }','3')">同意</a>&nbsp&nbsp<a
							href="javascript:void(0)" onclick="del('${c.mc }','3')">删除</a></td>
					</tr>
				</c:forEach>
				<c:forEach var="c" items="${requestScope.hasFollow }">
					<tr>
						<td>${c.mc }</td>
						<td>上级企业</td>
						<td>${c.address }</td>
						<td>${c.business }</td>
						<td><a href="javascript:void(0)"
							onclick="update('${c.mc }','4')">同意</a>&nbsp&nbsp<a
							href="javascript:void(0)" onclick="del('${c.mc }','4')">删除</a></td>
					</tr>
				</c:forEach>
			</table>
			<div id="alert">
				<span>${alert }</span>
			</div>
		</div>
	</div>
</body>
</html>
