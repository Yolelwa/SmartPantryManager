package com.example.smartpantrymanager;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RecipeDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        TextView txtRecipeName = findViewById(R.id.txtRecipeName);
        TextView txtIngredients = findViewById(R.id.txtIngredients);
        TextView txtSteps = findViewById(R.id.txtSteps);

        txtRecipeName.setText("Tomato Omelette");
        txtIngredients.setText("2 Eggs\n1 Tomato\n5ml Oil");
        txtSteps.setText("Beat eggs, add tomato, and cook in a pan.");
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

}