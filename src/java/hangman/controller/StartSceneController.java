package hangman.controller;

import hangman.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class StartSceneController implements Initializable
{
    /**
     * A textbox in which the user can input their name
     */
    @FXML
    private TextField inputName;


    /**
     * When the continue button is clicked, continue on to the main scene where the user can play hangman
     * @param event
     */
    @FXML
    private void continueAction(ActionEvent event)
    {
        switchToMain();
    }


    /**
     * When the user hits enter, continue on to the main scene where the user can play hangman
     * @param event
     */
    @FXML
    private void hitStartEnter(KeyEvent event)
    {
        if(event.getCode() == KeyCode.ENTER)
        {
            switchToMain();
        }
    }

    private void switchToMain()
    {
        Main.switchToMainScene(inputName.getText());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }
}
