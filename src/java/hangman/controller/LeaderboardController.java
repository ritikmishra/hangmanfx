package hangman.controller;

import hangman.Main;
import hangman.util.LeaderboardEntry;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Controls the leaderboard scene
 *
 * When initialized, goes through each leaderboard entry and adds them to the grid pane, ensuring that they are centered
 */
public class LeaderboardController implements Initializable
{
    @FXML
    GridPane pane;

    @FXML
    Button replayButton;

    @FXML
    private void goToMainScene(MouseEvent event)
    {
        Main.switchToMainScene();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        // Get all the entries of the leaderboard
        List<LeaderboardEntry> leaderboardEntries = Main.leaderboard.getLeaderboard();

        // For each leaderboard entry
        for(int i = 0; i < leaderboardEntries.size(); i++)
        {
            LeaderboardEntry entry = leaderboardEntries.get(i);

            // Make 3 new text elements for the name, score, and date attached to the entry
            Text name = new Text(entry.getName());
            Text score = new Text(String.valueOf(entry.getScore()));
            Text date = new Text(entry.getTimestamp().toString());

            int initialRows = getRowCount(pane);

            // Add them to the grid pane
            pane.addRow(i + initialRows, name, score, date);

            // Center them
            GridPane.setHalignment(name, HPos.CENTER);
            GridPane.setValignment(name, VPos.CENTER);

            GridPane.setHalignment(score, HPos.CENTER);
            GridPane.setValignment(score, VPos.CENTER);

            GridPane.setHalignment(date, HPos.CENTER);
            GridPane.setValignment(date, VPos.CENTER);

            // Make sure that the height of the row is consistent
            RowConstraints rowConstraint = new RowConstraints();
            rowConstraint.setPrefHeight(30);
            rowConstraint.setMinHeight(10);

            pane.getRowConstraints().add(rowConstraint);
        }
    }

    private int getRowCount(GridPane pane) {
        int numRows = pane.getRowConstraints().size();

        for (int i = 0; i < pane.getChildren().size(); i++) {
            Node child = pane.getChildren().get(i);
            if (child.isManaged()) {
                Integer rowIndex = GridPane.getRowIndex(child);
                if(rowIndex != null){
                    numRows = Math.max(numRows,rowIndex+1);
                }
            }
        }
        return numRows;
    }
}
