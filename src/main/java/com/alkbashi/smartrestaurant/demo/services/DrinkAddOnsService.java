package com.alkbashi.smartrestaurant.demo.services;

import com.alkbashi.smartrestaurant.demo.db.documents.drinkAddOns;
import com.alkbashi.smartrestaurant.demo.db.repos.DrinksAddOnsRepo;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@NoArgsConstructor
public class DrinkAddOnsService implements servicesTemplate<drinkAddOns>
{
    private DrinksAddOnsRepo drinksAddOnsRepo;

    @Autowired
    public DrinkAddOnsService(DrinksAddOnsRepo drinksAddOnsRepo)
    {
        this.drinksAddOnsRepo = drinksAddOnsRepo;
    }

    @Override
    public drinkAddOns get(String id)
    {
        return this.drinksAddOnsRepo.findById(id).orElseThrow(() -> new IllegalStateException("Drink Add-Ons not found"));
    }

    @Override
    public ArrayList<drinkAddOns> getAll()
    {
        return (ArrayList<drinkAddOns>) this.drinksAddOnsRepo.findAll();
    }

    @Override
    public String add(drinkAddOns obj)
    {
        return this.drinksAddOnsRepo.save(obj).getId();
    }

    @Override
    public void delete(String id)
    {
        this.drinksAddOnsRepo.deleteById(id);
    }

    @Override
    public void patchEdit(String id, drinkAddOns newDrinkAddOn)
    {
        drinkAddOns original = this.drinksAddOnsRepo.findById(id)
                .orElseThrow(() -> new IllegalStateException("Drink Add-Ons not found"));

        if(!newDrinkAddOn.getName().isBlank());
        original.setName(newDrinkAddOn.getName());
        if(newDrinkAddOn.getPrice()!=0)
            original.setPrice(newDrinkAddOn.getPrice());
        if(!(newDrinkAddOn.getDetails().capacity()==0))
            original.setDetails(newDrinkAddOn.getDetails());

        this.drinksAddOnsRepo.deleteById(id);
        this.drinksAddOnsRepo.save(original);
        //EXCEPTION NOT FOUND
    }

    @Override
    public boolean check(String id)
    {
        return this.drinksAddOnsRepo.existsById(id);
    }
}
