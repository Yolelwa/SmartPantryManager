package com.example.smartpantrymanager;

import java.util.*;
public class Recipe {
    public long id;
    public String name,method;
    public List<RecipeIngredient> ingredients=new ArrayList<>();
    public Recipe(long i,String n,String m){id=i;name=n;method=m;}
}
