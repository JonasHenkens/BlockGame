/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockgame.model.deserializer;

import blockgame.model.Block;
import blockgame.model.Block;
import blockgame.model.Item;
import blockgame.model.ItemType;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;

/**
 * Used to deserialize items.
 * @author Jonas
 */
public class ItemDeserializer implements JsonDeserializer<Item>{

    @Override
    public Item deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        
        JsonObject jObject = json.getAsJsonObject();
        // extract variables from json object
        // bron deserialize nested objects: http://www.javacreed.com/gson-deserialiser-example/
        String texture = jObject.get("texture").getAsString();
        double health = jObject.get("health").getAsDouble();
        double hardness = jObject.get("hardness").getAsDouble();
        int id = jObject.get("id").getAsInt();
        
        JsonElement jType = jObject.get("type");
        ItemType type = context.deserialize(jType, ItemType.class);
        
        int dropId = jObject.get("dropId").getAsInt();
        
        JsonElement jDropType = jObject.get("dropType");
        ItemType dropType = context.deserialize(jDropType, ItemType.class);
        
        String name = jObject.get("name").getAsString();
        Boolean visible = jObject.get("visible").getAsBoolean();
        
        
        
        if (jType.getAsString().equals("block")) {
            Block b = new Block(texture, health, hardness, id, dropId, dropType, name);
            b.setVisible(visible);
            return b;
        }
        else return null;
    }
    
    
}
