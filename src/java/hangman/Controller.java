package hangman;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable
{
    @FXML
    private TextField inputName;

    @FXML
    private Button submitName;

    @FXML
    private void continueAction(ActionEvent event)
    {
        switchToMain();
    }

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
