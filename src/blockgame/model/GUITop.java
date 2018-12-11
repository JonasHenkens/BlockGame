/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockgame.model;


/**
 * The GUI at the top of the screen.
 * @author Jonas
 */
public class GUITop{
    private int height;
    private int width;
    private int[] itemIds;
    private ItemType[] itemTypes;
    private int selectedItem;
    private Person person;
    private int size;

    /**
     * Constructor of GUITop.
     * @param person The person that the GUITop is linked to
     */
    public GUITop(Person person) {
        this.person = person;
        size = 30;
        itemIds = new int[size];
        itemTypes = new ItemType[size];
        height = 40;
        width = 1536;
        
        updateItems();
        selectedItem = 0;
        
        ItemInterface ii = new ItemInterface();
        
    }
    
    /**
     * Selects block if clicked on one.
     * @param x The x coordinate of the click.
     * @param y The y coordinate of the click.
     */
    public void leftClick(double x, double y){
        boolean done = false;
        for(int i=0; i<size && !done; i++){
            if(x >= (8+24*i) && x <= (8 + 24*i+16) && y>= 8 && y<= 22){
                selectedItem = i;
                done = true;
            }
        }
    }
    
    public void rightClick(double x, double y) {
        boolean done = false;
        try{
            for(int i=0; i<size && !done; i++){
                if(x >= (8+24*i) && x <= (8 + 24*i+16) && y>= 8 && y<= 22){
                    selectedItem = i;
                    person.removeInventoryItem(getItemId(selectedItem), getItemType(selectedItem), getAmount(selectedItem));
                    done = true;
                }
            }
        }
        catch(NullPointerException e){
        }
    }
    
    /**
     * Updates the items in each slot.
     */
    public void updateItems(){
        for(int i = 0; i<size; i++){
            try{
                itemIds[i] = person.getInventoryItemId(i);
                itemTypes[i] = person.getInventoryItemType(i);
            }
            catch(NullPointerException e){
                // no item stored
                itemIds[i] = -1;
                itemTypes[i] = null;
            }
        }
    }
    
    
    // getters

    /**
     * @return The place of the currently selected block.
     */
    public int getSelectedItem() {
        return selectedItem;
    }
    
    /**
     * @param index The place of the item.
     * @return The id of the item.
     */
    public int getItemId(int index){
        return itemIds[index];
    }
    
    /**
     * @param index The place of the item.
     * @return The type of the item.
     */
    public ItemType getItemType(int index){
        return itemTypes[index];
    }

    /**
     * @return The amount of slots shown.
     */
    public int getSize() {
        return size;
    }

    /**
     * The amount in the stack.
     * @param i 
     * @return The amount of items in the stack.
     */
    public int getAmount(int i) {
        return person.getInventoryItemAmount(i);
    }

    /**
     * @return The height of the interface.
     */
    public int getHeight() {
        return height;
    }

    /**
     * @return The width of the interface.
     */
    public int getWidth() {
        return width;
    }
    
}
