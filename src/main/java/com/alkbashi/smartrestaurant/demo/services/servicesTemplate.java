package com.alkbashi.smartrestaurant.demo.services;

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
