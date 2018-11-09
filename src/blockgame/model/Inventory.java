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

    public Inventory() { 
        inventory = new ItemStack[20];
        inventory[0]=new ItemStack((new Block("blockgame/textures/blocks/noTexture.png", 20, 3, 0, 0, "noTexture")),0);
        inventory[1]=new ItemStack((new Block("blockgame/textures/blocks/dirt.png", 1, 1, 1, 1, "dirt")),0);
        inventory[2]=new ItemStack((new Block("blockgame/textures/blocks/grass.png", 1, 1.25, 2, 2, "grass")),0);
        inventory[3]=new ItemStack((new Block("blockgame/textures/blocks/stone.png", 10, 12, 3, 3, "stone")),0);
        inventory[4]=new ItemStack((new Block("blockgame/textures/blocks/pink.png", 10, 12, 4, 4, "pink")),0);
        
        maxAmount = 250;
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
     * @param item the item to put in the inventory
     */
    public void addItemInInventory(Item item) {
        if(inventory.length<maxAmount){
            for(int i=0;i<25;i++){
                
            }
        }
        else{
            System.out.println("ERROR: No place in inventory.");
        }
    }
    
    
    
}
