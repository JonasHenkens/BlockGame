/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockgame.View;

import blockgame.model.Block;
import blockgame.model.ImageInterface;
import blockgame.model.ItemType;
import java.awt.Color;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.shape.Rectangle;

/**
 * View of a single item.
 * @author Jonas
 */
public class BlockView extends Region{
    private Block block;
    private ImageInterface imageInterface;
    
    private String texture;
    private boolean isVisible;

    /**
     * Constructor of the BlockView.
     * @param block The block that will be shown.
     * @param imageInterface The image interface used to request images.
     */
    public BlockView(Block block, ImageInterface imageInterface) {
        this.block = block;
        this.imageInterface = imageInterface;
        
        isVisible = block.isVisible();
        // draw for first time
        if(block.isVisible()){
            getChildren().clear();
            ImageView c = new ImageView(imageInterface.getImage(block.getId(), block.getItemType()));
            getChildren().add(c);
        }
        else{
            Rectangle rh = new Rectangle(16, 16, new javafx.scene.paint.Color(0, 0, 0, 1.0));
            getChildren().add(rh);
        }
    }
    
    /**
     * Updates the ItemView.
     */
    public void update(){
        
        // if visibility didn't change => no need to redraw
        // else => redraw
        if(isVisible == block.isVisible()){
            // don't redraw
        }
        else{
            isVisible = block.isVisible();
            // if block visible => normal view
            // else black rectangle
            if(block.isVisible()){
                getChildren().clear();
                ImageView c = new ImageView(imageInterface.getImage(block.getId(), block.getItemType()));
                getChildren().add(c);
            }
            else{
                Rectangle rh = new Rectangle(16, 16, new javafx.scene.paint.Color(0, 0, 0, 1.0));
                getChildren().add(rh);
            }
        }
    }
}
