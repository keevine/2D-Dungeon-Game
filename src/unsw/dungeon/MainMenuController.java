package unsw.dungeon;

import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MainMenuController {

    private Stage stage;
    private Level advanced;
    private Level boulders;
    private Level maze;

    @FXML
    private Button advancedButton;

    @FXML
    private Button bouldersButton;

    @FXML
    private Button mazeButton;

    public MainMenuController(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void handleAdvancedButton(ActionEvent event) throws IOException {
        advanced = new Level(stage, "advanced.json");
        setAdvancedLevel(advanced);
        advanced.start();
    }

    @FXML
    public void handleBouldersButton(ActionEvent event) throws IOException {
        boulders = new Level(stage, "boulders.json");
        setBouldersLevel(boulders);
        boulders.start();
    }

    @FXML
    public void handleMazeButton(ActionEvent event) throws IOException {
        maze = new Level(stage, "maze.json");
        setMazeLevel(maze);
        maze.start();
    }

    @FXML
    public void initialize(){
        Image advancedImage = new Image((new File("examples/advanced.png")).toURI().toString());
        ImageView advancedImageView = new ImageView(advancedImage);
        advancedImageView.setFitHeight(152);
        advancedImageView.setFitWidth(176);
        advancedButton.setGraphic(advancedImageView);

        Image bouldersImage = new Image((new File("examples/boulders.png")).toURI().toString());
        ImageView bouldersImageView = new ImageView(bouldersImage);
        bouldersImageView.setFitHeight(152);
        bouldersImageView.setFitWidth(176);
        bouldersButton.setGraphic(bouldersImageView);

        Image mazeImage = new Image((new File("examples/maze.png")).toURI().toString());
        ImageView mazeImageView = new ImageView(mazeImage);
        mazeImageView.setFitHeight(152);
        mazeImageView.setFitWidth(176);
        mazeButton.setGraphic(mazeImageView);
    }

    public void setAdvancedLevel(Level advanced){
        this.advanced = advanced;
    }

    public void setBouldersLevel(Level boulders){
        this.boulders = boulders;
    }

    public void setMazeLevel(Level maze){
        this.maze = maze;
    }
}