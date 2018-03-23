package hangman;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable
{
    StartingScreen startingScreen = new StartingScreen();

    @FXML
    private TextField field;

    @FXML
    private Button button;

    @FXML
    private void continueAction(ActionEvent event)
    {
        System.out.println(field.getText());

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
