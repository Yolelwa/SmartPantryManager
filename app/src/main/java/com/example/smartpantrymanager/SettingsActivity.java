package com.example.smartpantrymanager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Switch;
import android.content.Intent;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    private Switch switchAlerts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        switchAlerts = findViewById(R.id.switchAlerts);

        SharedPreferences preferences =
                getSharedPreferences("settings", MODE_PRIVATE);

        boolean alertsEnabled = preferences.getBoolean("alerts", true);
        switchAlerts.setChecked(alertsEnabled);

        switchAlerts.setOnCheckedChangeListener((buttonView, isChecked) ->
                preferences.edit()
                        .putBoolean("alerts", isChecked)
                        .apply()
        );

        Button btnPantry = findViewById(R.id.btnPanty);
        Button btnRecipes = findViewById(R.id.btnRecipes);

        btnPantry.setOnClickListener(v ->
                startActivity(
                        new Intent(
                                SettingsActivity.this,
                                MainActivity.class
                        )));
        btnRecipes.setOnClickListener(v ->
                startActivity(
                        new Intent(
                                SettingsActivity.this,
                               SuggestedRecipesActivity.class
                        )));

    }
}