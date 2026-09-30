package com. example.smartpantrymanager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
public class PantryAdapter
        extends RecyclerView.Adapter<PantryAdapter.ViewHolder> {

private List<PantryItem> pantryItems;

public PantryAdapter(List<PantryItem> pantryItems) {
    this.pantryItems = pantryItems;
}
@NonNull
@Override
public ViewHolder onCreateViewHolder(
        @NonNull ViewGroup parent,
        int viewType) {
    View view = LayoutInflater.from(parent.getContext ())
            .inflate(R.layout.item_pantry,
                    parent, false);

    return new ViewHolder(view);
}
@Override
public void onBindViewHolder(
        @NonNull ViewHolder holder,
        int position) {
    PantryItem item = pantryItems.get(position);
    holder.txtName.setText(item.getName());

    holder.txtQuantity.setText(
            item.getQuantity()
                    + " " +
                    item.getUnit()
    );
}
@Override
public int getItemCount () {
    return pantryItems.size();
}
static class ViewHolder
        extends
        RecyclerView.ViewHolder {
    TextView txtName;
    TextView txtQuantity;
    ViewHolder (View itemView) {
        super (itemView);
        txtName =
                itemView.findViewById(R.id.txtName) ;
        txtQuantity =
                itemView.findViewById(R.id.txtQuantity) ;
    }
}
}