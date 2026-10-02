package com.example.smartpantrymanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class PantryDb extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "smart_pantry.db";
    private static final int DATABASE_VERSION = 3;

    public PantryDb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE pantry (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "name TEXT NOT NULL," +
                        "quantity REAL NOT NULL," +
                        "unit TEXT NOT NULL," +
                        "expiry_date TEXT)"
        );

        db.execSQL(
                "CREATE TABLE recipes (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "name TEXT NOT NULL," +
                        "steps TEXT NOT NULL)"
        );

        db.execSQL(
                "CREATE TABLE recipe_ingredients (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "recipe_id INTEGER NOT NULL," +
                        "ingredient_name TEXT NOT NULL," +
                        "quantity REAL NOT NULL," +
                        "unit TEXT NOT NULL)"
        );

        seedRecipes(db);
    }

    // Adds a recipe and its ingredients. Each ingredient is {name, quantity, unit}.
    private void addRecipe(SQLiteDatabase db, String name, String steps, String[][] ingredients) {
        ContentValues rv = new ContentValues();
        rv.put("name", name);
        rv.put("steps", steps);
        long recipeId = db.insert("recipes", null, rv);

        for (String[] ing : ingredients) {
            ContentValues iv = new ContentValues();
            iv.put("recipe_id", recipeId);
            iv.put("ingredient_name", ing[0]);
            iv.put("quantity", Double.parseDouble(ing[1]));
            iv.put("unit", ing[2]);
            db.insert("recipe_ingredients", null, iv);
        }
    }

    private void seedRecipes(SQLiteDatabase db) {
        addRecipe(db, "Tomato Omelette", "Beat eggs, add chopped tomato and fry",
                new String[][]{{"egg", "2", "pcs"}, {"tomato", "1", "pcs"}});
        addRecipe(db, "Cheese Omelette", "Whisk eggs, add cheese and cook",
                new String[][]{{"egg", "2", "pcs"}, {"cheese", "30", "g"}});
        addRecipe(db, "Tomato Toast", "Toast bread and top with sliced tomato",
                new String[][]{{"bread", "2", "pcs"}, {"tomato", "1", "pcs"}});
        addRecipe(db, "Cheese Toast", "Toast bread and add cheese",
                new String[][]{{"bread", "2", "pcs"}, {"cheese", "30", "g"}});
        addRecipe(db, "Banana Smoothie", "Blend banana and milk",
                new String[][]{{"banana", "1", "pcs"}, {"milk", "250", "ml"}});
        addRecipe(db, "Scrambled Eggs", "Whisk eggs and cook in a pan",
                new String[][]{{"egg", "3", "pcs"}});
        addRecipe(db, "Egg Toast", "Fry egg and serve on toast",
                new String[][]{{"egg", "1", "pcs"}, {"bread", "1", "pcs"}});
        addRecipe(db, "Tomato Pasta", "Cook pasta and mix with tomato",
                new String[][]{{"pasta", "100", "g"}, {"tomato", "2", "pcs"}});
        addRecipe(db, "Garlic Pasta", "Cook pasta and mix with garlic",
                new String[][]{{"pasta", "100", "g"}, {"garlic", "2", "pcs"}});
        addRecipe(db, "Cheesy Pasta", "Cook pasta and stir in cheese",
                new String[][]{{"pasta", "100", "g"}, {"cheese", "50", "g"}});
        addRecipe(db, "Tomato Rice", "Cook rice and mix with tomato",
                new String[][]{{"rice", "100", "g"}, {"tomato", "2", "pcs"}});
        addRecipe(db, "Egg fried Rice", "Fry rice with egg",
                new String[][]{{"rice", "100", "g"}, {"egg", "2", "pcs"}});
        addRecipe(db, "Bean Rice Bowl", "Serve rice with cooked beans",
                new String[][]{{"rice", "100", "g"}, {"bean", "100", "g"}});
        addRecipe(db, "Mashed Potatoes", "Boil potatoes and mash",
                new String[][]{{"potato", "3", "pcs"}});
        addRecipe(db, "Roast Potatoes", "Roast potatoes until golden",
                new String[][]{{"potato", "3", "pcs"}});
        addRecipe(db, "Banana Oats", "Cook oats and add banana",
                new String[][]{{"oat", "50", "g"}, {"banana", "1", "pcs"}});
        addRecipe(db, "Apple Oats", "Cook oats and add apple",
                new String[][]{{"oat", "50", "g"}, {"apple", "1", "pcs"}});
        addRecipe(db, "Tomato Bean Stew", "Cook beans with tomato",
                new String[][]{{"bean", "150", "g"}, {"tomato", "2", "pcs"}});
        addRecipe(db, "Garlic Potatoes", "Cook potatoes with garlic",
                new String[][]{{"potato", "3", "pcs"}, {"garlic", "2", "pcs"}});
        addRecipe(db, "Simple Pancakes", "Mix flour, egg and milk; cook on a pan",
                new String[][]{{"flour", "100", "g"}, {"egg", "1", "pcs"}, {"milk", "150", "ml"}});
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS recipe_ingredients");
        db.execSQL("DROP TABLE IF EXISTS recipes");
        db.execSQL("DROP TABLE IF EXISTS pantry");
        onCreate(db);
    }

    public long addPantryItem(String name, double quantity, String unit, String expiryDate) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("quantity", quantity);
        values.put("unit", unit);
        values.put("expiry_date", expiryDate);

        return db.insert("pantry", null, values);
    }

    public List<PantryItem> getAllPantryItems() {
        List<PantryItem> items = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM pantry", null);
        while (cursor.moveToNext()) {
            items.add(new PantryItem(
                    cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getDouble(2),
                    cursor.getString(3),
                    cursor.getString(4)
            ));
        }
        cursor.close();
        return items;
    }

    public List<Recipe> getAllRecipes() {
        List<Recipe> recipes = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor rc = db.rawQuery("SELECT id, name, steps FROM recipes", null);
        while (rc.moveToNext()) {
            long id = rc.getLong(0);
            Recipe recipe = new Recipe(id, rc.getString(1), rc.getString(2));

            Cursor ic = db.rawQuery(
                    "SELECT ingredient_name, quantity, unit FROM recipe_ingredients WHERE recipe_id = ?",
                    new String[]{String.valueOf(id)});
            while (ic.moveToNext()) {
                recipe.addIngredient(new RecipeIngredient(
                        ic.getString(0), ic.getDouble(1), ic.getString(2)));
            }
            ic.close();

            recipes.add(recipe);
        }
        rc.close();
        return recipes;
    }

    public void deleteItem(long id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("pantry", "id=?", new String[]{String.valueOf(id)});
    }

    public boolean pantryItemExists(String name, String unit) {
        SQLiteDatabase db = getReadableDatabase();

        String query =
                "SELECT id FROM pantry " +
                        "WHERE LOWER(name) = LOWER(?) " +
                        "AND LOWER(unit) = LOWER(?)";
        Cursor cursor = db.rawQuery(query, new String[]{name, unit});
        boolean exists = cursor.moveToFirst();
        cursor.close();
        return exists;
    }
}