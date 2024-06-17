package org.example.controller;

import org.example.model.Recipe;
import org.example.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping
    public List<Recipe> getAllRecipes(){
        return recipeService.getAllRecipes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable Long id) {
        Optional<Recipe> recipe = recipeService.getRecipeById(id);
        if (recipe.isPresent()) {
            return ResponseEntity.ok(recipe.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public Recipe addRecipe(@RequestBody Recipe recipe){
        return  recipeService.addRecipe(recipe);
    }

    @GetMapping("/search")
    public List<Recipe> searchRecipes(@RequestParam(required = false) String name,
    @RequestParam(required = false) String ingredient){
        if(name!= null) {
            return recipeService.searchRecipesByName(name);
        }
        else if(ingredient!=null){
            return  recipeService.searchRecipesByIngredient(ingredient);
        }
        else {
            return  recipeService.getAllRecipes();
        }
    }
}
