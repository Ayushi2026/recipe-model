package org.example.service;

import org.example.model.Recipe;
import org.example.repository.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class RecipeServiceTest {

    @InjectMocks
    private RecipeService recipeService;

    @Mock
    private RecipeRepository recipeRepository;

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

        //When
        when(recipeRepository.findAll()).thenReturn(recipes);
        List<Recipe> result = recipeService.getAllRecipes();

       //Then
        assertEquals(2, result.size());
        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    public void should_get_recipe_by_id() {
        //Given
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        recipe.setName("Chicken Biryani");

        //When
        when(recipeRepository.findById(1L)).thenReturn(Optional.of(recipe));
        Recipe result = recipeService.getRecipeById(1L);
        when(recipeRepository.findById(2L)).thenReturn(Optional.empty());
        Recipe resultNotFound = recipeService.getRecipeById(2L);

        //Then
        assertEquals(recipe, result);
        assertNull(resultNotFound);
    }

    @Test
    public void should_create_the_recipe() {

        //Given
        Recipe recipe = new Recipe();
        recipe.setName("Chicken Biryani");

        //When
        when(recipeRepository.save(any(Recipe.class))).thenReturn(recipe);
        Recipe result = recipeService.createRecipe(recipe);

        //Then
        assertEquals("Chicken Biryani", result.getName());
        verify(recipeRepository, times(1)).save(recipe);
    }

    @Test
    public void should_update_the_recipe() {

        //Given
        Recipe existingRecipe = new Recipe();
        existingRecipe.setId(1L);
        existingRecipe.setName("Chicken Biryani");

        Recipe updatedDetails = new Recipe();
        updatedDetails.setName("Vegetable Biryani");

        //When
        when(recipeRepository.findById(1L)).thenReturn(Optional.of(existingRecipe));
        when(recipeRepository.save(any(Recipe.class))).thenReturn(existingRecipe);
        Recipe result = recipeService.updateRecipe(1L, updatedDetails);

        when(recipeRepository.findById(2L)).thenReturn(Optional.empty());
        Recipe resultNotFound = recipeService.updateRecipe(2L, updatedDetails);

        //Then
        assertEquals(existingRecipe, result);
        assertNull(resultNotFound);
    }

    @Test
    public void should_delete_the_recipe() {

        //Given
        doNothing().when(recipeRepository).deleteById(1L);

        //When
        recipeService.deleteRecipe(1L);

        //Then
        verify(recipeRepository, times(1)).deleteById(1L);
    }
}