/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockgame.model;

import blockgame.model.deserializer.ItemDeserializer;
import blockgame.model.deserializer.PersonDeserializer;
import blockgame.model.deserializer.InventoryDeserializer;
import blockgame.model.deserializer.ItemStackDeserializer;
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
            System.out.println("ERROR: Couldn't find default world file.");
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
            World newWorld = gson2.fromJson(new FileReader("src/blockgame/worlds/" + name + "/world.json"),World.class);
            return newWorld;
        } 
        catch (FileNotFoundException ex) {
            System.out.println("ERROR: Couldn't find world file.");
            return null;
        }
    }

    Person getPerson(String name) {
            System.out.println("A");
        try {
            GsonBuilder gsonBouwer = new GsonBuilder();
            gsonBouwer.registerTypeAdapter(Person.class, new PersonDeserializer());
            gsonBouwer.registerTypeAdapter(Inventory.class, new InventoryDeserializer());
            gsonBouwer.registerTypeAdapter(ItemStack.class, new ItemStackDeserializer());
            Gson gson2 = gsonBouwer.create();
            Person newPerson = gson2.fromJson(new FileReader("src/blockgame/worlds/" + name + "/person.json"),Person.class);
            System.out.println("B");
            return newPerson;
        } 
        catch (FileNotFoundException ex) {
            System.out.println("ERROR: Couldn't find person file.");
            return null;
        }
    }
    
}
