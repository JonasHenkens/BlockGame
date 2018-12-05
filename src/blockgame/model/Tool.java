/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockgame.model;

/**
 *
 * @author Jonas and Souhaib
 */
public class Tool extends Item {
    
    /**
     * @param id The id of the tool.
     * @param strength The strength of the tool.
     * @param name The name of the tool.
     * @param texture The texture of the tool.
     */
    public Tool(int id, double strength, String name, String texture) {
        this.id = id;
        this.strength = strength;
        this.name = name;
        this.texture = texture;
        this.maxStackSize = 1;
        this.type = ItemType.tool;
    }
    
    
}
