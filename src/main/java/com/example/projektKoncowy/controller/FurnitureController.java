package com.example.projektKoncowy.controller;


import com.example.projektKoncowy.model.Category;
import com.example.projektKoncowy.model.Furniture;
import com.example.projektKoncowy.model.TypeOfFurniture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
public class FurnitureController {


    @GetMapping
    public String showDesignForm(Model model) {
        List<Furniture> furnitures = Arrays.asList
                (new Furniture("One", "black", TypeOfFurniture.CHAIRS),
                        new Furniture("Two", "brown", TypeOfFurniture.CHAIRS),
                        new Furniture("FOUR", "white", TypeOfFurniture.CHAIRS),
                        new Furniture("Three", "brown", TypeOfFurniture.TABLES),
                        new Furniture("Four", "purple", TypeOfFurniture.TABLES));

        TypeOfFurniture[] types = TypeOfFurniture.values();
        for (TypeOfFurniture typeOfFurniture : types) {
            model.addAttribute(typeOfFurniture.toString().toLowerCase(), filterByType(furnitures, typeOfFurniture));
        }

        model.addAttribute("design", new Category());
        return "design";
    }

    @PostMapping
    public String processDesign(@Valid Category category, Errors errors){
        if(errors.hasErrors()){
            return "design";
        }
        log.info("Procesing design: " + category);
        return "redirect:/orders/current";
    }


    private List<Furniture> filterByType(List<Furniture> furnitures, TypeOfFurniture typeOfFurniture) {
        return furnitures.stream()
                .filter(x -> x.getTypeOfFurniture().equals(typeOfFurniture))
                .collect(Collectors.toList());
    }

}
