package com.alkbashi.smartrestaurant.demo.db.repos;

import com.alkbashi.smartrestaurant.demo.db.documents.drinks;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface drinksRepo extends MongoRepository<drinks, String>
{
}
