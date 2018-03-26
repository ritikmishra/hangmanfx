package hangman;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.awt.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class MainController implements Initializable
{
    /**
     * Makes a map that gets to see if a button can be clicked again
     */
    private Map<Button, Boolean> canClickButton = new HashMap<>();

    @FXML
    public Text showName;

    public static Text kShowName;

    @FXML
    private Button example;


    @FXML
    private void switchToMain()
    {
//        Main.switchToMainScene(inputName.getName());
    }


    /**
     * Action listener for the buttons
     */
    @FXML
    private void click(ActionEvent event)
    {
        for(Button button : canClickButton.keySet())
        {
            if(event.getSource() == button)
            {
                clickedButton(button);
            }
        }
    }

    /**
     * Checks if it can be clicked
     *  Changes the background and doesn't let it be clicked again once clicked
     * @param button which button is being changed
     */
    private void clickedButton(Button button)
    {
        if(canClickButton.get(button))
        {
            button.getStyleClass().add("clicked");
            canClickButton.replace(button, false);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        example.setVisible(false);
        kShowName = showName;
        canClickButton.put(example, false);
    }

}
