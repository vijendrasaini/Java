package com.library.repository;

import com.library.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {

    private final Map<String, User> userStore = new HashMap<>();

    public void save(User user) {
        userStore.put(user.getId(), user);
    }

    public User findById(String id) {
        return userStore.get(id);
    }
}