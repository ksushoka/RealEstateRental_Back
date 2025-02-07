package com.example.realestaterental.service;


import com.example.realestaterental.entity.User;
import com.example.realestaterental.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getUserById(Integer id) {
        User user = userRepository.findById(id).orElse(null);
        return user;
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public User changeUserName(String newUserName, Integer id) {
        User user = userRepository.findById(id).orElse(null);
        user.setUsername(newUserName);
        userRepository.save(user);
        return user;
    }

    public User findUserByUserName(String userName) {
        User user = userRepository.findByUsername(userName).orElse(null);
        return user;
    }

    public void deleteUserById(Integer id) {
        User user = userRepository.findById(id).orElse(null);
        userRepository.delete(user);
    }

}


