package hangman.controller;

import hangman.util.Leaderboard;
import hangman.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LeaderboardController implements Initializable
{
    @FXML
    GridPane pane;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        List<Leaderboard.LeaderboardEntry> leaderboardEntries = Main.leaderboard.getLeaderboard();

        Text[] names = new Text[leaderboardEntries.size()];
        Text[] scores = new Text[leaderboardEntries.size()];
        Text[] dates = new Text[leaderboardEntries.size()];

        for(int i = 0; i < leaderboardEntries.size(); i++)
        {
            Leaderboard.LeaderboardEntry entry = leaderboardEntries.get(i);

            names[i] = new Text(entry.getName());
            scores[i] = new Text(String.valueOf(entry.getScore()));
            dates[i] = new Text(entry.getTimestamp().toString());

            pane.addRow(i + 1, names[i], scores[i], dates[i]);

            RowConstraints rowConstraint = new RowConstraints();
            rowConstraint.setPrefHeight(30);
            rowConstraint.setMinHeight(10);

            pane.getRowConstraints().add(rowConstraint);
        }

        System.out.println("initialized leaderboard");

    }
}
