package com.alkbashi.smartrestaurant.demo.security.users;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class userService implements UserDetailsService
{
    private userRepo userRepo;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        return this.userRepo.findByUsername(username)
                .orElseThrow(IllegalStateException::new);
    }

    public void addUser(user user)
    {
        System.out.println(user.getUsername());
        if(this.userRepo.findByUsername(user.getUsername()).isPresent())
            throw new IllegalStateException("user duplicate exception");

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        this.userRepo.save(user);
    }
}
