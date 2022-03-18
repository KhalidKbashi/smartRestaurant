package com.alkbashi.smartrestaurant.demo.db.documents;

import com.alkbashi.smartrestaurant.demo.db.documents.interfaces.addOns;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class mealAddOn implements addOns
{
    @Id
    private String Id;

    private String name;
    private double price;
    private StringBuffer details;

}
