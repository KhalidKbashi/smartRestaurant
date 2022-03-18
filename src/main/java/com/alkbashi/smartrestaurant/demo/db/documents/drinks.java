package com.alkbashi.smartrestaurant.demo.db.documents;

import com.alkbashi.smartrestaurant.demo.db.documents.interfaces.addOns;
import com.alkbashi.smartrestaurant.demo.db.documents.interfaces.elements;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Document
@Data
public class drinks implements elements
{
    public drinks()
    {
        this.createdAt = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(LocalDateTime.now());
    }

    @Id
    private String Id;

    private String name;
    private double price;
    private elements type;
    private StringBuffer details;
    private boolean available;
    private List<addOns> addOnsList;
    private final String createdAt;

}
