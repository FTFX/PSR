<%@page import="java.sql.*"%>

<%
    request.setCharacterEncoding("utf-8");
    String username = request.getParameter("username");
    if(username.equals("admin")) {
        response.sendRedirect("admin.jsp");
    } else {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
	    String url = "jdbc:mysql://db:3306/course?useSSL=false";
        Connection connection = DriverManager.getConnection(url, "root", "root");
        Statement statement = connection.createStatement();
        statement.executeUpdate("DELETE FROM choose WHERE username='" + username + "'");
	    statement.executeUpdate("DELETE FROM user WHERE username='" + username + "'");
        response.sendRedirect("admin.jsp");
    }
%>