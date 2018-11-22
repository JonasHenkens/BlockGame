/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockgame.model;

import blockgame.View.BlockGameView;
import blockgame.thread.PersonMovement;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * In BlockGame all the big views get put together.
 * @author Jonas
 */
public class BlockGame {
    private World world;
    private GUITop guiTop;
    private Person person;
    private BlockGameView view;

    /**
     * Constructor for BlockGame.
     * @param world The world that will be loaded.
     * @param person The person that will be loaded.
     */
    public BlockGame(World world, Person person) {
        this.person = person;
        this.world = world;
        guiTop = new GUITop(person);
        
        view = new BlockGameView(this, world, guiTop, person);
        
        // Start the thread that manages the person's movement
        PersonMovement pm = new PersonMovement(person, view, world);
        Thread t = new Thread(pm);
        t.setDaemon(true);
        t.start();
        
        
    }
    
    /**
     * Checks on which element has been clicked and runs its clicked function using relative coordinates.
     * @param x The x coordinate of the mouseclick.
     * @param y The y coordinate of the mouseclick.
     */
    public void leftClick(double x, double y){
        if(x <= guiTop.getWidth() && y <= guiTop.getHeight()){
            // clicked on GUITop
            guiTop.leftClick(x, y);
        }
        else if(x <= world.getSizeX()*world.getTextureResolution() && (y - guiTop.getHeight()) <= world.getSizeY()*world.getTextureResolution()){
            // clicked on world
            // check if in range of person
            // middle coordinates of person
            double personMiddleX = person.getX() + person.getWidth()/world.getTextureResolution()/2;
            double personMiddleY = person.getY() + person.getHeight()/world.getTextureResolution()/2;
            // x and y of click in blocks
            double x2 = x/world.getTextureResolution();
            double y2 = (y-guiTop.getHeight())/world.getTextureResolution();
            
            // calculate distance using formula
            double distance = Math.sqrt(Math.pow((personMiddleX-x2), 2) + Math.pow((personMiddleY-y2), 2));
            
            if(distance < 3.5){
                // click is within range of person => can hit block
                int index = guiTop.getSelectedItem();
                int id = guiTop.getItemId(index);
                ItemType type = guiTop.getItemType(index);
                if(id == -1){
                    // no block in spot: use "noTexture" block
                    id = 0;
                    type = ItemType.block;
                }
                else{
                    // id and type are correct, nothing needs to change
                }
                Item item = world.hitBlock(x, y-guiTop.getHeight(), id, type);
                if(item != null){
                    boolean b = person.addItem(item.getId(), item.getItemType(), 1);
                }
            }
        }
        else{
            System.out.println("Clicked out of bounds.");
        }
        
        updateGui();
    }
    
    /**
     * Checks on which element has been clicked and runs its clicked function using relative coordinates.
     * @param x The x coordinate of the mouseclick.
     * @param y The y coordinate of the mouseclick.
     */
    public void rightClick(double x, double y){
        if(x <= guiTop.getWidth() && y <= guiTop.getHeight()){
            
        }
        else if(x <= world.getSizeX()*world.getTextureResolution() && (y - guiTop.getHeight()) <= world.getSizeY()*world.getTextureResolution()){
            // clicked on world
            // check if in range of person
            // middle coordinates of person
            double personMiddleX = person.getX() + person.getWidth()/world.getTextureResolution()/2;
            double personMiddleY = person.getY() + person.getHeight()/world.getTextureResolution()/2;
            // x and y of click in blocks
            double x2 = x/world.getTextureResolution();
            double y2 = (y-guiTop.getHeight())/world.getTextureResolution();
            
            // calculate distance using formula
            double distance = Math.sqrt(Math.pow((personMiddleX-x2), 2) + Math.pow((personMiddleY-y2), 2));
            
            if(distance < 3.5){
                // click is within range of person => can place block
                int index = guiTop.getSelectedItem();
                int id = guiTop.getItemId(index);
                ItemType type = guiTop.getItemType(index);
                if(type == ItemType.block){
                    // kan enkel blokken plaatsen
                    if(person.getInventoryAmount(id, type) >= 1){
                        boolean geplaatst = world.placeBlock(x, y-guiTop.getHeight(), id, type);
                        if(geplaatst){
                            person.removeInventoryItem(id, type, 1);
                        }
                    }
                    else{
                        // block is niet uit inventory verwijderd => niet plaatsen
                    }
                }
            }
        }
        else{
            System.out.println("Clicked out of bounds.");
        }
        updateGui();
    }
    
