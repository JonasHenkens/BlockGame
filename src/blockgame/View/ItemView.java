/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockgame.View;

import blockgame.model.ImageInterface;
import blockgame.model.ItemType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;

/**
 * View of a single item.
 * @author Jonas
 */
public class ItemView extends Region{
    private int id;
    private ItemType type;
    private ImageInterface imageInterface;

    /**
     * Constructor of the ItemView.
     * @param id The id of the item.
     * @param type The type of the item.
     * @param imageInterface The image interface used to request images.
     */
    public ItemView(int id, ItemType type, ImageInterface imageInterface) {
        this.id = id;
        this.type = type;
        this.imageInterface = imageInterface;
        update();
    }
    
    /**
     * Updates the ItemView.
     */
    public void update(){
        getChildren().clear();
        ImageView c = new ImageView(imageInterface.getImage(id, type));
        getChildren().add(c);
        
    }
}
