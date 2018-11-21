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
        System.out.println(jObject);
        // extract variables from json object
        ItemType itemType;
        String type = jObject.get("type").getAsString();
        System.out.println(type);
        
        if(type.equals(ItemType.block.toString())){
            itemType = ItemType.block;
        }
        else if(type.equals(ItemType.nothing.toString())){
            itemType = ItemType.nothing;
        }
        else {
            return null;
        }
        
        int id = jObject.get("id").getAsInt();
        int amount = jObject.get("amount").getAsInt();
        
        return new ItemStack(id, itemType, amount);
    }
    
    
}
