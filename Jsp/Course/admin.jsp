<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%
    if(request.getSession().getAttribute("username") == null) {
        response.sendRedirect("login.jsp");
    } else {
        if(!request.getSession().getAttribute("username").toString().equals("admin")) {
            response.sendRedirect("index.jsp");
        }
    }
%>
<html>
    <head>
        <title>Admin</title>
    </head>
    <body>
        <sql:setDataSource var="database" 
            driver="com.mysql.cj.jdbc.Driver" 
            url="jdbc:mysql://db:3306/course?useSSL=false" 
            user="root"
            password="root"/>
        
        <h3>课程</h3>
        <sql:query dataSource="${database}" var="course">
            SELECT * FROM course;
        </sql:query>
        <sql:query dataSource="${database}" var="choose">
            SELECT * FROM choose;
        </sql:query>
        <table border="1" width="100%">
        <tr>
            <th>课程名称</th>
            <th>上课时间</th>
            <th>上课地点</th>
            <th>任课教师</th>
            <th>选课人数</th>
            <th colspan="2">操作</th>
        </tr>
        <c:forEach var="row" items="${course.rows}">
        <tr>
            <td><c:out value="${row.course}"/></td>
            <td><c:out value="${row.time}"/></td>
            <td><c:out value="${row.location}"/></td>
            <td><c:out value="${row.teacher}"/></td>
            <td>
                <c:set var="choosecount" value="0"/>
                <c:forEach var="chooserow" items="${choose.rows}">
                    <c:if test="${chooserow.cid == row.cid}">
                        <c:set var="choosecount" value="${choosecount + 1}"/>
                    </c:if>
                </c:forEach>
                <a href="showchooseforstudent.jsp?cid=${row.cid}"><c:out value="${choosecount}"/></a>
            </td>
            <td><a href="modifycourseui.jsp?cid=${row.cid}">修改</a></td>
            <td><a href="deletecourse.jsp?cid=${row.cid}">删除</a></td>
        </tr>
        </c:forEach>
        <form action="addcourse.jsp" method="POST">
        <tr>
            <td><input type="text" name="name"></td>
            <td><input type="text" name="time"></td>
            <td><input type="text" name="location"></td>
            <td><input type="text" name="teacher"></td>
            <td>N/A</td>
            <td colspan="2"><input type="submit" value="添加"></td>
        </tr>
        </form>
        </table>

        <h3>学生</h3>
        <sql:query dataSource="${database}" var="user">
            SELECT * FROM user;
        </sql:query>
        <table border="1" width="100%">
        <tr>
            <th>学号</th>
            <th>密码</th>
            <th>已选课程</th>
            <th colspan="2">操作</th>
        </tr>
        <c:set var="admin" value="admin"/>
        <c:forEach var="row" items="${user.rows}">
        <c:if test="${row.username != admin}">
        <tr>
            <td><c:out value="${row.username}"/></td>
            <td><c:out value="${row.password}"/></td>
            <td>
                <c:set var="choosecount" value="0"/>
                <c:forEach var="chooserow" items="${choose.rows}">
                    <c:if test="${chooserow.username == row.username}">
                        <c:set var="choosecount" value="${choosecount + 1}"/>
                    </c:if>
                </c:forEach>
                <a href="showchooseforcourse.jsp?username=${row.username}"><c:out value="${choosecount}"/></a>
            </td>
            <td><a href="modifyuserui.jsp?username=${row.username}">修改</a></td>
            <td><a href="deleteuser.jsp?username=${row.username}">删除</a></td>
        </tr>
        </c:if>
        </c:forEach>
        <form action="adduser.jsp" method="POST">
        <tr>
            <td><input type="text" name="username"></td>
            <td><input type="text" name="password"></td>
            <td colspan="2"><input type="submit" value="添加"></td>
        </tr>
        <form>
        </table>
        <br><a href="modifyuserui.jsp?username='admin'">修改密码</a>  <a href="logout.jsp">退出</a><br>
    </body>
</html>
