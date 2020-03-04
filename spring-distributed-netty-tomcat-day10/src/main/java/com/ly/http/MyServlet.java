package com.ly.http;


public class MyServlet extends Servlet{

	@Override
	public void doGet(Request request, Response response) {
		
		try {
			response.write(request.getParameter("name"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doPost(Request request, Response response) {
		doGet(request, response);
	}


}
