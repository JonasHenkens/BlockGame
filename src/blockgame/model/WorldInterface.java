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
import blockgame.model.deserializer.TimeDeserializer;
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
     * @param name The name of the world that will be loaded.
     * @return The world connected to the name. Returns null if it doesn't exist.
     */
    public World getWorld(String name){
        try {
            GsonBuilder gsonBouwer = new GsonBuilder();
            gsonBouwer.registerTypeAdapter(Item.class, new ItemDeserializer());
            gsonBouwer.registerTypeAdapter(Time.class, new TimeDeserializer());
            Gson gson2 = gsonBouwer.create();
            World newWorld = gson2.fromJson(new FileReader("src/blockgame/worlds/" + name + "/world.json"),World.class);
            return newWorld;
        } 
        catch (FileNotFoundException ex) {
            // file not found, return null
            return null;
        }
    }

    /**
     * 
     * @param name The name of the world that the person is in.
     * @return The person connected to the name. Returns null if it doesn't exist.
     */
    public Person getPerson(String name) {
        try {
            GsonBuilder gsonBouwer = new GsonBuilder();
            gsonBouwer.registerTypeAdapter(Person.class, new PersonDeserializer());
            gsonBouwer.registerTypeAdapter(Inventory.class, new InventoryDeserializer());
            gsonBouwer.registerTypeAdapter(ItemStack.class, new ItemStackDeserializer());
            Gson gson2 = gsonBouwer.create();
            Person newPerson = gson2.fromJson(new FileReader("src/blockgame/worlds/" + name + "/person.json"),Person.class);
            return newPerson;
        } 
        catch (FileNotFoundException ex) {
            // file not found, return null
            return null;
        }
    }
    
}
