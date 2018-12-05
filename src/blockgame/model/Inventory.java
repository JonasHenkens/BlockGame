/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockgame.model;

/**
 * Items can be stored in an inventory.
 * @author Souhaib
 */
public class Inventory {
    private ItemStack[] inventory;

    /**
     * @param amount The amount of itemstacks in the inventory.
     */
    public Inventory(int amount) { 
        inventory = new ItemStack[amount];
    }

    /**
     * @param inventory The inventory to start with.
     */
    public Inventory(ItemStack[] inventory) {
        this.inventory = inventory;
    }
    
    /**
     * @param i Place of ItemStack in the inventory.
     * @return Id of the ItemStack.
     */
    public int getIdItemStack(int i){
        return inventory[i].getId();
    }
    
    /**
     * @param i Place of ItemStack in the inventory.
     * @return Type of the ItemStack.
     */
    public ItemType getTypeItemStack(int i){
        if(inventory[i] != null){
            return inventory[i].getType();
        }
        else return ItemType.nothing;
    }
    
    /**
     * @param i Place of ItemStack in the inventory.
     * @return The amount in this ItemStack.
     */
    public int getAmountInItemStack(int i) {
        return inventory[i].getAmount();
    }
    
    /**
     * @return The lenght of the inventory.
     */
    public int getLenghtInventory() {
        return inventory.length;
    }
    
    /**
     * @param id The id of the item to be added.
     * @param type The type of the item to be added.
     * @param amount The amount of the item to be added.
     * @return  True if items have been added.
     */
    public boolean addItemInInventory(int id, ItemType type,int amount) {
        ItemInterface ii= new ItemInterface();
        for(int i=0;i<getLenghtInventory();i++){
            try{
                if(getIdItemStack(i)==id && getTypeItemStack(i)==type){
                    if(ii.getMaxStacksize(id, type)>=getAmountInItemStack(i)+amount){
                        inventory[i].addItems(amount);
                        return true;
                    }
                    else if(ii.getMaxStacksize(id, type)==getAmountInItemStack(i)){
                        System.out.println("ERROR: Itemstack of this item is full.");
                    }
                    else{
                        int in= ii.getMaxStacksize(id, type)-getAmountInItemStack(i);
                        amount=amount-in;
                        inventory[i].addItems(in);
                    }
                } 
            }
            catch(NullPointerException e){
                inventory[i]=new ItemStack(id, type, 0);
                int in=0;
                if (amount>=ii.getMaxStacksize(id, type)){
                    in= ii.getMaxStacksize(id, type);
                }
                else{
                    in=amount;
                }
                amount=amount-in;
                inventory[i].addItems(in);
                if(amount==0){
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * @param id The id of the item to be removed.
     * @param type The type of the item to be removed.
     * @param amount The amount of the item to be removed.
     * @return  True if items have been removed.
     */
    public boolean removeItemInInventory(int id, ItemType type, int amount){
        int n = 0;
        if(getAantal(id, type)>=amount){
            for(ItemStack is : inventory){
                try{
                    if(is.getId()==id && is.getType()==type){
                        if(is.getAmount()>=amount){ 
                            is.removeItems(amount);
                            
                            if(is.getAmount() == 0){
                                // itemstack is empty => remove it
                                inventory[n] = null;
                            }
                            return true;
                        }
                        else{
                            amount=amount-is.getAmount();
                            is.removeItems(is.getAmount());
                            if(is.getAmount() == 0){
                                // itemstack is empty => remove it
                                inventory[n] = null;
                            }
                        }
                    }
                } 
                catch(NullPointerException e){
                }
                n++;
            }
        }
        else{
            System.out.println("ERROR: Inventory doesn't has so much of this item.");
        }
    return false;
    }
    
    /**
     * @param id The id of the item.
     * @param typeTje type of the item.
     * @return The amount of the item in the inventory.
     */
    public int getAantal(int id, ItemType type){
        int aantal=0;
        for(ItemStack is : inventory){
            if(is != null){
                if(is.getId()==id && is.getType()==type){
                    aantal=aantal+is.getAmount();
                }
            }
        }
        
        return aantal;
    }
}
    
