/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockgame.model.deserializer;

import blockgame.model.ItemStack;
import blockgame.model.ItemType;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;

/**
 * Used to deserialize ItemStack.
 * @author Jonas
 */
public class ItemStackDeserializer implements JsonDeserializer<ItemStack>{

    @Override
    public ItemStack deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jObject = json.getAsJsonObject();
        // extract variables from json object
        
        int id = jObject.get("id").getAsInt();
        JsonElement jType = jObject.get("type");
        ItemType type = context.deserialize(jType, ItemType.class);
        int amount = jObject.get("amount").getAsInt();
        
        
        return new ItemStack(id, type, amount);
    }
    
    
}
