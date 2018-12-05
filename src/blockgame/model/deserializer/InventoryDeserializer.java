/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockgame.model.deserializer;

import blockgame.model.Inventory;
import blockgame.model.ItemStack;
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
public class InventoryDeserializer implements JsonDeserializer<Inventory> {

    @Override
    public Inventory deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jObject = json.getAsJsonObject();
        
        // bron deserialize nested objects: http://www.javacreed.com/gson-deserialiser-example/
        ItemStack[] inventory = context.deserialize(jObject.get("inventory"), ItemStack[].class);
        Inventory inv = new Inventory(inventory);
        return inv;
        
        
    }
    
}
