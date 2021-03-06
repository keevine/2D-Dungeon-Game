/**
 *
 */
package unsw.dungeon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.ImageView;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon {

    private int width, height, timer;
    private List<Entity> entities;
    private List<Goal> goals;
    private List<CompositeGoal> compositeGoals;
    private Player player;
    private List<ImageView> entityImages;
    private DungeonController controller;

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.goals = new ArrayList<>();
        this.entityImages = new ArrayList<>();
        this.compositeGoals = new ArrayList<>();
        this.player = null;
    }

    /**
     * Adds a goal to the Goals list in the Dungeon class
     * 
     * @param goal The goal to be added
     */
    public void addGoal(Goal goal) {
        this.goals.add(goal);
    }

    /**
     * processExitGoal registers the subgoal as an observer for an Exit type goal
     * 
     * @param subgoal Exit type subgoal requires the player to reach the Exit
     */
    public void processExitGoal(Goal subgoal) {
        // Look for the exit and call exit.register()
        ArrayList<Entity> exits = findEntities("exit");
        Exit exit = (Exit) exits.get(0);

        ObserverExit observer = (ObserverExit) subgoal;
        exit.register(observer);
        subgoal.setName("exit");
    }

    /**
     * processBouldersGoal registers the subgoal as an observer for an Boulder type
     * goal
     * 
     * @param subgoal Boulders type subogoal requires the player to push a boulder
     *                onto all switches
     */
    public void processBouldersGoal(Goal subgoal) {
        ArrayList<Entity> switches = findEntities("floorSwitch");
        ObserverBoulders observer = (ObserverBoulders) subgoal;
        // attach this obs to all switches
        for (Entity e : switches) {
            FloorSwitch floorSwitch = (FloorSwitch) e;
            floorSwitch.register(observer);
        }
        ((SubGoalBoulders) subgoal).setNumSwitches(switches.size());
        subgoal.setName("boulders");
    }

    /**
     * processTreasuresGoal registers the subgoal as an observer for an Treasures
     * type goal
     * 
     * @param subgoal Treasures type subgoal requires the player to collect all
     *                treasures
     */
    public void processTreasureGoal(Goal subgoal) {
        ArrayList<Entity> treasureList = findEntities("treasure");
        ObserverTreasure observer = (ObserverTreasure) subgoal;
        // Attach this observer to all treasures
        for (Entity e : treasureList) {
            Treasure treasure = (Treasure) e;
            treasure.register(observer);
        }
        ((SubGoalTreasure) subgoal).setNumTreasure(treasureList.size());
        subgoal.setName("treasure");
    }

    /**
     * processEnemiesGoal registers the subgoal as an observer for an Enemies type
     * goal
     * 
     * @param subgoal Enemies type subgoal requires the player to kill all enemies
     */
    public void processEnemiesGoal(Goal subgoal) {
        ArrayList<Entity> enemyList = findEntities("enemy");
        ObserverEnemy observer = (ObserverEnemy) subgoal;
        // Attach this observer to all enemies
        for (Entity e : enemyList) {
            Enemy enemy = (Enemy) e;
            enemy.register(observer);
        }
        ((SubGoalEnemy) subgoal).setNumEnemies(enemyList.size());
        subgoal.setName("enemies");
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }

    public void setController(DungeonController controller) {
        this.controller = controller;
    }

    /**
     * Returns the entity at a chosen tile (that isn't the player)
     * 
     * @param x The X position of the tile you want to check
     * @param y The Y position of the tile you want to check
     * @return
     */
    public Entity getEntity(int x, int y) {
        for (Entity e : entities) {
            int entityX = e.getX();
            int entityY = e.getY();

            if (entityX == x && entityY == y && !(e instanceof Player)) {
                return e;
            }
        }
        return null;
    }

    /**
     * Returns a list of all entities at a chosen tile (that isn't the player)
     * 
     * @param x The X position of the tile you want to check
     * @param y The Y position of the tile you want to check
     * @return
     */
    public ArrayList<Entity> getEntityList(int x, int y) {
        ArrayList<Entity> eList = new ArrayList<>();
        for (Entity e : entities) {
            int entityX = e.getX();
            int entityY = e.getY();

            if (entityX == x && entityY == y && !(e instanceof Player)) {
                eList.add(e);
            }

        }
        if (eList.isEmpty()) {
            return null;
        }
        return eList;
    }

    /**
     * Loops through the list of all entities in the dungeon and returns a list of
     * matching entity names
     * 
     * @return
     */
    public ArrayList<Entity> findEntities(String name) {
        ArrayList<Entity> result = new ArrayList<Entity>();
        for (Entity e : entities) {
            if (e.getName().equals(name)) {
                result.add(e);
            }
        }
        return result;
    }

    // Helper function to detect if there is an entity in the entity list (for
    // testing)
    public Boolean entityExists(Entity entity) {
        return entities.contains(entity);
    }

    public void addImage(ImageView image) {
        entityImages.add(image);
    }

    public List<ImageView> getEntityImages() {
        return entityImages;
    }

    public void removeImage(Entity entity) {
        controller.removeImage(entity);
    }

    public void openDoor(Entity entity) {
        controller.openDoor(entity);
    }

    public void showVictoryScreen() throws IOException {
        controller.showVictoryScreen();
    }

    public void showDefeatScreen() throws IOException {
        controller.showDefeatScreen();
    }
    
    public void processCompositeGoal(Goal goal) {
        if (goal instanceof CompositeGoal) {
            CompositeGoal g = (CompositeGoal) goal;
            g.setDungeon(this);
        }
    }

    public void addCompositeGoal(CompositeGoal goal) {
        compositeGoals.add(goal);
    }

    public List<CompositeGoal> getCompositeGoals() {
        return compositeGoals;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public int getTimer() {
        return timer;
    }

    public void addImage(ImageView image, int x) {
        controller.addImage(image, x);
    }

    public void removeImage(ImageView image, int x){
        controller.removeImage(image, x);
    }

    public void updateChargesTreasureUI(){
        controller.updateChargesTreasureUI();
    }

    public void updateChargesSwordUI(int charges){
        controller.updateChargesSwordUI(charges);
    }

    public void updateChargesPotionUI(int charges){
        controller.updateChargesPotionUI(charges);
    }

    public void moveToSecretLevel(String level) {
        try {
            controller.startSecretLevel(level);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
