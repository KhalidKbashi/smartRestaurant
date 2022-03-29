package com.alkbashi.smartrestaurant.demo.services;

import com.alkbashi.smartrestaurant.demo.db.documents.interfaces.addOns;
import com.alkbashi.smartrestaurant.demo.db.documents.interfaces.element;
import com.alkbashi.smartrestaurant.demo.db.documents.interfaces.elements;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;

public interface servicesTemplate<T>
{
    T get(String id);
    Collection<T> getAll();
    String add(T obj);
    void delete(String id);
    void patchEdit(String id, T obj);
    boolean check(String id);
}
