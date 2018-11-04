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
 *
 * @author Jonas
 */
public class BlockGame {
    private World world;
    private GUITop guiTop;

    public BlockGame() {
        Person person = new Person(0, 0, "blockgame/textures/person.png");
        world = new World(96, 48, 16, person);
        guiTop = new GUITop();
    }

    /**
     * @return the currently loaded world
     */
    public World getWorld() {
        return world;
    }
    /**
     * @return the guiTop
     */
    public GUITop getGuiTop() {
        return guiTop;
    }
    
    /**
     * @param x the x coordinate of the mouseclick
     * @param y the y coordinate of the mouseclick
     */
    public void leftClick(double x, double y){
        
        if(x <= guiTop.getWidth() && y <= guiTop.getHeight()){
            guiTop.leftClick(x, y);
        }
        else if(x <= world.getWidth() && (y - guiTop.getHeight()) <= world.getHeight()){
            world.leftClick(x, y-guiTop.getHeight(), guiTop.getSelectedBlock());
        }
        else{
            System.out.println("Clicked out of bounds.");
        }
        
    }
    
    /**
     * @param x the x coordinate of the mouseclick
     * @param y the y coordinate of the mouseclick
     */
    public void rightClick(double x, double y){
        if(x <= guiTop.getWidth() && y <= guiTop.getHeight()){
            
        }
        else if(x <= world.getWidth() && (y + guiTop.getHeight()) <= world.getHeight()){
            world.rightClick(x, y-guiTop.getHeight(), guiTop.getSelectedBlock());
        }
        else{
            System.out.println("Clicked out of bounds.");
        }
    }
    
    
    
    
    /**
     * exports the currently loaded world
     * @param name the name the world will be called
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
     * load the world with name
     * @param name the name of the world that should be loaded
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
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
