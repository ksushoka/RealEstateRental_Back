package com.example.realestaterental.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity // Сущность, которая преобразуется в таблицу в БД
@Table(name = "users")
@AllArgsConstructor // Конструктор со всеми полями
@NoArgsConstructor // Пустой конструктор
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String username;
    private String password;

    // Новое поле для хранения пути к фотографии
    private String photoPath;

    private String email;

//    @OneToMany(mappedBy = "guest")
//    private List<Booking> bookings;

//    @OneToMany(mappedBy = "host")
//    private List<Property> properties;
}
