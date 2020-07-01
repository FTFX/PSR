<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<%
    request.setCharacterEncoding("utf-8");
    String username = request.getParameter("username");
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

        <sql:query dataSource="${database}" var="chooseresult">
            SELECT * FROM choose WHERE username='<%= username %>';
        </sql:query>
        <table border="1" width="100%">
        <tr>
            <th>课程</th>
        </tr>
        <c:forEach var="chooserow" items="${chooseresult.rows}">
            <sql:query dataSource="${database}" var="course">
                SELECT * FROM course WHERE cid=${chooserow.cid};
            </sql:query>
            <tr>
                <td>
                    <c:forEach var="courserow" items="${course.rows}">
                        <c:out value="${courserow.course}"/>
                    </c:forEach>
                </td>
            </tr>
        </c:forEach>
    </body>
</html>
