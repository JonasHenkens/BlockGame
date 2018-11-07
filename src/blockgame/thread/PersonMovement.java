/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockgame.thread;

import blockgame.View.BlockGameView;
import blockgame.model.Person;
import javafx.application.Platform;

/**
 *
 * @author Jonas
 */
public class PersonMovement implements Runnable{
    private Person model;
    private BlockGameView view;

    public PersonMovement(Person model, BlockGameView view) {
        this.model = model;
        this.view = view;
    }
    
    
    
    @Override
    public void run() {
        
        while(true){
            /* 
            move person in x and y direction
            limit player inside world:
            x: 0 => worldWidth - personWidth
            y: 0 => worldHeight - PersonHeigth
            
            x, v, a
            a = cte
            v = v + a*t
            x = x + v*t + a*t^2
            
            t=20ms
            1 block = 1 m
            ay = -10 m/s
            if block underneath => x = 0
            if no block underneath => x = x + v*t + a*t^2
            
            
            */
            
            
            
            
            view.updatePerson();
            Platform.runLater( () -> view.updatePerson() );
            try{
                Thread.sleep(20);
            }
            catch(InterruptedException e){
                
            }
        }
        
    }
    
}
