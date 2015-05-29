package cs48.project.game.Space_Blasters;

import javax.print.Doc;
import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.io.*;
import java.util.*;
import com.mongodb.*;
import com.mongodb.client.*;
import org.bson.*;

import static com.mongodb.client.model.Filters.exists;
import static com.mongodb.client.model.Sorts.descending;

/**
 * Created by Vivek on 5/7/2015.
 */
public class HighScores extends JPanel {

    private long[] scoreList;
    private String[][] tableData;
    private JLabel scoreLabel;
    private MongoDatabase db;
    private MongoCollection<Document> highScores;
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
        String[] columnNames = {"Names", "Scores"};
        scoreTable = new JTable(tableData, columnNames);
        this.setLayout(new BorderLayout());
        //this.add(scoreTable.getTableHeader(), BorderLayout.PAGE_START);
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
        MongoCursor<Document> cursor = highScores.find(exists("score")).sort(descending("score")).iterator();
        for (int i = 0; i < 10; i++) {
            tableData[i][1] = cursor.next().toString();
        }
        cursor = highScores.find(exists("name")).sort(descending("score")).iterator();
        for (int i = 0; i < 10; i++) {
            tableData[i][0] = cursor.next().toString();
        }
    }

}

