/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockgame.model;

import blockgame.model.Block;
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
public class ItemDeserializer implements JsonDeserializer<Item>{

    @Override
    public Item deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        
        JsonObject jObject = json.getAsJsonObject();
        JsonElement jTexture = jObject.get("texture");
        JsonElement jHealth = jObject.get("health");
        JsonElement jHardness = jObject.get("hardness");
        JsonElement jId = jObject.get("id");
        JsonElement jDropId = jObject.get("dropId");
        JsonElement jType = jObject.get("type");
        JsonElement jName = jObject.get("name");
        JsonElement jVisible = jObject.get("visible");
        JsonElement jMaxStackSize = jObject.get("maxStackSize");
        
        
        if (jType.getAsString().equals("blocks")) {
            Block b = new Block(jTexture.getAsString(), jHealth.getAsDouble(), jHardness.getAsDouble(), jId.getAsInt(), jDropId.getAsInt(), jName.getAsString(), jMaxStackSize.getAsInt());
            b.setVisible(jVisible.getAsBoolean());
            return b;
        }
        else return null;
    }
    
    
}
