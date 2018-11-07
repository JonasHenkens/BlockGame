/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockgame.View;

import blockgame.model.Block;
import blockgame.model.ImageInterface;
import blockgame.model.GUITop;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * View of the GUI at the top of the game.
 * @author Jonas
 */
public class GUITopView extends Region{
    private GUITop model;

    /**
     * Constructor for the GUITopView.
     * @param model The GUI to be displayed.
     */
    public GUITopView(GUITop model) {
        this.model = model;
        update();
    }
    
    /**
     * Updates the GUITopView.
     */
    public void update(){
        getChildren().clear();
        
        Rectangle rh = new Rectangle(model.getWidth(), model.getHeight(), Color.GREY);
        getChildren().add(rh);
        
        ImageInterface ii = new ImageInterface();
        
        for(int i=0; i<10; i++){
            Block b = model.getBlock(i);
            BlockView bv = new BlockView(b, ii);
            bv.setTranslateY(8);
            bv.setTranslateX(8 + 24*i);
            getChildren().add(bv);
        }
        
    }
    
}
