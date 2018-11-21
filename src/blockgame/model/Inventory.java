/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockgame.model;

/**
 * Items can be stored in an inventory
 * @author Souhaib
 */
public class Inventory {
    private ItemStack[] inventory;

    /**
     * @param amount, the amount of Itemstacks in the inventory
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
     * @param i place of ItemStack in inventory
     * @return id of the ItemStack
     */
    public int getIdItemStack(int i){
        return inventory[i].getId();
    }
    
    /**
     * @param i place of ItemStack in inventory
     * @return type of the ItemStack
     */
    public ItemType getTypeItemStack(int i){
        if(inventory[i] != null){
            return inventory[i].getType();
        }
        else return ItemType.nothing;
    }
    
    /**
     * @param i place of ItemStack in inventory
     * @return the amount in this ItemStack
     */
    public int getAmountInItemStack(int i) {
        return inventory[i].getAmount();
    }
    
    /**
     * @return the lenght of the inventory
     */
    public int getLenghtInventory() {
        return inventory.length;
    }
    
    /**
     * TODO
     * @param id
     * @param type
     * @param much the amount 
     * @return  
     */
    public boolean addItemInInventory(int id, ItemType type,int much) {
        ItemInterface ii= new ItemInterface();
        for(int i=0;i<getLenghtInventory();i++){
            try{
                if(getIdItemStack(i)==id && getTypeItemStack(i)==type){
                    if(ii.getMaxStacksize(id, type)>=getAmountInItemStack(i)+much){
                        inventory[i].addItems(much);
                        return true;
                    }
                    else if(ii.getMaxStacksize(id, type)==getAmountInItemStack(i)){
                        System.out.println("ERROR: Itemstack of this item is full.");
                    }
                    else{
                        int in= ii.getMaxStacksize(id, type)-getAmountInItemStack(i);
                        much=much-in;
                        inventory[i].addItems(in);
                    }
                } 
            }
            catch(NullPointerException e){
                inventory[i]=new ItemStack(id, type, 0);
                int in=0;
                if (much>=ii.getMaxStacksize(id, type)){
                    in= ii.getMaxStacksize(id, type);
                }
                else{
                    in=much;
                }
                much=much-in;
                inventory[i].addItems(in);
                if(much==0){
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * TODO
     * @param id
     * @param type
     * @param much the amount 
     * @return  
     */
    public boolean removeItemInInventory(int id, ItemType type, int much){
        int n = 0;
        if(getAantal(id, type)>=much){
            for(ItemStack is : inventory){
                try{
                    if(is.getId()==id && is.getType()==type){
                        if(is.getAmount()>=much){ 
                            is.removeItems(much);
                            
                            if(is.getAmount() == 0){
                                // itemstack is empty => remove it
                                inventory[n] = null;
                            }
                            return true;
                        }
                        else{
                            much=much-is.getAmount();
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
     * TODO
     * @param id
     * @param type
     * @return 
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
    
