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
    
    public void makeGoldIngot(int id, int aantal){
        aantal=person.getInventoryAmount(id, ItemType.block);
        
        
        
        
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
