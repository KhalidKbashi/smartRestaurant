package com.alkbashi.smartrestaurant.demo.security.users;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class userService implements UserDetailsService
{
    private final userRepo userRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        return this.userRepo.findByUsername(username)
                .orElseThrow(IllegalStateException::new);
    }

    public void addUser(user user)
    {
        if(check(user))
            throw new IllegalStateException("user duplicate exception");

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        this.userRepo.save(user);
    }

    public boolean check(user user)
    {
        return this.userRepo.existsByUsername(user.getUsername());
    }

    public void enableUser(String username)
    {
        user user = this.userRepo.findByUsername(username).get();

        this.userRepo.delete(user);

        user.setEnabled(true);
        user.setEnabledAt(LocalDateTime.now());
        
        this.addUser(user);
    }
}
