package com.store.app.utils;

import com.store.app.entity.User;

public interface UserOperations {
    public User createUserByType(User user, UserTypes type);
}
