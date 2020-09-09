<%@page import="java.sql.*"%>

<%
    request.setCharacterEncoding("utf-8");
    String username = request.getSession().getAttribute("username").toString();
    
	Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
	String url = "jdbc:mysql://db:3306/course?useSSL=false";
    Connection connection = DriverManager.getConnection(url, "root", "root");
    Statement coursestatement = connection.createStatement();
    Statement choosestatement = connection.createStatement();
	ResultSet course = coursestatement.executeQuery("SELECT * FROM course");
    ResultSet choose = choosestatement.executeQuery("SELECT * FROM choose WHERE username='" + username + "'"); // Make sure only select the user

    int flag = 0;

    System.out.println(choose);
    while(course.next()) {
        flag = 0;
        // If a selection is ON
        if(request.getParameter(course.getString("cid")) != null) {
            // Check it if it's selected or not
            while(choose.next()) {
                if(choose.getString("cid").equals(course.getString("cid"))) {
                    flag = 1;
                    break;
                }
            }
            // If the course does not exist in choose table
            if(flag == 0) {
                // Then add it.
                connection.createStatement().execute("INSERT INTO choose (username,cid) VALUES ('" + username + "', '" + course.getString("cid") +"')");
            }
        } else {
            // If a selection is OFF
            // Make sure it's not selected
            System.out.println("SELECTION IS OFF");
            while(choose.next()) {
                System.out.println(choose.getString("cid") + ":" + course.getString("cid"));
                if(choose.getString("cid").equals(course.getString("cid"))) {
                    
                    // It means the selection exist in the choose table and we need to delete it
                    connection.createStatement().execute("DELETE FROM choose WHERE username='" + username + "' AND cid='" + course.getString("cid") + "'");
                }
            }
        }
        choose.beforeFirst();
    }

    System.out.println("333");
    choose.close();
    choosestatement.close();
    course.close();
    coursestatement.close();
    connection.close();

    response.sendRedirect("index.jsp");
%>
