package com.ags.websocket.service;

import com.ags.websocket.repository.UserRepository;
import com.ags.websocket.user.Status;
import com.ags.websocket.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public void saveUser(User user) {

        user.setStatus(Status.ONLINE);
        repository.save(user);

    }

    public void disconnect(User user) {

        var storedUser = repository.findById(user.getNickName()).orElse(null);

        if (storedUser != null) {
            storedUser.setStatus(Status.OFFLINE);
            repository.save(user);
        }


    }

    public List<User> findConnectedUsers() {

        return repository.findAllByStatus(Status.ONLINE);
    }

}
