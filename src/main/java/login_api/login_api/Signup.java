package login_api.login_api;

import java.sql.*;

public class Signup {

    private String username;
    private String password;
    private String phone;
    private String name;
    private String message;

    public Signup() {
        this.username = "";
        this.password = "";
        this.phone = "";
        this.name = "";
        this.message = "";
    }

    public String Sign_up(String username, String password, String phone, String name, String url, String server_id, String server_pw) {
        this.username = username;
        this.phone = phone;
        this.name = name;
        Connection conn = null;
        PreparedStatement stmt = null;
        PreparedStatement query = null;
        ResultSet rs = null;            
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection(url, server_id, server_pw);
            stmt = conn.prepareStatement("SELECT * FROM users");
            rs = stmt.executeQuery();
            while (rs.next()) {
                if (username.equals(rs.getString("ID"))) {
                    this.message = this.username + " already exists!";
                    this.username = "";
                    this.password = "";
                    this.phone = "";
                    this.name = "";
                    return message;
                }
            }
            query = conn.prepareStatement("INSERT INTO users VALUES (?, ?, ?, ?)");
            query.setString(1, username);
            query.setString(2, password);
            query.setString(3, phone);
            query.setString(4, name);
            query.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) { rs.close(); }
                if (stmt != null) { stmt.close(); }
                if (conn != null) { conn.close(); }
                if (query != null) { query.close(); }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        this.message = "Signup successful!";
        return this.message;

    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getMessage() {
        return this.message;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getName() {
        return this.name;
    }



}
