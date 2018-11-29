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
    
    public Crafting(Person person) {
        geopend = false;
        this.person = person;
    }
    
    public void setGeopend() {
        geopend = !geopend;
    }
    
    public void makeGoldIngot(){
        int aantal=person.getInventoryAmount(7, ItemType.block);
        
        if(aantal>=2){
            person.removeInventoryItem(7, ItemType.block, 1);
            person.addItem(1, ItemType.material,1);
        }
    }
    
    public void makeIronIngot(){
        int aantal=person.getInventoryAmount(5, ItemType.block);
        
        if(aantal>=2){
            person.removeInventoryItem(5, ItemType.block, 1);
            person.addItem(0, ItemType.material, 1);
        }
    }
    
    public void makeDiamant(){
        int aantal=person.getInventoryAmount(6, ItemType.block);
        
        if(aantal>=2){
            person.removeInventoryItem(6, ItemType.block, 1);
            person.addItem(2, ItemType.material,1);
        }
    }
    
    public void makeCoal(){
        int aantal=person.getInventoryAmount(4, ItemType.block);
        
        if(aantal>=2){
            person.removeInventoryItem(4, ItemType.block, 1);
            person.addItem(3, ItemType.material, 1);
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
    
}
