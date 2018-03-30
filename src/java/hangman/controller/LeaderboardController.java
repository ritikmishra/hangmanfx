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
import java.util.List;
import java.util.ResourceBundle;

/**
 * Controls the leaderboard scene
 * <p>
 * When initialized, goes through each leaderboard entry and adds them to the grid pane, ensuring that they are centered
 */
public class LeaderboardController implements Initializable
{

    /**
     * Pane on which the leaderboard entries are stored
     */
    @FXML
    GridPane pane;

    /**
     * Button that needs to be clicked to play again
     */
    @FXML
    Button replayButton;

    /**
     * Listener for the play again button
     * Goes back to the main scene
     *
     * @param event
     */
    @FXML
    private void goToMainScene(MouseEvent event)
    {
        Main.switchToMainScene();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        Main.leaderboard.updateFile();

        // For each leaderboard entry
        updateLeaderboard();
    }

    /**
     * Updates the leaderboard
     */
    public void updateLeaderboard()
    {
        clearRows();

        // Get all the entries of the leaderboard
        List<LeaderboardEntry> leaderboardEntries = Main.leaderboard.getLeaderboard();

        // For each leaderboard entry (up to a max of 10)
        for(int i = 0; i < Math.min(10, leaderboardEntries.size()); i++)
        {
            LeaderboardEntry entry = leaderboardEntries.get(i);

            // Make text elements for all parts of the leaderboard entry
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

    /**
     * Listener for refresh leaderboard button
     *
     * @param event
     */
    @FXML
    private void updateLeaderboard(Event event)
    {
        updateLeaderboard();
    }

    /**
     * Count rows
     *
     * @return The estimated number of rows in the gridpane
     */
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

    /**
     * Get rid of all the nodes in {@link LeaderboardController#pane} and hide the leftover empty rows
     */
    private void clearRows()
    {
        System.out.println("pane.getChildren() = " + pane.getChildren());

        // Clear all nodes
        pane.getChildren().clear();

        // Hide the evidence
        // Squish the rows to 0 pixels
        for(int i = 0; i < pane.getRowConstraints().size(); i++)
        {
            RowConstraints constraint = pane.getRowConstraints().get(i);
            constraint.setMinHeight(0);
            constraint.setPrefHeight(0);
            constraint.setMaxHeight(0);
        }
    }

    /**
     * Center a node in its cell
     *
     * @param thing The node to center
     */
    private void centerNode(Node thing)
    {
        GridPane.setHalignment(thing, HPos.CENTER);
        GridPane.setValignment(thing, VPos.CENTER);
    }
}
