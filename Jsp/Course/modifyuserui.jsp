<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<%
    request.setCharacterEncoding("utf-8");
    String username = request.getParameter("username");
%>

<html>
    <head>
        <title>修改课程</title>
    </head>
    <body>
        <sql:setDataSource var="database" 
            driver="com.mysql.cj.jdbc.Driver" 
            url="jdbc:mysql://db:3306/course?useSSL=false" 
            user="root"
            password="root"/>

        <sql:query dataSource="${database}" var="result">
            SELECT * FROM user WHERE username='<%= username %>';
        </sql:query>

        <c:forEach var="row" items="${result.rows}">
        <form action="modifyuser.jsp" method="POST">
            学号:<input type="text" name="username" value="${row.username}" readonly="true"><br>
            密码:<input type="text" name="password" value="${row.password}"><br>
            <input type="submit" value="修改"><br>
        </form>
        </c:forEach>
    </body>
</html>
