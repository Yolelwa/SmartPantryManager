package com.example.smartpantrymanager;

import android.content.Context;

import android.database.sqlite.SQLiteDatabase;

import android.database.sqlite.SQLiteOpenHelper;

public class PantryDb extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "smart_pantry.db";

    private static final int DATABASE_VERSION = 1;

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

    }

    @Override

    public void onUpgrade(SQLiteDatabase db,

                          int oldVersion,

                          int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS recipe_ingredients");

        db.execSQL("DROP TABLE IF EXISTS recipes");

        db.execSQL("DROP TABLE IF EXISTS pantry");

        onCreate(db);

    }

}