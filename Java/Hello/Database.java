import java.sql.*;

public class Database {
    Connection connection = null;
    Statement statement = null;
    ResultSet resultset = null;

    public Database(String dbFile) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found.");
            e.printStackTrace();
        }
        try {
            String dbURL = "jdbc:ucanaccess://" + dbFile;
            connection = DriverManager.getConnection(dbURL);
            statement = connection.createStatement();
        } catch (Exception e) {
            System.out.println("Cannot access database.");
        }
    }

    public boolean changeUsername(String oldname, String newname) {
        if(validate(newname))
        {
            return false;
        }
        try {
            String sql = "update user set username = \"" + newname + "\" where username = \"" + oldname + "\"";
            statement.execute(sql);
            return true;
        } catch (Exception e) {
            System.out.println("Cannot change username.");
        }
        return false;
    }

    public boolean changePassword(String user, String password)
    {
        try {
            String sql = "update user set password = \"" + password + "\" where username = \"" + user + "\"";
            statement.execute(sql);
            return true;
        } catch (Exception e) {
            System.out.println("Cannot change password.");
        }
        return false;
    }

    public boolean validate(String name) {
        try {
            String sql = "select * from user where username = \"" + name + "\"";
            resultset = statement.executeQuery(sql);
            if (resultset.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Something went wrong while executing SQL.");
        }
        return false;
    }

    public boolean validate(String name, String password) {
        try {
            String sql = "select * from user where username = \"" + name + "\" and password = \"" + password + "\"";
            resultset = statement.executeQuery(sql);
            if (resultset.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Something went wrong while executing SQL.");
        }
        return false;
    }

    public boolean registry(String name, String password) {
        String ip = new String("127.0.0.1");
        if (validate(name)) {
            return false;
        }
        try {
            String sql = "insert into user (username, password, ip) values (\"" + name + "\", \"" + password + "\", \"" + ip + "\")";
            statement.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean delete(String name, String password) {
        if(!validate(name, password))
        {
            return false;
        }
        try {
            String sql = "delete from user where username = \"" + name + "\"";
            statement.execute(sql);
            return true;
        } catch (Exception e) {
            System.out.println("Failed to delete user.");
        }
        return false;
    }
}
