package com.example.springdemo.controller;

import com.example.springdemo.domain.Design;
import com.example.springdemo.domain.Ingredient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignController {

    @GetMapping
    public String showDesignForm(Model model){
        Ingredient wrap1 = new Ingredient("WRAP1", "wrap1 name", Ingredient.Type.WRAP);
        Ingredient wrap2 = new Ingredient("WRAP2", "wrap2 name", Ingredient.Type.WRAP);
        Ingredient protein1 = new Ingredient("PRO1", "protein1 name", Ingredient.Type.PROTEIN);
        Ingredient protein2 = new Ingredient("PRO2", "protein2 name", Ingredient.Type.PROTEIN);

        model.addAttribute(Ingredient.Type.WRAP.toString().toLowerCase(), new Ingredient[] {wrap1, wrap2});
        model.addAttribute(Ingredient.Type.PROTEIN.toString().toLowerCase(), new Ingredient[] {protein1, protein2});
        model.addAttribute("design", new Design());

        return "design";
    }

    @PostMapping
    public String processDesign(Design design){
        // save the design
        log.info("Processing design: " + design);

        return "redirect:/orders/current";
    }

}
