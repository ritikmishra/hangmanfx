package hangman.controller;

import com.sun.org.apache.xml.internal.security.Init;
import hangman.Main;
import hangman.util.LeaderboardEntry;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;

/**
 * Controls the leaderboard scene
 * <p>
 * When initialized, goes through each leaderboard entry and adds them to the grid pane, ensuring that they are centered
 */
public class LeaderboardController implements Initializable
{
    private static int INITIAL_ROWS = 2;

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
        Main.leaderboard.updateFile();

        INITIAL_ROWS = getRowCount();

        // For each leaderboard entry
        updateLeaderboard();
    }

    public void updateLeaderboard()
    {
        clearRows();

        // Get all the entries of the leaderboard
        List<LeaderboardEntry> leaderboardEntries = Main.leaderboard.getLeaderboard();

        // For each leaderboard entry
        for(int i = 0; i < Math.min(10, leaderboardEntries.size()); i++)
        {
            LeaderboardEntry entry = leaderboardEntries.get(i);

            // Make 3 new text elements for the name, score, and date attached to the entry
            Text name = new Text(entry.getName());
            Text score = new Text(String.format("%.2f", entry.getScore()));
            Text date = new Text(entry.getTimestamp().toString());
            Text word = new Text(entry.getWord());
            Text time = new Text(String.format("%.2f", entry.getElapsedTimeSeconds()));

            int currentRows = getRowCount();

            // Add them to the grid pane
            pane.addRow(i + currentRows, name, score, date, word, time);

            // Center them
            centerNode(name);
            centerNode(score);
            centerNode(date);
            centerNode(word);
            centerNode(time);
            // Make sure that the height of the row is consistent
            RowConstraints rowConstraint = new RowConstraints();
            rowConstraint.setPrefHeight(30);
            rowConstraint.setMinHeight(10);

            pane.getRowConstraints().add(rowConstraint);
        }

    }

    @FXML
    private void updateLeaderboard(KeyEvent event)
    {
        updateLeaderboard();

    }

    @FXML
    private void clickRefresh(MouseEvent event)
    {
        updateLeaderboard();
    }

    private int getRowCount()
    {
        int numRows = pane.getRowConstraints().size();

        for(int i = 0; i < pane.getChildren().size(); i++)
        {
            Node child = pane.getChildren().get(i);
            if(child.isManaged())
            {
                Integer rowIndex = GridPane.getRowIndex(child);
                if(rowIndex != null)
                {
                    numRows = Math.max(numRows, rowIndex + 1);
                }
            }
        }
        return numRows;
    }

    private void clearRows()
    {
        System.out.println("pane.getChildren() = " + pane.getChildren());

        pane.getChildren().clear();

        // Hide the evidence
        // Squish the rows to 0 pixels
        for(int i = INITIAL_ROWS; i < pane.getRowConstraints().size(); i++)
        {
            RowConstraints constraint = pane.getRowConstraints().get(i);
            constraint.setMinHeight(0);
            constraint.setPrefHeight(0);
            constraint.setMaxHeight(0);
        }
    }

    private void centerNode(Node thing)
    {
        GridPane.setHalignment(thing, HPos.CENTER);
        GridPane.setValignment(thing, VPos.CENTER);
    }
}
