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
public class ItemStack {
    private Item item;
    private int amount;
    private int maxStackSize;

    public ItemStack(Item item, int amount) {
        this.item = item;
        this.amount = amount;
        maxStackSize = item.getMaxStackSize();
    }
    
    
    
    
}
