package cs48.project.game.Space_Blasters;

import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.io.*;
import java.util.*;
import com.mongodb.*;
import com.mongodb.Cursor;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by Vivek on 5/7/2015.
 */
public class HighScores extends JPanel {

    private long[] scoreList;
    private String[][] tableData;
    private JLabel scoreLabel;
    private MongoDatabase db;
    MongoCollection highScores;
    private JTable scoreTable;

    /**
     * Default no-arg constructor for HighScore JPanel
     */
    public HighScores() {
        super();
        connectToDatabase();
        scoreList = new long[10];
        tableData = new String[10][2];
        fillScoreTable();
        this.add(scoreTable, BorderLayout.CENTER);
    }

    private void connectToDatabase() {
        try {
            MongoClientURI uri = new MongoClientURI("mongodb://vivek_patel:root@ds034348.mongolab.com:34348/spaceblasters");
            MongoClient mongoClient = new MongoClient(uri);
            mongoClient.getDatabase("spaceblasters");
            db = mongoClient.getDatabase("spaceblasters");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void fillScoreTable() {
        highScores = db.getCollection("HighScores");

    }

    public void addHighScore(String name, long score) {

    }
}


