<%@page import="java.sql.*"%>

<%
    request.setCharacterEncoding("utf-8");
    String name = request.getParameter("name");
    String time = request.getParameter("time");
    String location = request.getParameter("location");
    String teacher = request.getParameter("teacher");
    
	Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
	String url = "jdbc:mysql://db:3306/course?useSSL=false";
    Connection connection = DriverManager.getConnection(url, "root", "root");
    Statement statement = connection.createStatement();
    String sql = "INSERT INTO course (course,time,location,teacher) VALUES ('" + name + "', '" + time +"', '" + location + "', '" + teacher + "')";
	statement.executeUpdate(sql);
    response.sendRedirect("admin.jsp");
%>