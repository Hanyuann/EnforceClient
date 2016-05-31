<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath }/css/add.css">
</head>

<body>
	<div id="container">
		<form
			action="${pageContext.request.contextPath }/servlet/AddNewRelationServlet"
			method="post">
			<span class="info">请输入对方企业名称：</span> <input type="text" name="mc2"
				class="txt" value="${mc2 }" /> <span class="message">${errors.mc2 }</span>
			<br /> <span class="info">您想要成为对方的：</span> <select name="mode"
				class="txt">
				<option value="1" ${mode=='1'?'selected':'' }>供应商</option>
				<option value="2" ${mode=='2'?'selected':'' }>供应接受商</option>
				<option value="3" ${mode=='3'?'selected':'' }>子企业</option>
				<option value="4" ${mode=='4'?'selected':'' }>上级企业</option>
			</select> <span class="message">${errors.mode }</span>
			<div id="button">
				<input type="reset" value="重  置" class="myButton" /> <input
					type="submit" value="申  请" class="myButton" />
			</div>
			<div id="alert">
				<span>${alert }</span>
			</div>
		</form>
	</div>
</body>
</html>
