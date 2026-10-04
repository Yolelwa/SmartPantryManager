package com.example.smartpantrymanager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SuggestedRecipesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecipeAdapter recipeAdapter;
    private PantryDb db;
    private final List<Recipe> suggestedRecipes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggested_recipes);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        db = new PantryDb(this);

        recyclerView = findViewById(R.id.recyclerRecipes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        recipeAdapter = new RecipeAdapter(suggestedRecipes);
        recyclerView.setAdapter(recipeAdapter);

        Button btnPantry = findViewById(R.id.btnPanty);
        Button btnSettings = findViewById(R.id.btnSettings);

        btnPantry.setOnClickListener(v ->
                startActivity(
                        new Intent(
                                this,
                                MainActivity.class
                        )));
        btnSettings.setOnClickListener(v ->
                startActivity(
                        new Intent(
                                this,
                                SettingsActivity.class
                        )));
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadSuggestions();
    }

    private void loadSuggestions() {

        suggestedRecipes.clear();
        suggestedRecipes.addAll(
             db.getAllRecipes()

        );
        recipeAdapter.notifyDataSetChanged();

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}