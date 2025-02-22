package com.example.realestaterental.controller;

import com.example.realestaterental.entity.User;
import com.example.realestaterental.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserService userService;

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
//    @GetMapping("/{id}")
//    public User getUserById(@PathVariable("id") Integer id){
//        return userService.getUserById(id);
//    }
//    @PostMapping("/save")
//    public void saveUser(@RequestBody User user){
//        userService.saveUser(user);
//    }
//    @PatchMapping("/update/{id}")
//    public User changeUserName(@RequestParam("newUserName") String newUserName,@PathVariable("id") Integer id){
//        return userService.changeUserName(newUserName,id);
//    }
//    @GetMapping("/get/{userName}")
//    public User getUserByUserName(@PathVariable("userName") String userName){
//        return userService.findUserByUserName(userName);
//    }
//    @DeleteMapping("/delete/user/{id}")
//    public void deleteUser(@PathVariable("id") Integer id){
//         userService.deleteUserById(id);
//    }

//    @GetMapping("/all")
//    public List<User> getAll(){
//        return userService.getAll();
//    }
//
//    @DeleteMapping("/delete/{username}")
//    public void deleteUserByUsername(@PathVariable("username") String username){
//        userService.deleteUserByName(username);
//    }
//
//    @PostMapping("/save")
//    public ResponseEntity<User> saveUser(
//            @RequestParam("username") String username,
//            @RequestParam("password") String password,
//            @RequestParam("photo") MultipartFile photo) {
//
//        User user = new User();
//        user.setUsername(username);
//        user.setPassword(password);
//
//        if (!photo.isEmpty()) {
//            String fileName = System.currentTimeMillis() + "_" + photo.getOriginalFilename();
//            Path filePath = Paths.get("photos", fileName);
//            try {
//                Files.createDirectories(filePath.getParent());
//                Files.write(filePath, photo.getBytes());
//                user.setPhotoPath("/photos/" + fileName);
//            } catch (IOException e) {
//                e.printStackTrace();
//                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//            }
//        }
//
//        userService.saveUser(user);
//        return ResponseEntity.ok(user);
//    }
}

