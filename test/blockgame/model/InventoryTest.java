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
        // This test is tested 1000 times
        for (int i=0; i<1000; i++){
            // Each test has its inventory with 3 itemstacks
            inv = new Inventory(3);

            // Random id: from 0 to 4
            // Random amount: 1 or 2
            int id = (int) (Math.random()*5);
            int amount = (int) (Math.random()*2)+1;

            // Inventory is empty, so every tool can be placed in it
            boolean expResult = true;
            boolean result = inv.addItemInInventory(id, ItemType.tool , amount);
            assertEquals("Didn't add the tool(s)",expResult, result);

            // Random id: from 0 to 3
            // Random amount: from 1 to 40
            id = (int) (Math.random()*4);
            amount = (int) (Math.random()*40)+1;

            // Inventory has 1 or 2 places left, so every material can be placed in it 
            boolean expResult2 = true;
            boolean result2 = inv.addItemInInventory(id, ItemType.material , amount);
            assertEquals("Didn't add the materials",expResult2, result2);

            // Random id: from 0 to 9
            // Random amount: from 1 to 40
            id = (int) (Math.random()*10);
            amount = (int) (Math.random()*40)+1;

            // The expectation is true,
            // except if the inventory is filled
            // (Last itemstack must have less then 0 items)
            // Then the expectation is false
            boolean expResult3 = true;
            if(inv.getAmountInItemStack(2)>0){
                expResult3 = false;
            }
            boolean result3 = inv.addItemInInventory(id, ItemType.block, amount);
            assertEquals("Didn't work",expResult3, result3); 
        }    
    }

    @Test
    public void testRemoveItemInInventory() {
        inv = new Inventory(3);
        // Random id: from 0 to 4 & amount: 1 tool
        // Add tool in inventory
        int id = (int) (Math.random()*5);
        int amount = 1;
        inv.addItemInInventory(id, ItemType.tool , amount);

        // Random id: from 0 to 3 & Random amount: from 1 to 40
        // Add materials in inventory
        id = (int) (Math.random()*4);
        amount = (int) (Math.random()*40)+1;
        inv.addItemInInventory(id, ItemType.material , amount);

        // Random id: from 0 to 9 & Random amount: from 1 to 40
        // Add blocks in inventory
        id = (int) (Math.random()*10);
        amount = (int) (Math.random()*40)+1;
        inv.addItemInInventory(id, ItemType.block, amount);
    }
    
}