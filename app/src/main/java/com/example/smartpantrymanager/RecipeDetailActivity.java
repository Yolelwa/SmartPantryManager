package com.example.smartpantrymanager;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;
public class RecipeDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState;
        setContentView(R.layout.activity_recipe_detail);
        TextView txtRecipeName = findViewById(R.id.txtRecipeName);
        TextView txtIngredients = findViewById(R.id.txtIngredients);
        TextView txtSteps = findViewById(R.id.txtSteps);
        txtRecipeName.setText("Tomato Omelette");
        txtIngredients.setText(
                "2 Eggs\n1 Tomato\n5ml Oil"
        );
        txtSteps.setText(
                "Beat eggs, add tomato, and cook in a pan."
        );
    }
}
