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

@WebServlet("/ApplyManaServlet")
public class ApplyManaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Enterprise enterprise = (Enterprise) request.getSession().getAttribute(
				"enterprise");
		String mc = URLEncoder.encode(enterprise.getMc(), "UTF-8");
		// System.out.println(request.getParameter("page"));
		GetMethod getMethod = new GetMethod(
				"http://localhost:8080/EnforceService/rest/enterprise/getReiationApply?mc="
						+ mc);
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

					Gson gson = new Gson();
					JSONArray enterprisesSupplyFrom = (JSONArray) jo
							.get("Enterprises_Supply_From");
					if (enterprisesSupplyFrom != null) {
					List<Enterprise> supplyFrom = new ArrayList<Enterprise>();
						for (int i = 0; i < enterprisesSupplyFrom.size(); i++) {
							Enterprise e = gson.fromJson(
									enterprisesSupplyFrom.getString(i),
									Enterprise.class);
							supplyFrom.add(e);
						}
						request.setAttribute("supplyFrom", supplyFrom);
					}
					JSONArray enterprisesSupplyTo = (JSONArray) jo
							.get("Enterprises_Supply_To");
					if (enterprisesSupplyTo != null) {
					List<Enterprise> supplyTo = new ArrayList<Enterprise>();
						for (int i = 0; i < enterprisesSupplyTo.size(); i++) {
							Enterprise e = gson.fromJson(
									enterprisesSupplyTo.getString(i),
									Enterprise.class);
							supplyTo.add(e);
						}
						request.setAttribute("supplyTo", supplyTo);
					}
					JSONArray enterprisesFollower = (JSONArray) jo
							.get("Enterprises_Follower");
					if (enterprisesFollower != null) {
					List<Enterprise> follower = new ArrayList<Enterprise>();
						for (int i = 0; i < enterprisesFollower.size(); i++) {
							Enterprise e = gson.fromJson(
									enterprisesFollower.getString(i),
									Enterprise.class);
							follower.add(e);
						}
						request.setAttribute("follower", follower);
					}
					JSONArray enterprisesHasFollow = (JSONArray) jo
							.get("Enterprises_Has_Follow");
					if (enterprisesHasFollow != null) {
					List<Enterprise> hasFollow = new ArrayList<Enterprise>();
						for (int i = 0; i < enterprisesHasFollow.size(); i++) {
							Enterprise e = gson.fromJson(
									enterprisesHasFollow.getString(i),
									Enterprise.class);
							hasFollow.add(e);
						}
						request.setAttribute("hasFollow", hasFollow);
					}
					request.setAttribute("alert", request.getAttribute("alert"));
					request.getRequestDispatcher("/WEB-INF/jsp/apply.jsp")
							.forward(request, response);
				} else {
					request.setAttribute("alert", "没有企业发来申请");
					request.getRequestDispatcher("/WEB-INF/jsp/apply.jsp")
							.forward(request, response);
				}
			} else {
				request.setAttribute("message", "没有连接到服务器！");
				request.getRequestDispatcher("/message.jsp").forward(request,
						response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "对不起，出错了！");
			request.getRequestDispatcher("/message.jsp").forward(request,
					response);
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
