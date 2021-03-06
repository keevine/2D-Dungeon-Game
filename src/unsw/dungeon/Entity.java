package unsw.dungeon;

import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * An entity in the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class Entity {
    
    // IntegerProperty is used so that changes to the entities position can be
    // externally observed.
    private IntegerProperty x, y;
    private Boolean impassible; 
    private String name;
    private Boolean item = false;

    /**
     * Create an entity positioned in square (x,y)
     * @param x
     * @param y
     */
    public Entity(int x, int y) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
    }

    public IntegerProperty x() {
        return x;
    }

    public IntegerProperty y() {
        return y;
    }

    public int getY() {
        return y().get();
    }

    public int getX() {
        return x().get();
    }

    public void setXPos(int x) {
        x().set(x);
    }

    public void setYPos(int y) {
        y().set(y);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setItem(Boolean item) {
        this.item = item;
    }

    public int getId() {
        return -1;
    }

    public void setImpassible(Boolean impassible) {
        this.impassible = impassible;
    }

    public Boolean isImpassible() {
        return impassible;
    }
    
    public Boolean isItem() {
        return item;
    }
    
    public void process(Player player) {
        return;
    }

    /**
     * Check if a specific entity exist given a list of entities
     * @param eList The list of entities to be checked
     * @param name The name of the entity
     * @return The entity if found, otherwise null
     */
    public Entity checkEntityList(ArrayList<Entity> eList, String name){
        for(Entity e: eList){
            if (e.getName().equals(name)){
                return e;
            }
        }
        return null;
    }

}
