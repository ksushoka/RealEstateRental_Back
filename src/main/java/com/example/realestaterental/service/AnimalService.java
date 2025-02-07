package com.example.realestaterental.service;

import com.example.realestaterental.entity.Animal;
import com.example.realestaterental.repository.AnimalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnimalService {

    private final AnimalRepository animalRepository;

    public void saveAnimal(Animal animal) {
       animalRepository.save(animal);
   }
   public Animal getAnimalById(Integer id) {
      Animal animal = animalRepository.findById(id).orElse(null);
      return animal;
   }
   public Animal changeWeightById(double weight, Integer id) {
        Animal animal = animalRepository.findById(id).orElse(null);
        animal.setWeight(weight);
        animalRepository.save(animal);
        return animal;
   }
}
