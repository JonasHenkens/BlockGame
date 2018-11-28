/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockgame;

/**
 *
 * @author Souhaib El Habti
 */
public class Test {
    private boolean gelukt;

    public Test(boolean gelukt) {
        this.gelukt = gelukt;
    }

    /**
     * @return the gelukt
     */
    public boolean isGelukt() {
        return gelukt;
    }

    /**
     */
    public void setGelukt() {
        gelukt=!gelukt;
    }
   
}
