package com.alkbashi.smartrestaurant.demo.security.registrationModel;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

@Controller
@RequestMapping(path = "/register")
@AllArgsConstructor
public class registrationController
{
    private registrationService registrationService;

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping
    public void registerRequest(@Valid @RequestBody registrationDTO registrationDTO)
    {
        if(Objects.isNull(registrationDTO))
            throw new IllegalStateException("NO DATA");

        this.registrationService.registerUser(registrationDTO);
    }
}
