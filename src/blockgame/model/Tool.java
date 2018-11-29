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
public class Tool extends Item {
    private int durability;
    private int maxDurability;
    
    /**
     * 
     * @param id The id of the tool.
     * @param strength The strength of the tool.
     * @param name The name of the tool.
     * @param texture The texture of the tool.
     * @param durability  The maximum durability of the tool.
     */
    public Tool(int id, double strength, String name, String texture, int durability) {
        this.id = id;
        this.strength = strength;
        this.name = name;
        this.texture = texture;
        this.maxStackSize = 1;
        this.type = ItemType.tool;
        this.durability = this.maxDurability = durability;
    }
    
    /**
     * 
     * @param amount The amount of damage you do to the tool
     * @return True if pickaxe broke, false if durability left.
     */
    public boolean damage(int amount){
        durability = durability-amount;
        if(durability<0){
            durability = 0;
            return true;
        }
        return false;
    }

    /**
     * @return The durability left on the tool.
     */
    public int getDurability() {
        return durability;
    }

    /**
     * @return The maximum amount of durability.
     */
    public int getMaxDurability() {
        return maxDurability;
    }
    
    
    
}
