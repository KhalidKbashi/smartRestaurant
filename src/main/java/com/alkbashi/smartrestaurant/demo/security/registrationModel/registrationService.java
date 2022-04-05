package com.alkbashi.smartrestaurant.demo.security.registrationModel;

import com.alkbashi.smartrestaurant.demo.security.registrationModel.token.Tokens;
import com.alkbashi.smartrestaurant.demo.security.registrationModel.token.TokensService;
import com.alkbashi.smartrestaurant.demo.security.roles.userRoles;
import com.alkbashi.smartrestaurant.demo.security.users.user;
import com.alkbashi.smartrestaurant.demo.security.users.userService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class registrationService
{
    private final userService userService;
    private final TokensService tokensService;

    public String registerUser(registrationDTO registrationDTO)
    {
        user user = new user(registrationDTO);

        user.setUserRoles(List.of(userRoles.USER));

        this.userService.addUser(user);

        log.info("REGISTERING USER {}",user.getUsername());
        return this.tokensService.addToken(new Tokens(user));
    }

    public void validateToken(String tokenString)
    {
        Tokens tokens = this.tokensService.getTokensByToken(tokenString);

        if(tokens.getExpiresAt().isBefore(LocalDateTime.now()))
        {
            log.warn("TOKEN {} IS EXPIRED",tokenString);
            throw new IllegalStateException("TOKEN EXPIRED");
        }

        if(tokens.getConformedAt()!=null)
        {
            log.warn("TOKEN {} IS ALREADY USED",tokenString);
            throw new IllegalStateException("TOKEN ALREADY USED");
        }

        log.info("TOKEN : {} IS VALID",tokenString);
        this.userService.enableUser(tokens.getUser().getUsername());
        this.tokensService.setCreatedAt(tokens.getToken());

    }
}