package hangman.controller;

import hangman.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Controls the scene when the user is playing the Hangman game (when the user is on the main scene)
 */
public class MainController implements Initializable
{
    /**
     * Makes a map that gets to see if a button can be clicked again
     */
    private Map<Button, Boolean> canClickButton = new HashMap<>();

    /**
     * Makes it easier to return letter of the button clicked
     */
    private Map<Button, Character> buttonLetter = new HashMap<>();

    /**
     * Displays the username in the corner
     */
    @FXML
    public Text showName;


    /**
     * Displays the head of the corpse on the gallows
     * Hidden by default
     */
    @FXML
    public Ellipse head;

    /**
     * Displays the body of the corpse on the gallows
     * Hidden by default
     */
    @FXML
    public Rectangle torso;

    /**
     * Displays the left arm of the corpse on the gallows
     * Hidden by default
     */
    @FXML
    public Rectangle armLeft;

    /**
     * Displays the right arm of the corpse on the gallows
     * Hidden by default
     */
    @FXML
    public Rectangle armRight;

    /**
     * Displays the left leg of the corpse on the gallows
     * Hidden by default
     */
    @FXML
    public Rectangle legLeft;

    /**
     * Displays the right leg of the corpse on the gallows
     * Hidden by default
     */
    @FXML
    public Rectangle legRight;

    /**
     * Displays the letters the user has correctly guessed, and the number of letters in the word
     */
    @FXML
    public Text userGuesses;

    /**
     * A public reference to {@link MainController#userGuesses}. This ensures that {@link Main} can set the number of underscores when it chooses a word
     */
    public static Text kUserGuesses;

    /**
     * A button that lets the user go to the leaderboard scene
     */
    @FXML
    public Button continueToEndScene;

    /**
     * Button "A"
     */
    @FXML
    public Button A;

    /**
     * Button "B"
     */
    @FXML
    public Button B;

    /**
     * Button "C"
     */
    @FXML
    public Button C;

    /**
     * Button "D"
     */
    @FXML
    public Button D;

    /**
     * Button "E"
     */
    @FXML
    public Button E;

    /**
     * Button "F"
     */
    @FXML
    public Button F;

    /**
     * Button "G"
     */
    @FXML
    public Button G;

    /**
     * Button "H"
     */
    @FXML
    public Button H;

    /**
     * Button "I"
     */
    @FXML
    public Button I;

    /**
     * Button "J"
     */
    @FXML
    public Button J;

    /**
     * Button "K"
     */
    @FXML
    public Button K;

    /**
     * Button "L"
     */
    @FXML
    public Button L;

    /**
     * Button "M"
     */
    @FXML
    public Button M;

    /**
     * Button "N"
     */
    @FXML
    public Button N;

    /**
     * Button "O"
     */
    @FXML
    public Button O;

    /**
     * Button "P"
     */
    @FXML
    public Button P;

    /**
     * Button "Q"
     */
    @FXML
    public Button Q;

    /**
     * Button "R"
     */
    @FXML
    public Button R;

    /**
     * Button "S"
     */
    @FXML
    public Button S;

    /**
     * Button "T"
     */
    @FXML
    public Button T;

    /**
     * Button "U"
     */
    @FXML
    public Button U;

    /**
     * Button "V"
     */
    @FXML
    public Button V;

    /**
     * Button "W"
     */
    @FXML
    public Button W;

    /**
     * Button "X"
     */
    @FXML
    public Button X;


    /**
     * Button "Y"
     */
    @FXML
    public Button Y;

    /**
     * Button "Z"
     */
    @FXML
    public Button Z;

    /**
     * The array of shapes making up the body
     * <p>
     * Now that they are in an array, we can easily show body parts based on the number of incorrect guesses
     */
    private Shape[] body;

    /**
     * A public reference to {@link MainController#showName}. This ensures that {@link Main} can set the text of the name when it receives the name
     */
    public static Text kShowName;

