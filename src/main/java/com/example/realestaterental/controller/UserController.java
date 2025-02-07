package com.example.realestaterental.controller;

import com.example.realestaterental.entity.User;
import com.example.realestaterental.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Integer id){
        return userService.getUserById(id);
    }
    @PostMapping("/save")
    public void saveUser(@RequestBody User user){
        userService.saveUser(user);
    }
    @PatchMapping("/update/{id}")
    public User changeUserName(@RequestParam("newUserName") String newUserName,@PathVariable("id") Integer id){
        return userService.changeUserName(newUserName,id);
    }
    @GetMapping("/get/{userName}")
    public User getUserByUserName(@PathVariable("userName") String userName){
        return userService.findUserByUserName(userName);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable("id") Integer id){
         userService.deleteUserById(id);
    }
}
