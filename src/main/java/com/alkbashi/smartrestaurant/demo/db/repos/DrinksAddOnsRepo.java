package com.alkbashi.smartrestaurant.demo.db.repos;

import com.alkbashi.smartrestaurant.demo.db.documents.drinkAddOns;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrinksAddOnsRepo extends MongoRepository<drinkAddOns, String>
{
}
