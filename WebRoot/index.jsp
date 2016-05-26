<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>Enforce企业关系管理</title>
		<link type="text/css" rel="stylesheet"
			href="${pageContext.request.contextPath }/css/index.css">
	</head>

	<body>
		<div id="container">
			<div id="login">
				<div id="title"><p>Enforce企业关系管理</p></div>
				<div id="form">
					<form action="${pageContext.request.contextPath }/servlet/LoginServlet" method="post">
						<div id="input">
							<div id="text" class="inline">
								<input type="text" name="mc" value="${form.mc }"/>
							</div>
							<div id="btn" class="inline">
								<input type="submit" value=" " />
							</div>
						</div>
						<div id="error">
							<span>${form.error }</span>
						</div>
					</form>
				</div>
			</div>
		</div>
	</body>
</html>