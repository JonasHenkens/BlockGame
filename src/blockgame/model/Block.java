/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockgame.model;


/**
 *
 * @author Jonas
 */
public class Block extends Item{
    private double health;
    private double hardness;
    private int dropId;
    private ItemType dropType;
    private boolean visible;

    /**
     * Constructor of the Block.
     * @param texture Path to the texture of the block.
     * @param health Health of the block.
     * @param hardness Hardness of the block.
     * @param id Id of the block.
     * @param dropId Id of the item that will be dropped when broken.
     * @param dropType Type of the item that will be dropped when broken.
     * @param name Name of the block.
     */
    public Block(String texture, double health, double hardness, int id, int dropId, ItemType dropType, String name) {
        this.texture = texture;
        this.health = health;
        this.hardness = hardness;
        this.id = id;
        this.dropId = dropId;
        this.dropType = dropType;
        type = ItemType.block;
        this.name = name;
        visible = false;
        maxStackSize = 50;
        strength = 1;
    }
    
    /**
     * Hits the block with the current item.
     * @param itemId The id of the item used to hit the block; 
     * @param type 
     * @return True if block has been broken;
     */
    public boolean hitBlock(int itemId, ItemType type){
        ItemInterface ii = new ItemInterface();
        double strength = ii.getItem(itemId, type).getStrength();
        health = health - strength/hardness;
        if(health < 0){
            health = 0;
        }
        return health == 0.0;
    }

    /**
     * @param visible True for block is visible.
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    

    // getters
    
    
    /**
     * @return The id of the item dropped when breaking the block.
     */
    public int getDropId() {
        return dropId;
    }

    /**
     * @return The health of the block.
     */
    public double getHealth() {
        return health;
    }

    /**
     * @return The hardness of the block.
     */
    public double getHardness() {
        return hardness;
    }
    
    /**
     * @return This returns true if the block is visible.
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * @return The type of the item to be dropped when broken.
     */
    public ItemType getDropType() {
        return dropType;
    }
    
}
