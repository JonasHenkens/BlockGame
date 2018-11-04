/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockgame.model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;

/**
 *
 * @author Jonas
 */
public class ItemStackDeserializer implements JsonDeserializer<ItemStack>{

    @Override
    public ItemStack deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jObject = json.getAsJsonObject();
        
        // bron deserialize nested objects: http://www.javacreed.com/gson-deserialiser-example/
        Item item = context.deserialize(jObject.get("item"), Item.class);
        int amount = jObject.get("amount").getAsInt();
        
        return new ItemStack(item, amount);
    }
    
    
    
    
}
