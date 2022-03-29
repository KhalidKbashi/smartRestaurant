package com.alkbashi.smartrestaurant.demo.controllers;

import com.alkbashi.smartrestaurant.demo.db.documents.drinkAddOns;
import com.alkbashi.smartrestaurant.demo.db.documents.drinks;
import com.alkbashi.smartrestaurant.demo.db.documents.meal;
import com.alkbashi.smartrestaurant.demo.db.documents.mealAddOn;
import com.alkbashi.smartrestaurant.demo.services.DrinkAddOnsService;
import com.alkbashi.smartrestaurant.demo.services.drinksService;
import com.alkbashi.smartrestaurant.demo.services.mealAddOnsService;
import com.alkbashi.smartrestaurant.demo.services.mealService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping(path = "/store/elements/")
@AllArgsConstructor
public class ElementsEndpointController
{
    private final mealService mealService;
    private final drinksService drinksService;
    private final DrinkAddOnsService dinkAddOnsService;
    private final mealAddOnsService mealAddOnsService;



    //GET MEAL
    @GetMapping(path = "/meal")
    @ResponseStatus(code = HttpStatus.OK)
    public meal getMealByIdRequest(@RequestParam(name = "id") String id)
    {
        if(id.isBlank())
            throw new IllegalStateException("NO ID SENT");

        meal meal = this.mealService.get(id);

        if(Objects.isNull(meal))
            System.out.println("an exception in meals controller class");

        return meal;
        //return this.mealAssembler.toModel(meal);
    }

    //GET DRINK
    @GetMapping(path = "/drinks")
    @ResponseStatus(code = HttpStatus.OK)
    public drinks getDrinkByIdRequest(@RequestParam(name = "id") String id)
    {
        if(id.isBlank())
            throw new IllegalStateException("NO ID SENT");

        drinks drinks = this.drinksService.get(id);

        if(Objects.isNull(drinks))
            System.out.println("an exception in meals controller class");

        return drinks;
        //return this.mealAssembler.toModel(drinks);
    }

    //GET DRINK ADD-ON
    @GetMapping(path = "drinkAddOns")
    @ResponseStatus(code = HttpStatus.OK)
    public drinkAddOns getDrinkAddOnsByIdRequest(@RequestParam(name = "id") String id)
    {
        if(id.isBlank())
            throw new IllegalStateException("NO ID SENT");

        drinkAddOns drinkAddOns = this.dinkAddOnsService.get(id);

        if(Objects.isNull(drinkAddOns))
            System.out.println("an exception in meals controller class");

        return drinkAddOns;
        //return this.mealAssembler.toModel(drinksAddOn);
    }

    //GET MEAL ADD-ON
    @GetMapping(path = "mealAddOns")
    @ResponseStatus(code = HttpStatus.OK)
    public mealAddOn getMealAddOnsByIdRequest(@RequestParam(name = "id") String id)
    {
        if(id.isBlank())
            throw new IllegalStateException("NO ID SENT");

        mealAddOn mealAddOn = this.mealAddOnsService.get(id);

        if(Objects.isNull(mealAddOn))
            System.out.println("an exception in meals controller class");

        return mealAddOn;
        //return this.mealAssembler.toModel(mealAddOn);
    }

}
