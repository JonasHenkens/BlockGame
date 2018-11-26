/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockgame.thread;

import blockgame.View.BlockGameView;
import blockgame.model.Block;
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
    private World world;

    public PersonMovement(Person model, BlockGameView view, World world) {
        this.model = model;
        this.view = view;
        this.world = world;
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
            double personHeigth = model.getHeight()/world.getTextureResolution();
            // person width in blocks
            double personWidth = model.getWidth()/world.getTextureResolution();
            
            
            
            /*
            TODO?: (only top of world not working correctly(if hit top while moving left/right, you teleport))
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
                    Block btl = world.getBlock(x, yNew);
                    Block btm = world.getBlock(x + personWidth/2, yNew);
                    Block btr = world.getBlock(x + personWidth*0.99, yNew);
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
                    Block bbl = world.getBlock(x, yNew + personHeigth);
                    Block bbm = world.getBlock(x + personWidth/2, yNew + personHeigth);
                    Block bbr = world.getBlock(x + personWidth*0.99, yNew + personHeigth);
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
                if(vy > 0){
                    dy = world.getSizeY() - y - personHeigth;
                    dvy = -vy;
                }
                else if(vy < 0){
                    dy = -y;
                    dvy = -vy;
                    
                }
            }
            
            
            
            
            
            
            
            
            /*
            x direction:
            right is positive
            ax = 0
            if vx = 0 => dx = dvx = 0
            if vx is positive => block right (check block right:  top, middle and bottom)
                if no block at new coords         => dx = (distance right to block)   => dvx = -vx
                if a block exists at new coords   => dx = vx*t                        => dvx = -vx
            
            if vx is negative => block left (check block left:  top, middle and bottom)
                if no block at new coords          => dx = -(distance left to block)   => dvx = -vx
                if a block exists at new coords    => dx = vx*t                        => dvx = -vx
            
            
            */
            try{
                if(vx == 0.0){
                    dx = 0;
                    dvx = 0;
                }
                if(vx < 0){
                    // moving to the left
                    
                    /*
                    Check if moving is possible
                    */
                    boolean move = true;
                    // amount of checks depends on heigth of the person
                    int n = (int)Math.ceil(personHeigth) + 1;
                    
                    for(int i = 0; i <= n; i++){
                        double yc = yNew + (personHeigth - (double)1/world.getTextureResolution())*i/n;
                        Block block = world.getBlock(xNew, yc);
                        if(block != null){
                            // A block is in the way: can't move
                            move = false;
                        }
                    }
                    
                    if(move){
                        // can move no problem
                        if(standingOnBlock){
                            dx = vx*t;
                            dvx = -vx;
                        }
                        else{
                            dx = vx*t/2;
                            dvx = -vx/64;
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
                    
                    /*
                    Check if moving is possible
                    */
                    boolean move = true;
                    // amount of checks depends on heigth of the person
                    int n = (int)Math.ceil(personHeigth) + 1;
                    
                    for(int i = 0; i <= n; i++){
                        double yc = yNew + (personHeigth - (double)1/world.getTextureResolution())*i/n;
                        Block block = world.getBlock(xNew + personWidth, yc);
                        if(block != null){
                            // A block is in the way: can't move
                            move = false;
                        }
                    }
                    
                    if(move){
                        // can move no problem
                        if(standingOnBlock){
                            dx = vx*t;
                            dvx = -vx;
                        }
                        else{
                            dx = vx*t/2;
                            dvx = -vx/64;
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
                if(vx < 0){
                    dx = -x;
                    dvx = -vx;
                }
                else if(vx > 0){
                    dx = world.getSizeX() - x - personWidth;
                    dvx = -vx;
                }
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
