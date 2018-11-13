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
        
        view = new BlockGameView(this, world, guiTop);
        
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
            guiTop.leftClick(x, y);
        }
        else if(x <= world.getSizeX()*world.getTextureResolution() && (y - guiTop.getHeight()) <= world.getSizeY()*world.getTextureResolution()){
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
            world.leftClick(x, y-guiTop.getHeight(), id, type);
        }
        else{
            System.out.println("Clicked out of bounds.");
        }
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
            int index = guiTop.getSelectedItem();
            int id = guiTop.getItemId(index);
            ItemType type = guiTop.getItemType(index);
            world.rightClick(x, y-guiTop.getHeight(), id, type);
        }
        else{
            System.out.println("Clicked out of bounds.");
        }
    }
    
    /**
     * Exports the currently loaded world.
     * @param name The name the world will be called.
     */
    public void exportWorld(String name){
        System.out.println("Currently not working anymore");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(world);
        
        try{
            JsonWriter jsonWriter = gson.newJsonWriter(new FileWriter("src/blockgame/worlds/" + name + ".json"));
            jsonWriter.jsonValue(json);
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
        System.out.println("Currently not working anymore");
        try {
            WorldInterface wi = new WorldInterface();
            World newWorld = wi.getWorld(name);
            world.renew(newWorld);
        } 
        catch (NullPointerException e) {
            System.out.println("ERROR: Couldn't find file.");
        }
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
    
    
    
}
