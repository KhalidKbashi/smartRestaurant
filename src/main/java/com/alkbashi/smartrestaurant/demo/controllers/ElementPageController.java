package com.alkbashi.smartrestaurant.demo.controllers;

import com.alkbashi.smartrestaurant.demo.HATEOAS.assamblers.mealAssembler;
import com.alkbashi.smartrestaurant.demo.HATEOAS.models.mealModel;
import com.alkbashi.smartrestaurant.demo.db.documents.meal;
import com.alkbashi.smartrestaurant.demo.services.mealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Objects;

@RestController
@RequestMapping(path = "")
public class ElementPageController
{
    private mealService mealService;
    private mealAssembler mealAssembler;

    @Autowired
    public ElementPageController(mealService mealService, mealAssembler mealAssembler)
    {
        this.mealService = mealService;
        this.mealAssembler = mealAssembler;
    }

    //GET
    @GetMapping(path = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public mealModel getMealByIdRequest(@PathVariable(name = "id") String id)
    {
        meal meal = this.mealService.get(id);

        if(Objects.isNull(meal))
            System.out.println("an exception in meals controller class");

        return this.mealAssembler.toModel(meal);
    }

    //GET-ALL
    @GetMapping(path = "/All")
    @ResponseStatus(code = HttpStatus.OK)
    public CollectionModel<mealModel> getAllMealsRequest()
    {
        ArrayList<meal> meals = this.mealService.getAll();

        if(meals.isEmpty())
            System.out.println("an exception in meals controller class");

        return this.mealAssembler.toCollectionModel(meals);
    }

    // POST
    @GetMapping("/")
    @ResponseStatus(code = HttpStatus.CREATED)
    public String postMealRequest(meal meal)
    {
        return this.mealService.add(meal);
    }

    //DELETE
    @DeleteMapping("/")
    @ResponseStatus(code = HttpStatus.GONE)
    public void deleteMealByIdRequest(String id)
    {
        this.mealService.delete(id);
    }

    //PATCH
    @PatchMapping("/{id}")
    @ResponseStatus(code = HttpStatus.GONE)
    public void patchMealRequest(@PathVariable(name = "id") String id,meal meal)
    {
        this.mealService.patchEdit(id,meal);
    }

}
