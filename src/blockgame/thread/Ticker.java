/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockgame.thread;

import blockgame.model.BlockGame;

/**
 *
 * @author Jonas
 */
public class Ticker implements Runnable{
    private BlockGame model;
    
    /**
     * 
     * @param model The blackgame used.
     */
    public Ticker(BlockGame model) {
        this.model = model;
        
        
    }
    
    
    @Override
    public void run() {
        while (true){
            
            model.tick();
            try{
                Thread.sleep(50);
            }
            catch(InterruptedException ex){
                
            }
        }
    }
    
    
    
}
