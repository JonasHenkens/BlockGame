/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockgame.View;

import blockgame.model.Block;
import blockgame.model.ImageInterface;
import blockgame.model.World;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;

/**
 *
 * @author Jonas
 */
public class WorldView extends Region{
    private World model;

    public WorldView(World model) {
        this.model = model;
        update();
    }
    
    
    public void update(){
        getChildren().clear();
        ImageInterface ii = new ImageInterface();
        
        // draw the world
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
        
        // draw the person
        
        double personX = model.getPersonX();
        double personY = model.getPersonY();
        String personTexture = model.getPersonTexture();
        
        ImageView personView = new ImageView(personTexture);
        personView.setTranslateX(personX);
        personView.setTranslateX(personY);
        getChildren().add(personView);
    }
}
