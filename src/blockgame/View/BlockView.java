/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockgame.View;

import blockgame.model.Block;
import blockgame.model.ImageInterface;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;

/**
 * View of a single block
 * @author Jonas
 */
public class BlockView extends Region{
    private Block model;
    private ImageInterface imageInterface;

    /**
     * Constructor of the BlockView.
     * @param model The block to be displayed.
     * @param imageInterface The imageinterfaced used to request images.
     */
    public BlockView(Block model, ImageInterface imageInterface) {
        this.model = model;
        this.imageInterface = imageInterface;
        update();
    }
    
    /**
     * Updates the BlockView.
     */
    public void update(){
        getChildren().clear();
        ImageView c = new ImageView(imageInterface.getImage(model.getId(), "block"));
        getChildren().add(c);
        
    }
}
