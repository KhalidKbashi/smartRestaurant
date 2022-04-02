package com.alkbashi.smartrestaurant.demo.security.registrationModel.token;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TokensService
{
    private final TokensRepo tokensRepo;

    public String addToken(Tokens tokens)
    {
        return this.tokensRepo.save(tokens).getToken();
    }

    public Tokens getTokensByToken(String token)
    {
        return this.tokensRepo.findByToken(token).orElseThrow(() -> new IllegalStateException("Token Not Found"));
    }

    public void setCreatedAt(String token)
    {
        Tokens tokens = this.tokensRepo.findByToken(token).get();
        this.tokensRepo.delete(tokens);
        tokens.setConformedAt();
        this.addToken(tokens);

        /*
        MOVE METHOD NAME TO endToken
        this.tokensRepo.deleteByToken(token);
         */
    }
}
