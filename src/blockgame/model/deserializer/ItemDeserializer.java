/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockgame.model.deserializer;

import blockgame.model.Block;
import blockgame.model.Item;
import blockgame.model.ItemType;
import blockgame.model.Material;
import blockgame.model.Sapling;
import blockgame.model.Tool;
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
        
        // variables all items:
        int id = jObject.get("id").getAsInt();
        int maxStackSize = jObject.get("maxStackSize").getAsInt();
        double strength = jObject.get("strength").getAsDouble();
        JsonElement jType = jObject.get("type");
        ItemType type = context.deserialize(jType, ItemType.class);
        String name = jObject.get("name").getAsString();
        String texture = jObject.get("texture").getAsString();
        
        // block
        double health = jObject.get("health").getAsDouble();
        double hardness = jObject.get("hardness").getAsDouble();
        int dropId = jObject.get("dropId").getAsInt();
        JsonElement jDropType = jObject.get("dropType");
        ItemType dropType = context.deserialize(jDropType, ItemType.class);
        Boolean visible = jObject.get("visible").getAsBoolean();
        String placeSound = jObject.get("placeSound").getAsString();
        String breakSound = jObject.get("breakSound").getAsString();
        
        // Sapling (block)
        int woodId = jObject.get("woodId").getAsInt();
        int leavesId = jObject.get("leavesId").getAsInt();
        double progress = jObject.get("progress").getAsDouble();
        
        // material and tool: no special variables

        
        if (type == ItemType.block) {
            if(jObject.get("woodId") == null){
                Block b = new Block(texture, health, hardness, id, dropId, dropType, name, placeSound, breakSound);
                b.setVisible(visible);
                return b;
            }
            else{
                Sapling s = new Sapling(texture, health, hardness, id, dropId, dropType, name, placeSound, breakSound, woodId, leavesId);
                s.setVisible(visible);
                return s;
            }
            
        }
        else if (type == ItemType.material) {
            Material m = new Material(id, name, texture);
            return m;
        }
        else if (type == ItemType.tool) {
            Tool t = new Tool(id, strength, name, texture);
            return t;
        }
        else return null;
    }
    
    
}
