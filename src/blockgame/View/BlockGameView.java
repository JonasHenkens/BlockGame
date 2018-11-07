/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockgame.View;

import blockgame.model.BlockGame;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;

/**
 * Complete worldView of blockgame.
 * @author Jonas
 */
public class BlockGameView extends Region{
    private BlockGame model;
    private WorldView worldView;
    private GUITopView topView;
    private ImageView personView;
    
    /**
     * Constructor of the BlockGameView.
     * @param model This is the BlockGame used for the view.
     */
    public BlockGameView(BlockGame model) {
        this.model = model;
        worldView = new WorldView(model.getWorld());
        worldView.setTranslateY(model.getGuiTop().getHeight());
        topView = new GUITopView(model.getGuiTop());
        getChildren().addAll(worldView, topView);
        
        String personTexture = model.getPersonTexture();
        personView = new ImageView(personTexture);
        getChildren().add(personView);
        
        update();
    }
    
    /**
     * Updates the BlockGameView.
     */
    public void update(){
        worldView.update();
        topView.update();
        updatePerson();
    }
    
    /**
     * Updates the personView.
     */
    public void updatePerson(){
        
        double personX = model.getPersonX();
        double personY = model.getPersonY();
        personView.setTranslateX(personX);
        personView.setTranslateY(personY);
        
        
        
    }
    
}
