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
        // Crafting menu-screen
        Rectangle r = new Rectangle(500, 250, Color.rgb(134,136,138));
        r.setTranslateX(646);
        r.setTranslateY(35);
        
        // Titel menu
        Label l = new Label("CRAFTING MENU");
        Font f = new Font(35);
        l.setFont(f);
        l.setTextFill(Color.rgb(255,255,255));
        l.setTranslateX(780);
        l.setTranslateY(40);
        
        getChildren().addAll(r, l);

        // Ingot-label
        Label li = new Label("1) MATERIAL");
        Font fi = new Font(15);
        li.setFont(fi);
        li.setTextFill(Color.rgb(255,255,255));
        li.setTranslateX(650);
        li.setTranslateY(105);
        
        // Material-craftButton: IRON, GOLD AND DIAMOND
        Button mi = new Button("IRON INGOT");
        mi.setTranslateX(750);
        mi.setTranslateY(100);
        
        Button mg = new Button("GOLD INGOT");
        mg.setTranslateX(900);
        mg.setTranslateY(100);
        
        Button md = new Button("DIAMOND");
        md.setTranslateX(1050);
        md.setTranslateY(100);
        
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
        
        Button ps = new Button("STONE");
        ps.setTranslateX(820);
        ps.setTranslateY(145);
        
        Button pi = new Button("IRON");
        pi.setTranslateX(905);
        pi.setTranslateY(145);
        
        Button pg = new Button("GOLD");
        pg.setTranslateX(980);
        pg.setTranslateY(145);
        
        Button pd = new Button("DIAMOND");
        pd.setTranslateX(1050);
        pd.setTranslateY(145);
        
        getChildren().addAll(lp, pw, ps, pi, pg, pd);
    }
    
}
