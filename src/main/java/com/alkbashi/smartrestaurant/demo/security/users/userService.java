package com.alkbashi.smartrestaurant.demo.security.users;

import com.alkbashi.smartrestaurant.demo.security.PasswordEncodingClass;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class userService implements UserDetailsService
{
    private final userRepo userRepo;
    private final PasswordEncodingClass passwordEncodingClass;


    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        username = username.toLowerCase();
        Optional<user> user;

        if(username.contains("@"))
            user = this.userRepo.findByEmail(username);
        else
            user = this.userRepo.findByUsername(username);

        if(user.isEmpty())
            throw new IllegalStateException("USER NOT FOUND");

        return new User(user.get().getUsername(),user.get().getPassword(),
                user.get().isEnabled(),user.get().isAccountNonExpired(),user.get().isCredentialsNonExpired()
                ,user.get().isAccountNonLocked(),user.get().getAuthorities());
    }

    public void addUser(user user)
    {
        if(check(user))
            throw new IllegalStateException("user duplicate exception");

        user.setPassword(passwordEncodingClass.encoder().encode(user.getPassword()));

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

        this.userRepo.save(user);
    }
}
