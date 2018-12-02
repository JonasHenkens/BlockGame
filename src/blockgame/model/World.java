/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockgame.model;


/**
 * A world is made of blocks.
 * @author Jonas and Souhaib
 */
public class World {
    private Block[][] blocks;
    private int sizeX;
    private int sizeY;
    private int textureResolution;
    private Time time;

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
        loadNewWorld();
        updateVisibilityAll();
        time = new Time(0, 0, 0);
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
                
                updateVisibilityAll();
                // return item that has been dropped
                ItemInterface ii = new ItemInterface();
                return ii.getItem(b.getDropId(), b.getDropType());
                
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
     * @param id The id of the item that has been used to click.
     * @param type The type of the item that has been used to click.
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
                // check for visibility
                updateVisibilityAll();
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
     * Loads a new world.
     */
    public void loadNewWorld(){
        ItemInterface ii = new ItemInterface();
        // air layer
        for(int i = 0;i<sizeX;i++){
            for(int j = 0;j<15;j++){
               blocks[i][j] = null;
            } 
        }
        // air layer + trees
        for(int i = 0;i<sizeX;i++){
            int j = 15;
            double rng = Math.random()*100;
            if(rng<10){
                Sapling s = (Sapling)ii.getBlock(10);
                // set progress to 100 so they all grow
                s.setProgress(100);
                blocks[i][j] = s;
            }
            else{
                blocks[i][j] = null;
            }
            
        }
        // grass layer
        for(int i = 0;i<sizeX;i++){
            for(int j = 16;j<17;j++){
               blocks[i][j] = ii.getBlock(2);
            } 
        }
        // dirt layer
        for(int i = 0;i<sizeX;i++){
            for(int j = 17;j<20;j++){
               blocks[i][j] = ii.getBlock(1);
            } 
        }
        
        int[] ores = new int[4];
        // id
        ores[0] = 4; // coal
        ores[1] = 5; // iron
        ores[2] = 7; // gold
        ores[3] = 6; // diamond
        
        double[] rarity = new double[4];
        // percentage
        rarity[0] = 10; // coal
        rarity[1] = 5; // iron
        rarity[2] = 2; // gold
        rarity[3] = 0.5; // diamond
        
        // underground: mostly stune with random ores
        for(int i = 0;i<sizeX;i++){
            for(int j = 20;j<sizeY;j++){
               int k = 0;
               boolean placedBlock = false;
               for(double per : rarity){
                   if(Math.random()*100 <= per){
                       blocks[i][j] = ii.getBlock(ores[k]);
                       placedBlock = true;
                   }
                   k++;
               }
               if(!placedBlock){
                   // didn't place ore => place stone
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
        
        time = new Time(nieuw.getSec(),nieuw.getMin(),nieuw.getHour());
        updateVisibilityAll();
        
    }
    
    /**
     * @param x The x coordinate of the block to be updated.
     * @param y The y coordinate of the block to be updated.
     */
    public void updateVisibility(int x, int y){
        // if in range an empty block => make visible
        // check square aroung block
        int range = 3;
        
        if(blocks[x][y] != null){
            // block exists
            
            for(int i = -range; i<= range; i++){
                // x
                for(int j = -range; j<= range; j++){
                    // y
                    // x + 1 and y + j cant be negative (out of world)
                    // AND cant be bigger or equals to world size
                    if(x+i >= 0 && y+j >=0 && x+i < sizeX && y+j <sizeY){
                        if(blocks[x + i][y + j] == null){
                            // empty block in range of square
                            // now check if the middle of empty spot is in the range of circle
                            double middleEmptyX = x + i;
                            double middleEmptyY = y + j;
                            double middleBlockX = x;
                            double middleBlockY = y;

                            double distance = Math.sqrt(Math.pow((middleBlockX-middleEmptyX), 2) + Math.pow((middleBlockY-middleEmptyY), 2));
                            if(distance < range-0.5){
                                // empty spot in range => block is visible
                                blocks[x][y].setVisible(true);
                                return;
                            }
                            else{
                                // empty spot isn't in range => check next
                            }  
                        }
                        else{
                            // this block isn't empty => try next one
                        }
                    }
                }
            }
            // no empty blocks in range => not visible
            blocks[x][y].setVisible(false);
        }
    }
    
    /**
     * Updates the visibility of all blocks
     */
    public void updateVisibilityAll(){
        for(int i = 0; i<sizeX; i++){
            for(int j = 0; j<sizeY; j++){
                updateVisibility(i, j);
            }
        }
    }
    /**
     * TODO
     */
    public void secPlusEen() {
        time.secPlusEen();
    }
    
    /**
     * Grows saplings and turns them into trees they are mature.
     * @return True if a tree has been placed.
     */
    public boolean updateSaplings(){
        for(int i=0; i<sizeX; i++){
            for(int j=0; j<sizeY; j++){
                if (blocks[i][j] instanceof Sapling){
                    Sapling s = (Sapling)blocks[i][j];
                    int saplingTrunkId = s.getWoodId();
                    int saplingLeavesId = s.getLeavesId();
                    int saplingX = i;
                    int saplingY = j;
                    boolean mature = s.addRandomProgress();
                    if(mature){
                        // tree is mature => check if space around is free to place blocks
                        // trunk has to have space, leaves don't (won't be placed when block is in the way)
                        // if yes:
                        // place tree trunk of 4 high
                        // place leaves around top block
                        boolean placeable = true;
                        for(int dy = -1; dy>= -3; dy--){
                            // has to be inside world
                            if(saplingX >= 0 && saplingY+dy >=0 && saplingX < sizeX && saplingY+dy <sizeY){
                                if(blocks[saplingX][saplingY + dy] != null){
                                    placeable = false;
                                }
                            }
                            else{
                                placeable = false;
                            }
                        }
                        if(placeable){
                            ItemInterface ii = new ItemInterface();
                            // place trunk
                            for(int dy = 0; dy>= -3; dy--){
                                blocks[saplingX][saplingY + dy] = ii.getBlock(saplingTrunkId);
                            }
                            
                            // place leaves
                            for(int dx = -1; dx<= 1; dx++){
                                for(int dy = -3; dy>= -4; dy--){
                                    // has to be inside world
                                    if(saplingX+dx >= 0 && saplingY+dy >=0 && saplingX+dx < sizeX && saplingY+dy <sizeY){
                                        if(blocks[saplingX + dx][saplingY + dy] == null){
                                            Block b = ii.getBlock(saplingLeavesId);
                                            blocks[saplingX + dx][saplingY + dy] = b;
                                        }
                                        else{
                                            // block in the way => don't place leaves
                                        }
                                    }
                                }
                            }
                            updateVisibilityAll();
                            return true;
                        } 
                    }
                }
            }
        }
        return false;
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
    
    /**
     * TODO
     * @return hour
     */
    public int getHour(){
        return time.getHour();
    }
    
    /**
     *TODO
     * @return min
     */
    public int getMin(){
        return time.getMin();
    }
    
    /**
     * TODO
     * @return sec
     */
    public int getSec(){
        return time.getSec();
    }
}
