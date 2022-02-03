package com.jorfermo.tacocloud.data;

import java.util.Optional;

import com.jorfermo.tacocloud.tacos.Ingredient;

public interface IngredientsRepository {
   Iterable<Ingredient> findAll();

   Optional<Ingredient> findOne(String id);

   Ingredient save(Ingredient ingredient);
}
