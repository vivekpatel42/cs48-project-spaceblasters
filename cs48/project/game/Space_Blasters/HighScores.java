package cs48.project.game.Space_Blasters;

import java.awt.*;
import java.sql.*;
import java.util.Date;

/**
 * Created by Vivek on 5/7/2015.
 */
public class HighScores extends Canvas {

/*
    Not working right now! WIP
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "demo";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "";
        try {
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url + dbName, userName, password);
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery("SELECT * FROM event");
            while (res.next()) {
                int id = res.getInt("id");
                String msg = res.getString("msg");
                System.out.println(id + "\t" + msg);
            }
            int val = st.executeUpdate("INSERT into event VALUES(" + 1 + "," + "'Easy'" + ")");
            if (val == 1) System.out.print("Successfully inserted value");
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

*/

}
