/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockgame.View;

import blockgame.model.Crafting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Souhaib El Habti
 */
public class CraftingView extends Region{
    private Crafting model;
    
    public CraftingView(Crafting model) {
        model=this.model;
        update();
    }
    
    public void update(){
        getChildren().clear();
        Rectangle r = new Rectangle(500, 250, Color.rgb(134,136,138));
        r.setX(646);
        r.setY(35);
        
        getChildren().addAll(r);
    }
    
}
