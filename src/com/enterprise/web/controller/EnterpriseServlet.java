package com.enterprise.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.enterprise.utils.HttpUtils;

@WebServlet("/EnterpriseServlet")
public class EnterpriseServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String method = request.getParameter("method");
		if (method.equals("getAll")) {
			JSONObject jo = HttpUtils
					.sendGetMethod("http://localhost:8080/enforce/enterprise/getAll");
			if (jo != null) {
				
			}else{
				
			}
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
