package com.example.smartpantrymanager;

public class RecipeIngredient {
    public String name,unit;
    public double quantity;
    public RecipeIngredient(String n,double q,String u){name=n;quantity=q;unit=u;}

    public  String getIngredientName() {
        return ingredientName;
    }
    public double qetQuantity() {
        return quantity;
    }
    public String getUnit() {
        return unit;
    }
}
