package com.alkbashi.smartrestaurant.demo.db.repos;

import com.alkbashi.smartrestaurant.demo.db.documents.mealAddOn;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface mealAddOnsRepo extends MongoRepository<mealAddOn, String>
{
}
