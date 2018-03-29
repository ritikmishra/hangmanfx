package hangman.util;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.HashMap;
import java.util.Map;

public class LetterButtons
{
    /**
     * Makes a map that gets to see if a button can be clicked again
     */
    public Map<Button, Boolean> canClickButton = new HashMap<>();

    /**
     * Makes it easier to return letter of the button clicked
     */
    public Map<Button, Character> buttonLetter = new HashMap<>();

    private Button[] buttons;

    public void setButtons(Button... buttons)
    {
        this.buttons = buttons;
    }

    public void setButtonMaps()
    {
        for(Button button : buttons)
        {
            canClickButton.put(button, true);
            buttonLetter.put(button, button.getText().charAt(0));
        }
    }

    /**
     * Checks if it can be clicked
     * Changes the background and doesn't let it be clicked again once clicked
     *
     * @param button which button is being changed
     */
    public boolean clickedButton(Button button)
    {
        if(canClickButton.get(button))
        {
            canClickButton.replace(button, false);
            changeColor(button);
            return true;
        }

        return false;
    }

    public Button click(ActionEvent event)
    {
        for(Button button : canClickButton.keySet())
        {
            if(event.getSource() == button && clickedButton(button))
            {
                return button;
            }
        }
        return null;
    }

    public void changeColor(Button button)
    {
        button.getStyleClass().add("clicked");
    }

}
