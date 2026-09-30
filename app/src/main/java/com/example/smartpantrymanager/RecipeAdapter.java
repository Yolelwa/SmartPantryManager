package com.example.smartpantrymanager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {
    private final List<Recipe> recipes;

    public RecipeAdapter(List<Recipe> recipes) {
        this.recipes = recipes;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {
        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_recipe,
                                parent,false);
        return new ViewHolder(view);
    }
    @Override
    public  void onBindViewHolder(
            @NonNull ViewHolder holder,
            int position) {
        holder.recipeName.setText(
                recipes.get(position).getName()
        );
    }

        @Override
    public int getItemCount() {
        return recipes.size();
        }

        static class ViewHolder extends RecyclerView.ViewHolder {
        TextView recipeName;
        ViewHolder(View itemView) {
            super(itemView);
            recipeName =
                    itemView.findViewById(
                            R.id.txtRecipeName
                    );
        }


    }

}
