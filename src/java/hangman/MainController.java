package hangman;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable
{
    @FXML
    public Text showName;

    @FXML
    public Path gallows;

    @FXML
    public Ellipse head;

    @FXML
    public Rectangle torso;

    @FXML
    public Rectangle armLeft;

    @FXML
    public Rectangle armRight;

    @FXML
    public Rectangle legLeft;

    @FXML
    public Rectangle legRight;

    @FXML
    public Text userGuesses;

    @FXML
    public Button continueToEndScene;

    public Shape[] body;

    public static Text kShowName;

    @FXML
    private void updateLives(MouseEvent event)
    {
        userGuesses.setText(Main.hangman.getUserDisplay());
        updateLives(Main.hangman.getLives());
        Main.hangman.takeLife();

        if(Main.hangman.getLives() == 0)
        {
            continueToEndScene.setVisible(true);
        }
    }

    private void updateLives(int lives)
    {
        System.out.println("lives = [" + lives + "]");
        body[body.length - lives].setVisible(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

        kShowName = showName;

        body = new Shape[] {head, torso, armLeft, armRight, legLeft, legRight};

        for(Shape bodyPart : body)
        {
            bodyPart.setVisible(false);
        }

//        userGuesses.setText(Main.hangman.getUserDisplay());
    }

    @FXML
    private void continueToLeaderboard(MouseEvent mouseEvent)
    {
        Main.switchToLeaderboardScene();
    }
}
