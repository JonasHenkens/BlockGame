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

    /**
     * Constructor for ItemStack.
     * @param item The item that will be stored in this item stack.
     * @param amount The amount that the item stack starts with
     */
    public ItemStack(Item item, int amount) {
        this.item = item;
        this.amount = amount;
        maxStackSize = item.getMaxStackSize();
    }
    
    /**
     * Adds items to the stack. This will not add any items if it tries to add more than allowed.
     * @param amount The amount that will be tried to add.
     * @return True if items are added. False if items are not added.
     */
    public boolean addItems(int amount){
        if(amount + this.amount <= maxStackSize){
            this.amount = this.amount + amount;
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * @param amount The amount that has to be removed.
     * @return True if items are removed. False if items aren't removed.
     */
    public boolean removeItems(int amount){
        if(amount >= this.amount){
            this.amount = this.amount - amount;
            return true;
        }
        else{
            return false;
        }
    }
    
    /**
     * @return the amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * @return the item
     */
    public Item getItem() {
        return item;
    }
    
}
