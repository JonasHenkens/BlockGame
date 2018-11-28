/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockgame.View;

import blockgame.model.Crafting;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

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
        
        Label l = new Label("Crafting Menu");
        Font f = new Font(35);
        l.setFont(f);
        l.setTextFill(Color.rgb(255,255,255));
        l.setTranslateX(790);
        l.setTranslateY(40);
        
        /*
            Rectangle rc = new Rectangle(300, 40, Color.rgb(134,136,138));
            
        */
        
        getChildren().addAll(r, l);
    }
    
}
