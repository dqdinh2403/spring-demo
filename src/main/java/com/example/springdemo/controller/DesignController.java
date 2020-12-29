package com.example.springdemo.controller;

import com.example.springdemo.domain.Design;
import com.example.springdemo.domain.Ingredient;
import com.example.springdemo.domain.Order;
import com.example.springdemo.service.DesignRepository;
import com.example.springdemo.service.IngredientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignController {

    private IngredientRepository ingredientRepository;

    private DesignRepository designRepository;

    @ModelAttribute(name = "order")
    public Order order(){
        return new Order();
    }

    @ModelAttribute(name = "design")
    public Design design(){
        return new Design();
    }


    @Autowired
    public DesignController(IngredientRepository ingredientRepository, DesignRepository designRepository){
        this.ingredientRepository = ingredientRepository;
        this.designRepository = designRepository;
    }

    @GetMapping
    public String showDesignForm(Model model){
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepository.findAll().forEach(i -> ingredients.add(i));

        Ingredient.Type[] types = Ingredient.Type.values();
        for(Ingredient.Type type : types){
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }

        model.addAttribute("design", new Design());
        return "design";
    }

    @PostMapping
    public String processDesign(@Valid Design design, Errors errors, @ModelAttribute Order order){
        if(errors.hasErrors()){
            return "design";
        }

        Design designPersistence = designRepository.save(design);
        order.addDesign(designPersistence);

        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Ingredient.Type type) {
        return ingredients.stream().filter(each -> each.getType() == type).collect(Collectors.toList());
    }

}
