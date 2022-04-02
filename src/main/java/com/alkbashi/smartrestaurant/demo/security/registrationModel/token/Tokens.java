package com.alkbashi.smartrestaurant.demo.security.registrationModel.token;

import com.alkbashi.smartrestaurant.demo.security.users.user;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Document
public class Tokens
{
    public Tokens(user user)
    {
        this.user = user;
        this.setCreatedAt();
        this.setExpiresAt();
        this.setToken();
    }

    @Id
    private String id;

    private String token;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private LocalDateTime conformedAt;

    private user user;

    public LocalDateTime setConformedAt()
    {
        return conformedAt = LocalDateTime.now();
    }

    public void setCreatedAt()
    {
        this.createdAt = LocalDateTime.now();
    }

    public void setExpiresAt()
    {
        this.expiresAt = LocalDateTime.now().plusMinutes(15);
    }

    public void setToken()
    {
        this.token = UUID.randomUUID().toString();
    }
}
