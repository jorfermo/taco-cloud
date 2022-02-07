package com.jorfermo.tacocloud.data;

import com.jorfermo.tacocloud.tacos.Ingredient;

import org.springframework.data.repository.CrudRepository;

public interface IngredientsRepository extends CrudRepository<Ingredient, String> {

}
