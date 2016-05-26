<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
   "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<head>
		<title>My JSP 'left.jsp' starting page</title>
		<link type="text/css" rel="stylesheet"
			href="${pageContext.request.contextPath }/css/left.css">
	</head>

	<body>
		<div id="container">
			<p>
				<a class="a_demo_three" href="${pageContext.request.contextPath }/servlet/EnterpriseRelationServlet?method=getAllRelation&mode=1" target="main"> 业务企业 </a>
			</p>
			<p>
				<a class="a_demo_three second_button" href="${pageContext.request.contextPath }/servlet/MyCourseServlet?username=${username }" target="main"> 建立关系</a>
			</p>
			<p>
				<a class="a_demo_three third_button" href="${pageContext.request.contextPath }/servlet/ClassroomManaServlet?username=${username }" target="main"> 查看申请 </a>
			</p>
		</div>
	</body>
</html>
