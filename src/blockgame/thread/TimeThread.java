/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockgame.thread;

import blockgame.FXMLBlockGameController;
import blockgame.model.BlockGame;
import javafx.application.Platform;

/**
 *
 * @author Souhaib El Habti
 */
public class TimeThread implements Runnable {
private BlockGame model;
private FXMLBlockGameController controller;

    /**
     * The constructor of the Threadclass TimeThread
     * @param model The current blockgame.
     * @param controller The current controller.
     */
    public TimeThread(BlockGame model, FXMLBlockGameController controller) {
        this.model = model;
        this.controller = controller;
    }

    @Override
    public void run() {
        while (true){
            model.secPlusEen();
            Platform.runLater(()->controller.updateTimeText());
            try{
                Thread.sleep(1000);
            }
            catch(InterruptedException ex){
                
            }
        }
    }
    
}
