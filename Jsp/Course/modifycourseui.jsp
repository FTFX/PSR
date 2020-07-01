<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<%
    request.setCharacterEncoding("utf-8");
    String cid = request.getParameter("cid");
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
            SELECT * FROM course WHERE cid=<%= cid %>;
        </sql:query>

        <c:forEach var="row" items="${result.rows}">
        <form action="modifycourse.jsp" method="POST">
            课程编号:<input type="text" name="cid" value="<%= cid %>" readonly="true"><br>
            课程名称:<input type="text" name="name" value="${row.course}"><br>
            上课时间:<input type="text" name="time" value="${row.time}"><br>
            上课地点:<input type="text" name="location" value="${row.location}"><br>
            任课教师:<input type="text" name="teacher" value="${row.teacher}"><br>
            <input type="submit" value="修改"><br>
        </form>
        </c:forEach>
    </body>
</html>
