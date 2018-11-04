/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockgame.model;


/**
 *
 * @author Jonas
 */
public class GUITop{
    private int height;
    private int width;
    private Block[] blocks;
    private Block selectedBlock;

    public GUITop() {
        height = 32;
        width = 1536;
        
        ItemInterface ii = new ItemInterface();
        
        blocks = new Block[10];
        blocks[0] = ii.getBlock(1);
        blocks[1] = ii.getBlock(2);
        blocks[2] = ii.getBlock(3);
        blocks[3] = ii.getBlock(4);
        blocks[4] = ii.getBlock(5);
        blocks[5] = ii.getBlock(6);
        blocks[6] = ii.getBlock(7);
        blocks[7] = ii.getBlock(8);
        blocks[8] = ii.getBlock(9);
        blocks[9] = ii.getBlock(10);
        selectedBlock = blocks[0];
    }

    /**
     * @return the height of the interface
     */
    public int getHeight() {
        return height;
    }

    /**
     * @return the width of the interface
     */
    public int getWidth() {
        return width;
    }
    
    /**
     * @param n the index
     * @return the block on index n
     */
    public Block getBlocks(int n){
        return blocks[n];
    }
    
    public void leftClick(double x, double y){
        boolean done = false;
        for(int i=0; i<10 && !done; i++){
            if(x >= (8+24*i) && x <= (8 + 24*i+16) && y>= 8 && y<= 22){
                selectedBlock = blocks[i];
                done = true;
            }
        }
        
    }

    /**
     * @return the selectedBlock
     */
    public Block getSelectedBlock() {
        return selectedBlock;
    }
    
    
}
