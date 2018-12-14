/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockgame.View;

import blockgame.model.Crafting;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

/**
 *
 * @author Souhaib
 */
public class CraftingView extends Region{
    private Crafting model;
    private Label[] labelIngredient;
    
    /**
     * Constructor of CraftingView
     * @param model: the model which the view is linked to 
     */
    public CraftingView(Crafting model) {
        this.model = model;
        labelIngredient = new Label[1]; 
        update();
    }
    
    /**
     *  This methode makes the crafting window and the titel
     */
    public void craftMenu(){
        Rectangle r = new Rectangle(500, 164, Color.rgb(134,136,138));
        r.setTranslateX(646);
        r.setTranslateY(35);
        
        Rectangle ck = new Rectangle(500, 80, Color.rgb(0,200,100));
        ck.setStroke(Color.rgb(255,0,0));
        ck.setStrokeWidth(3);
        ck.setTranslateX(646);
        ck.setTranslateY(200);
        
        Label l = new Label("CRAFTING MENU");
        Font f = new Font(35);
        l.setFont(f);
        l.setTextFill(Color.rgb(255,255,255));
        l.setTranslateX(780);
        l.setTranslateY(40);
        
        getChildren().addAll(r, ck, l);
    }
    
    /**
     *  Updates the CraftingView
     */
    public void update(){
        getChildren().clear();
        
        // Crafting menu-screen
        craftMenu();

        // Ingot-label
        Label li = new Label("1) MATERIAL");
        Font fi = new Font(15);
        li.setFont(fi);
        li.setTextFill(Color.rgb(255,255,255));
        li.setTranslateX(650);
        li.setTranslateY(105);
        
        // Material-craftButton: IRON, GOLD AND DIAMOND
        Button mi = new Button("IRON INGOT");
        mi.setFocusTraversable(false);
        mi.setTranslateX(750);
        mi.setTranslateY(100);     
        mi.setOnMouseClicked(e->makeMaterial(0));
        
        Button mg = new Button("GOLD INGOT");
        mg.setFocusTraversable(false);
        mg.setTranslateX(900);
        mg.setTranslateY(100);
        mg.setOnMouseClicked(e->makeMaterial(1));
        
        Button md = new Button("DIAMOND");
        md.setFocusTraversable(false);
        md.setTranslateX(1050);
        md.setTranslateY(100);
        md.setOnMouseClicked(e->makeMaterial(2));
        
        getChildren().addAll(li, md, mi, mg);
        
        // Pickaxe-label
        Label lp = new Label("2) PICKAXE");
        Font fp = new Font(15);
        lp.setFont(fp);
        lp.setTextFill(Color.rgb(255,255,255));
        lp.setTranslateX(650);
        lp.setTranslateY(150);
        
        // Pickaxe-craftButton: WOOD, STONE, IRON, GOLD, DIAMOND
        Button pw = new Button("WOOD");
        pw.setTranslateX(735);
        pw.setTranslateY(145);
        pw.setOnMouseClicked(e->makePickaxe(0));
        
        Button ps = new Button("STONE");
        ps.setTranslateX(820);
        ps.setTranslateY(145);
        ps.setOnMouseClicked(e->makePickaxe(1));
        
        Button pi = new Button("IRON");
        pi.setTranslateX(905);
        pi.setTranslateY(145);
        pi.setOnMouseClicked(e->makePickaxe(3));
        
        Button pg = new Button("GOLD");
        pg.setTranslateX(980);
        pg.setTranslateY(145);
        pg.setOnMouseClicked(e->makePickaxe(4));
        
        Button pd = new Button("DIAMOND");
        pd.setTranslateX(1050);
        pd.setTranslateY(145);
        pd.setOnMouseClicked(e->makePickaxe(2));
        
        getChildren().addAll(lp, pw, ps, pi, pg, pd);
    }   
    
    /**
     *  Methode that makes a pickaxe
     * @param id: The id of the tool to be made.
     */
    public void makePickaxe(int id){
        String make = model.makePickaxe(id);
        getChildren().remove(labelIngredient[0]);
        Label mp = new Label(make);
        Font mf = new Font(20);
        mp.setFont(mf);
        mp.setTranslateX(750);
        mp.setTranslateY(220);
        labelIngredient[0] = mp;
        getChildren().add(mp);
    }
    
    /**
     * Methode that makes a material
     * @param id: The id of the material to be made.
     */
    public void makeMaterial(int id){
        String make = model.makeMaterial(id);
        getChildren().remove(labelIngredient[0]);
        Label mm = new Label(make);
        Font mf = new Font(20);
        mm.setFont(mf);
        mm.setTranslateX(750);
        mm.setTranslateY(220);
        labelIngredient[0] = mm;
        getChildren().add(mm);
    }
    
}
