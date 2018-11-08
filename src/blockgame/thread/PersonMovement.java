/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockgame.thread;

import blockgame.View.BlockGameView;
import blockgame.model.Block;
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
        this.blockGame = blockGame;
    }
    
    
    
    @Override
    public void run() {
        while(true){
            // t = 20ms = 0.02s
            double t = 0.02;
            // x and y in blocks
            double x = model.getX();
            double y = model.getY();
            // gravity: ay = 10 blocks/s^2
            double ay = 10;
            double vy = model.getVy();
            double vx = model.getVx();
            double dy = 0;
            double dvy = 0;
            double dx = 0;
            double dvx = 0;
            // person heigth in blocks
            double personHeigth = model.getHeight()/blockGame.getTextureResolution();
            // person width in blocks
            double personWidth = model.getWidth()/blockGame.getTextureResolution();
            
            
            
            /*
            TODO:
            when move person in x and y direction
            limit player inside world:
            x: 0 => worldWidth - personWidth
            y: 0 => worldHeight - PersonHeigth
            */
            
            // new x coordinate of top left corner
            double xNew = x + vx*t;
            // new y coordinate of top left corner
            double yNew = y + vy*t;
            
            
            
            /*
            check block top: left and right
                if no block at new coords       => dy = vy*t                        => dvy = ay*t
                if a block exists at new coords => dy = -(distance top to block)    => dvy = - vy
            
            check block bottom: left and right
                if no block at new coords       => dy = vy*t                        => dvy = ay*t
                if a block exists at new coords => dy = (distance bottom to block)  => dvy = ay*t - vy
            */
            boolean standingOnBlock = false;
            
            try{
                if(vy < 0){
                    // check top: left, middle, right
                    Block btl = blockGame.getWorld().getBlock(x, yNew);
                    Block btm = blockGame.getWorld().getBlock(x + personWidth/2, yNew);
                    Block btr = blockGame.getWorld().getBlock(x + personWidth*0.99, yNew);
                    if(btl == null && btm == null && btr == null){
                        // can move no problem
                        dy = vy*t;
                        dvy = ay*t;
                    }
                    else{
                        // can only move to right below block
                        // new y = yblock + 1
                        // dy = (yblock + 1) - 1
                        dy = ((int)Math.floor(yNew) + 1) - y;
                        dvy =  - vy;
                        standingOnBlock = true;
                    }
                }

                
                
                if(vy >= 0){
                    // check bottom: left, middle, right
                    Block bbl = blockGame.getWorld().getBlock(x, yNew + personHeigth);
                    Block bbm = blockGame.getWorld().getBlock(x + personWidth/2, yNew + personHeigth);
                    Block bbr = blockGame.getWorld().getBlock(x + personWidth*0.99, yNew + personHeigth);
                    if(bbl == null && bbm == null && bbr == null){
                        // can move no problem
                        dy = vy*t;
                        dvy = ay*t;

                    }
                    else{
                        // can only move to right above block
                        // new y = yblock - playerheight
                        // dy = yblock - (playerheight + y)
                        dy = (int)Math.floor(yNew + personHeigth) - (personHeigth + y);
                        dvy =  - vy;
                        standingOnBlock = true;
                    }
                }
            }
            catch(ArrayIndexOutOfBoundsException e){
                dy = 0;
                dvy = 0;
            }
            
            
            
            
            
            
            
            
            /*
            x direction:
            right is positive
            ax = 0
            
            if vx is positive => block right (check block right:  top, middle and bottom)
                if vx*t < distance right to block   => dx = (distance right to block)   => dvx = -vx
                if vx*t > distance right to block   => dx = vx*t                        => dvx = -vx
            
            if vx is negative => block left (check block left:  top, middle and bottom)
                if vx*t < distance left to block    => dx = -(distance left to block)   => dvx = -vx
                if vx*t > distance left to block    => dx = vx*t                        => dvx = -vx
            
            
            */
            try{
                if(vx == 0.0){
                    dx = 0;
                    dvx = 0;
                }
                if(vx < 0){
                    // moving to the left
                    // check left: top, middle, bottom
                    Block blt = blockGame.getWorld().getBlock(xNew, yNew);
                    Block blm = blockGame.getWorld().getBlock(xNew, yNew + personHeigth/2);
                    Block blb = blockGame.getWorld().getBlock(xNew, yNew + personHeigth - (double)1/blockGame.getTextureResolution());
                    
                    /*
                    can also check different way: amount of checks  n = Math.ceil(personHeigth) + 1
                    for(int i = 0, i <= n; i++)
                    y = yNew + (personHeigth - (double)1/blockGame.getTextureResolution())*i/n
                    */
                    
                    
                    if(blt == null && blm == null && blb == null){
                        // can move no problem
                        if(standingOnBlock){
                            dx = vx*t;
                            dvx = -vx;
                        }
                        else{
                            dx = vx*t/2;
                            dvx = 0;
                        }
                    }
                    else{
                        // can only move to right of block
                        // new x = xblock + 1
                        // dx = (xblock + 1) - x
                        dx = ((int)Math.floor(xNew + 1)) - x;
                        dvx =  - vx;
                    }
                }
                else if(vx > 0){
                    // moving to the right
                    // check right: top, middle, bottom
                    Block brt = blockGame.getWorld().getBlock(xNew + personWidth, yNew);
                    Block brm = blockGame.getWorld().getBlock(xNew + personWidth, yNew + personHeigth/2);
                    Block brb = blockGame.getWorld().getBlock(xNew + personWidth, yNew + personHeigth - (double)1/blockGame.getTextureResolution());
                    if(brt == null && brm == null && brb == null){
                        // can move no problem
                        if(standingOnBlock){
                            dx = vx*t;
                            dvx = -vx;
                        }
                        else{
                            dx = vx*t/2;
                            dvx = 0;
                        }
                    }
                    else{
                        // can only move to left of block
                        // new x = xblock - personWidth
                        // dx = (xblock - personWidth) - x
                        dx = ((int)Math.floor(xNew + personWidth) - personWidth) - x;
                        dvx =  - vx;
                    }
                }
            }
            catch(ArrayIndexOutOfBoundsException e){
                dx = 0;
                dvx = 0;
            }
            
            
            model.move(dx, dy);
            model.changeSpeed(dvx, dvy);
            
            
            Platform.runLater( () -> view.updatePerson() );
            try{
                Thread.sleep(20);
            }
            catch(InterruptedException e){
                
            }
        }
        
    }
    
}
