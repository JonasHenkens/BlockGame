/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockgame.model;

import javafx.scene.image.Image;

/**
 * A person can move around the world and interact with it.
 * @author Jonas
 */
public class Person {

    
    private Inventory inventory;
    private double x;
    private double y;
    private double spawnX;
    private double spawnY;
    private double health;
    private double maxHealth;
    private double width;
    private double height;
    private String texture;
    private int experience;
    private double vx;
    private double vy;
    private double maxVX;
    private double maxVY;

    /**
     * Constructor of a standard person.
     * @param spawnX The x coordinate the person will originally be spawned at or after death in blocks.
     * @param spawnY The y coordinate the person will originally be spawned at or after death in blocks.
     * @param texture The texture of the person.
     */
    public Person(double spawnX, double spawnY, String texture) {
        // hotbar 10 slots: 0-9
        // inventor 40 slots: 10-49
        // total 50 slots
        inventory = new Inventory(30);
        
        x = spawnX;
        y = spawnY;
        this.spawnX = spawnX;
        this.spawnY = spawnY;
        health = maxHealth = 20.0;
        Image i = new Image(texture);
        width = i.getWidth();
        height = i.getHeight();
        this.texture = texture;
        experience = 0;
        vx = 0;
        vy = 0;
        maxVX = 20;
        maxVY = 20;
    }
    
    /**
     * Changes this person into the new person.
     * @param person The person that will be replacing the current person.
     */
    public void renew(Person person) {
        
        int n = person.getInventorySize();
        inventory = new Inventory(30);
        for(int i = 0; i<n; i++){
            if(person.getInventoryItemType(i) != ItemType.nothing){
                inventory.addItemInInventory(person.getInventoryItemId(i), person.getInventoryItemType(i), person.getInventoryItemAmount(i));
            }
        }
        
        x = person.getX();
        y = person.getY();
        spawnX = person.getSpawnX();
        spawnY = person.getSpawnY();
        health = person.getHealth();
        maxHealth = person.getMaxHealth();
        width = person.getWidth();
        height = person.getHeight();
        texture = person.getTexture();
        experience = person.getExperience();
        vx = person.getVx();
        vy = person.getVy();
        maxVX = 20;
        maxVY = 20;
    }
    
    /**
     * Full constructor of a person.
     * @param inventory The inventory of the person.
     * @param x The x coordinate of the person in blocks.
     * @param y The y coordinate of the person in blocks.
     * @param spawnX The x coordinate the person will originally be spawned at or after death in blocks.
     * @param spawnY The y coordinate the person will originally be spawned at or after death in blocks.
     * @param health The health of the person.
     * @param maxHealth The maximum amount of health the person can have.
     * @param texture The texture of the person.
     * @param experience The amount of experience the person has.
     * @param vx The speed in the x direction in blocks/s. (right is positive)
     * @param vy The speed in the y direction in blocks/s. (down is positive)
     */
    public Person(Inventory inventory, double x, double y, double spawnX, double spawnY, double health, double maxHealth, String texture, int experience, double vx, double vy) {
        this.inventory = inventory;
        this.x = x;
        this.y = y;
        this.spawnX = spawnX;
        this.spawnY = spawnY;
        this.health = health;
        this.maxHealth = maxHealth;
        this.texture = texture;
        this.experience = experience;
        Image i = new Image(texture);
        width = i.getWidth();
        height = i.getHeight();
        this.vx = vx;
        this.vy = vy;
        maxVX = 20;
        maxVY = 20;
    }
    
    /**
     * Resets the x and y coordinate to the spawn coordinates.
     */
    public void respawn(){
        x = spawnX;
        y = spawnY;
    }
    
    /**
     * 
     * @param dx The change in the x direction in blocks. Right is positive.
     * @param dy The change in the y direction in blocks. Down is positive.
     */
    public void move(double dx, double dy){
        x = x + dx;
        y = y + dy;
    }
    
