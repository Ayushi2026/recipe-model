package org.example.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;
    @ElementCollection
    private List<String> ingredients;
    @Column(length = 1000)
    private String instructions;

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
    public void setId(Long id){
        this.id =id;
    }
    public void setName(String name){
        this.name= name;
    }
    public void setInstructions(String instructions){
        this.instructions= instructions;
    }
    public void setIngredients(List<String> ingredients){this.ingredients=ingredients;
    }
}

