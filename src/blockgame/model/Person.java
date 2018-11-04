/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockgame.model;

import javafx.scene.image.Image;

/**
 *
 * @author Jonas
 */
public class Person {
    private ItemStack[] inventory;
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

    public Person(double spawnX, double spawnY, String texture) {
        // hotbar 10 slots: 0-9
        // inventort 40 slots: 10-49
        // armor 4 slots: 50-53
        // total 54 slots
        inventory = new ItemStack[54];
        
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
    }

    public Person(ItemStack[] inventory, double x, double y, double spawnX, double spawnY, double health, double maxHealth, String texture, int experience) {
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
    }
    
    /**
     * resets the x and y coordinate to the spawn coordinates
     */
    public void respawn(){
        x = spawnX;
        y = spawnY;
    }
    
    /**
     * 
     * @param dx the amount moved in the x direction
     * @param dy the amount moved in the y direction 
     */
    public void move(double dx, double dy){
        x = x + dx;
        y = y + dy;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /**
     * @return the x
     */
    public double getX() {
        return x;
    }

    /**
     * @return the y
     */
    public double getY() {
        return y;
    }

    /**
     * @return the spawnX
     */
    public double getSpawnX() {
        return spawnX;
    }

    /**
     * @return the spawnY
     */
    public double getSpawnY() {
        return spawnY;
    }

    /**
     * @return the health
     */
    public double getHealth() {
        return health;
    }

    /**
     * @return the maxHealth
     */
    public double getMaxHealth() {
        return maxHealth;
    }

    /**
     * @return the width
     */
    public double getWidth() {
        return width;
    }

    /**
     * @return the height
     */
    public double getHeight() {
        return height;
    }

    /**
     * @return the texture
     */
    public String getTexture() {
        return texture;
    }

    /**
     * @return the experience
     */
    public int getExperience() {
        return experience;
    }

    /**
     * @param x the new x coordinate
     * @param y the new y coordinate
     */
    public void setCoordinates(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @param spawnX the new spawn x coordinate
     * @param spawnY the new spawn y coordinate
     */
    public void setSpawn(double spawnX, double spawnY) {
        this.spawnX = spawnX;
        this.spawnY = spawnY;
    }
    
}
