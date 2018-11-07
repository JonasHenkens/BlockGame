/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockgame.model;

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

    public BlockGame() {
        person = new Person(0, 0, "blockgame/textures/person.png");
        world = new World(96, 48, 16);
        guiTop = new GUITop();
    }

    /**
     * @return The currently loaded world.
     */
    public World getWorld() {
        return world;
    }
    /**
     * @return The guiTop used.
     */
    public GUITop getGuiTop() {
        return guiTop;
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
            world.leftClick(x, y-guiTop.getHeight(), guiTop.getSelectedBlock());
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
            world.rightClick(x, y-guiTop.getHeight(), guiTop.getSelectedBlock());
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
     * @return The x coordinate of the person.
     */
    public double getPersonX(){
        return person.getX();
    }
    
    /**
     * 
     * @return The y coordinate of the person.
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
     * Moves the person.
     * @param dx The change in the x direction. Right is positive.
     * @param dy The change in the y direction. Down is positive.
     */
    public void movePerson(double dx, double dy){
        person.move(dx, dy);
    }
    
    /**
     * Makes the person jump
     * @param speed The speed of the person's jump upwards.
     */
    public void jump(double speed){
        person.jump(speed);
    }
    
    
    
    
    
    
    
    
    
}
