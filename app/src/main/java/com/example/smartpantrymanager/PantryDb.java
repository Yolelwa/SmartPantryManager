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
    private static final int DATABASE_VERSION = 2;

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

    private void seedRecipes(SQLiteDatabase db) {
        db.execSQL("INSERT INTO recipes(name, step) VALUES(" + "'Tomato Omelette'," + "'Beat eggs, add chopped tomato and fry')");
        db.execSQL("INSERT INTO recipes(name, steps) VALUES(" + "'Cheese Omelette'," + "'Whisk eggs, add cheese and cook')");
        db.execSQL("INSERT INTO recipes(name, steps) VALUES(" + "'Tomato Toast'," + "'Toast bread and top with sliced tomato')");
        db.execSQL("INSERT INTO recipes(name, steps) VALUES(" + "'Cheese Toast'," + "'Toast bread and add cheese')");
        db.execSQL("INSERT INTO recipes(name, steps) VALUES(" + "'Banana Smoothie'," + "'Blend banana and milk')");
        db.execSQL("INSERT INTO recipes(name, steps) VALUES(" + "'Scrambled Eggs'," + "'Whisk eggs and cook in a pan')");
        db.execSQL("INSERT INTO recipes(name, steps) VALUES(" + "'Egg Toast'," + "'Fry egg and serve on toast')");
        db.execSQL("INSERT INTO recipes(name, steps) VALUES(" + "'Tomato Pasta'," + "'Cook pasta and mix with tomato')");
        db.execSQL("INSERT INTO recipes(name, steps) VALUES(" + "'Garlic Pasta'," + "'Cook pasta and mix with garlic')");
        db.execSQL("INSERT INTO recipes(name, steps) VALUES(" + "'Cheesy Pasta'," + "'Cook pasta and stir in cheese')");
        db.execSQL("INSERT INTO recipes(name, steps) VALUES(" + "'Tomato Rice'," + "'Cook rice and mix with tomato')");
        db.execSQL("INSERT INTO recipes(name, steps) VALUES(" + "'Egg fried Rice'," + "'Fry rice with egg')");
        db.execSQL("INSERT INTO recipes(name, steps) VALUES(" + "'Bean Rice Bowl'," + "'Serve rice with cooked beans')");
        db.execSQL("INSERT INTO recipes(name, steps) VALUES(" + "'Mashed Potatoes'," + "'Boil potatoes and mash')");
        db.execSQL("INSERT INTO recipes(name, steps) VALUES(" + "'Roast Potatoes'," + "'Roast potatoes until golden')");
        db.execSQL("INSERT INTO recipes(name, steps) VALUES(" + "'Banana Oats'," + "'Cook oats and add banana')");
        db.execSQL("INSERT INTO recipes(name, steps) VALUES(" + "'Apple Oats'," + "'Cook oats and add apple')");
        db.execSQL("INSERT INTO recipes(name, steps) VALUES(" + "'Tomato Bean Stew'," + "'Cook beans with tomato')");
        db.execSQL("INSERT INTO recipes(name, steps) VALUES(" + "'Garlic Potatoes'," + "'Cook potatoes with garlic')");
        db.execSQL("INSERT INTO recipes(name, steps) VALUES(" + "'Simple Pancakes'," + "'Mix flour, egg and milk; cook on a pan')");


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