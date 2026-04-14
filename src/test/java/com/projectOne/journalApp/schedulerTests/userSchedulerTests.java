package com.projectOne.journalApp.schedulerTests;

import com.projectOne.journalApp.scheduler.UserScheduler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class userSchedulerTests {

    @Autowired
    private UserScheduler userScheduler;

    @Test
    public void testFetchUsersandSendSaMails(){
        userScheduler.fetchUsersAndSendSaMails();
    }

}
