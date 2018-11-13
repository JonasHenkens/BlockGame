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
public interface Item {
    int getId();
    ItemType getItemType();
    String getName();
    String getTexture();
    int getMaxStackSize();
    double getStrength();
}
