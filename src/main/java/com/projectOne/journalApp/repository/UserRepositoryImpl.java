package com.projectOne.journalApp.repository;


import com.projectOne.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public class UserRepositoryImpl{

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<User> getUserForSA() {
        Query query = new Query();
        Criteria criteria = new Criteria();

        query.addCriteria(Criteria.where("email").regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"));
//        query.addCriteria(Criteria.where("email").exists(true));
//        query.addCriteria(Criteria.where("email").ne(null).ne(""));
        query.addCriteria(Criteria.where("sentimentAnalysis").is(true));

//        query.addCriteria(criteria.andOperator(
//                Criteria.where("email").exists(true),
//                Criteria.where("sentimentAnalysis").is(true)
//        ));

        List<User> users = mongoTemplate.find(query, User.class);
        return users;
    }


}