    /**
     * When the user clicks on a button to guess the letter, update the amount of lives they have
     *
     * @param letter The letter the user guessed
     */
    @FXML
    private void updateGuess(char letter)
    {

        boolean correctlyGuessed = Main.hangman.checkIfContainLetter(letter);

        if(!correctlyGuessed)
        {
            addBodyParts(Main.hangman.getLives());
        }

        if(Main.hangman.getLives() == 0 || Main.hangman.lettersLeft() == 0)
        {
            continueToEndScene.setVisible(true);

            // Reveal the word
            userGuesses.setText(Main.hangman.getWordToGuess());
        }
        else
        {
            updateText();
        }
    }

    /**
     * Given a certain amount of lives left, show a body part
     *
     * @param lives Amount of lives
     */
    private void addBodyParts(int lives)
    {
        System.out.println("lives = [" + lives + "]");
        body[body.length - lives - 1].setVisible(true);
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
     * Changes the background and doesn't let it be clicked again once clicked
     *
     * @param button which button is being changed
     */
    private void clickedButton(Button button)
    {
        if(canClickButton.get(button))
        {
            button.getStyleClass().add("clicked");
            canClickButton.replace(button, false);
            updateGuess(buttonLetter.get(button));
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
//        updateText();
        // Ensure {@link Main} can access the text object
        kShowName = showName;

        kUserGuesses = userGuesses;

        // Build the body
        body = new Shape[] { head, torso, armLeft, armRight, legLeft, legRight };

        canClickButton.put(A, true);
        buttonLetter.put(A, 'A');

        canClickButton.put(B, true);
        buttonLetter.put(B, 'B');

        canClickButton.put(C, true);
        buttonLetter.put(C, 'C');

        canClickButton.put(D, true);
        buttonLetter.put(D, 'D');

        canClickButton.put(E, true);
        buttonLetter.put(E, 'E');

        canClickButton.put(F, true);
        buttonLetter.put(F, 'F');

        canClickButton.put(G, true);
        buttonLetter.put(G, 'G');

        canClickButton.put(H, true);
        buttonLetter.put(H, 'H');

        canClickButton.put(I, true);
        buttonLetter.put(I, 'I');

        canClickButton.put(J, true);
        buttonLetter.put(J, 'J');

        canClickButton.put(K, true);
        buttonLetter.put(K, 'K');

        canClickButton.put(L, true);
        buttonLetter.put(L, 'L');

        canClickButton.put(M, true);
        buttonLetter.put(M, 'M');

        canClickButton.put(N, true);
        buttonLetter.put(N, 'N');

        canClickButton.put(O, true);
        buttonLetter.put(O, 'O');

        canClickButton.put(P, true);
        buttonLetter.put(P, 'P');

        canClickButton.put(Q, true);
        buttonLetter.put(Q, 'Q');

        canClickButton.put(R, true);
        buttonLetter.put(R, 'R');

        canClickButton.put(S, true);
        buttonLetter.put(S, 'S');

        canClickButton.put(T, true);
        buttonLetter.put(T, 'T');

        canClickButton.put(U, true);
        buttonLetter.put(U, 'U');

        canClickButton.put(V, true);
        buttonLetter.put(V, 'V');

        canClickButton.put(W, true);
        buttonLetter.put(W, 'W');

        canClickButton.put(X, true);
        buttonLetter.put(X, 'X');

        canClickButton.put(Y, true);
        buttonLetter.put(Y, 'Y');

        canClickButton.put(Z, true);
        buttonLetter.put(Z, 'Z');


        // hide the body
        for(Shape bodyPart : body)
        {
            bodyPart.setVisible(false);
        }
//        updateText();
    }

    private void updateText()
    {
        String userDisplay = Main.hangman.getUserDisplay();
        userGuesses.setText(userDisplay);
    }

    /**
     * When {@link MainController#continueToEndScene} is clicked, continue to the leaderboard scene
     *
     * @param mouseEvent mouse listener
     */
    @FXML
    private void continueToLeaderboard(MouseEvent mouseEvent)
    {
        canClickButton.forEach((key, value) -> {
            canClickButton.put(key, true);
            key.getStyleClass().remove("clicked");

    });
        for(Shape part : body)
        {
            part.setVisible(false);
        }
        continueToEndScene.setVisible(false);
        Main.switchToLeaderboardScene();
    }
}
