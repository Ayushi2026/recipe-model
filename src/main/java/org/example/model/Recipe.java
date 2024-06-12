package org.example.model;

import jakarta.persistence.*;

@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;
    private String instructions;
    private String ingredients;

    public Recipe(){}
    public Recipe(String name, String instructions, String ingredients)
    {
        this.name=name;
        this.instructions= instructions;
        this.ingredients= ingredients;

    }
    public Long getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getInstructions(){
        return instructions;
    }

    public  String getIngredients(){
        return ingredients;
    }

    public void setId(){
        this.id =id;
    }

    public void setName(){
        this.name=name;
    }
    public void setInstructions(){
        this.instructions=instructions;
    }

    public void setIngredients(){
        this.ingredients=ingredients;
    }

}

