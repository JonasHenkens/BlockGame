/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockgame.model;

import blockgame.model.Block;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * ItemInterface is used to request items by id and type
 * @author Jonas
 */
public class ItemInterface {
    private Block[] blocks;
    
    public ItemInterface() {
        try {
            GsonBuilder gsonBouwer = new GsonBuilder();
            gsonBouwer.registerTypeAdapter(Item.class, new ItemDeserializer());
            Gson gson2 = gsonBouwer.create();
            blocks = gson2.fromJson(new FileReader("src/blockgame/objects/blocks.json"),Block[].class);
        } 
        catch (FileNotFoundException e) {
            DefaultObjectGenerator dog = new DefaultObjectGenerator();
            dog.generateAllNoOverwrite();
            try {
                GsonBuilder gsonBouwer = new GsonBuilder();
                gsonBouwer.registerTypeAdapter(Item.class, new ItemDeserializer());
                Gson gson2 = gsonBouwer.create();
                blocks = gson2.fromJson(new FileReader("src/blockgame/objects/blocks.json"),Block[].class);
            } 
            catch (FileNotFoundException ex) {
                System.out.println("ERROR: This shouldn't happen.");
            }
        }
    }
    
    /**
     * @param n index of block also called the "id"
     * @return block located at index n
     */
    public Block getBlock(int n){
        try{
            return new Block(blocks[n].getTexture(), blocks[n].getHealth(), blocks[n].getHardness(), blocks[n].getId(), blocks[n].getDropId(), blocks[n].getName(), blocks[n].getMaxStackSize());
        }
        catch(NullPointerException e){
            return new Block(blocks[0].getTexture(), blocks[0].getHealth(), blocks[0].getHardness(), blocks[0].getId(), blocks[0].getDropId(), blocks[0].getName(), blocks[0].getMaxStackSize());
        }
    }
    
    public int getBlocksLength(){
        return blocks.length;
    }
    
}
