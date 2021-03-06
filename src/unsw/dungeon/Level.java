package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Level {
    private Stage stage;
    private String title;
    private DungeonController controller;
    private Scene scene;
    private String fxmlLevel;

    public Level(Stage stage, String level) throws IOException{
        this.stage = stage;
        title = "Dungeon";
        
        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(level);
        fxmlLevel = level;

        controller = dungeonLoader.loadController();
        controller.setStage(stage);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        scene = new Scene(root);
        root.requestFocus();
    }

    public void start(){
        stage.setTitle(title);
        stage.setScene(scene);
        controller.setCurrentLevel(this);
        controller.startCountdown();
        stage.show();
    }

    public Stage getStage() {
        return stage;
    }
    
    public void setContoller(DungeonController controller) {
        this.controller = controller;
    }
    
    public DungeonController getController(){
        return controller;
    }

    public String getLevel() {
        return fxmlLevel;
    }
    
    
}