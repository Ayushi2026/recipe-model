package org.example.controller;

import org.example.model.Recipe;
import org.example.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class RecipeControllerTest {

    @InjectMocks
    private RecipeController recipeController;

    @Mock
    private RecipeService recipeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_get_all_recipes() {
        //Given
        Recipe recipe1 = new Recipe();
        recipe1.setId(1L);
        recipe1.setName("Chicken Biryani");
        recipe1.setInstructions("Cook rice and mix with spices");
        recipe1.setIngredients(Arrays.asList("Rice", "Chicken","Spices"));

        Recipe recipe2 = new Recipe();
        recipe2.setId(2L);
        recipe2.setName("Vegetable Biryani");
        recipe2.setInstructions("Cook rice and mix with spices");
        recipe2.setIngredients(Arrays.asList("Rice", "Gobhi","Spices"));

        List<Recipe> recipes = Arrays.asList(recipe1, recipe2);

        //when
        when(recipeService.getAllRecipes()).thenReturn(recipes);
        List<Recipe> result = recipeController.getAllRecipes();

        //then
        assertEquals(2, result.size());
        verify(recipeService, times(1)).getAllRecipes();
    }

    @Test
    public void should_get_recipe_by_id() {
        //Given
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        recipe.setName("Chicken Biryani");

        //When
        when(recipeService.getRecipeById(1L)).thenReturn(recipe);
        ResponseEntity<Recipe> response = recipeController.getRecipeById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(recipe, response.getBody());

        when(recipeService.getRecipeById(2L)).thenReturn(null);
        ResponseEntity<Recipe> responseNotFound = recipeController.getRecipeById(2L);

       //Then
        assertEquals(HttpStatus.NOT_FOUND, responseNotFound.getStatusCode());
        assertNull(responseNotFound.getBody());
    }

    @Test
    public void should_create_recipe() {
        //Given
        Recipe recipe = new Recipe();
        recipe.setName("Chicken Biryani");
        recipe.setInstructions("Cook rice and mix with spices");
        recipe.setIngredients(Arrays.asList("Rice", "Chicken","Spices"));

        //When
        when(recipeService.createRecipe(any(Recipe.class))).thenReturn(recipe);

        Recipe result = recipeController.createRecipe(recipe);
        //Then
        assertEquals("Chicken Biryani", result.getName());
        assertEquals("Cook rice and mix with spices", result.getInstructions());
        assertEquals(Arrays.asList("Rice", "Chicken","Spices"), result.getIngredients());
        verify(recipeService, times(1)).createRecipe(recipe);
    }

    @Test
    public void should_update_recipe() {
        //Given
        Recipe existingRecipe = new Recipe();
        existingRecipe.setId(1L);
        existingRecipe.setName("Chicken Biryani");

        Recipe updatedDetails = new Recipe();
        updatedDetails.setName("Vegatable Biryani");

        //When
        when(recipeService.updateRecipe(eq(1L), any(Recipe.class))).thenReturn(existingRecipe);
        ResponseEntity<Recipe> response = recipeController.updateRecipe(1L, updatedDetails);

        //Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(existingRecipe, response.getBody());

        when(recipeService.updateRecipe(eq(2L), any(Recipe.class))).thenReturn(null);

        ResponseEntity<Recipe> responseNotFound = recipeController.updateRecipe(2L, updatedDetails);

        assertEquals(HttpStatus.NOT_FOUND, responseNotFound.getStatusCode());
    }

    @Test
    public void should_delete_recipe() {
        //Given
        doNothing().when(recipeService).deleteRecipe(1L);

        //When
        ResponseEntity<Void> response = recipeController.deleteRecipe(1L);

        //Then
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(recipeService, times(1)).deleteRecipe(1L);
    }
}