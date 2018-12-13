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
        // Random id: from 0 to 3
        // Random amount: 1 or 2
        int id = (int) (Math.random()*4);
        int amount = (int) (Math.random()*2)+1;
        
        // Inventory is empty, so everything can be placed in it
        boolean expResult = true;
        boolean result = inv.addItemInInventory(id, ItemType.tool , amount);
        assertEquals("Didn't add the tool(s)",expResult, result);
        
        // Random id: from 0 to 9
        // Random amount: from 1 to 40
        id = (int) (Math.random()*10);
        amount = (int) (Math.random()*40)+1;
        
        // Inventory has 1 or 2 places left, so everything can be placed in it 
        boolean expResult2 = true;
        boolean result2 = inv.addItemInInventory(id, ItemType.material , amount);
        assertEquals("Didn't add the materials",expResult2, result2);
        
        // Random id: from 0 to 2
        // Random amount: from 1 to 40
        id = (int) (Math.random()*3);
        amount = (int) (Math.random()*40)+1;
        
        // If Inventory has 1 place left, then the expectation is true
        //Else the expectation becomes false
        boolean expResult3;
        if(inv.getAmountInItemStack(2)>0){
            expResult3 = false;
        }
        else{
            expResult3 = true;
        }
        boolean result3 = inv.addItemInInventory(id, ItemType.block, amount);
        assertEquals("Didn't work",expResult3, result3);      
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