package com.example.realestaterental.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity//сущность, которая преобразуется в табличку в БД с теми полями, которые мы задаём
@Table(name = "animals")
@AllArgsConstructor//создаёт конструктор со всеми полями
@NoArgsConstructor//создаёт пустой конструктор
@Getter//взять
@Setter//установить, изменить
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private double weight;
    private int age;
    @Enumerated(EnumType.STRING)
    private HealthyAnimal healthyAnimal;
}
