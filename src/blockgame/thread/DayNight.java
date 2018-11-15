/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockgame.thread;

import blockgame.FXMLBlockGameController;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

/**
 *
 * @author Souhaib El Habti
 */
public class DayNight implements Runnable{
    private FXMLBlockGameController c;
    private boolean day;

    public DayNight(FXMLBlockGameController c, boolean day) {
        this.c = c;
        this.day = day;
    }

    @Override
    public void run() {
        while(true){
        if(day==true){
            c.setBackground(Color.rgb(0, 200, 255));
            Platform.runLater(()->c.update());
            day=!day;
        }
        else{
            c.setBackground(Color.rgb(0, 0, 255));
            Platform.runLater(()->c.update());
            day=!day;
        }
        try{
            Thread.sleep(6000);
        }
        catch(InterruptedException ex){        
        }   
        }
    }
    
    
    
    
}
