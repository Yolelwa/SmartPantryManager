package com.example.smartpantrymanager;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddEditIngredientActivity extends AppCompatActivity {
    private EditText edtName;
    private EditText edtQuantity;
    private EditText edtUnit;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);


        edtName = findViewById(R.id.edtName);
        edtQuantity = findViewById(R.id.edtQuantity);
        edtUnit = findViewById(R.id.edtUnit);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(v ->saveIngredient());
    }
private void saveIngredient() {
        String name =
                edtName.getText().toString().trim();

    String quantityText =
            edtQuantity.getText().toString().trim();
    String unit =
            edtUnit.getText().toString().trim();

    if (name.isEmpty()) {
        edtName.setError("Ingredient name is required");
        return;
    }

    if (quantityText.isEmpty()) {
        edtQuantity.setError("Quantity is required");
    }

    double quantity = 0;
    try {
        quantity = Double.parseDouble(quantityText);
    } catch (NumberFormatException e) {
        edtQuantity.setError("Enter a valid quantity");
        return;
    }

    if (quantity <= 0) {
        edtQuantity.setError(
                "Quantity must be greater than zero"
        );
        return;
    }
    PantryDb db =new PantryDb(this);

    if (db.pantryItemExists(name, unit)) {
        Toast.makeText(
                this,
                "Ingredient already exists",
                Toast.LENGTH_SHORT
        ).show();
        return;
    }
    db.addPantryItem(
            name,
            quantity,
            unit,
            ""
    );
    Toast.makeText(
            this,
            "Ingredient added successfully",
            Toast.LENGTH_SHORT
    ).show();
}
}
