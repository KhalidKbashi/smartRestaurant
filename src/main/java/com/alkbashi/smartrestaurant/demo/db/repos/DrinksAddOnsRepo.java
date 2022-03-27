package com.alkbashi.smartrestaurant.demo.db.repos;

import com.alkbashi.smartrestaurant.demo.db.documents.drinksAddOn;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrinksAddOnsRepo extends MongoRepository<drinksAddOn, String>
{
}
