package com.CRM.crm.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositoryI extends JpaRepository<User ,Long> {
    User findByActivationToken(String token);
}
