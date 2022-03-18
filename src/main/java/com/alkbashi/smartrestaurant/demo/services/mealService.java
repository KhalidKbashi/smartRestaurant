package com.alkbashi.smartrestaurant.demo.services;

import com.alkbashi.smartrestaurant.demo.db.documents.meal;
import com.alkbashi.smartrestaurant.demo.db.repos.mealsRepo;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
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
    public void add(meal obj)
    {
        this.mealsRepo.save(obj);
    }

    @Override
    public void delete(String id)
    {
        this.mealsRepo.deleteById(id);
    }

    @Override
    public void patchEdit(String id, meal newOne)
    {
        Optional<meal> original = this.mealsRepo.findById(id);
        if(original.isPresent())
        {
            if(!newOne.getName().isBlank());
                original.get().setName(newOne.getName());
            if(newOne.getPrice()!=0)
                original.get().setPrice(newOne.getPrice());
            if(!Objects.isNull(newOne.getType()))
                original.get().setType(newOne.getType());
            if(!(newOne.getDetails().capacity()==0))
                original.get().setDetails(newOne.getDetails());
            if(newOne.isAvailable())
                original.get().setAvailable(newOne.isAvailable());
            if(newOne.getAddOnsList().isEmpty())
                original.get().setAddOnsList(newOne.getAddOnsList());

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
