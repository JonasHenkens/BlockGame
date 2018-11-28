/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockgame.model;

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
        blocks = new Block[1024];
        addBlock(new Block("blockgame/textures/blocks/noTexture.png", 20, 3, 0, 0, ItemType.block, "noTexture"));
        addBlock(new Block("blockgame/textures/blocks/dirt.png", 1, 1, 1, 1, ItemType.block, "dirt"));
        addBlock(new Block("blockgame/textures/blocks/grass_side.png", 1, 1.25, 2, 2, ItemType.block, "grass"));
        addBlock(new Block("blockgame/textures/blocks/stone.png", 5, 3, 3, 3, ItemType.block, "stone"));
        addBlock(new Block("blockgame/textures/blocks/coal_ore.png", 5, 3, 4, 4, ItemType.block, "coal ore"));
        addBlock(new Block("blockgame/textures/blocks/iron_ore.png", 7, 4, 5, 5, ItemType.block, "iron ore"));
        addBlock(new Block("blockgame/textures/blocks/diamond_ore.png", 15, 10, 6, 6, ItemType.block, "diamond ore"));
        addBlock(new Block("blockgame/textures/blocks/gold_ore.png", 8, 4, 7, 7, ItemType.block, "gold ore"));
    }
    
    /**
     * @param id Id of the block.
     * @return The block with the id. Returns block with id 0 if it doesn't exist.
     */
    public Block getBlock(int id){
        try{
            return new Block(blocks[id].getTexture(), blocks[id].getHealth(), blocks[id].getHardness(), blocks[id].getId(), blocks[id].getDropId(), blocks[id].getItemType(), blocks[id].getName());
        }
        catch(NullPointerException e){
            return new Block(blocks[0].getTexture(), blocks[0].getHealth(), blocks[0].getHardness(), blocks[0].getId(), blocks[0].getDropId(), blocks[0].getItemType(), blocks[0].getName());
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
    
    /**
     * @param id The id of the item.
     * @param type The type of the item.
     * @return The max stacksize of the item. Returns -1 if item does not exist.
     */
    public int getMaxStacksize(int id, ItemType type){
         if(type.equals(ItemType.block)){
            return blocks[id].getMaxStackSize();
        }
        else{
            return -1;
        }
    }
    
    
    
}
