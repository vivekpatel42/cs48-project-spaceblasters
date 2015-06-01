package cs48.project.game.Space_Blasters;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import org.json.*;

import javax.swing.*;
import java.awt.*;
import java.util.*;

import static com.mongodb.client.model.Filters.exists;
import static com.mongodb.client.model.Sorts.descending;

/**
 * Created by Vivek on 5/7/2015.
 */
public class HighScores extends JPanel {

    private long[] scoreList;
    private String[][] tableData;
    private MongoDatabase db;
    private MongoCollection<Document> highScores;
    private JTable scoreTable;

    /**
     * Default no-arg constructor for HighScore JPanel
     */
    public HighScores() {
        super();
        connectToDatabase();
        highScores = db.getCollection("HighScores");
        scoreList = new long[10];
        tableData = new String[10][2];
        fillScoreTable();
        String[] columnNames = {"Names", "Scores"};
        scoreTable = new JTable(tableData, columnNames);
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(scoreTable.getTableHeader());
        this.add(scoreTable);
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
        MongoCursor<Document> cursor = highScores.find(exists("score")).sort(descending("score")).iterator();
        for(int i = 0; i < 10; i++) {
            JSONObject json = new JSONObject(cursor.next().toJson());
            if(json.toString().contains("$numberLong")) {
                scoreList[i] = json.getJSONObject("score").getLong("$numberLong");
            } else {
                scoreList[i] = json.getLong("score");
            }
            tableData[i][1] = String.valueOf(scoreList[i]);
            tableData[i][0] = json.getString("name");
        }
    }

    public void writeHighScore(String name, long score) {
        Document scoreData = new Document("name", name).append("score", score);
        highScores.insertOne(scoreData);
    }

    public long[] getScoreList() {
        return scoreList;
    }
}

