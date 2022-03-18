package com.alkbashi.smartrestaurant.demo.HATEOAS.assamblers;

import com.alkbashi.smartrestaurant.demo.HATEOAS.models.mealModel;
import com.alkbashi.smartrestaurant.demo.controllers.mealsController;
import com.alkbashi.smartrestaurant.demo.db.documents.meal;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class mealAssembler extends RepresentationModelAssemblerSupport<meal, mealModel>
{
    public mealAssembler()
    {
        super(mealsController.class, mealModel.class);
    }

    @Override
    public CollectionModel<mealModel> toCollectionModel(Iterable<? extends meal> entities)
    {
        return super.toCollectionModel(entities).add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(mealsController.class).getAllMealsRequest()).withSelfRel());
    }

    @Override
    public mealModel toModel(meal entity)
    {
        return new mealModel(entity).add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(mealsController.class)
                        .getMealByIdRequest(entity.getId())).withSelfRel());
    }
}
