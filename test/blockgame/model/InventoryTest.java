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
 * @author Souhaib El Habti
 */
public class InventoryTest {
    private Inventory inv;
    
    public InventoryTest() {
        inv = new Inventory(3);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addItemInInventory method, of class Inventory.
     */
    @Test
    public void testAddItemInInventory() {
        int id = (int) (Math.random()*4);
        int amount = (int) (Math.random()*2)+1;
        boolean expResult = true;
        boolean result = inv.addItemInInventory(id, ItemType.tool , amount);
        assertEquals(expResult, result);
        
        id = (int) (Math.random()*10);
        amount = (int) (Math.random()*40)+1;
        boolean expResult2 = true;
        boolean result2 = inv.addItemInInventory(id, ItemType.material , amount);
        assertEquals(expResult2, result2);
        
        id = (int) (Math.random()*3);
        amount = (int) (Math.random()*40)+1;
        boolean expResult3 = false;
        if(inv.getAmountInItemStack(2)<=0){
            expResult3 = true;
        }
        boolean result3 = inv.addItemInInventory(id, ItemType.block, amount);
        assertEquals(expResult3, result3);
        
    }

    /**
        @Test
        public void testRemoveItemInInventory() {
            System.out.println("removeItemInInventory");
            int id = 0;
            ItemType type = null;
            int amount = 0;
            Inventory instance = null;
            boolean expResult = false;
            boolean result = instance.removeItemInInventory(id, type, amount);
            assertEquals(expResult, result);
            // TODO review the generated test code and remove the default call to fail.
            fail("The test case is a prototype.");
        }
    */
}