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
    private BlockGame blockGame;
    
    /**
     * Constructor of Crafting.
     * @param person: The person that the Crafting is linked to.
     * @param blockGame: the blockgame where the Crafting makes part 
     * of (only used for updating the blockgameView).
     */
    public Crafting(Person person, BlockGame blockGame) {
        geopend = false;
        this.person = person;
        this.blockGame = blockGame;
    }
    
    /**
     *  Changing geopend to open or close the crafting window.
     */
    public void setGeopend() {
        geopend = !geopend;
    }
    
    /**
     * To make materials with
     * @param id the id of the to make material
     * @return a String: Tells you which elements you need 
     * to make that material
     */
    public String makeMaterial(int id){
        int aantalc=person.getInventoryAmount(3, ItemType.material);      
        switch (id) {
            case 0:
                int aantali=person.getInventoryAmount(5, ItemType.block);
                if(aantali>=1 && aantalc>=1){
                    person.removeInventoryItem(5, ItemType.block, 1);
                    person.removeInventoryItem(3, ItemType.material, 1);
                    person.addItem(id, ItemType.material, 1);
                }
                else{
                    return "You need 1 iron ore and 1 coal";
                }
                break;
            case 1:
                int aantalg=person.getInventoryAmount(7, ItemType.block);
                if(aantalg>=1 && aantalc>=2){
                    person.removeInventoryItem(7, ItemType.block, 1);
                    person.removeInventoryItem(3, ItemType.material, 2);
                    person.addItem(id, ItemType.material,1);
                }
                else{
                    return "You need 1 gold ore and 2 coals";
                }
                break;
            case 2:
                int aantald=person.getInventoryAmount(6, ItemType.block);
                if(aantald>=1 && aantalc>=3){
                    person.removeInventoryItem(6, ItemType.block, 1);
                    person.removeInventoryItem(3, ItemType.material, 3);
                    person.addItem(id, ItemType.material,1);
                }
                else{
                    return "You need 1 diamond ore and 3 coals";
                }
                break;
        }
        blockGame.getView().update();
        return "";
    }
    
    /**
     * To make a Pickaxe
     * @param id: the id of the to make pickaxe
     * @return a String: Tells you which elements you need 
     * to make that pickaxe
     */
    public String makePickaxe(int id){
        int amountW=person.getInventoryAmount(8, ItemType.block);
        switch (id) {
            case 0:
                if(amountW>=5){
                    person.removeInventoryItem(8, ItemType.block, 5);
                    person.addItem(id, ItemType.tool, 1);
                }
                else{
                    return "You need 5 wood";
                }
                break;
            case 1:
                int amountS=person.getInventoryAmount(3, ItemType.block);
                if(amountS>=3 && amountW>=2){
                    person.removeInventoryItem(8, ItemType.block, 2);
                    person.removeInventoryItem(3, ItemType.block, 3);
                    person.addItem(id, ItemType.tool, 1);
                }
                else{
                    return "You need 3 stones and 2 wood";
                }
                break;
            case 2:
                int amountD=person.getInventoryAmount(2, ItemType.material);
                if(amountW>=2 && amountD>=3){
                    person.removeInventoryItem(8, ItemType.block, 2);
                    person.removeInventoryItem(2, ItemType.material, 3);
                    person.addItem(id, ItemType.tool, 1);
                }
                else{
                    return "You need 3 diamond ingots and 2 wood";
                }
                break;
            case 3:
                int amountI=person.getInventoryAmount(0, ItemType.material);     
                if(amountW>=2 && amountI>=3){
                    person.removeInventoryItem(8, ItemType.block, 2);
                    person.removeInventoryItem(0, ItemType.material, 3);
                    person.addItem(id, ItemType.tool, 1);
                }
                else{
                    return "You need 3 iron ingots and 2 wood";
                }
                break;
            case 4:
                int amountG=person.getInventoryAmount(1, ItemType.material);    
                if(amountW>=2 && amountG>=3){
                    person.removeInventoryItem(8, ItemType.block, 2);
                    person.removeInventoryItem(1, ItemType.material, 3);
                    person.addItem(id, ItemType.tool, 1);
                }
                else{
                    return "You need 3 gold ingots and 2 wood";
                }
                break;
        }   
        blockGame.getView().update();
        return "";
    }

    //GETTERS
    
    /**
     * @return geopend: TRUE, if the crafting menu is open
     * FALSE, if the crafting menu is closed
     */
    public boolean isGeopend() {
        return geopend;
    }
}