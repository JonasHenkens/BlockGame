/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockgame.thread;

import blockgame.FXMLBlockGameController;
import javafx.scene.paint.Color;

/**
 *
 * @author Souhaib
 */
public class DayNight implements Runnable{
    private FXMLBlockGameController c;
    private boolean day;

    /**
     * The constructor of the treadclass DayNight
     * @param c: the controller
     * @param day: true if it's day and false if it's night
     */
    public DayNight(FXMLBlockGameController c, boolean day) {
        this.c = c;
        this.day = day;
    }

    @Override
    public void run() {
        while(true){
            if(day==true){
                for(int i=0;i<200;i=i+2){
                    c.setBackground(Color.rgb(0, i, 255));
                    try{
                        Thread.sleep(2500);
                    }
                    catch(InterruptedException ex){        
                    }
                }
                for(int i=200;i>0;i=i-2){
                    c.setBackground(Color.rgb(0, i, 255));
                    try{
                        Thread.sleep(2500);
                    }
                    catch(InterruptedException ex){        
                    }
                }
                day=!day;
            }
            else{
                for(int i=255;i>75;i=i-2){
                    c.setBackground(Color.rgb(0, 0, i));
                    try{
                        Thread.sleep(2500);
                    }
                    catch(InterruptedException ex){        
                    }
                }
                for(int i=75;i<255;i=i+2){
                    c.setBackground(Color.rgb(0, 0, i));
                    try{
                        Thread.sleep(2500);
                    }
                    catch(InterruptedException ex){        
                    }
                }
                day=!day;
            }   
        }
    }
    
    
    
    
}
