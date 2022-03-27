package com.alkbashi.smartrestaurant.demo.services;

import com.alkbashi.smartrestaurant.demo.db.documents.meal;
import com.alkbashi.smartrestaurant.demo.db.repos.mealsRepo;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

@Service
@NoArgsConstructor
public class mealService implements servicesTemplate<meal>
{
    private mealsRepo mealsRepo;

    @Autowired
    public mealService(mealsRepo mealsRepo)
    {
        this.mealsRepo = mealsRepo;
    }

    @Override
    public meal get(String id)
    {
        return this.mealsRepo.findById(id).orElse(null);
    }

    @Override
    public ArrayList<meal> getAll()
    {
        return (ArrayList<meal>) this.mealsRepo.findAll();
    }

    @Override
    public String add(meal obj)
    {
        return this.mealsRepo.save(obj).getId();
    }

    @Override
    public void delete(String id)
    {
        this.mealsRepo.deleteById(id);
    }

    @Override
    public void patchEdit(String id, meal newMeal)
    {
        Optional<meal> original = this.mealsRepo.findById(id);
        if(original.isPresent())
        {
            if(!newMeal.getName().isBlank());
                original.get().setName(newMeal.getName());
            if(newMeal.getPrice()!=0)
                original.get().setPrice(newMeal.getPrice());
            if(!Objects.isNull(newMeal.getType()))
                original.get().setType(newMeal.getType());
            if(!(newMeal.getDetails().capacity()==0))
                original.get().setDetails(newMeal.getDetails());
            if(newMeal.isAvailable())
                original.get().setAvailable(newMeal.isAvailable());
            if(newMeal.getAddOnsList().isEmpty())
                original.get().setAddOnsList(newMeal.getAddOnsList());

            this.mealsRepo.deleteById(id);
            this.mealsRepo.save(original.get());
        }
        //EXCEPTION NOT FOUND
    }

    @Override
    public boolean check(String id)
    {
        return this.mealsRepo.existsById(id);
    }
}
