package com.alkbashi.smartrestaurant.demo.security.registrationModel;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequestMapping(path = "/register")
@AllArgsConstructor
public class registrationController
{
    private final registrationService registrationService;

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping
    public String registerRequest(@Valid @RequestBody registrationDTO registrationDTO)
    {
        if(Objects.isNull(registrationDTO))
            throw new IllegalStateException("NO DATA");

        return this.registrationService.registerUser(registrationDTO);
    }

    @ResponseStatus(code = HttpStatus.ACCEPTED)
    @GetMapping("/activate")
    public void validateTokenRequest(@RequestParam(name = "token") String token)
    {
        if(token.isBlank())
            throw new IllegalStateException("NO TOKEN PASSED");

        this.registrationService.validateToken(token);
    }
}
