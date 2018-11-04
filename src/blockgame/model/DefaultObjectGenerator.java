/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockgame.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Jonas
 */
public class DefaultObjectGenerator {
    private Block[] blocks;
    
    public DefaultObjectGenerator() {
    }
    
    
    public void generateAllOverwrite(){
        generateBlocks();
    }
    
    public void generateAllNoOverwrite(){
        // Bron voor controleren of bestand al bestaat: https://stackoverflow.com/questions/6824987/how-to-check-if-a-file-exists
        File b = new File("src/blockgame/objects/blocks.json");
        if(!b.exists()){
            generateBlocks();
        }
    }
    
    public void generateBlocks(){
        blocks = new Block[1024];
        blocks[0] = new Block("blockgame/textures/blocks/noTexture.png", 20, 3, 0, 0, "noTexture", 100);
        blocks[1] = new Block("blockgame/textures/blocks/dirt.png", 1, 1, 1, 1, "dirt", 100);
        blocks[2] = new Block("blockgame/textures/blocks/grass.png", 1, 1.25, 2, 2, "grass", 100);
        blocks[3] = new Block("blockgame/textures/blocks/stone.png", 10, 12, 3, 3, "stone", 100);
        blocks[4] = new Block("blockgame/textures/blocks/pink.png", 10, 12, 4, 4, "pink", 100);
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(blocks);
        
        try{
            JsonWriter jsonWriter = gson.newJsonWriter(new FileWriter("src/blockgame/objects/blocks.json"));
            jsonWriter.jsonValue(json);
            jsonWriter.close();
        }
        catch(IOException e){
            
        }
    }
    
    public void generateWorlds(){
        
    }
    
}
