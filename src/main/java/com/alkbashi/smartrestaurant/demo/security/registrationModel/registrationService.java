package com.alkbashi.smartrestaurant.demo.security.registrationModel;

import com.alkbashi.smartrestaurant.demo.security.users.user;
import com.alkbashi.smartrestaurant.demo.security.users.userService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class registrationService
{
    private userService userService;

    public void registerUser(registrationDTO registrationDTO)
    {
        this.userService.addUser(new user(registrationDTO));
    }
}
