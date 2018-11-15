/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockgame.model;


/**
 * A world is made of blocks.
 * @author Jonas
 */
public class World {
    private Block[][] blocks;
    private int sizeX;
    private int sizeY;
    private int textureResolution;

    /**
     * Constructor of World.
     * @param sizeX The amount of blocks in the x direction.
     * @param sizeY The amount of blocks in the y direction.
     * @param textureResolution The rib of the block's texture in amount of pixels.
     */
    public World(int sizeX, int sizeY, int textureResolution) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.textureResolution = textureResolution;
        blocks = new Block[sizeX][sizeY];
        loadDefaultWorld();
        
    }
    
    /**
     * @param x The x coordinate of the mouseclick.
     * @param y The y coordinate of the mouseclick.
     * @param id The id of the block that has been used to click.
     * @param type The type of the block that has been used to click
     * @return The item that has been dropped, null if nothing was dropped
     */
    public Item hitBlock(double x, double y, int id, ItemType type){
        Block b = blocks[(int)(x/16)][(int)(y/16)];
        if(b != null){
            boolean isBroken = b.hitBlock(id, type);
            if(isBroken){
                //remove block from world
                blocks[(int)(x/16)][(int)(y/16)] = null;
                // return item that has been dropped
                ItemInterface ii = new ItemInterface();
                return ii.getItem(b.getDropId(), b.getItemType());
                
            }
            else{
                return null;
            }
        }
        else{
            //block doesn't exist
            return null;
        }
    }
    
    /**
     * @param x The x coordinate of the mouseclick.
     * @param y The y coordinate of the mouseclick.
     * @param id
     * @param type
     */
    public boolean placeBlock(double x, double y, int id, ItemType type){
        if(type == ItemType.block){
            // item is a block => can place
            ItemInterface ii = new ItemInterface();
            Block block = ii.getBlock(id);
            int bX = (int)(x/16);
            int bY = (int)(y/16);
            if(blocks[bX][bY] == null){
                blocks[bX][bY] = block;
                return true;
            }
            else{
                return false;
            }
        }
        else{
            // Not a block so can't be placed.
            return false;
        }
    }
    
    /**
     * Tries to load default world from "objects/defaultWorld.json".
     * If fails: generate standard world.
     */
    public void loadDefaultWorld(){
        try {
            WorldInterface wi = new WorldInterface();
            World newWorld = wi.getDefaultWorld();
            renew(newWorld);
        } 
        catch (NullPointerException e) {
            System.out.println("INFO: Default world not found. Generating standard world.");
            
            ItemInterface ii = new ItemInterface();
            
            for(int i = 0;i<sizeX;i++){
                for(int j = 0;j<16;j++){
                   blocks[i][j] = null;
                } 
            }
            for(int i = 0;i<sizeX;i++){
                for(int j = 16;j<17;j++){
                   blocks[i][j] = ii.getBlock(2);
                } 
            }
            for(int i = 0;i<sizeX;i++){
                for(int j = 17;j<20;j++){
                   blocks[i][j] = ii.getBlock(1);
                } 
            }
            for(int i = 0;i<sizeX;i++){
                for(int j = 20;j<sizeY;j++){
                   blocks[i][j] = ii.getBlock(3);
                } 
            }
        }
    }
    
    public void renew(World nieuw){
        sizeX = nieuw.getSizeX();
        sizeY = nieuw.getSizeY();
        textureResolution = nieuw.getTextureResolution();
        
        for(int i = 0; i<sizeX; i++){
            for(int j = 0; j<sizeY; j++){
                blocks[i][j] = nieuw.getBlock(i, j);
            }
        }
        
    }
    
    
    //getters
    
    
    /**
     * @return The horizontal size of the world in amount of blocks.
     */
    public int getSizeX() {
        return sizeX;
    }

    /**
     * @return The vertical size of the world in amount of blocks.
     */
    public int getSizeY() {
        return sizeY;
    }
    
    /**
     * @return The rib of the block's texture in amount of pixels.
     */
    public int getTextureResolution() {
        return textureResolution;
    }
    
    /**
     * @param x The x coordinate of the block in blocks.
     * @param y The y coordinate of the block in blocks.
     * @return The block located on the given coordinates.
     */
    public Block getBlock(double x, double y){
        int x2 = (int)Math.floor(x);
        int y2 = (int)Math.floor(y);
        
        return blocks[x2][y2];
    }
    
    /**
     * @param x The x coordinate of the mouseclick.
     * @param y The y coordinate of the mouseclick.
     * 
     * @return The block located on the given coordinates.
     */
    public Block getBlockMouse(double x, double y){
        return blocks[(int)(x/16)][(int)(y/16)];
    }
    
}
