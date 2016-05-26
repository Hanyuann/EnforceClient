package com.enterprise.web.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import com.enterprise.bean.Enterprise;
import com.enterprise.utils.HttpUtils;
import com.google.gson.Gson;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Gson gson = new Gson();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String mc = request.getParameter("mc");
		PostMethod postMethod = new PostMethod(
				"http://localhost:8080/EnforceService/rest/enterprise/login");
		postMethod.getParams().setParameter(
				HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
		postMethod.addParameter("mc", mc);
		HttpClient client = new HttpClient();
		client.setConnectionTimeout(1000 * 60);
		int status = 0;
		try {
			status = client.executeMethod(postMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (status == HttpStatus.SC_OK) {
			String result = postMethod.getResponseBodyAsString();
			if (!result.equals("{}")) {
				Enterprise enterprise = gson.fromJson(result, Enterprise.class);
				System.out.println(enterprise.toString());
				request.getSession().setAttribute("enterprise", enterprise);
				request.getRequestDispatcher("/WEB-INF/jsp/enterprise.jsp")
						.forward(request, response);
			} else {
				Map<String, String> form = new HashMap<String, String>();
				form.put("mc", mc);
				form.put("error", "对不起，该企业不存在");
				request.setAttribute("form", form);
				request.getRequestDispatcher("/index.jsp").forward(request,
						response);
			}
		} else {
			postMethod.releaseConnection();
			Map<String, String> form = new HashMap<String, String>();
			form.put("mc", mc);
			form.put("error", "请求失败");
			request.setAttribute("form", form);
			request.getRequestDispatcher("/index.jsp").forward(request,
					response);
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
