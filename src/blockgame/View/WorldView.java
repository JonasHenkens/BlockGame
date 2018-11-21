/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockgame.View;

import blockgame.model.Block;
import blockgame.model.ImageInterface;
import blockgame.model.World;
import javafx.scene.layout.Region;

/**
 * View of the world.
 * @author Jonas
 */
public class WorldView extends Region{
    private World model;
    // blocks die zijn weergegeven
    private Block[][] blocks;
    // views die bij de weergegeven blocks horen
    private BlockView[][] blockViews;
    // imageinterface die gebruikt wordt
    private ImageInterface ii;

    /**
     * Constructor of the WorldView.
     * @param model The world to be displayed.
     */
    public WorldView(World model) {
        this.model = model;
        blocks = new Block[model.getSizeX()][model.getSizeY()];
        blockViews = new BlockView[model.getSizeX()][model.getSizeY()];
        ii = new ImageInterface();
        update();
    }
    
    /**
     * Updates the WorldView.
     */
    public void update(){
        
        // gaat elke blok af
        for(int i=0; i<model.getSizeX(); i++){
            for(int j=0; j<model.getSizeY(); j++){
                Block b = model.getBlock(i, j);

                // als block veranderd is: verwijder oude view en maak nieuwe aan
                // anders doe niets
                if(blocks[i][j] != b){
                    try{
                        getChildren().remove(blockViews[i][j]);
                    }
                    catch(NullPointerException e){
                        // view bestaat niet dus moet niet verwijderd worden
                    }
                    
                    blocks[i][j] = b;
                    //als b = null: niet tekenen
                    if(b != null){
                        BlockView bv = new BlockView(b, ii);
                        bv.setTranslateX(model.getTextureResolution()*i);
                        bv.setTranslateY(model.getTextureResolution()*j);
                        blockViews[i][j] = bv;
                        getChildren().add(bv);
                    }
                } 
                else {
                    // block didnt change
                    if(blockViews[i][j] != null){
                        blockViews[i][j].update();
                    }
                }
                    
                
            }
        }
    }
    
    
    
    
}
