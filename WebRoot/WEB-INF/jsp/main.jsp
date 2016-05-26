<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<link type="text/css" rel="stylesheet"
			href="${pageContext.request.contextPath }/css/main.css">
	</head>

	<body>
		<div id="welcome">
			<span>欢迎您, ${enterprise.mc }</span><br>
			<span>企业地址：${enterprise.address }</span><br>
			<span>主营业务：${enterprise.business }</span><br>
			<span>企业标准：${enterprise.biaozhun }</span><br>
			<span>信息来源：${enterprise.source }</span><br>
			<span>企业状态：${enterprise.status }</span><br>
			<span>创建日期：${enterprise.createTime }</span><br>
			<span>最后修改：${enterprise.lastModifyTime }</span><br>
		</div>
	</body>
</html>
