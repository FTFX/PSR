<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%
    String user = "";
    if(request.getSession().getAttribute("username") == null) {
        response.sendRedirect("login.jsp");
    } else {
        if(request.getSession().getAttribute("username").toString().equals("admin")) {
            response.sendRedirect("admin.jsp");
        }
        user = request.getSession().getAttribute("username").toString();
    }
%>
<html>
    <head>
        <title>Course</title>
    </head>
    <body>
        <h2><%="Hello " + user%></h2>
        <a href="logout.jsp">退出</a><br><br>

        <sql:setDataSource var="database" 
            driver="com.mysql.cj.jdbc.Driver" 
            url="jdbc:mysql://db:3306/course?useSSL=false" 
            user="root"
            password="root"/>

        <sql:query dataSource="${database}" var="course">
            SELECT * FROM course;
        </sql:query>

        <sql:query dataSource="${database}" var="selected">
            SELECT * FROM choose WHERE username='<%= user %>';
        </sql:query>

        <form action="choose.jsp" method="POST">
        <input type="submit" value="确认选课"><br><br>
        <table border="1" width="100%">
        <tr>
            <th>课程选择</th>
            <th>课程名称</th>
            <th>上课时间</th>
            <th>上课地点</th>
            <th>任课教师</th>
        </tr>

        <c:forEach var="row" items="${course.rows}">
        <tr>
            <c:set var="checked" value="0"/>
            <c:forEach var="record" items="${selected.rows}">
                <c:if test="${record.cid == row.cid}">
                    <td><input type="checkbox" checked="checked" name="${row.cid}"></td>
                    <c:set var="checked" value="1"/>
                </c:if>
            </c:forEach>
            <c:if test="${checked == 0}">
                <td><input type="checkbox" name="${row.cid}"></td>
            </c:if>

            <td><c:out value="${row.course}"/></td>
            <td><c:out value="${row.time}"/></td>
            <td><c:out value="${row.location}"/></td>
            <td><c:out value="${row.teacher}"/></td>
        </tr>
        </c:forEach>
        </form>
        </table>

    </body>
</html>
