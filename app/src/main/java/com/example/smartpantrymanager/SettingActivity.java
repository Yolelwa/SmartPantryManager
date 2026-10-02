package com.example.smartpantrymanager;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Switch;
import androidx.appcompat.app.AppCompatActivity;

public class SettingActivity extends AppCompatActivity{
    private Switch switchAlerts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        switchAlerts = findViewById(R.id.swithAlerts);

        SharedPreferences preferences =
                getSharedPreferences(
                        "settings",
                        MODE_PRIVATE
                );
        boolean alertsEnabled =
                preferences.getBoolean(
                        "alerts",
                        true
                );
        switchAlerts.setChecked(alertsEnabled);

        switchAlerts.setOnCheckedChangedListener(
                (buttonView, isChecked) -> {
                    preferences.edit()
                            .putBoolean(
                                    "alerts",
                                    isChecked
                            )
                            .apply();
                }
        );
    }
}
