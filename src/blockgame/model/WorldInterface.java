/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockgame.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 *
 * @author Jonas
 */
public class WorldInterface {

    /**
     * Constructor of the WorldInterface.
     */
    public WorldInterface() {
    }
    /**
     * @return The default world. Returns null if world doesn't exist.
     */
    public World getDefaultWorld(){
        try {
            
            GsonBuilder gsonBouwer = new GsonBuilder();
            gsonBouwer.registerTypeAdapter(Item.class, new ItemDeserializer());
            gsonBouwer.registerTypeAdapter(Person.class, new PersonDeserializer());
            gsonBouwer.registerTypeAdapter(ItemStack.class, new ItemStackDeserializer());
            Gson gson = gsonBouwer.create();
            World world = gson.fromJson(new FileReader("src/blockgame/objects/defaultWorld.json"),World.class);
            
            return world;
        } 
        catch (FileNotFoundException ex) {
            System.out.println("ERROR: Couldn't find file.");
            return null;
        }
    }
    
    /**
     * @param name The name of the world that will be loaded.
     * @return The world connected to the name. Returns null if it doesn't exist.
     */
    public World getWorld(String name){
        try {
            GsonBuilder gsonBouwer = new GsonBuilder();
            gsonBouwer.registerTypeAdapter(Item.class, new ItemDeserializer());
            Gson gson2 = gsonBouwer.create();
            World newWorld = gson2.fromJson(new FileReader("src/blockgame/worlds/" + name + ".json"),World.class);
            return newWorld;
        } 
        catch (FileNotFoundException ex) {
            System.out.println("ERROR: Couldn't find file.");
            return null;
        }
    }
    
}
