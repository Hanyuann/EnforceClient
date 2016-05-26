package com.enterprise.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

public class HttpUtils {
	public static JSONObject sendGetMethod(String url) {
		GetMethod GetMethod = new GetMethod(
				"http://localhost:8080/enforce/enterprise/getAll");
		HttpClient client = new HttpClient();
		client.setConnectionTimeout(1000 * 60);
		int status = 0;
		try {
			status = client.executeMethod(GetMethod);
			if (status == HttpStatus.SC_OK) {
				String result = GetMethod.getResponseBodyAsString();
				return JSONObject.fromObject(result);
			} else {
				GetMethod.releaseConnection();
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
