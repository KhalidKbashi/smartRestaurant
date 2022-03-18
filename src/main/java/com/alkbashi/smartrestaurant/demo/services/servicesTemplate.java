package com.alkbashi.smartrestaurant.demo.services;

import java.util.Collection;

public interface servicesTemplate<T>
{
    public T get(String id);
    public Collection<T> getAll();
    public void add(T obj);
    public void delete(String id);
    public void patchEdit(String id,T obj);
    public boolean check(String id);
}
