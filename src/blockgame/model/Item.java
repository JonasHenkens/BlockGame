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
public abstract class Item {
    int id;
    int maxStackSize;
    double strength;
    ItemType type;
    String name;
    String texture;
    
    /**
     * @return The maximum stack size.
     */
    public int getMaxStackSize() {
        return maxStackSize;
    }

    /**
     * @return The strength.
     */
    public double getStrength() {
        return strength;
    }
    
    /**
     * @return The type ItemType.block.
     */
    public ItemType getItemType() {
        return type;
        
    }
    
    /**
     * @return The id of the block.
     */
    public int getId(){
        return id;
    }

    /**
     * @return The name of the block.
     */
    public String getName() {
        return name;
    }

    /**
     * @return The texture used for this block.
     */
    public String getTexture() {
        return texture;
    }
    
    
}
