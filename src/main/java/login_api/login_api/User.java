package login_api.login_api;

import java.sql.*;

public class User {
    private String username;
    private String password;
    private String message;
    private String name;
    private String phone;

    public User() {
        this.username = "";
        this.password = "";
        this.message = "";
        this.name = "";
        this.phone = "";
    }
    public String login_check(String username, String password, String url, String server_id, String server_pw) {
        this.username = username;
        this.password = password;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
            
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection(url, server_id, server_pw);
            stmt = conn.prepareStatement("SELECT * FROM users");
            rs = stmt.executeQuery();
	     while (rs.next()) {
                if (username.equals(rs.getString("ID")) && password.equals(rs.getString("PW"))) {
                    this.message = "Login successful!";
                    this.name = rs.getString("name");
                    this.phone = rs.getString("phone");
                    // message ,name ,phone return
                    return this.message + " " + this.name + " " + this.phone;
                }
            }
	    this.message = "Login failed!";


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) { rs.close(); }
                if (stmt != null) { stmt.close(); }
                if (conn != null) { conn.close(); }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

      	return this.message;

    }

    public String getUsername() { return this.username; }
    public String getPassword() { return this.password; }
    public String getMessage() { return this.message; }

    public String getName() { return this.name; }
    public String getPhone() { return this.phone; }



    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setMessage(String message) { this.message = message; }


}

