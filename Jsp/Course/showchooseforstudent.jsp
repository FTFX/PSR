<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<%
    request.setCharacterEncoding("utf-8");
    String cid = request.getParameter("cid");
%>

<html>
    <head>
        <title>选课结果</title>
    </head>
    <body>
        <sql:setDataSource var="database" 
            driver="com.mysql.cj.jdbc.Driver" 
            url="jdbc:mysql://db:3306/course?useSSL=false" 
            user="root"
            password="root"/>

        <sql:query dataSource="${database}" var="result">
            SELECT * FROM choose WHERE cid=<%= cid %>;
        </sql:query>
        <table border="1" width="100%">
        <tr>
            <th>学号</th>
        </tr>
        <c:forEach var="row" items="${result.rows}">
        <tr>
            <td><c:out value="${row.username}"/></td>
        </tr>
        </c:forEach>
    </body>
</html>
