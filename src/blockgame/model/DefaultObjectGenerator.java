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
 * Used to generate the default object files.
 * @author Jonas
 */
public class DefaultObjectGenerator {
    private Block[] blocks;
    
    /**
     * Constructor of the DefaultObjectGenerator.
     */
    public DefaultObjectGenerator() {
    }
    
    /**
     * Generates all files, overwrites existing files.
     */
    public void generateAllOverwrite(){
        generateBlocks();
    }
    
    /**
     * Generates all files that do not exist yet.
     */
    public void generateAllNoOverwrite(){
        // Bron voor controleren of bestand al bestaat: https://stackoverflow.com/questions/6824987/how-to-check-if-a-file-exists
        File b = new File("src/blockgame/objects/blocks.json");
        if(!b.exists()){
            generateBlocks();
        }
    }
    
    /**
     * Generates the "blocks.json" file.
     */
    public void generateBlocks(){
        File directory = new File("src/blockgame/objects");
        
        if(!directory.exists()){
            directory.mkdir();
        }
        
        blocks = new Block[1024];
        addBlock(new Block("blockgame/textures/blocks/noTexture.png", 20, 3, 0, 0, "noTexture"));
        addBlock(new Block("blockgame/textures/blocks/dirt.png", 1, 1, 1, 1, "dirt"));
        addBlock(new Block("blockgame/textures/blocks/grass.png", 1, 1.25, 2, 2, "grass"));
        addBlock(new Block("blockgame/textures/blocks/stone.png", 5, 3, 3, 3, "stone"));
        addBlock(new Block("blockgame/textures/blocks/pink.png", 10, 12, 4, 4, "pink"));
        
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
    
    /**
     * Adds a block to the blocks list. This replaces an already existing block if the same id is used.
     * @param block The block that will be added.
     */
    public void addBlock(Block block){
        
        if(blocks[block.getId()] != null){
            System.err.println("WARNING: Block with id " + block.getId() + "has been replaced");
        }
        blocks[block.getId()] = block;
        
    }
    
}
