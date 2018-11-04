/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockgame.model;

import blockgame.model.Block;
import java.util.ArrayList;
import javafx.scene.image.Image;

/**
 * The ImageInterface is used to request blockImages by id
 * @author Jonas
 */
public class ImageInterface {
    private ArrayList<Image> blockImages;
    
    public ImageInterface() {
        blockImages = new ArrayList<>();
        ItemInterface ii = new ItemInterface();
        int n = 0;
        for(n=0; n<ii.getBlocksLength(); n++){
            try{
                Block b = ii.getBlock(n);
                String tex = b.getTexture();
                Image i = new Image(tex);
                blockImages.add(i);
            }
            catch(NullPointerException e){
                
            }
        }
        
        
    }
    
    
    public Image getImage(int id, String type){
        if(type.equals("block")){
            return blockImages.get(id);
        }
        else{
            return null;
        }
    }
    
}
