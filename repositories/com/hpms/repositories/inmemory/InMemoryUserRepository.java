package com.hpms.repositories.inmemory;

import com.hpms.domain.User;
import com.hpms.repositories.UserRepository;

public class InMemoryUserRepository extends AbstractInMemoryRepository<User, java.util.UUID> implements UserRepository {
    public InMemoryUserRepository() {
        super(User::getUserId);
    }
}