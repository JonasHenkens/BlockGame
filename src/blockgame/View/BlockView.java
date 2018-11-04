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
 *
 * @author Jonas
 */
public class BlockView extends Region{
    private Block model;
    private ImageInterface imageInterface;

    public BlockView(Block model, ImageInterface imageInterface) {
        this.model = model;
        this.imageInterface = imageInterface;
        update();
    }
    
    public void update(){
        getChildren().clear();
        ImageView c = new ImageView(imageInterface.getImage(model.getId(), "block"));
        getChildren().add(c);
        
    }
}
