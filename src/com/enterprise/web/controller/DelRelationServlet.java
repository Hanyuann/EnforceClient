package com.enterprise.web.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.DeleteMethod;

import com.enterprise.bean.Enterprise;

@WebServlet("/DelRelationServlet")
public class DelRelationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Enterprise enterprise = (Enterprise) request.getSession().getAttribute(
				"enterprise");
		String mc = URLEncoder.encode(enterprise.getMc(), "UTF-8");
		String mc2 = URLEncoder.encode(
				URLDecoder.decode(request.getParameter("mc2"), "UTF-8"),
				"UTF-8");
		String from = request.getParameter("from");
		int mode = Integer.parseInt(request.getParameter("mode"));
		DeleteMethod deleteMethod = new DeleteMethod(
				"http://localhost:8080/EnforceService/rest/enterprise/deleteEnterpriseRelation?mc="
						+ mc + "&mc2=" + mc2 + "&mode=" + mode);
		HttpClient client = new HttpClient();
		client.setConnectionTimeout(1000 * 60);
		int status = 0;
		try {
			status = client.executeMethod(deleteMethod);
			if (status == HttpStatus.SC_OK) {
				String result = deleteMethod.getResponseBodyAsString();
				// System.out.println(result);
				if (!result.equals("{}")) {
					JSONObject jo = JSONObject.fromObject(result);
					String message = (String) jo.get("message");
					if (message.equals("ok")) {
						request.setAttribute("alert", "删除成功");
					}
				} else {
					request.setAttribute("alert", "删除失败");
				}
				if (from.equals("apply")) {
					request.getRequestDispatcher("/servlet/ApplyManaServlet")
							.forward(request, response);
				} else {
					request.getRequestDispatcher(
							"/servlet/EnterpriseRelationServlet").forward(
							request, response);
				}
			}
		} catch (Exception e) {
			request.setAttribute("message", "没有连接到服务器！");
			request.getRequestDispatcher("/message.jsp").forward(request,
					response);
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
