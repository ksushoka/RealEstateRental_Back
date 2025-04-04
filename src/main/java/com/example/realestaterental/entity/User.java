package com.example.realestaterental.entity;

import com.example.realestaterental.entity.type.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity // Сущность, которая преобразуется в таблицу в БД
@Table(name = "users")
@AllArgsConstructor // Конструктор со всеми полями
@NoArgsConstructor // Пустой конструктор
@Getter
@Setter
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String username;
    private String password;
    private Boolean enabled = false;

    // Новое поле для хранения пути к фотографии
    private String photoPath;

    private String email;

    @OneToMany(mappedBy = "guest")
    @JsonIgnore
    private List<Booking> bookings;
//    @OneToMany(mappedBy = "guest")
//    private List<Booking> bookings;

    @Enumerated(EnumType.STRING)
    private Role role;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority(role.getAuthority());
        return Collections.singletonList(authority);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }


//    @OneToMany(mappedBy = "guest")
//    private List<Booking> bookings;

    @OneToMany(mappedBy = "host")
    @JsonIgnore
    private List<Property> properties;
}
