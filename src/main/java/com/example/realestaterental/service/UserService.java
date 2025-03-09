package com.example.realestaterental.service;

import com.example.realestaterental.entity.User;
import com.example.realestaterental.entity.type.Role;
import com.example.realestaterental.registration.RegistrationRequest;
import com.example.realestaterental.registration.token.ConfirmationTokenEntity;
import com.example.realestaterental.registration.token.ConfirmationTokenService;
import com.example.realestaterental.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final static String USER_NOT_FOUND_MSG =
            "user with email %s not found";
    private final static Integer EMAIL_SEND_MINUTS = 15;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return userRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, username)));
//
//    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return user; // Возвращаем напрямую вашу сущность User
    }

    public String signUpUser(RegistrationRequest registrationRequest) {
        boolean userExists = userRepository
                .findByUsername(registrationRequest.getUsername())
                .isPresent();

        if (userExists) {
            // TODO check of attributes are the same and
            // TODO if email not confirmed send confirmation email.

            throw new IllegalStateException("email already taken");
        }
//appUserRepository.findByEmail("email").ifPresentOrElse(AppUser::getEmail);
        String encodedPassword = bCryptPasswordEncoder
                .encode(registrationRequest.getPassword());
        User user = new User();
        user.setPassword(encodedPassword);
        user.setUsername(registrationRequest.getUsername());
        user.setEmail(registrationRequest.getEmail());
        user.setRole(Role.USER);
        userRepository.save(user);

        String token = UUID.randomUUID().toString();

        ConfirmationTokenEntity confirmationTokenEntity = new ConfirmationTokenEntity(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(EMAIL_SEND_MINUTS),
                user
        );

        confirmationTokenService.saveConfirmationToken(
                confirmationTokenEntity);


        return token;
    }

//    private static User setAttributeForUSer(RegistrationRequest request) {
//        User newUser = new User();
//        newUser.setUsername(request.getUsername());
//        newUser.setEmail(request.getEmail());
//        newUser.setType(request.getType());
//        newUser.setFirstname(request.getFirstname());
//        newUser.setLastname(request.getLastname());
//        newUser.setPassword(request.getPassword());
//        return newUser;
//    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void enableUser(String email) {
        userRepository.enableUser(email);
    }
}