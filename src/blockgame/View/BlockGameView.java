/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockgame.View;

import blockgame.model.BlockGame;
import blockgame.model.Crafting;
import blockgame.model.GUITop;
import blockgame.model.Person;
import blockgame.model.World;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;

/**
 * Complete worldView of blockgame.
 * @author Jonas
 */
public class BlockGameView extends Region{
    private BlockGame model;
    private World world;
    private GUITop guiTop;
    private Crafting craft;
    
    
    private WorldView worldView;
    private GUITopView topView;
    private PersonView personView;
    private CraftingView cv;
    
    /**
     * Constructor of the BlockGameView.
     * @param model This is the BlockGame used for the view.
     */
    public BlockGameView(BlockGame model, World world, GUITop guiTop, Person person, Crafting craft) {
        this.model = model;
        this.world = world;
        this.guiTop = guiTop;
        this.craft=craft;
        // Make world view
        worldView = new WorldView(world);
        worldView.setTranslateY(guiTop.getHeight());
        // Make gui top view
        topView = new GUITopView(guiTop);
        // Make person view
        personView = new PersonView(person);
        // Make crafting view
        cv= new CraftingView(craft);
        // add all views exept crafting view
        getChildren().addAll(worldView, topView, personView);
        
        update();
    }
    
    /**
     * Updates the BlockGameView.
     */
    public void update(){
        worldView.update();
        topView.update();
        updatePerson();
        if(craft.isGeopend()==true){
            cv.update();
        }
    }
    
    /**
     * Updates the personView.
     */
    public void updatePerson(){
        
        double personX = model.getPersonX();
        double personY = model.getPersonY();
        personView.setTranslateX(personX*model.getTextureResolution());
        personView.setTranslateY(personY*model.getTextureResolution() + guiTop.getHeight());
        
        
        
    }
    
}
