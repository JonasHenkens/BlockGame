/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockgame.View;

import blockgame.model.Person;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * View of the person.
 * @author Jonas
 */
public class PersonView extends Region{
    private Person model;

    /**
     * Constructor of the PersonView.
     * @param model The person to be displayed.
     */
    public PersonView(Person model) {
        this.model = model;
        update();
    }
    
    /**
     * Updates the view.
     */
    public void update(){
        getChildren().clear();
        
        
        
        
        Image i = new Image(model.getTexture());
        ImageView iv = new ImageView(i);
        getChildren().add(iv);
        
        // temp to check range
//        double x = model.getWidth()/2;
//        double y = model.getHeight()/2;
//        Circle cirkel1 = new Circle(x, y, 3.5*16);
//        cirkel1.setFill(Color.TRANSPARENT);
//        cirkel1.setStroke(Color.RED);
//        
//        getChildren().addAll(cirkel1);
        
    }
    
    
}
