package com.alkbashi.smartrestaurant.demo.db.documents;

import com.alkbashi.smartrestaurant.demo.db.documents.interfaces.elements;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Document
@Data
public class order
{
    public order()
    {
        this.createdAt = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(LocalDateTime.now());
    }

    @Id
    private String Id;

    private String name;
    private StringBuffer details;
    private boolean isPaid;
    private boolean takeAway;
    private double totalPrice;
    private List<elements> elementsList;
    private final String createdAt;

}
