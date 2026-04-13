package com.projectOne.journalApp.cache;

import com.projectOne.journalApp.entity.ConfigAppJournalEntity;
import com.projectOne.journalApp.repository.configJournalAppRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    public enum keys {
        WEATHER_API ;
    }

    @Autowired
    private configJournalAppRepository configJournalAppRepository;

    public Map<String, String> appCache;

    @PostConstruct
    public void init() {
        appCache = new HashMap<>();
        List<ConfigAppJournalEntity> all = configJournalAppRepository.findAll();

        for (ConfigAppJournalEntity configAppJournalEntity : all){
            appCache.put(configAppJournalEntity.getKey(), configAppJournalEntity.getValue());
        }

    }

}
