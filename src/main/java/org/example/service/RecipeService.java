package org.example.service;

import org.example.model.Recipe;
import org.example.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    public List<Recipe> getAllRecipes(){
        return recipeRepository.findAll();
    }
    public Recipe getRecipeById(Long id){
        return recipeRepository.findById(id).orElse(null);
    }
    public Recipe createRecipe(Recipe recipe)
    {
        return recipeRepository.save(recipe);
    }

 public Recipe updateRecipe(Long id, Recipe recipeDetails){
        Recipe recipe = recipeRepository.findById(id).orElse(null);
        if (recipe!=null)
        {
            recipe.setName(recipeDetails.getName());
            recipe.setInstructions(recipeDetails.getInstructions());
            recipe.setIngredients(recipeDetails.getIngredients());
            return recipeRepository.save(recipe);
        }
        return null;
 }
 public void deleteRecipe(Long id)
 {
     recipeRepository.deleteById(id);
 }
}
