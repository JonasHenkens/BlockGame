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
public class Block implements Item{
    private String texture;
    private double health;
    private double hardness;
    private int id;
    private int dropId;
    private String type;
    private String name;
    private boolean visible;
    private int maxStackSize;

    public Block(String texture, double health, double hardness, int id, int dropId, String name, int maxStackSize) {
        this.texture = texture;
        this.health = health;
        this.hardness = hardness;
        this.id = id;
        this.dropId = dropId;
        this.type = "block";
        this.name = name;
        visible = false;
        this.maxStackSize = maxStackSize;
    }

    /**
     * @return the texture
     */
    public String getTexture() {
        return texture;
    }

    /**
     * @return the health
     */
    public double getHealth() {
        return health;
    }

    /**
     * @return the hardness
     */
    public double getHardness() {
        return hardness;
    }
    
    
    public void hitBlock(double strength){
        health = health - strength/hardness;
        if(health < 0){
            health = 0;
        }
    }

    @Override
    public String getItemType() {
        return "Block";
    }
    
    @Override
    public int getId(){
        return id;
    }

    /**
     * @return the dropId
     */
    public int getDropId() {
        return dropId;
    }

    /**
     * @return the name of the block
     */
    public String getName() {
        return name;
    }

    /**
     * @return true is block is visible
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * @param visible true for block is visible
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * @return the maxStack
     */
    public int getMaxStackSize() {
        return maxStackSize;
    }

    
}
