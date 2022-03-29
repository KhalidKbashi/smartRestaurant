package com.alkbashi.smartrestaurant.demo.services;

import com.alkbashi.smartrestaurant.demo.db.documents.drinks;
import com.alkbashi.smartrestaurant.demo.db.documents.meal;
import com.alkbashi.smartrestaurant.demo.db.documents.mealAddOn;
import com.alkbashi.smartrestaurant.demo.db.repos.drinksRepo;
import com.alkbashi.smartrestaurant.demo.db.repos.mealAddOnsRepo;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

@Service
@NoArgsConstructor
public class mealAddOnsService implements servicesTemplate<mealAddOn>
{
    private mealAddOnsRepo mealAddOnsRepo;

    @Autowired
    public mealAddOnsService(mealAddOnsRepo mealAddOnsRepo)
    {
        this.mealAddOnsRepo = mealAddOnsRepo;
    }

    @Override
    public mealAddOn get(String id)
    {
        return this.mealAddOnsRepo.findById(id).orElseThrow(() -> new IllegalStateException("Meal Add-Ons not found"));
    }

    @Override
    public ArrayList<mealAddOn> getAll()
    {
        return (ArrayList<mealAddOn>) this.mealAddOnsRepo.findAll();
    }

    @Override
    public String add(mealAddOn obj)
    {
        return this.mealAddOnsRepo.save(obj).getId();
    }

    @Override
    public void delete(String id)
    {
        this.mealAddOnsRepo.deleteById(id);
    }

    @Override
    public void patchEdit(String id, mealAddOn newMealAddOn)
    {
        mealAddOn original = this.mealAddOnsRepo.findById(id)
                .orElseThrow(() -> new IllegalStateException("Drink Add-Ons not found"));

        if(!newMealAddOn.getName().isBlank());
        original.setName(newMealAddOn.getName());
        if(newMealAddOn.getPrice()!=0)
            original.setPrice(newMealAddOn.getPrice());
        if(!(newMealAddOn.getDetails().capacity()==0))
            original.setDetails(newMealAddOn.getDetails());

        this.mealAddOnsRepo.deleteById(id);
        this.mealAddOnsRepo.save(original);
        //EXCEPTION NOT FOUND
    }

    @Override
    public boolean check(String id)
    {
        return this.mealAddOnsRepo.existsById(id);
    }
}
