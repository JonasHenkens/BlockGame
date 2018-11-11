/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockgame.model;

/**
 * Items can be stored in an inventory
 * @author Jonas
 */
public class Inventory {
    private ItemStack[] inventory;
    private int maxAmount;
    private int amount;

    public Inventory() { 
        inventory = new ItemStack[20];
        inventory[0]=new ItemStack((new Block("blockgame/textures/blocks/noTexture.png", 20, 3, 0, 0, "noTexture")),0);
        inventory[1]=new ItemStack((new Block("blockgame/textures/blocks/dirt.png", 1, 1, 1, 1, "dirt")),0);
        inventory[2]=new ItemStack((new Block("blockgame/textures/blocks/grass.png", 1, 1.25, 2, 2, "grass")),0);
        inventory[3]=new ItemStack((new Block("blockgame/textures/blocks/stone.png", 10, 12, 3, 3, "stone")),0);
        inventory[4]=new ItemStack((new Block("blockgame/textures/blocks/pink.png", 10, 12, 4, 4, "pink")),0);
        amount=0;
        maxAmount = 250;
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
     * @return the maxAmount
     */
    public int getMaxAmount() {
        return maxAmount;
    }

    /**
     * @return amount
     */
    public int getAmountInventory(){
        for(int i=0;i<20;i++){
            amount=+inventory[i].getAmount();
        }
        return amount;
    }
    
    /**
     * @param item the item to put in the inventory
     * @param much the amount 
     */
    public void addItemInInventory(Item item,int much) {
        for(int i=0;i<20;i++){
            if(getIdItemStack(i)==item.getId()){
                if(getAmountInventory()<maxAmount){
                    inventory[i].addItems(much);
                }
                else{
                    System.out.println("ERROR: No place in inventory.");
                }
            }
        }
    }
    
    /**
     * @param item the item in the inventory that has to be used.
     * @param much the amount 
     */
    public void useItemInInventory(Item item,int much){
        for(int i=0;i<20;i++){
            if(getIdItemStack(i)==item.getId()){
                if(getAmountInventory()>=much){
                    inventory[i].removeItems(much);
                }
                else if (getAmountInventory()!=0){
                    System.out.println("ERROR: Inventory doesn't has so much of this item.");
                }
                else{
                    System.out.println("ERROR: Inventory doesn't has this item.");
                }
            }
        }
    }
}
    
