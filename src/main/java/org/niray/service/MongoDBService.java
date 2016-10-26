package org.niray.service;

import org.niray.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

/**
 * Created by Mac on 16/10/25.
 */
@Service
public class MongoDBService {

    private static String USER_COLLECTION = "user";

    @Autowired
    MongoTemplate mongoTemplate;

    public void addUser(User user) {
        mongoTemplate.save(user, USER_COLLECTION);
    }

    public User findUserByName(String name) {
        return mongoTemplate.findOne(new Query(Criteria.where("name").is(name)), User.class, USER_COLLECTION);
    }

}
