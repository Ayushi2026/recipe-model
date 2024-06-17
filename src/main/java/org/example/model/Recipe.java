package org.example.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;
    @Column(length = 1000)
    private String instructions;
    @ElementCollection
    @CollectionTable(name = "recipe_ingredients", joinColumns = @JoinColumn(name = "recipe_id"))
    @Column(name = "ingredient")
    private List<String> ingredients;

    public Long getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getInstructions(){
        return instructions;
    }

    public  List<String> getIngredients(){
        return ingredients;
    }
    public void setId(){
        this.id =id;
    }
    public void setName(String name){
        this.name= this.name;
    }
    public void setInstructions(String instructions){
        this.instructions= this.instructions;
    }
    public void setIngredients(List<String>ingredients){this.ingredients=ingredients;
    }
}

