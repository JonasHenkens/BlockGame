/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockgame.model;

/**
 *
 * @author Souhaib El Habti
 */
public class Crafting {
    private boolean geopend;
    private Person person;
    private double height;
    private double width;
    
    public Crafting(Person person) {
        geopend = false;
        this.person = person;
        height=250;
        width=500;
    }
    
    public void setGeopend() {
        geopend = !geopend;
    }
    
    public void makeGoldIngot(){
        int aantalg=person.getInventoryAmount(7, ItemType.block);
        int aantalc=person.getInventoryAmount(3, ItemType.material);
        
        if(aantalg>=1 && aantalc>=2){
            person.removeInventoryItem(7, ItemType.block, 1);
            person.removeInventoryItem(3, ItemType.material, 2);
            person.addItem(1, ItemType.material,1);
        }
        else{
        }
    }
    
    public void makeIronIngot(){
        int aantali=person.getInventoryAmount(5, ItemType.block);
        int aantalc=person.getInventoryAmount(3, ItemType.material);

        if(aantali>=1 && aantalc>=1){
            person.removeInventoryItem(5, ItemType.block, 1);
            person.removeInventoryItem(3, ItemType.material, 1);
            person.addItem(0, ItemType.material, 1);
        }
        else{
        }
    }
    
    public void makeDiamant(){
        int aantald=person.getInventoryAmount(6, ItemType.block);
        int aantalc=person.getInventoryAmount(3, ItemType.material);
        
        if(aantald>=1 && aantalc>=3){
            person.removeInventoryItem(6, ItemType.block, 1);
            person.removeInventoryItem(3, ItemType.material, 3);
            person.addItem(2, ItemType.material,1);
        }
        else{
        }
    }
    
    public void makeWoodPickaxe(){
        int amount=person.getInventoryAmount(8, ItemType.block);
        
        if(amount>=4){
            person.removeInventoryItem(8, ItemType.block, 4);
            person.addItem(0, ItemType.tool, 1);
        }
        else{
        }
    }
    
    public void makeStonePickaxe(){
        int amountS=person.getInventoryAmount(3, ItemType.block);
        int amountW=person.getInventoryAmount(8, ItemType.block);
        
        if(amountS>=3 && amountW>=1){
            person.removeInventoryItem(8, ItemType.block, 1);
            person.removeInventoryItem(3, ItemType.block, 3);
            person.addItem(1, ItemType.tool, 1);
        }
        else{
        }
    }
    
    public void makeIronPickaxe(){
        int amountW=person.getInventoryAmount(8, ItemType.block);
        int amountI=person.getInventoryAmount(0, ItemType.material);
        
        if(amountW>=1 && amountI>=3){
            person.removeInventoryItem(8, ItemType.block, 1);
            person.removeInventoryItem(0, ItemType.material, 3);
            person.addItem(3, ItemType.tool, 1);
        }
        else{
        }
    }
    
    public void makeGoldPickaxe(){
        int amountW=person.getInventoryAmount(8, ItemType.block);
        int amountG=person.getInventoryAmount(1, ItemType.material);
        
        if(amountW>=1 && amountG>=3){
            person.removeInventoryItem(8, ItemType.block, 1);
            person.removeInventoryItem(1, ItemType.material, 3);
            person.addItem(4, ItemType.tool, 1);
        }
        else{
        }
    }
    
    public void makeDiamondPickaxe(){
        int amountW=person.getInventoryAmount(8, ItemType.block);
        int amountD=person.getInventoryAmount(2, ItemType.material);
        
        if(amountW>=1 && amountD>=2){
            person.removeInventoryItem(8, ItemType.block, 1);
            person.removeInventoryItem(2, ItemType.material, 2);
            person.addItem(2, ItemType.tool, 1);
        }
        else{
        }
    }
    
    
    //GETTERS
    
    /**
     * @return the geopend: TRUE, als de craftingmenu open staat
     * FALSE, als de craftingmenu gesloten is
     */
    public boolean isGeopend() {
        return geopend;
    }

    /**
     * @return the height
     */
    public double getHeight() {
        return height;
    }

    /**
     * @return the width
     */
    public double getWidth() {
        return width;
    }
    
}
