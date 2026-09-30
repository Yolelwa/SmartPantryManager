package com.example.smartpantrymanager;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
 import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

   RecyclerView recyclerView;
   PantryAdapter pantryAdapter;

   @Override
protected void onCreate(Bundle savedInstanceState) {
   super.onCreate(savedInstanceState);
   setContentView(R.layout.activity_main);

   recyclerView = findViewById(R.id.recyclerViewPantry);

   recyclerView.setLayoutManager(
           new LinearLayoutManager(this));
   pantryAdapter = new PantryAdapter(new ArrayList<>());
   recyclerView.setAdapter(pantryAdapter);
   }
}