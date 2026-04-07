package com.projectOne.journalApp.controller;


import com.projectOne.journalApp.api.response.WeatherResponse;
import com.projectOne.journalApp.entity.User;
import com.projectOne.journalApp.repository.UserRepository;
import com.projectOne.journalApp.service.UserService;
import com.projectOne.journalApp.service.WeatherService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextChangedEvent;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Security;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WeatherService weatherService;

//    @GetMapping
//    public List<User> getAllUsers(){
//        return userService.getAll();
//    }

    @PostMapping
    public boolean createUser(@RequestBody User user){
        userService.saveUser(user);
        return true;
    }

//    @GetMapping("/user/{id}")
//    public Optional<User> getUserById(@PathVariable ObjectId id){
//        return userService.findById(id);
//    }
    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        User userInDb = userService.findByUserName(userName);

        userInDb.setUserName(user.getUserName());
        userInDb.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.saveNewUser(userInDb);

        return new ResponseEntity<>( HttpStatus.NO_CONTENT );
    }

    @DeleteMapping

    public ResponseEntity<?> deleteByUserName(@RequestBody User user){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUserName(authentication.getName());
        return new ResponseEntity<>( HttpStatus.NO_CONTENT );
    }

    @GetMapping
    public ResponseEntity<?> greetings() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse weatherResponse = weatherService.getWeather("Ooty");
        String greeting = "";
        if(weatherResponse != null){
            greeting = " Weather feels like : " + weatherResponse.getCurrent().getFeelslike();
        }
        return new ResponseEntity<>("Hi " + authentication.getName() + greeting , HttpStatus.OK);
    }

}
