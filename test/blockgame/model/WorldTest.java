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
    public void testHitBlock() {
        
        // diamond pickaxe, breaks everything but ores in one hit
        Tool tool = ii.getTool(2);
        
        // place some blocks to hit
        
        for(int i = 0; i<=1000; i++){
            double x = Math.random()*world.getSizeX();
            double y = Math.random()*world.getSizeY();
            
            // dirt:1, grass:2 or stone:3
            int id = (int)(Math.random()*3+1);
            ItemType type = ItemType.block;
            
            world.placeBlock(x, y, id, type);
            Item droppedItem = world.hitBlock(x, y, tool.getId(), tool.getItemType());
            int id2 = droppedItem.getId();
            assertEquals(id + "   " + id2,id, id2);
            assertEquals("block not removed",world.getBlock(x, y), null);
        }
    }

    /**
     * Test of placeBlock method, of class World.
     */
    @Test
    public void testPlaceBlock() {
        System.out.println("placeBlock");
        double x = 0.0;
        double y = 0.0;
        int id = 0;
        ItemType type = null;
        World instance = null;
        boolean expResult = false;
        boolean result = instance.placeBlock(x, y, id, type);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    
    /**
     * Test of hitBlock method, of class World.
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
