<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    if(!(request.getSession().getAttribute("username") == null)) {
        if(request.getSession().getAttribute("username").toString().equals("admin")) {
            response.sendRedirect("admin.jsp");
        } else {
            response.sendRedirect("index.jsp");
        }
    }
%>
<html>
    <head>
        <title>Login</title>
    </head>
    <body>
        <form action="authentication.jsp" method="POST">
            Username: <br>
            <input type="text" name="uid"><br>
            Password: <br>
            <input type="text" name="pwd"><br>
            <br><input type="submit" value="登录">
        </form>
        <%
            if(request.getParameter("info") != null) {
                out.println("密码错误<br>");
            }
        %>
    </body>
</html>