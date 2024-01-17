package com.ags.websocket.repository;

import com.ags.websocket.user.Status;
import com.ags.websocket.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {

    List<User> findAllByStatus(Status online);
}
