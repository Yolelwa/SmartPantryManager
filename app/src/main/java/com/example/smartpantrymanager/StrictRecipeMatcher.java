package com.example.smartpantrymanager;

import java.util.ArrayList;
import java.util.List;

public class StrictRecipeMatcher {

    public static boolean canMakeRecipe(Recipe recipe, List<PantryItem> pantryItems) {
        for (RecipeIngredient required : recipe.getIngredients()) {
            boolean ingredientFound = false;

            for (PantryItem pantryItem : pantryItems) {
                boolean sameName = normalizeName(pantryItem.getName())
                        .equals(normalizeName(required.getIngredientName()));

                boolean sameUnit = pantryItem.getUnit()
                        .equalsIgnoreCase(required.getUnit());

                boolean enoughQuantity =
                        pantryItem.getQuantity() >= required.getQuantity();

                if (sameName && sameUnit && enoughQuantity) {
                    ingredientFound = true;
                    break;
                }
            }

            if (!ingredientFound) {
                return false;
            }
        }
        return true;

    }

    public static List<Recipe> getSuggestedRecipes(
            List<Recipe> recipes,
            List<PantryItem> pantryItems) {

        List<Recipe> suggestions = new ArrayList<>();

        for (Recipe recipe : recipes) {
            if (canMakeRecipe(recipe, pantryItems)) {
                suggestions.add(recipe);
            }
        }
        return suggestions;
    }

    private static String normalizeName(String name) {
        String normalized = name.toLowerCase().trim();

        if (normalized.endsWith("s") && normalized.length() > 1) {
            normalized = normalized.substring(0, normalized.length() - 1);
        }
        return normalized;
    }
}