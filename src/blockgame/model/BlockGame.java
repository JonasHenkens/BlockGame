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
import java.util.ArrayList;
import java.util.Iterator;
import javafx.application.Platform;

/**
 * In BlockGame all the models get put together.
 * @author Jonas and Souhaib
 */
public class BlockGame {
    private World world;
    private GUITop guiTop;
    private Person person;
    private BlockGameView view;
    private ArrayList<Key> keysBeingHeld;
    private Crafting craft;

    /**
     * Constructor for BlockGame.
     * @param world The world that will be loaded.
     * @param person The person that will be loaded.
     */
    public BlockGame(World world, Person person) {
        this.person = person;
        this.world = world;
        guiTop = new GUITop(person);
        craft = new Crafting(person, this);
        
        view = new BlockGameView(this, world, guiTop, person, craft);
        
        // Start the thread that manages the person's movement
        PersonMovement pm = new PersonMovement(person, view, world, this);
        Thread t = new Thread(pm);
        t.setDaemon(true);
        t.start();
        
        keysBeingHeld = new ArrayList<>();
        
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
            //Clicked out of bounds."
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
            // clicked on GUITop
            guiTop.rightClick(x, y);
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
            
            if(distance < 3.5 && !doesBlockOverlapPerson((int)Math.floor(x2), (int)Math.floor(y2))){
                // click is within range of person => can place block
                // block wont overlap with person
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
            //Clicked out of bounds.
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
            // world does not exist
        }
        updateGui();
        view.update();
    }
    
    /**
     * Resets the person and world.
     */
    public void resetWorld(){
        world.loadNewWorld();
        person.renew(new Person(0, 0, "blockgame/textures/person.png"));
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
    
    /**
     * Adds one second to the time of the world.
     */
    public void secPlusEen() {
        world.secPlusEen();
    }
    
    
    
    /**
     * Checks if a block would overlap with the person.
     * @param x The x coordinate of the block in blocks.
     * @param y The y coordinate of the block in blocks.
     * @return True if it overlaps, False if it doesn't.
     */
    public boolean doesBlockOverlapPerson(int x, int y){
        // check if corners are inside person => block overlaps person
        // else not overlapping
        
        double pXMin = person.getX();
        double pXMax = pXMin + person.getWidth()/16;
        double pYMin = person.getY();
        double pYMax = pYMin + person.getHeight()/16;
        
        // corner 1: x,y
        if(pXMin < x && x < pXMax && pYMin < y && y < pYMax){
            return true;
        }
        // corner 2: x+1, y
        if(pXMin < x+1 && x+1 < pXMax && pYMin < y && y < pYMax){
            return true;
        }
        // corner 3: x, y+1
        if(pXMin < x && x < pXMax && pYMin < y+1 && y+1 < pYMax){
            return true;
        }
        // corner 4: x+1, y+1
        if(pXMin < x+1 && x+1 < pXMax && pYMin < y+1 && y+1 < pYMax){
            return true;
        }
        // none of the corners are in the person so not overlapping
        return false;
    }
    
    
    /**
     * @param key The key being held.
     */
    public void addKeyBeingHeld(Key key){
        if(!keysBeingHeld.contains(key)){
            keysBeingHeld.add(key);
        }
    }
    
    /**
     * @param key The key not being held anymore.
     */
    public void removeKeyBeingHeld(Key key){
        if(keysBeingHeld.contains(key)){
            keysBeingHeld.remove(key);
        }
    }
    
    /**
     * Runs code that has to happen each tick.
     */
    public void tick() {
        boolean saplingsUpdated = world.updateSaplings();
        if(saplingsUpdated){
            
            Platform.runLater( () -> view.update() );
        }
    }
    
    
    // setters

    /**
     * @param vx The new speed in the x direction in blocks/sec.
     */
    public void setVx(double vx) {
        person.setVx(vx);
    }

    /**
     * @param vy The new speed in the y direction in blocks/sec.
     */
    public void setVy(double vy) {
        person.setVy(vy);
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
     * @return The current hour of the world.
     */
    public int getHour(){
        return world.getHour();
    }
    
    /**
     * @return The current minute of the world.
     */
    public int getMin(){
        return world.getMin();
    }
    
    /**
     * @return The current second of the world.
     */
    public int getSec(){
        return world.getSec();
    }
    
    /**
     * @return A iterator of all keys currently being held.
     */
    public Iterator<Key> getKeysBeingHeldIterator(){
        return keysBeingHeld.iterator();
    }

    /**
     * @return The craft model being used.
     */
    public Crafting getCraft() {
        return craft;
    }

    /**
     * @return The current person.
     */
    public Person getPerson() {
        return person;
    }
}
