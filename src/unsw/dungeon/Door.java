package unsw.dungeon;


public class Door extends Entity {
    
    private int id;
    
    /**
     * 
     * @param x X coordinate of the door (Starting from 0 Left to right)
     * @param y Y coordinate of the door (Starting from 0 Top to bottom)
     * @param id ID of the door, opened by a key with matching ID
     */
    public Door(int x, int y, int id){
        super(x, y);
        this.id = id;
        setImpassible(true);
        setName("door");
    }

    @Override
    public int getId() {
        return id;
    }

    public void openDoor(Player player){
        setImpassible(false);
        player.openDoor(this);
    }

    @Override
	public void process(Player player) {
        Inventory inventory = player.getInventory();
        if (inventory.checkKey(id)) {
            inventory.useKey();
            openDoor(player);
        }
	}
    

}
