package com.alkbashi.smartrestaurant.demo.HATEOAS.models;

import com.alkbashi.smartrestaurant.demo.db.documents.interfaces.addOns;
import com.alkbashi.smartrestaurant.demo.db.documents.meal;
import com.alkbashi.smartrestaurant.demo.global.enums.elementType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Getter
@AllArgsConstructor
public class mealModel extends RepresentationModel<mealModel>
{
    public mealModel(meal meal)
    {
        this.name = meal.getName();
        this.price = meal.getPrice();
        this.type = meal.getType();
        this.details = meal.getDetails();
        this.available = meal.isAvailable();
        this.addOnsList = meal.getAddOnsList();
        this.createdAt = meal.getCreatedAt();
    }

    private final String name;
    private final double price;
    private final elementType type;
    private final StringBuffer details;
    private final boolean available;
    private final List<addOns> addOnsList;
    private final String createdAt;
}
