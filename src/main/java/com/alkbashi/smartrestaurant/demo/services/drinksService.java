package com.alkbashi.smartrestaurant.demo.services;

import com.alkbashi.smartrestaurant.demo.db.documents.drinks;
import com.alkbashi.smartrestaurant.demo.db.repos.drinksRepo;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

@Service
@NoArgsConstructor
public class drinksService implements servicesTemplate<drinks>
{
    private drinksRepo drinksRepo;

    @Autowired
    public drinksService(drinksRepo drinksRepo)
    {
        this.drinksRepo = drinksRepo;
    }

    @Override
    public drinks get(String id)
    {
        return this.drinksRepo.findById(id).orElseThrow(() -> new IllegalStateException("Drink not found"));
    }

    @Override
    public ArrayList<drinks> getAll()
    {
        return (ArrayList<drinks>) this.drinksRepo.findAll();
    }

    @Override
    public String add(drinks obj)
    {
        return this.drinksRepo.save(obj).getId();
    }

    @Override
    public void delete(String id)
    {
        this.drinksRepo.deleteById(id);
    }

    @Override
    public void patchEdit(String id, drinks newDrink)
    {
        Optional<drinks> original = this.drinksRepo.findById(id);
        if(original.isPresent())
        {
            if(!newDrink.getName().isBlank());
                original.get().setName(newDrink.getName());
            if(newDrink.getPrice()!=0)
                original.get().setPrice(newDrink.getPrice());
            if(!Objects.isNull(newDrink.getType()))
                original.get().setType(newDrink.getType());
            if(!(newDrink.getDetails().capacity()==0))
                original.get().setDetails(newDrink.getDetails());
            if(newDrink.isAvailable())
                original.get().setAvailable(newDrink.isAvailable());
            if(newDrink.getAddOnsList().isEmpty())
                original.get().setAddOnsList(newDrink.getAddOnsList());

            this.drinksRepo.deleteById(id);
            this.drinksRepo.save(original.get());
        }
        //EXCEPTION NOT FOUND
    }

    @Override
    public boolean check(String id)
    {
        return this.drinksRepo.existsById(id);
    }
}
