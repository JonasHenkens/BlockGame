/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockgame.View;

import blockgame.model.BlockGame;
import blockgame.View.GUITopView;
import blockgame.View.WorldView;
import javafx.scene.layout.Region;

/**
 *
 * @author Jonas
 */
public class BlockGameView extends Region{
    private BlockGame model;
    private WorldView view;
    private GUITopView view2;
    
    public BlockGameView(BlockGame model) {
        this.model = model;
        view = new WorldView(model.getWorld());
        view.setTranslateY(model.getGuiTop().getHeight());
        view2 = new GUITopView(model.getGuiTop());
        getChildren().addAll(view, view2);
        update();
    }
    
    public void update(){
        view.update();
        view2.update();
    }
    
}
