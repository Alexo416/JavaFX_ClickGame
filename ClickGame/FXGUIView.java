package ClickGame;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Alert;

/**
 * @author Alexander Hernandez
 * <p>
 * This is a click game where you start off doing 1 damage per click to the monster in the middle
 * The user can purchase items on the upper right hand side to increase Damage Per Click
 * <p>
 * This will also display stats on the top with a brief description in the bottom.
 * If the user does not have enough currency an error message will appear.
 * <p>
 * It will take 5 clicks to generate 1 Gold
 */
public class FXGUIView extends Application {


    private final int damagePerClick = 1;
    private Game newGame;
    private Label descriptionLabel;
    private Label secondDescriptionLabel;
    private Label calculationLabel;
    private Button monsterButton, firstItemButton, secondItemButton;
    private boolean firstSelectionTrue = false;
    private boolean secondSelectionTrue = false;


    /**
     * @param args unused
     */

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * @param e this is the ActionEvent handler for the monster in the middle.
     *          If user selects an item the damage per click will be modified.
     *          <p>
     *          At the end of every press it will check to see if monster is at 0 Health,
     *          this will also update the label.
     */
    private void damageButtonHandler(ActionEvent e) {

        if (firstSelectionTrue) {
            newGame.setNumberOfClicks(damagePerClick + 2);
        } else if (secondSelectionTrue) {
            newGame.setNumberOfClicks(damagePerClick + 20);
        } else
            newGame.setNumberOfClicks(damagePerClick);

        gameFinish();
        calculationLabel.setText(newGame.toString());

    }

    /**
     * @param e This is the first item Button that checks to see if the user has enough Gold before adding damage per click.
     *          If the user does not have enough Gold a warning pop up will appear
     */

    private void firstItemButtonHandler(ActionEvent e) {
        if (newGame.getNumberOfGold() >= 5 && newGame.getNumberOfGold() > 0) {
            newGame.setNumberOfGold(-5);
            firstSelectionTrue = true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(String.format("You Need 50 Gold To Purchase This Item"));
            alert.setTitle("Warning");
            alert.showAndWait();
        }

    }

    /**
     * @param e This is the second item Button that checks to see if the user has enough Gold before adding damage per click.
     *          *          If the user does not have enough Gold a warning pop up will appear
     */
    private void secondItemButtonHandler(ActionEvent e) {
        if (newGame.getNumberOfGold() >= 100 && newGame.getNumberOfGold() > 0) {
            newGame.setNumberOfGold(-100);
            secondSelectionTrue = true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(String.format("You Need 100 Gold To Purchase This Item"));
            alert.setTitle("Warning");
            alert.showAndWait();

        }
    }

    /**
     * This checks to see if the monster has enough Health left, if monster is at 0 health a congratulation message will appear.
     */
    private void gameFinish() {
        if (newGame.getHealthPoints() <= 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(String.format("Congratulations You Have Finished The Game :) "));
            alert.setTitle("YAY!");
            alert.showAndWait();
        }
    }

    /**
     * This is where you create your components and the model and add event
     * handlers.
     *
     * @param stage The main stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        Pane root = new Pane();
        Scene scene = new Scene(root, 800, 600); // set the size here
        stage.setTitle("Click Game - Alexander Hernandez"); // set the window title here
        stage.setScene(scene);
        Image img = new Image("ClickGame/Giygas.jpg"); // Image to be used for Monster
        ImageView view = new ImageView(img);
        view.setFitHeight(200);
        view.setPreserveRatio(true);


        newGame = new Game(); // Create a new game

        descriptionLabel = new Label("Click on the Monster to do damage, the more you click the more Gold you generate.");
        secondDescriptionLabel = new Label("Buy weapons to increase the number of damage per click!"); // Second description label was used for more clarity in the GUI.
        calculationLabel = new Label("Calculation Will Display After First Click"); // Number of gold, damage and Health will be presented after first click of the monster.
        monsterButton = new Button();
        firstItemButton = new Button("Buy Weapon (+2 To Damage)");
        secondItemButton = new Button("Buy Weapon (+20 To Damage)");


        root.getChildren().addAll(descriptionLabel, monsterButton, firstItemButton, secondItemButton, calculationLabel, secondDescriptionLabel);

        String css = "-fx-text-fill:white;-fx-font: 13pt Bold;"; // Font for the text on the screen
        String buttonStyle = "-fx-background-color:white;-fx-font: 13pt Bold;-fx-text-fill:black;"; // Style for the buttons

        descriptionLabel.setStyle(css);
        descriptionLabel.relocate(20, 500);

        secondDescriptionLabel.setStyle(css);
        secondDescriptionLabel.relocate(20, 515);

        calculationLabel.setStyle(css);
        calculationLabel.relocate(20, 20);

        monsterButton.relocate(300, 200);
        monsterButton.setPrefSize(80, 80);
        monsterButton.setGraphic(view);

        firstItemButton.setStyle(buttonStyle);
        firstItemButton.relocate(530, 20);

        secondItemButton.setStyle(buttonStyle);
        secondItemButton.relocate(530, 70);


        root.setStyle("-fx-background-color:black;"); // Background color of the GUI
        monsterButton.setOnAction(this::damageButtonHandler);
        firstItemButton.setOnAction(this::firstItemButtonHandler);
        secondItemButton.setOnAction(this::secondItemButtonHandler);

        stage.show();
    }
}
