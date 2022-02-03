package com.jorfermo.tacocloud.web;

import com.jorfermo.tacocloud.data.IngredientsRepository;
import com.jorfermo.tacocloud.tacos.Ingredient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {
      private IngredientsRepository ingredientsRepo;

      @Autowired
      public IngredientByIdConverter(IngredientsRepository ingredientsRepo) {
            this.ingredientsRepo = ingredientsRepo;
      }

      @Override
      public Ingredient convert(String id) {
            return ingredientsRepo.findOne(id).orElse(null);
      }
}
