/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockgame.thread;

import blockgame.View.BlockGameView;
import blockgame.model.Block;
import blockgame.model.BlockGame;
import blockgame.model.Key;
import blockgame.model.Person;
import blockgame.model.World;
import java.util.ArrayList;
import java.util.Iterator;
import javafx.application.Platform;

/**
 * Used to make the person move using speed and acceleration.
 * @author Jonas
 */
public class PersonMovement implements Runnable{
    private Person person;
    private BlockGameView view;
    private World world;
    private BlockGame blockgame;

    public PersonMovement(Person person, BlockGameView view, World world, BlockGame blockgame) {
        this.person = person;
        this.view = view;
        this.world = world;
        this.blockgame = blockgame;
    }
    
    
    
    @Override
    public void run() {
        while(true){
            
            
            
            
            // t = 20ms = 0.02s
            double t = 0.02;
            // x and y in blocks
            double x = person.getX();
            double y = person.getY();
            // gravity: ay = 10 blocks/s^2
            double ay = 10;
            double vy = person.getVy();
            double vx = person.getVx();
            double dy = 0;
            double dvy = 0;
            double dx = 0;
            double dvx = 0;
            // person heigth in blocks
            double personHeigth = person.getHeight()/world.getTextureResolution();
            // person width in blocks
            double personWidth = person.getWidth()/world.getTextureResolution();
            
            
           
            // new x coordinate of top left corner
            double xNew = x + vx*t;
            // new y coordinate of top left corner
            double yNew = y + vy*t;
            
            
            ArrayList<Key> keysBeingHeld = new ArrayList<>();
            Iterator<Key> it = blockgame.getKeysBeingHeldIterator();
            while(it.hasNext()){
                keysBeingHeld.add(it.next());
            }
            
            if(keysBeingHeld.contains(Key.LEFT) && keysBeingHeld.contains(Key.RIGHT)){
                person.setVx(0);
            }
            else if(keysBeingHeld.contains(Key.LEFT)){
                if(keysBeingHeld.contains(Key.SPRINT)){
                    person.setVx(-12);
                }
                else{
                    person.setVx(-6);
                }
            }
            
            else if(keysBeingHeld.contains(Key.RIGHT)){
                if(keysBeingHeld.contains(Key.SPRINT)){
                    person.setVx(12);
                }
                else{
                    person.setVx(6);
                }
            }
            
            if(keysBeingHeld.contains(Key.UP) && person.getVy() == 0){
                // eerst controleren of persoon ob blok staar, anders kan je niet springen
                Block b1 = world.getBlock(x, y+personHeigth);
                Block b2 = world.getBlock(x + personWidth, y+personHeigth);
                
                if(b1 != null || b2 != null){
                    person.setVy(-7);
                }
            }
            
            
            
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
                        if(yc<0){
                            yc = 0;
                        }
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
                            dvx = -vx/32;
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
                        if(yc<0){
                            yc = 0;
                        }
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
                            dvx = -vx/32;
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
            
            
            person.move(dx, dy);
            person.changeSpeed(dvx, dvy);
            
            
            Platform.runLater( () -> view.updatePerson() );
            try{
                Thread.sleep(20);
            }
            catch(InterruptedException e){
                
            }
        }
        
    }
    
}
