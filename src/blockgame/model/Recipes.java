/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockgame.model;

import java.util.ArrayList;

/**
 *
 * @author Souhaib El Habti
 */
public class Recipes {
    private ArrayList<Item> product;
    private ArrayList<Item> ingredients;

    public Recipes() {
        product = new ArrayList<>();
        ingredients = new ArrayList<>();
    }

    /**
     * @param item the product you want to add
     */
    public void addProduct(Item item) {
        if(!product.contains(item)){
            product.add(item);
        }
    }

    /**
     * @param item the ingredients to set
     */
    public void addIngredients(Item item) {
        if(!ingredients.contains(item)){
            ingredients.add(item);
        }
    }

    /**
     * @param place
     * @return the product
     */
    public Item getProduct(int place) {
        return product.get(place);
    }

    /**
     * @param place
     * @return the ingredients
     */
    public Item getIngredients(int place) {
        return product.get(place);
    }
    
    
    
    
    
    
    
    
}
