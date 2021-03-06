/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockgame.View;

import blockgame.model.ImageInterface;
import blockgame.model.GUITop;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

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
        
        ImageInterface imi = new ImageInterface();
        
        for(int i=0; i<model.getSize(); i++){
            Rectangle sq;
            if(model.getSelectedItem() == i){
                sq = new Rectangle(6 + 24*i, 6, 20, 20);
                sq.setFill(Color.WHITE);
            }
            else{
                sq = new Rectangle(7 + 24*i, 7, 18, 18);
                sq.setFill(Color.DARKGREY);
            }
            
            getChildren().add(sq);
            
            if(model.getItemId(i) == -1){
            }
            else{
                ItemView iv = new ItemView(model.getItemId(i), model.getItemType(i), imi);
                iv.setTranslateX(8 + 24*i);
                iv.setTranslateY(8);
                Text t = new Text(10 + 24*i, 37, model.getAmount(i) + "");
                
                getChildren().addAll(iv,t);
            }
        }
    }
}
