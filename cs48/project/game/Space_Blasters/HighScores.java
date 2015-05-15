package cs48.project.game.Space_Blasters;

import com.sun.prism.*;

import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.io.*;
import java.sql.*;
import java.util.*;

/**
 * Created by Vivek on 5/7/2015.
 */
public class HighScores extends JPanel {

    private ArrayList<String> names;
    private ArrayList<Long> scores;
    private String[][] tableData;
    private JLabel scoreLabel;

    /**
     * Default no-arg constructor for HighScore JPanel
     */
    public HighScores() {
        super();
        names = new ArrayList<String>();
        scores = new ArrayList<Long>();
        tableData = new String[10][2];
        readFile();
        String[] columnNames = {"Names", "Scores"};
        JTable scoreTable = new JTable(tableData, columnNames);
        scoreTable.setPreferredScrollableViewportSize(new Dimension(500, 70));
        scoreTable.setFillsViewportHeight(true);
        this.add(scoreTable, BorderLayout.CENTER);
    }

    private void readFile() {
        String fileName = "res/highscores.txt";
        String line = null;
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(new File(fileName));
            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            int row = 0;
            int column;
            while ((line = bufferedReader.readLine()) != null) {
                column = 0;
                String[] result = line.split(" ");
                names.add(result[0]);
                scores.add(Long.parseLong(result[1]));
                tableData[row][column] = result[0];
                tableData[row][column + 1] = result[1];
                row++;
            }
            // Always close files.
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

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


