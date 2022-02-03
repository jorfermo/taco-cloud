package com.jorfermo.tacocloud.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.jorfermo.tacocloud.tacos.Ingredient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcIngredientsRepository implements IngredientsRepository {
   @Autowired
   private JdbcTemplate jdbcTemplate;

   public Iterable<Ingredient> findAll() {
      return jdbcTemplate.query("select id, name, type from ingredient", this::mapRowToIngredient);
   }

   public Optional<Ingredient> findOne(String id) {
      List<Ingredient> results = jdbcTemplate.query("select id, name, type from ingredient where id = ?",
            this::mapRowToIngredient, id);

      return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
   }

   public Ingredient save(Ingredient ingredient) {
      jdbcTemplate.update("insert into ingredient (id, name, type) values (?, ?, ?)",
            ingredient.getId(),
            ingredient.getName(),
            ingredient.getType());
      return ingredient;
   }

   private Ingredient mapRowToIngredient(ResultSet row, int rowNum) throws SQLException {
      return new Ingredient(row.getString("id"), row.getString("name"), Ingredient.Type.valueOf(row.getString("type")));
   }
}
