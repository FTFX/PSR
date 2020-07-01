<%@page import="java.sql.*"%>

<%
    request.setCharacterEncoding("utf-8");
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    
	Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
	String url = "jdbc:mysql://db:3306/course?useSSL=false";
    Connection connection = DriverManager.getConnection(url, "root", "root");
    Statement statement = connection.createStatement();
    String sql = "INSERT INTO user (username,password) VALUES ('" + username + "', '" + password +"')";
	statement.executeUpdate(sql);
    response.sendRedirect("admin.jsp");
%>