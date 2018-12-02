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
public class Sapling extends Block{
    private int woodId;
    private int leavesId;
    private double progress;
    
    /**
     * Constructor of the Block.
     * @param texture Path to the texture of the block.
     * @param health Health of the block.
     * @param hardness Hardness of the block.
     * @param id Id of the block.
     * @param dropId Id of the item that will be dropped when broken.
     * @param dropType Type of the item that will be dropped when broken.
     * @param name Name of the block.
     * @param woodId The id of the block used to make the trunk of the tree.
     * @param leavesId The id of the block used to make the leaves of the tree.
     */
    public Sapling(String texture, double health, double hardness, int id, int dropId, ItemType dropType, String name, int woodId, int leavesId) {
        super(texture, health, hardness, id, dropId, dropType, name);
        progress = 0;
        this.woodId = woodId;
        this.leavesId = leavesId;
    }

    
    
    /**
     * Adds random amount of progress.
     * @return True if tree mature.
     */
    public boolean addRandomProgress(){
        progress = progress + Math.random()/5;
        if(progress > 100){
            progress = 100;
        }
        return isDone();
    }

    /**
     * @param progress the progress to set
     */
    public void setProgress(double progress) {
        this.progress = progress;
    }
    
    // getters
    
    /**
     * @return True if sapling mature.
     */
    public boolean isDone(){
        if(progress >= 100.0){
            return true;
        }
        return false;
    }
    
    /**
     * @return The id of the block used to make the trunk of the tree.
     */
    public int getWoodId() {
        return woodId;
    }

    /**
     * @return The id of the block used to make the leaves of the tree.
     */
    public int getLeavesId() {
        return leavesId;
    }

    /**
     * @return the progress
     */
    public double getProgress() {
        return progress;
    }
}
