package com.alkbashi.smartrestaurant.demo.security.users;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface userRepo extends CrudRepository<user,String>
{
    Optional<user> findByUsername(String username);
    Boolean existsByUsername(String username);
    Optional<user> findByEmail(String email);

    /*@Query("UPDATE user a SET a.enabled = TRUE WHERE a.username = ?1")
    int enableUser(String username);*/
}