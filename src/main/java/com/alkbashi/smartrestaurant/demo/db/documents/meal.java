package com.alkbashi.smartrestaurant.demo.db.documents;

import com.alkbashi.smartrestaurant.demo.db.documents.interfaces.addOns;
import com.alkbashi.smartrestaurant.demo.db.documents.interfaces.elements;
import com.alkbashi.smartrestaurant.demo.global.enums.elementType;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Document
@Data
public class meal implements elements
{
    //todo set initial price's value to 0 and avaliablity to false and type to null
    public meal()
    {
        this.createdAt = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(LocalDateTime.now());
        this.price = 0f;
        this.available = false;
    }

    @Id
    private String Id;

    private String name;
    private double price;
    private elementType type;
    private StringBuffer details;
    private boolean available;
    private List<addOns> addOnsList;
    private final String createdAt;

}
