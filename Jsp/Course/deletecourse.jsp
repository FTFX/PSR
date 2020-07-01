<%@page import="java.sql.*"%>

<%
    request.setCharacterEncoding("utf-8");
    String cid = request.getParameter("cid");
    
	Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
	String url = "jdbc:mysql://db:3306/course?useSSL=false";
    Connection connection = DriverManager.getConnection(url, "root", "root");
    Statement statement = connection.createStatement();
    statement.executeUpdate("DELETE FROM choose WHERE cid='" + cid + "'");
	statement.executeUpdate("DELETE FROM course WHERE cid='" + cid + "'");
    response.sendRedirect("admin.jsp");
%>