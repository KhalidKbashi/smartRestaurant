package com.alkbashi.smartrestaurant.demo.security.registrationModel;

import com.alkbashi.smartrestaurant.demo.security.registrationModel.token.Tokens;
import com.alkbashi.smartrestaurant.demo.security.registrationModel.token.TokensService;
import com.alkbashi.smartrestaurant.demo.security.users.user;
import com.alkbashi.smartrestaurant.demo.security.users.userService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class registrationService
{
    private final userService userService;
    private final TokensService tokensService;

    public String registerUser(registrationDTO registrationDTO)
    {
        user user = new user(registrationDTO);


        this.userService.addUser(user);

        return this.tokensService.addToken(new Tokens(user));
    }

    public void validateToken(String tokenString)
    {
        Tokens tokens = this.tokensService.getTokensByToken(tokenString);

        if(tokens.getExpiresAt().isBefore(LocalDateTime.now()))
            throw new IllegalStateException("TOKEN EXPIRED");

        if(tokens.getConformedAt()!=null)
            throw new IllegalStateException("TOKEN ALREADY USED");

        this.userService.enableUser(tokens.getUser().getUsername());
        this.tokensService.setCreatedAt(tokens.getToken());

    }
}