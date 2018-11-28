/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockgame.model;

/**
 *
 * @author Souhaib
 */
public class ItemStack {
    private int id;
    private ItemType type;
    private int amount;
    private int maxStackSize;

    /**
     *
     * @param id
     * @param type
     * @param amount
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
     * @return the amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the type
     */
    public ItemType getType() {
        return type;
    }

    /**
     * @return the maxStackSize
     */
    public int getMaxStackSize() {
        return maxStackSize;
    }

}