    /**
     * Exports the currently loaded world.
     * @param name The name the world will be called.
     */
    public void exportWorld(String name){
        // export world and player to file
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonWorld = gson.toJson(world);
        String jsonPerson = gson.toJson(person);
        
        try{
            File path = new File("src/blockgame/worlds/" + name + "/");
            if(!path.exists()){
                path.mkdirs();
            }
            
            // write world to file
            JsonWriter jsonWriter = gson.newJsonWriter(new FileWriter("src/blockgame/worlds/" + name + "/world.json"));
            jsonWriter.jsonValue(jsonWorld);
            jsonWriter.close();
            // write person to file
            jsonWriter = gson.newJsonWriter(new FileWriter("src/blockgame/worlds/" + name + "/person.json"));
            jsonWriter.jsonValue(jsonPerson);
            jsonWriter.close();
            
        }
        catch(IOException e){
            
        }
    }
    
    /**
     * Loads the world with name.
     * @param name The name of the world that will be loaded.
     */
    public void loadWorld(String name){
        try {
            WorldInterface wi = new WorldInterface();
            World newWorld = wi.getWorld(name);
            Person newPerson = wi.getPerson(name);
            world.renew(newWorld);
            person.renew(newPerson);
        } 
        catch (NullPointerException e) {
            System.out.println("ERROR: BlockGame.loadWorld NullPointer");
        }
        updateGui();
        view.update();
    }
    
    
    /**
     * Moves the person.
     * @param dx The change in the x direction in blocks. Right is positive.
     * @param dy The change in the y direction in blocks. Down is positive.
     */
    public void movePerson(double dx, double dy){
        person.move(dx, dy);
    }
    
    /**
     * Changes the speed of the person.
     * @param dvx The change in speed in the x direction in blocks/s.
     * @param dvy The change in speed in the y direction in blocks/s.
     */
    public void changePersonSpeed(double dvx, double dvy){
        person.changeSpeed(dvx, dvy);
    }
    
    /**
     * Updates the Gui.
     */
    public void updateGui(){
        guiTop.updateItems();
    }
    
    public void secPlusEen() {
        world.secPlusEen();
    }
    
    // getters
    
    /**
     * @return The rib of the block's texture in amount of pixels.
     */
    public int getTextureResolution() {
        return world.getTextureResolution();
    }
    
    /**
     * @return The x coordinate of the person in blocks.
     */
    public double getPersonX(){
        return person.getX();
    }
    
    /**
     * @return The y coordinate of the person in blocks.
     */
    public double getPersonY(){
        return person.getY();
    }
    
    /**
     * @return The texture of the person.
     */
    public String getPersonTexture(){
        return person.getTexture();
    }

    /**
     * @return The view of BlockGame.
     */
    public BlockGameView getView() {
        return view;
    }

    /**
     * @return The heigth of the GUITop.
     */
    public double getGUITopHeight() {
        return guiTop.getHeight();
    }

    /**
     * @return The size of the world in the x direction in blocks.
     */
    public int getWorldSizeX() {
        return world.getSizeX();
    }

    /**
     * @return The size of the world in the y direction in blocks.
     */
    public int getWorldSizeY() {
        return world.getSizeY();
    }

    /**
     * @return The speed of the person in the y direction in blocks/s.
     */
    public double getPersonVy() {
        return person.getVy();
    }

    /**
     * @return The speed of the person in the x direction in blocks/s.
     */
    public double getPersonVx() {
        return person.getVx();
    }
    
    /**
     * TODO
     * @return hour
     */
    public int getHour(){
        return world.getHour();
    }
    
    /**
     *TODO
     * @return min
     */
    public int getMin(){
        return world.getMin();
    }
    
    /**
     * TODO
     * @return sec
     */
    public int getSec(){
        return world.getSec();
    }
    
}
