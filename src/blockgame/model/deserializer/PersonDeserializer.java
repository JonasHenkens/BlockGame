/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockgame.model.deserializer;

import blockgame.model.Inventory;
import blockgame.model.Person;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;

/**
 * Used to deserialize Person.
 * @author Jonas
 */
public class PersonDeserializer implements JsonDeserializer<Person>{

    @Override
    public Person deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        
        JsonObject jObject = json.getAsJsonObject();
        
        // bron deserialize nested objects: http://www.javacreed.com/gson-deserialiser-example/
        Inventory inventory = context.deserialize(jObject.get("inventory"), Inventory.class);
        double x = jObject.get("x").getAsDouble();
        double y = jObject.get("y").getAsDouble();
        double spawnX = jObject.get("spawnX").getAsDouble();
        double spawnY = jObject.get("spawnY").getAsDouble();
        double health = jObject.get("health").getAsDouble();
        double maxHealth = jObject.get("maxHealth").getAsDouble();
        String texture = jObject.get("texture").getAsString();
        int experience = jObject.get("experience").getAsInt();
        double vx = jObject.get("vx").getAsDouble();
        double vy = jObject.get("vy").getAsDouble();
        Person person = new Person(inventory, x, y, spawnX, spawnY, health, maxHealth, texture, experience, vx, vy);
        return person;
        
    }
    
    
    
    
}
