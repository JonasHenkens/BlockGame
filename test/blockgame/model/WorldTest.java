/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockgame.model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jonas
 */
public class WorldTest {
    private World world;
    private ItemInterface ii;
    
    public WorldTest() {
        world = new World(64, 64, 16);
        ii = new ItemInterface();
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        world.makeWorldEmpty();
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of hitBlock method, of class World.
     */
    @Test
    public void testPlaceHitBlock() {
        
        // wooden pickaxe, stone: 10 hits, dirt/grass 1 hit
        Tool tool1 = ii.getTool(0);
        // diamond pickaxe, breaks everything but ores in one hit
        Tool tool2 = ii.getTool(2);
        
        
        for(int i = 0; i<=2000; i++){
            int x = (int)Math.random()*world.getSizeX();
            int y = (int)Math.random()*world.getSizeY();
            
            // dirt:1, grass:2 or stone:3
            int id = (int)(Math.random()*3+1);
            ItemType type = ItemType.block;
            world.placeBlock(x, y, id, type);
            
            assertEquals("block not correctly placed", id, world.getBlock(x, y).getId());
            
            // first hit with wooden pick, if stone then shouldn't break and hit with diamond pick, else should drop the block
            Item droppedItem = world.hitBlock(x, y, tool1.getId(), tool1.getItemType());
            if(id == 3){
                assertEquals("dropped item but shouldn't have",null, droppedItem);
                droppedItem = world.hitBlock(x, y, tool2.getId(), tool2.getItemType());
            }
            else{
            }
            
            int id2 = droppedItem.getId();
            assertEquals(id + "   " + id2,id, id2);
            assertEquals("block not removed",world.getBlock(x, y), null);
        }
    }
    
    
    
    /**
     * Test of makeWorldEmpty method, of class World.
     */
    @Test
    public void testMakeWorldEmpty() {
        for(int i = 0; i<world.getSizeX(); i++){
            for(int j = 0; j<world.getSizeX(); j++){
                Block b = world.getBlock(i, j);
                assertEquals("x:" + i + " - y:" + j,b, null);
            }
        }
    }

}
