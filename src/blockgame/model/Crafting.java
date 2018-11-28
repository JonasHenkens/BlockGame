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
    
    public Crafting() {
        geopend=false;
    }

    /**
     * @return the geopend: TRUE, als de craftingmenu open staat
     * FALSE, als de craftingmenu gesloten is
     */
    public boolean isGeopend() {
        return geopend;
    }

    public void setGeopend() {
        geopend = !geopend;
    }
    
    
    
    
    
}
