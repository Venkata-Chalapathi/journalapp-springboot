package com.projectOne.journalApp.scheduler;

import com.projectOne.journalApp.cache.AppCache;
import com.projectOne.journalApp.entity.JournalEntry;
import com.projectOne.journalApp.entity.User;
import com.projectOne.journalApp.enums.Sentiment;
import com.projectOne.journalApp.repository.UserRepositoryImpl;
import com.projectOne.journalApp.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserScheduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepositoryImpl userRepositoryImpl;

    @Autowired
    private AppCache appCache;

    @Scheduled(cron = "0 0 9 * * SUN")
    public void fetchUsersAndSendSaMails(){
        List<User> users =  userRepositoryImpl.getUserForSA();
        for(User user : users){
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<Sentiment> sentiments = journalEntries.stream()
                    .filter(x -> x.getDate().isAfter(LocalDateTime.now().minusDays(7)))
                    .map(x -> x.getSentiment())
                    .toList();
            Map<Sentiment, Integer> sentimentCounts = new HashMap<>();
            for (Sentiment sentiment: sentiments){
                if(sentiment != null ){
                    sentimentCounts.put(sentiment, sentimentCounts.getOrDefault(sentiment, 0) + 1);
                }
            }

            Sentiment mostFreqSentiment = null;
            int max = 0;

            for(Map.Entry<Sentiment, Integer> entry : sentimentCounts.entrySet()){
                if(entry.getValue() > max){
                    max = entry.getValue();
                    mostFreqSentiment = entry.getKey();
                }
            }
            if(mostFreqSentiment != null){
                emailService.sendEmail(user.getEmail(),  "Sentiment for last 7 days", mostFreqSentiment.toString());
            }

        }
    }
    @Scheduled(cron = "0 0 9 * * SUN")
    public void clearAppCache(){
        appCache.init();
    }

}
