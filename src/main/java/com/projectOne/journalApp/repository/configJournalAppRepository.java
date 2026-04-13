package com.projectOne.journalApp.repository;


import com.projectOne.journalApp.entity.ConfigAppJournalEntity;
import com.projectOne.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface configJournalAppRepository extends MongoRepository<ConfigAppJournalEntity, ObjectId> {



}