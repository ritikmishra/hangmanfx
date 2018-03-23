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
    private TextField field;

    @FXML
    private Button button;

    @FXML
    private void continueAction(ActionEvent event)
    {
        start();
    }

    @FXML
    private void hitStartEnter(KeyEvent event)
    {
        if(event.getCode() == KeyCode.ENTER)
        {
            start();
        }
    }

    private void start()
    {
        StartingScreen startingScreen = new StartingScreen();

        new Hangman(field.getText());

        try
        {
            startingScreen.runAnotherApp(Main.class);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }
}
