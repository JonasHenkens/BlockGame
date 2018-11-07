/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockgame.thread;

import blockgame.View.BlockGameView;
import blockgame.model.BlockGame;
import blockgame.model.Person;
import blockgame.model.World;
import javafx.application.Platform;

/**
 * Used to make the person move using speed and acceleration.
 * @author Jonas
 */
public class PersonMovement implements Runnable{
    private Person model;
    private BlockGameView view;
    private BlockGame blockGame;

    public PersonMovement(Person model, BlockGameView view, BlockGame blockGame) {
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
            
            
            
            t = 20ms = 0.02s
            */
            double t = 0.02;
            double x = model.getX();
            double y = model.getY();
            /*
            
            y direction: 
            down is positive
            gravity: ay = 10 blocks/s^2
            */
            double ay = 10;
            double vy = model.getVy();
            double dy = 0;
            double dvy = 0;
            /*
            
            if vy = 0   => dy = 0   => dvy = ay*t
            */
            if(vy == 0){
                // no speed in y direction so no need to move
                dy = 0;
                dvy = ay*t;
            }
            /*
            
            if vy is positive => block underneath (check block below:  left and right)
                if vy*t < distance bottom to block  => dy = (distance bottom to block)  => dvy = ay*t - vy
                if vy*t > distance bottom to block  => dy = vy*t                        => dvy = ay*t
            */
            else if(vy > 0){
                // get the block below left
                // for example: block bottom left corner is at (4.1, 7.8) => block below is at (4.1, 8.7)
                
                
                // new x coordinate of bottom left corner
                double xbl = x;
                // new y coordinate of bottom left corner
                double ybl = y + model.getHeight()/blockGame.getTextureResolution() + vy*t;
                
                
                if(blockGame.getWorld().getBlock(xbl, ybl) == null && blockGame.getWorld().getBlock(xbl + 1, ybl) == null){
                    // can move no problem
                    // TODO
                }
                else{
                    // can only move to right above block
                    // TODO
                }
                 
            }
            else if(vy < 0){
                //TODO
            }
            
            
            /*
            
            
            if vy is negative => block above (check block above:  left and right)
                if vy*t < distance top to block     => dy = -(distance top to block)    => dvy = ay*t - vy
                if vy*t > distance top to block     => dy = vy*t                        => dvy = ay*t
            
            
            
            
            
            
            x direction:
            right is positive
            ax = 0
            
            if vx is positive => block right (check block right:  top, middle and bottom)
                if vx*t < distance right to block   => dx = (distance right to block)   => dvx = -vx
                if vx*t > distance right to block   => dx = vx*t                        => dvx = -vx
            
            if vx is negative => block left (check block left:  top, middle and bottom)
                if vx*t < distance left to block    => dx = -(distance left to block)   => dvx = -vx
                if vx*t > distance left to block    => dx = vx*t                        => dvx = -vx
            
            person.move(dx, dy);
            person.changespeed(dvy, dvx);
            
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