    /**
     * Changes the speed of the person.
     * @param dvx The change in speed in the x direction in blocks/s.
     * @param dvy The change in speed in the y direction in blocks/s.
     */
    public void changeSpeed(double dvx, double dvy){
        vx = vx + dvx;
        vy = vy + dvy;
        
        if(vx > maxVX){
            vx = maxVX;
        }
        if(vy > maxVY){
            vy = maxVY;
        }
    }
    
    
    
    /**
     * @param id The id of the item to be removed.
     * @param type The type of the item to be removed.
     * @param amount The amount to be removed.
     * @return True if items are removed.
     */
    public boolean removeInventoryItem(int id, ItemType type, int amount){
        boolean verwijderd = inventory.removeItemInInventory(id, type, amount);
        return verwijderd;
    }

    
    
    /**
     * @param id The id of the item to be added.
     * @param type The type of the item to be added.
     * @param amount The amount of the item to be added.
     * @return 
     */
    public boolean addItem(int id, ItemType type, int amount){
        return inventory.addItemInInventory(id, type, amount);
    }
    
    
    
    // setters
    
    /**
     * @param vx The new speed in the x direction in blocks/sec.
     */
    public void setVx(double vx) {
        this.vx = vx;
    }

    /**
     * @param vy The new speed in the y direction in blocks/sec.
     */
    public void setVy(double vy) {
        this.vy = vy;
    }
    
    /**
     * @param x The new x coordinate in blocks.
     * @param y The new y coordinate in blocks.
     */
    public void setCoordinates(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @param spawnX Yhe new spawn x coordinate in blocks.
     * @param spawnY Yhe new spawn y coordinate in blocks.
     */
    public void setSpawn(double spawnX, double spawnY) {
        this.spawnX = spawnX;
        this.spawnY = spawnY;
    }
    
    
    
    
    // getters
    
    /**
     * @param id The id of the item you want to get the amount of.
     * @param type The type of the item you want to get the amount of.
     * @return The amount of the item in the inventory.
     */
    public int getInventoryAmount(int id, ItemType type){
        return inventory.getAantal(id, type);
    }
    
    /**
     * @return The x coordinate of the person in blocks.
     */
    public double getX() {
        return x;
    }

    /**
     * @return The y coordinate of the person in blocks.
     */
    public double getY() {
        return y;
    }

    /**
     * @return The x coordinate the person will originally be spawned at or after death in blocks.
     */
    public double getSpawnX() {
        return spawnX;
    }

    /**
     * @return The y coordinate the person will originally be spawned at or after death in blocks.
     */
    public double getSpawnY() {
        return spawnY;
    }

    /**
     * @return The health of the person.
     */
    public double getHealth() {
        return health;
    }

    /**
     * @return The maximum amount of health the person can have.
     */
    public double getMaxHealth() {
        return maxHealth;
    }

    /**
     * @return The width of the person in pixels.
     */
    public double getWidth() {
        return width;
    }

    /**
     * @return The heigth of the person in pixels.
     */
    public double getHeight() {
        return height;
    }

    /**
     * @return The texture of the person.
     */
    public String getTexture() {
        return texture;
    }

    /**
     * @return The amount of experience the person has.
     */
    public int getExperience() {
        return experience;
    }
    
    /**
     * @return The velocity in the x direction in blocks/s.
     */
    public double getVx() {
        return vx;
    }

    /**
     * @return The velocity in the y direction in blocks/s.
     */
    public double getVy() {
        return vy;
    }
    
    /**
     * @param index The place of the item stack in the inventory.
     * @return The id of the item.
     */
    public int getInventoryItemId(int index){
        return inventory.getIdItemStack(index);
    }
    
    /**
     * @param index The place of the item stack in the inventory.
     * @return The type of the item.
     */
    public ItemType getInventoryItemType(int index){
        return inventory.getTypeItemStack(index);
    }

    /**
     * 
     * @param index The place of the itemstack in the inventory.
     * @return The amount in the itemstack.
     */
    public int getInventoryItemAmount(int index) {
        return inventory.getAmountInItemStack(index);
    }

    public int getInventorySize() {
        return inventory.getLenghtInventory();
    }
}
