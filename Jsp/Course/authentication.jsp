<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>

<%
    request.setCharacterEncoding("utf-8");
    String username = request.getParameter("uid");
    String password = request.getParameter("pwd");
    
	Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
	String url = "jdbc:mysql://db:3306/course?useSSL=false";
    Connection connection = DriverManager.getConnection(url, "root", "root");
    Statement statement = connection.createStatement();
    String sql = "SELECT * FROM user WHERE username = '" + username + "' AND password = '" + password + "'";
	ResultSet rs = statement.executeQuery(sql);
	if (rs.next()) {
        if(username.equals("admin")) {
            session.setAttribute("username", username);
            response.sendRedirect("admin.jsp");
        } else {
            session.setAttribute("username", username);
		    response.sendRedirect("index.jsp");
        }
	}else{
		response.sendRedirect("login.jsp?info=1");
	}
%>

