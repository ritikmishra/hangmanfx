package hangman;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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


    public Shape[] body = new Shape[] {head, torso, armLeft, armRight, legLeft, legRight};

    public static Text kShowName;




    public void updateLives(int lives)
    {
        body[body.length - lives + 1].setVisible(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        kShowName = showName;

        head.setVisible(false);
        armLeft.setVisible(false);
        armRight.setVisible(false);
        torso.setVisible(false);
        legLeft.setVisible(false);
        legRight.setVisible(false);

        body = new Shape[] {head, torso, armLeft, armRight, legLeft, legRight};
    }
}
