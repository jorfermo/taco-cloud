package com.jorfermo.tacocloud.web;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.Valid;

import com.jorfermo.tacocloud.data.IngredientsRepository;
import com.jorfermo.tacocloud.tacos.Ingredient;
import com.jorfermo.tacocloud.tacos.Ingredient.Type;
import com.jorfermo.tacocloud.tacos.Taco;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {

   private IngredientsRepository ingredientsRepo;

   public DesignTacoController(IngredientsRepository ingredientsRepo) {
      this.ingredientsRepo = ingredientsRepo;
   }

   @ModelAttribute
   public void addIngredientsToModel(Model model) {
      Iterable<Ingredient> ingredients = ingredientsRepo.findAll();
      Type[] types = Ingredient.Type.values();
      for (Ingredient.Type type : types) {
         model.addAttribute(type.toString().toLowerCase(),
               filterByType(ingredients, type));
      }
   }

   @GetMapping
   public String showDesignForm(Model model) {
      model.addAttribute("taco", new Taco());
      return "design";
   }

   @PostMapping
   public String processTaco(@Valid @ModelAttribute("taco") Taco taco, Errors errors) {
      if (errors.hasErrors()) {
         return "design";
      }

      log.info("Processing taco " + taco);

      return "redirect:/orders/current";
   }

   private Iterable<Ingredient> filterByType(Iterable<Ingredient> ingredients, Ingredient.Type type) {
      return StreamSupport.stream(ingredients.spliterator(), false).filter(x -> x.getType().equals(type))
            .collect(Collectors.toList());
   }
}
