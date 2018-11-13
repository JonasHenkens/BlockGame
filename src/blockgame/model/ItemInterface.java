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
 * ItemInterface is used to request items by id and type.
 * @author Jonas
 */
public class ItemInterface {
    private Block[] blocks;
    
    /**
     * Constructor of ItemInterface.
     */
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
     * @param id Id of the block.
     * @return The block with the id. Returns block with id 0 if it doesn't exist.
     */
    public Block getBlock(int id){
        try{
            return new Block(blocks[id].getTexture(), blocks[id].getHealth(), blocks[id].getHardness(), blocks[id].getId(), blocks[id].getDropId(), blocks[id].getName());
        }
        catch(NullPointerException e){
            return new Block(blocks[0].getTexture(), blocks[0].getHealth(), blocks[0].getHardness(), blocks[0].getId(), blocks[0].getDropId(), blocks[0].getName());
        }
    }
    
    /**
     * @return The amount of blocks that exist.
     */
    public int getBlocksLength(){
        return blocks.length;
    }
    
    /**
     * @param itemId The id of the item.
     * @param type The type of the item.
     * @return The Item with the id and type. Returns null if it doesn't exist.
     */
    public Item getItem(int itemId, ItemType type){
        if(type.equals(ItemType.block)){
            return blocks[itemId];
        }
        else{
            return null;
        }
    }
    
    public int getMaxStacksize(int id, ItemType type){
         if(type.equals(ItemType.block)){
            return blocks[id].getMaxStackSize();
        }
        else{
            return -1;
        }
    }
}
