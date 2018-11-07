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

    /**
     * Constructor of the WorldView.
     * @param model The world to be displayed.
     */
    public WorldView(World model) {
        this.model = model;
        update();
    }
    
    /**
     * Updates the WorldView.
     */
    public void update(){
        getChildren().clear();
        ImageInterface ii = new ImageInterface();
        for(int i=0; i<model.getSizeX(); i++){
            for(int j=0; j<model.getSizeY(); j++){
                try{
                    Block b = model.getBlock(i, j);
                    BlockView bv = new BlockView(b, ii);
                    bv.setTranslateX(model.getTextureResolution()*i);
                    bv.setTranslateY(model.getTextureResolution()*j);
                    getChildren().add(bv);
                }
                catch(NullPointerException e){
                }
            }
        }
    }
    
    
    
    
}
