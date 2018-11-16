/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockgame.thread;

import blockgame.FXMLBlockGameController;
import blockgame.model.Time;
import javafx.application.Platform;

/**
 *
 * @author Souhaib El Habti
 */
public class TimeTread implements Runnable {
private Time time;
private FXMLBlockGameController controller;

    public TimeTread(Time time, FXMLBlockGameController controller) {
        this.time = time;
        this.controller = controller;
    }

    @Override
    public void run() {
        while (true){
            time.secPlusEen();
            Platform.runLater(()->controller.updateTimeText());
            try{
                Thread.sleep(1000);
            }
            catch(InterruptedException ex){
                
            }
        }
    }
    
}