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

/**
 *
 * @author Jonas
 */
public class PersonView extends Region{
    private Person model;

    public PersonView(Person model) {
        this.model = model;
        update();
    }
    
    public void update(){
        getChildren().clear();
        
        Image i = new Image(model.getTexture());
        ImageView iv = new ImageView(i);
        getChildren().add(iv);
        
        
        
    }
    
    
}
