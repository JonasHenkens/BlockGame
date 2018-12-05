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
public class Material extends Item {

    /**
     * @param id The id of the material.
     * @param name The name of the material.
     * @param texture The texture of the material.
     */
    public Material(int id, String name, String texture) {
        this.id = id;
        this.maxStackSize = 50;
        this.strength = 1;
        this.type = ItemType.material;
        this.name = name;
        this.texture = texture;
    }
    
    
}
