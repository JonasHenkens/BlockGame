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
    private Material[] materials;
    private Tool[] tools;
    
    /**
     * Constructor of ItemInterface.
     */
    public ItemInterface() {
        blocks = new Block[1024];
        addBlock(new Block("blockgame/textures/blocks/noTexture.png", 20, 3, 0, 0, ItemType.block, "noTexture"));
        addBlock(new Block("blockgame/textures/blocks/dirt.png", 1, 1, 1, 1, ItemType.block, "dirt"));
        addBlock(new Block("blockgame/textures/blocks/grass_side.png", 1, 1.25, 2, 2, ItemType.block, "grass"));
        addBlock(new Block("blockgame/textures/blocks/stone.png", 5, 3, 3, 3, ItemType.block, "stone"));
        addBlock(new Block("blockgame/textures/blocks/coal_ore.png", 5, 3, 4, 3, ItemType.material, "coal ore"));
        addBlock(new Block("blockgame/textures/blocks/iron_ore.png", 7, 4, 5, 5, ItemType.block, "iron ore"));
        addBlock(new Block("blockgame/textures/blocks/diamond_ore.png", 15, 10, 6, 6, ItemType.block, "diamond ore"));
        addBlock(new Block("blockgame/textures/blocks/gold_ore.png", 8, 4, 7, 7, ItemType.block, "gold ore"));
        addBlock(new Block("blockgame/textures/blocks/log_oak.png", 4, 2, 8, 8, ItemType.block, "wood"));
        
        materials = new Material[1024];
        addMaterial(new Material(0, "iron ingot", "blockgame/textures/materials/iron_ingot.png"));
        addMaterial(new Material(1, "gold ingot", "blockgame/textures/materials/gold_ingot.png"));
        addMaterial(new Material(2, "diamond", "blockgame/textures/materials/diamond.png"));
        addMaterial(new Material(3, "coal", "blockgame/textures/materials/coal.png"));
        
        tools = new Tool[1024];
        
        
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
     * Add a material to the materials list. This replaces an already existing material if the same id is used.
     * @param material 
     */
    public void addMaterial(Material material){
        
        if(materials[material.getId()] != null){
            System.err.println("WARNING: Material with id " + material.getId() + "has been replaced");
        }
        materials[material.getId()] = material;
        
    }
    
    /**
     * Add a tool to the materials list. This replaces an already existing tool if the same id is used.
     * @param tool 
     */
    public void addTool(Tool tool){
        
        if(tools[tool.getId()] != null){
            System.err.println("WARNING: Tool with id " + tool.getId() + "has been replaced");
        }
        tools[tool.getId()] = tool;
        
    }
    
    /**
     * @param id Id of the block.
     * @return The block with the id. Returns block with id 0 if it doesn't exist.
     */
    public Block getBlock(int id){
        try{
            return new Block(blocks[id].getTexture(), blocks[id].getHealth(), blocks[id].getHardness(), blocks[id].getId(), blocks[id].getDropId(), blocks[id].getDropType(), blocks[id].getName());
        }
        catch(NullPointerException e){
            return new Block(blocks[0].getTexture(), blocks[0].getHealth(), blocks[0].getHardness(), blocks[0].getId(), blocks[0].getDropId(), blocks[0].getDropType(), blocks[0].getName());
        }
    }
    
    /**
     * @param id Id of the material.
     * @return The material with the id. Returns null if it doesn't exist.
     */
    public Material getMaterial(int id){
        try{
            return new Material(materials[id].getId(), materials[id].getName(), materials[id].getTexture());
        }
        catch(NullPointerException e){
            return null;
        }
    }
    
    /**
     * @param id Id of the tool.
     * @return The tool with the id. Returns null if it doesn't exist.
     */
    public Tool getTool(int id){
        try{
            return new Tool(tools[id].getId(), tools[id].getStrength(), tools[id].getName(), tools[id].getTexture(), tools[id].getMaxDurability());
        }
        catch(NullPointerException e){
            return null;
        }
    }
    
    /**
     * @return The amount of blocks that exist.
     */
    public int getBlocksLength(){
        return blocks.length;
    }
    
    /**
     * @return The amount of materials that exist.
     */
    public int getMaterialsLength(){
        return materials.length;
    }
    
    /**
     * @return The amount of tools that exist.
     */
    public int getToolsLength(){
        return tools.length;
    }
    
    /**
     * @param itemId The id of the item.
     * @param type The type of the item.
     * @return The Item with the id and type. Returns null if it doesn't exist.
     */
    public Item getItem(int itemId, ItemType type){
        switch (type) {
            case block:
                return blocks[itemId];
            case material:
                return materials[itemId];
            case tool:
                return tools[itemId];
            default:
                return null;
        }
    }
    
    /**
     * @param id The id of the item.
     * @param type The type of the item.
     * @return The max stacksize of the item. Returns -1 if item does not exist.
     */
    public int getMaxStacksize(int id, ItemType type){
        switch (type) {
            case block:
                return blocks[id].getMaxStackSize();
            case material:
                return materials[id].getMaxStackSize();
            case tool:
                return tools[id].getMaxStackSize();
            default:
                return -1;
        }
    }
    
    
    
}
