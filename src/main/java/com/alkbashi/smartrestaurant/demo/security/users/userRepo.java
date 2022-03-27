package com.alkbashi.smartrestaurant.demo.security.users;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface userRepo extends CrudRepository<user,String>
{
    public Optional<user> findByUsername(String username);
}