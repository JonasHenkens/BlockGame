/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockgame.model.deserializer;

import blockgame.model.Time;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;

/**
 *
 * @author Souhaib El Habti
 */
public class TimeDeserializer implements JsonDeserializer<Time>{

    @Override
    public Time deserialize(JsonElement js, Type type, JsonDeserializationContext jdc) throws JsonParseException {
        JsonObject jObject = js.getAsJsonObject();
        System.out.println(jObject);
        int sec = jObject.get("sec").getAsInt();
        int min = jObject.get("min").getAsInt();
        int hour = jObject.get("hour").getAsInt();
        Time time = new Time(sec,min,hour);
        return time;
    }
    
}
