package com.projectOne.journalApp.service;

import com.projectOne.journalApp.entity.JournalEntry;
import com.projectOne.journalApp.entity.User;
import com.projectOne.journalApp.repository.JournalEntryRepository;
import com.projectOne.journalApp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    public boolean saveNewUser(User user) {
        try {

            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            log.info("Info logging level");
            log.error("Error Occurred");
            log.warn("Warning");
            log.debug("Customized Debug");
            return false;
        }
    }
    public void saveUser(User user) {
        userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(ObjectId id) {
        return userRepository.findById(id);
    }

    public void deleteById(ObjectId id) {
        userRepository.deleteById(id);
    }

    public User findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }
}