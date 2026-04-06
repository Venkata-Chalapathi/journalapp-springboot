package com.projectOne.journalApp.service;

import com.projectOne.journalApp.entity.User;
import com.projectOne.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserRepository userRepository;

    @Disabled
    @Test
    public void testFindByUserName(){
        User user = userRepository.findByUserName("gvc");
        assertTrue(!user.getJournalEntries().isEmpty());
    //        assertEquals(4, 2 + 2);
        assertNotNull(userRepository.findByUserName("imran"));
    }


    @ParameterizedTest
    @CsvSource({
            "4, 7, 11",
            "5, 3, 8",
            "4, 4, 8"
    })
    public void test(int a, int b, int expected){
        assertEquals(expected, a + b);
    }

}
