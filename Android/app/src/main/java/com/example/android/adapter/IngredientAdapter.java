package com.example.android.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.R;
import com.example.android.model.FoodIngredient;

import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder>  {

    private List<FoodIngredient> ingredients;

    public IngredientAdapter(List<FoodIngredient> ingredients) {
        this.ingredients = ingredients;
    }

    @NonNull
    @Override
    public IngredientAdapter.IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.ingredient_row, parent, false);
        IngredientAdapter.IngredientViewHolder viewHolder = new IngredientViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientAdapter.IngredientViewHolder holder, int position) {
        final FoodIngredient ingredient = ingredients.get(position);
        holder.ingredientName.setText(ingredient.getName());

    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }


    public static class IngredientViewHolder extends RecyclerView.ViewHolder {
        public TextView ingredientName;

        public IngredientViewHolder(View itemView) {
            super(itemView);
            this.ingredientName = (TextView) itemView.findViewById(R.id.ingredientName);
        }
    }

}
