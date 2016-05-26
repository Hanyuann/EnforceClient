package com.enterprise.web.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;

import com.enterprise.bean.Enterprise;
import com.google.gson.Gson;

@WebServlet("/EnterpriseRelationServlet")
public class EnterpriseRelationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final int PAGE_SIZE = 10;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		if (method.equals("getAllRelation")) {
			getAllRelation(request, response);
		}
	}

	private void getAllRelation(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Enterprise enterprise = (Enterprise) request.getSession().getAttribute(
				"enterprise");
		int page = 1;
		String mc = URLEncoder.encode(enterprise.getMc(), "UTF-8");
		String mode = request.getParameter("mode");
		//System.out.println(request.getParameter("page"));
		page = request.getParameter("page") == null ? 1 : Integer
				.parseInt(request.getParameter("page"));
		GetMethod getMethod = new GetMethod(
				"http://localhost:8080/EnforceService/rest/enterprise/getPartnersByEnterpriseMc?mc="
						+ mc + "&page=" + page + "&mode=" + mode);
		HttpClient client = new HttpClient();
		client.setConnectionTimeout(1000 * 60);
		int status = 0;
		try {
			status = client.executeMethod(getMethod);
			if (status == HttpStatus.SC_OK) {
				String result = getMethod.getResponseBodyAsString();
				// System.out.println(result);
				if (!result.equals("{}")) {
					JSONObject jo = JSONObject.fromObject(result);
					int total = (Integer) jo.get("total");
					request.setAttribute("mode", mode);
					request.setAttribute("page", page);
					request.setAttribute("total", total);

					int totalpage;
					if (total % PAGE_SIZE == 0) {
						totalpage = total / PAGE_SIZE;
					} else {
						totalpage = total / PAGE_SIZE + 1;
					}
					request.setAttribute("totalpage", totalpage);

					int[] pagebar = getPageBar(totalpage, page);
					request.setAttribute("pagebar", pagebar);

					JSONArray enterprises = (JSONArray) jo.get("Enterprises");
					Gson gson = new Gson();
					List<Enterprise> list = new ArrayList<Enterprise>();
					for (int i = 0; i < enterprises.size(); i++) {
						Enterprise e = gson.fromJson(enterprises.getString(i),
								Enterprise.class);
						list.add(e);
					}
					request.setAttribute("list", list);
					request.getRequestDispatcher("/WEB-INF/jsp/relation.jsp")
							.forward(request, response);
				} else {
					request.setAttribute("alert", "没有符合查询条件的企业");
					request.getRequestDispatcher("/WEB-INF/jsp/relation.jsp")
							.forward(request, response);
				}
			} else {
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "对不起，出错了！");
			request.getRequestDispatcher("/message.jsp").forward(request,
					response);
		}
	}

	private int[] getPageBar(int totalpage, int page) {
		int startpage;
		int endpage;
		int pagebar[] = null;
		if (totalpage <= 10) {
			pagebar = new int[totalpage];
			startpage = 1;
			endpage = totalpage;
		} else {
			pagebar = new int[10];
			startpage = page - 4;
			endpage = page + 5;

			// 总页数=30 3 -1
			// 总页数=30 29 34 21 30
			if (startpage < 1) {
				startpage = 1;
				endpage = 10;
			}

			if (endpage > totalpage) {
				endpage = totalpage;
				startpage = totalpage - 9;
			}
		}

		int index = 0;
		for (int i = startpage; i <= endpage; i++) {
			pagebar[index++] = i;
		}

		return pagebar;
		/*
		 * int pagebar[] = new int[this.totalpage]; for(int
		 * i=1;i<=this.totalpage;i++){ pagebar[i-1] = i; } this.pagebar =
		 * pagebar; return pagebar;
		 */
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
