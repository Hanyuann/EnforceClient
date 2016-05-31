package com.enterprise.web.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import com.enterprise.bean.Enterprise;

@WebServlet("/AddNewRelationServlet")
public class AddNewRelationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Enterprise enterprise = (Enterprise) request.getSession().getAttribute(
				"enterprise");
		String mc2 = request.getParameter("mc2");
		String mode = request.getParameter("mode");
		PostMethod postMethod = new PostMethod(
				"http://localhost:8080/EnforceService/rest/enterprise/sendRelationApply");
		postMethod.getParams().setParameter(
				HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
		postMethod.addParameter("mc", enterprise.getMc());
		postMethod.addParameter("mc2", mc2);
		postMethod.addParameter("mode", mode);
		HttpClient client = new HttpClient();
		client.setConnectionTimeout(1000 * 60);
		int status = 0;
		try {
			status = client.executeMethod(postMethod);
			if (status == HttpStatus.SC_OK) {
				String result = postMethod.getResponseBodyAsString();
				// System.out.println(result);
				if (!result.equals("{}")) {
					JSONObject jo = JSONObject.fromObject(result);
					String message = (String) jo.get("message");
					request.setAttribute("mc2", mc2);
					request.setAttribute("mode", mode);

					Map<String, String> errors = new HashMap<String, String>();
					if (message.equals("no")) {
						errors.put("mc2", "����ҵ�����ڣ�");
						request.setAttribute("errors", errors);
						request.getRequestDispatcher("/WEB-INF/jsp/add.jsp")
								.forward(request, response);
					} else if (message.equals("send")) {
						errors.put("mode", "�����ѷ��͹�����ȴ��Է�ȷ�ϣ�");
						request.setAttribute("errors", errors);
						request.getRequestDispatcher("/WEB-INF/jsp/add.jsp")
								.forward(request, response);
					}  else if (message.equals("exist")) {
						errors.put("mode", "����ҵ��ϵ�Ѿ����ڣ�");
						request.setAttribute("errors", errors);
						request.getRequestDispatcher("/WEB-INF/jsp/add.jsp")
								.forward(request, response);
					} else if (message.equals("ok")) {
						request.setAttribute("alert", "�����ͳɹ�");
						request.getRequestDispatcher(
								"/WEB-INF/jsp/add.jsp").forward(request,
								response);
					}
				} else {
					request.setAttribute("alert", "û�з��ϲ�ѯ��������ҵ");
					request.getRequestDispatcher(
							"/servlet/EnterpriseRelationServlet?mode=" + mode)
							.forward(request, response);
				}
			} else {
				request.setAttribute("message", "û�����ӵ���������");
				request.getRequestDispatcher("/message.jsp").forward(request,
						response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "�Բ��𣬳����ˣ�");
			request.getRequestDispatcher("/message.jsp").forward(request,
					response);
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
