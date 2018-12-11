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
        System.out.println("Making an itemstack with a random amount of a random id of pickaxe");
        int id = (int) (Math.random()*4);
        int amount = (int) (Math.random()*1)+2;
        i = new ItemStack(id, ItemType.tool, amount);
        
        System.out.println("Making an itemstack with a random amount of a random type of block");
        id = (int) (Math.random()*10);
        amount = (int) (Math.random()*40)+1;
        is = new ItemStack(id, ItemType.block, amount);
        
        System.out.println("Making an itemstack with a random amount of a random type of material");
        id = (int) (Math.random()*3);
        amount = (int) (Math.random()*40)+1;
        isi = new ItemStack(id, ItemType.material, amount);
        
        System.out.println("Add first itemstack in inventory");   
        boolean expResult = true;
        boolean result = inv.addItemInInventory(i.getId(), i.getType(), i.getAmount());
        assertEquals(expResult, result);
        
        System.out.println("Add second itemstack in inventory");
        boolean expResult2 = true;
        boolean result2 = inv.addItemInInventory(is.getId(), is.getType(), is.getAmount());
        assertEquals(expResult2, result2);
        
        System.out.println("Add last itemstack in inventory");
        boolean expResult3 = true;
        boolean result3 = inv.addItemInInventory(isi.getId(), isi.getType(), isi.getAmount());
        assertEquals(expResult2, result2);
        
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