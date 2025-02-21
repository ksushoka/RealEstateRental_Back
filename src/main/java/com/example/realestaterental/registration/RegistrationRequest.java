package com.example.realestaterental.registration;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.usertype.UserType;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class RegistrationRequest {
    private String username;
//    private String firstname;
//    private String lastname;
    private String email;
    private String password;
//    private UserType type;
}
