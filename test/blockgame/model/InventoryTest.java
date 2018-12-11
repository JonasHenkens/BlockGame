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
    private ItemStack i;
    private ItemStack is;
    private ItemStack isi;
    
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
        System.out.println("Making an itemStack of 2 wood pickaxes");
        i = new ItemStack(0, ItemType.tool, 2);
        System.out.println("Making an itemStack of 2 wood pickaxes");
        
        System.out.println("Making an itemStack of 2 wood pickaxes");
        
        
        
        System.out.println("addItemInInventory");
        int id = 0;
        ItemType type = null;
        int amount = 0;
        Inventory instance = null;
        boolean expResult = false;
        boolean result = instance.addItemInInventory(id, type, amount);
        assertEquals(expResult, result);
    }

    /**
     * Test of removeItemInInventory method, of class Inventory.
     */
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
}