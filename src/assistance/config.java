
package assistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class config {
    public static Connection connectDB() {
        Connection con = null;
        try {
            Class.forName("org.sqlite.JDBC"); 
            con = DriverManager.getConnection("jdbc:sqlite:assistance.db"); 
            System.out.println("Connection Successful");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Connection Failed: " + e);
        }
        return con;
    }
    public void addRecord(String sql, String... values) {
        try (Connection conn = config.connectDB(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

           
            for (int i = 0; i < values.length; i++) {
                pstmt.setString(i + 1, values[i]); 
            }

            pstmt.executeUpdate();
            System.out.println("Record added successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding record: " + e.getMessage());
        }
    }

    
}
