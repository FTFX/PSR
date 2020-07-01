<%
    request.getSession().setAttribute("username", null);
    response.sendRedirect("login.jsp");
%>