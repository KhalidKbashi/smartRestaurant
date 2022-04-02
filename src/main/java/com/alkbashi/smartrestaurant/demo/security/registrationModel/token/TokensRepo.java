package com.alkbashi.smartrestaurant.demo.security.registrationModel.token;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokensRepo extends MongoRepository<Tokens,String>
{
    Optional<Tokens> findByToken(String token);
    Boolean deleteByToken(String token);

    /*@Query("UPDATE Tokens c SET c.confirmedAt = ?2 WHERE c.token = ?1")
    void setConformedAt(String token, LocalDateTime confirmedAt);*/
}
