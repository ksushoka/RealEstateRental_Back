package com.example.realestaterental.controller;

import com.example.realestaterental.entity.Animal;
import com.example.realestaterental.service.AnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/animals")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AnimalController {

    private final AnimalService animalService;

    @PostMapping("/save")
    public void saveAnimal(@RequestBody Animal animal) {
        animalService.saveAnimal(animal);
    }
    @GetMapping("/{id}")
    public Animal getAnimalById(@PathVariable("id") Integer id){
        return animalService.getAnimalById(id);
    }
    @PatchMapping("/update/{id}")
    public Animal updateAnimal(@PathVariable("id") Integer id,@RequestParam ("newWeight") double newWeight){
        return animalService.changeWeightById(newWeight, id);
    }
}
