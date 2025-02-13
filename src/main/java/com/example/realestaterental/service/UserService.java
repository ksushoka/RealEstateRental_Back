package com.example.realestaterental.service;


import com.example.realestaterental.entity.User;
import com.example.realestaterental.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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
        if (user != null) {
            if (user.getPhotoPath() != null && !user.getPhotoPath().isEmpty()) {
                String filePathStr = user.getPhotoPath();
                if (filePathStr.startsWith("/")) {
                    filePathStr = filePathStr.substring(1);
                }
                Path filePath = Paths.get(filePathStr);
                try {
                    Files.deleteIfExists(filePath);
                    System.out.println("Photo of user remotely: " + filePath.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            userRepository.delete(user);
        }
    }


    public List<User> getAll(){
        return userRepository.findAll();
    }

    public void deleteUserByName(String userName) {
        User user = userRepository.findByUsername(userName).orElse(null);
        if (user != null) {
            if (user.getPhotoPath() != null && !user.getPhotoPath().isEmpty()) {
                String filePathStr = user.getPhotoPath();
                if (filePathStr.startsWith("/")) {
                    filePathStr = filePathStr.substring(1);
                }
                Path filePath = Paths.get(filePathStr);
                try {
                    Files.deleteIfExists(filePath);
                    System.out.println("Фото пользователя удалено: " + filePath.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            userRepository.delete(user);
        }
    }

}


