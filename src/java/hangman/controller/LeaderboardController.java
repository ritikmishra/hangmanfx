package hangman.controller;

import hangman.Main;
import hangman.util.LeaderboardEntry;
import javafx.event.Event;
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
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * Controls the leaderboard scene
 *
 * When initialized, goes through each leaderboard entry and adds them to the grid pane, ensuring that they are centered
 */
public class LeaderboardController implements Initializable
{
    private static int INITIAL_ROWS;

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

        INITIAL_ROWS = getRowCount();

        // For each leaderboard entry
        updateLeaderboard();
    }

    private void updateLeaderboard()
    {
        for(int i = INITIAL_ROWS; i < getRowCount(); i++)
        {
            deleteRow(i);
        }

        // Get all the entries of the leaderboard
        List<LeaderboardEntry> leaderboardEntries = Main.leaderboard.getLeaderboard();

        // For each leaderboard entry
        for(int i = 0; i < Math.min(10, leaderboardEntries.size()); i++)
        {
            LeaderboardEntry entry = leaderboardEntries.get(i);

            // Make 3 new text elements for the name, score, and date attached to the entry
            Text name = new Text(entry.getName());
            Text score = new Text(String.valueOf(entry.getScore()));
            Text date = new Text(entry.getTimestamp().toString());
            Text word = new Text(entry.getWord());

            int currentRows = getRowCount();

            // Add them to the grid pane
            pane.addRow(i + currentRows, name, score, date, word);

            // Center them
            GridPane.setHalignment(name, HPos.CENTER);
            GridPane.setValignment(name, VPos.CENTER);

            GridPane.setHalignment(score, HPos.CENTER);
            GridPane.setValignment(score, VPos.CENTER);

            GridPane.setHalignment(date, HPos.CENTER);
            GridPane.setValignment(date, VPos.CENTER);

            GridPane.setHalignment(word, HPos.CENTER);
            GridPane.setValignment(word, VPos.CENTER);

            // Make sure that the height of the row is consistent
            RowConstraints rowConstraint = new RowConstraints();
            rowConstraint.setPrefHeight(30);
            rowConstraint.setMinHeight(10);

            pane.getRowConstraints().add(rowConstraint);
        }

    }

    @FXML
    private void updateLeaderboard(Event event)
    {
        updateLeaderboard();
    }
    private int getRowCount() {
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

    private void deleteRow(final int row) {
        Set<Node> deleteNodes = new HashSet<>();
        for (Node child : pane.getChildren()) {
            // get index from child
            Integer rowIndex = GridPane.getRowIndex(child);

            // handle null values for index=0
            int r = rowIndex == null ? 0 : rowIndex;

            if (r > row) {
                // decrement rows for rows after the deleted row
                GridPane.setRowIndex(child, r-1);
            } else if (r == row) {
                // collect matching rows for deletion
                deleteNodes.add(child);
            }
        }

        // remove nodes from row
        pane.getChildren().removeAll(deleteNodes);
    }
}
