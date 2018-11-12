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
     * @param i place of ItemStack in inventory
     * @return id of the ItemStack
     */
    public int getIdItemStack(int i){
        return inventory[i].getItem().getId();
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
     * @param item the item to put in the inventory
     * @param much the amount 
     */
    public void addItemInInventory(Item item,int much) {
        for(int i=0;i<getLenghtInventory();i++){
            try{
                if(getIdItemStack(i)==item.getId()){
                    if(item.getMaxStackSize()<=getAmountInItemStack(i)+much){
                        inventory[i].addItems(much);
                        break;
                    }
                    else if(item.getMaxStackSize()==getAmountInItemStack(i)){
                        System.out.println("ERROR: Itemstack of this item is full.");
                    }
                    else{
                        System.out.println("ERROR: Can't put so much of this item in the inventory.");
                    }
                } 
            }
            catch(NullPointerException e){
                inventory[i]=new ItemStack(item, much);
                break;
            }
            if(i==getLenghtInventory()-1){
                System.out.println("ERROR: Item is not in inventory & inventory is full.");
            }
        }
    }
    
    /**
     * @param item the item in the inventory that has to be used.
     * @param much the amount 
     */
    public void useItemInInventory(Item item, int much){
        for(int i=0;i<getLenghtInventory();i++){
            try{
                if(getIdItemStack(i)==item.getId()){
                    if(getAmountInItemStack(i)>=much){ 
                        inventory[i].removeItems(much);
                        break;
                    }
                    else{
                        System.out.println("ERROR: Inventory doesn't has so much of this item.");
                        break;
                    }
                } 
            }
            catch(NullPointerException e){
                System.out.println("ERROR: Inventory doesn't has this item!");
            }
        }
    }
}
    
