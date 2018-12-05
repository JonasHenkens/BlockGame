/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockgame.model;

/**
 *
 * @author Souhaib and Jonas
 */
public class ItemStack {
    private int id;
    private ItemType type;
    private int amount;
    private int maxStackSize;

    /**
     * @param id The id of the items stored in the itemstack.
     * @param type The type of the items stored in the itemstack.
     * @param amount The amount of the item stored in the itemstack.
     */
    public ItemStack(int id, ItemType type, int amount) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        ItemInterface i=new ItemInterface();
        maxStackSize = i.getMaxStacksize(id, type);
    }
    
    /**
     * Adds items to the stack. This will not add any items if it tries to add more than allowed.
     * @param amount The amount that will be tried to add.
     * @return True if items are added. False if items are not added.
     */
    public boolean addItems(int amount){
        if(amount + this.amount <= getMaxStackSize()){
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
        if(amount <= this.amount){
            this.amount = this.amount - amount;
            return true;
        }
        else{
            return false;
        }
    }
    
    /**
     * @return The amount in the itemstack.
     */
    public int getAmount() {
        return amount;
    }

    /**
     * @return The id of the items in the itemstack.
     */
    public int getId() {
        return id;
    }

    /**
     * @return The type of the items in the itemstack.
     */
    public ItemType getType() {
        return type;
    }

    /**
     * @return The maximum stack size of the items in the itemstack.
     */
    public int getMaxStackSize() {
        return maxStackSize;
    }

}
