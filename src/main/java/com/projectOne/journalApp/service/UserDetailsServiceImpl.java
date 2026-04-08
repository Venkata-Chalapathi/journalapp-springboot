package com.projectOne.journalApp.service;

import com.projectOne.journalApp.entity.User;
import com.projectOne.journalApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
//    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//
//
//        User user = userRepository.findByUserName(userName);
//
//        if(user != null){
//            return org.springframework.security.core.userdetails.User.builder()
//                    .username(user.getUserName())
//                    .password(user.getPassword())
//                    .roles(user.getRoles().toArray(new String[0]))
//                    .build();
//        }
//
//        throw new UsernameNotFoundException("User not found with username :" + userName);

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userName);

        System.out.println("Looking up user: " + userName);  // ← add this
        System.out.println("Found: " + (user != null ? user.getUserName() : "NULL")); // ← add this

        if(user != null){
            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUserName())
                    .password(user.getPassword())
                    .roles(user.getRoles().toArray(new String[0]))
                    .build();
        }
        throw new UsernameNotFoundException("User not found with username :" + userName);
    }
}
