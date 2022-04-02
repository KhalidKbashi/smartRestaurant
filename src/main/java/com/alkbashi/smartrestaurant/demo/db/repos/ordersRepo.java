package com.alkbashi.smartrestaurant.demo.db.repos;

import com.alkbashi.smartrestaurant.demo.db.documents.order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ordersRepo extends MongoRepository<order, String>
{
}
