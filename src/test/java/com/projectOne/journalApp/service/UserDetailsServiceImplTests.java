package com.projectOne.journalApp.service;

import com.projectOne.journalApp.entity.User;
import com.projectOne.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;

import static org.mockito.Mockito.when;

@ActiveProfiles("dev")
//@SpringBootTest
public class UserDetailsServiceImplTests {

//    @Autowired
    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

//    @MockBean
    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void loadUserByUsername(){

        // ✅ Create your actual entity
        User mockUser = new User();
        mockUser.setUserName("gvc");
        mockUser.setPassword("fsdfsdf");
        mockUser.setRoles(Arrays.asList("USER"));

        // ✅ Mock repository
        when(userRepository.findByUserName(ArgumentMatchers.anyString()))
                .thenReturn(mockUser);

        // ✅ Call method
        UserDetails user = userDetailsService.loadUserByUsername("gvc");

        // ✅ Correct assertions
        Assertions.assertNotNull(user);
        Assertions.assertEquals("gvc", user.getUsername());
    }
}