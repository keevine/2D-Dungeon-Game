package unsw.dungeon;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.File;

/**
 * A DungeonLoader that also creates the necessary ImageViews for the UI,
 * connects them via listeners to the model, and creates a controller.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonControllerLoader extends DungeonLoader {

    private List<ImageView> entities;

    //Images
    private Image playerImage;
    private Image wallImage;
    private Image exitImage;
    private Image doorImage;
    private Image keyImage;
    private Image treasureImage;
    private Image portalImage;
    private Image boulderImage;
    private Image floorSwitchImage;
    private Image enemyImage;
    private Image swordImage;
    private Image potionImage;
    private Image brokenImage;
    private Image icyFloorImage;

    public DungeonControllerLoader(String filename)
            throws FileNotFoundException {
        super(filename);
        entities = new ArrayList<>();
        playerImage = new Image((new File("images/human_new.png")).toURI().toString());
        wallImage = new Image((new File("images/brick_brown_0.png")).toURI().toString());
        exitImage = new Image((new File("images/exit.png")).toURI().toString());
        doorImage = new Image((new File("images/closed_door.png")).toURI().toString());
        keyImage = new Image((new File("images/key.png")).toURI().toString());
        treasureImage = new Image((new File("images/gold_pile.png")).toURI().toString());
        portalImage = new Image((new File("images/portal.png")).toURI().toString());
        boulderImage = new Image((new File("images/boulder.png")).toURI().toString());
        floorSwitchImage = new Image((new File("images/pressure_plate.png")).toURI().toString());
        enemyImage = new Image((new File("images/hound.png")).toURI().toString());
        swordImage = new Image((new File("images/greatsword_1_new.png")).toURI().toString()); 
        potionImage = new Image((new File("images/bubbly.png")).toURI().toString());
        brokenImage = new Image((new File("images/broken_wall.png")).toURI().toString());
        icyFloorImage = new Image((new File("images/icyFloor.png")).toURI().toString());
    }

    @Override
    public void onLoad(Entity player) {
        ImageView view = new ImageView(playerImage);
        view.setId(player.getName());
        addEntity(player, view);
    }

    @Override
    public void onLoad(Wall wall) {
        ImageView view = new ImageView(wallImage);
        view.setId(wall.getName());
        addEntity(wall, view);
    }

    @Override
    public void onLoad(Exit exit) {
        ImageView view = new ImageView(exitImage);
        view.setId(exit.getName());
        addEntity(exit, view);
    }

    @Override
    public void onLoad(Door door) {
        ImageView view = new ImageView(doorImage);
        view.setId(door.getName());
        addEntity(door, view);
    }

    @Override
    public void onLoad(Key key) {
        ImageView view = new ImageView(keyImage);
        view.setId(key.getName());
        addEntity(key, view);
    }

    @Override
    public void onLoad(Treasure treasure) {
        ImageView view = new ImageView(treasureImage);
        view.setId(treasure.getName());
        addEntity(treasure, view);
    }

    @Override
    public void onLoad(Portal portal) {
        ImageView view = new ImageView(portalImage);
        view.setId(portal.getName());
        addEntity(portal, view);
    }

    @Override
    public void onLoad(Boulder boulder) {
        ImageView view = new ImageView(boulderImage);
        view.setId(boulder.getName());
        addEntity(boulder, view);
    }

    @Override
    public void onLoad(FloorSwitch floorSwitch) {
        ImageView view = new ImageView(floorSwitchImage);
        view.setId(floorSwitch.getName());
        addEntity(floorSwitch, view);
    }

    @Override
    public void onLoad(Enemy enemy) {
        ImageView view = new ImageView(enemyImage);
        view.setId(enemy.getName());
        addEntity(enemy, view);
    }

    @Override 
    public void onLoad(InvincibilityPotion potion) {
        ImageView view = new ImageView(potionImage);
        view.setId(potion.getName());
        addEntity(potion, view);
    }
        
    @Override
    public void onLoad(Sword sword) {
        ImageView view = new ImageView(swordImage);
        view.setId(sword.getName());
        addEntity(sword, view);
    }

    @Override
    public void onLoad(BrokenWall brokenWall) {
        ImageView view = new ImageView(brokenImage);
        view.setId(brokenWall.getName());
        addEntity(brokenWall, view);
    }

    @Override
    public void onLoad(IcyFloor icyFloor) {
        ImageView view = new ImageView(icyFloorImage);
        view.setId(icyFloor.getName());
        addEntity(icyFloor, view);
    }


    private void addEntity(Entity entity, ImageView view) {
        trackPosition(entity, view);
        entities.add(view);
    }

    /**
     * Set a node in a GridPane to have its position track the position of an
     * entity in the dungeon.
     *
     * By connecting the model with the view in this way, the model requires no
     * knowledge of the view and changes to the position of entities in the
     * model will automatically be reflected in the view.
     * @param entity
     * @param node
     */
    private void trackPosition(Entity entity, Node node) {
        GridPane.setColumnIndex(node, entity.getX());
        GridPane.setRowIndex(node, entity.getY());
        entity.x().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setColumnIndex(node, newValue.intValue());      
            }
        });
        entity.y().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setRowIndex(node, newValue.intValue());
            }
        });
    }

    /**
     * Create a controller that can be attached to the DungeonView with all the
     * loaded entities.
     * @return
     * @throws FileNotFoundException
     */
    public DungeonController loadController() throws FileNotFoundException {
        return new DungeonController(load(), entities);
    }

    


}
