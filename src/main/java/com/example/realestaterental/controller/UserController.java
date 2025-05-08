package com.example.realestaterental.controller;

import com.example.realestaterental.dto.PropertyDTO;
import com.example.realestaterental.dto.UserDTO;
import com.example.realestaterental.entity.Property;
import com.example.realestaterental.entity.User;
import com.example.realestaterental.mapper.PropertyMapper;
import com.example.realestaterental.mapper.UserMapper;
import com.example.realestaterental.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
@CrossOrigin(origins = {"http://localhost:3000", "https://realestaterental-8ce5c.firebaseapp.com/"})
public class UserController {
    private final UserMapper userMapper;
    private final UserService userService;
    private final PropertyMapper propertyMapper;
//    @GetMapping("/all")
//    public List<User> getAllUsers() {
//        return userService.getAllUsers();
//    }
    @GetMapping("/all")
    public List<UserDTO> getAllUsers() {
        return userMapper.toDtoList(userService.getAllUsers());
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Integer id) {
        User user = userService.getUserById(id);
        UserDTO dto = userMapper.toDto(user);
        return ResponseEntity.ok(dto);
    }
    @GetMapping("/{userId}/properties")
    public List<Property> getUserProperties(@PathVariable Integer userId) {
        return userService.getUserProperties(userId);
    }
//
//    @GetMapping("/properties")
//    public List<Property> getUserProperties(@AuthenticationPrincipal User userPrincipal) {
//        User user = userService.getUserByUsername(userPrincipal.getUsername());
//        return userService.getUserProperties(user.getId());
//    }

    @GetMapping("/properties")
    public List<PropertyDTO> getUserProperties(@AuthenticationPrincipal User userPrincipal) {
        User user = userService.getUserByUsername(userPrincipal.getUsername());
        return propertyMapper.toDtoList(userService.getUserProperties(user.getId()));
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

