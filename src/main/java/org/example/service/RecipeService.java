package org.example.service;

import org.example.model.Recipe;
import org.example.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    public List<Recipe> getAllRecipes(){
        return recipeRepository.findAll();
    }
    public Optional<Recipe> getRecipeById(Long id){
        return recipeRepository.findById(id);
    }
    public Recipe addRecipe(Recipe recipe)
    {
        return recipeRepository.save(recipe);
    }

    public List<Recipe> searchRecipesByName(String name){
        return recipeRepository.findByNameContainingIgnoreCase(name);
    }
    public List<Recipe> searchRecipesByIngredient(String ingredient){
        return recipeRepository.findByIngredientsContainingIgnoreCase(ingredient);
    }
}
