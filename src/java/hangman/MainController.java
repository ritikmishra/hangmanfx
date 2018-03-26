package hangman;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable
{
    @FXML
    public Text showName;

    public static Text kShowName;



    @FXML
    private void switchToMain()
    {
//        Main.switchToMainScene(inputName.getName());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        kShowName = showName;
    }
}
