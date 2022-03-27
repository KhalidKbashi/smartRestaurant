package com.alkbashi.smartrestaurant.demo.controllers;

import com.alkbashi.smartrestaurant.demo.HATEOAS.assamblers.mealAssembler;
import com.alkbashi.smartrestaurant.demo.HATEOAS.models.mealModel;
import com.alkbashi.smartrestaurant.demo.db.documents.meal;
import com.alkbashi.smartrestaurant.demo.services.mealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;

@Controller
@RequestMapping(path = "/customer")
public class customerController
{
    //SERVICES
    private final mealService mealService;

    //ASSEMBLERS
    private final mealAssembler mealAssembler;

    @Autowired
    public customerController(mealService mealService, mealAssembler mealAssembler)
    {
        this.mealService = mealService;
        this.mealAssembler = mealAssembler;
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
}
