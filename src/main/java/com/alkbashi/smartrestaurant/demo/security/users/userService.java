package com.alkbashi.smartrestaurant.demo.security.users;

import com.alkbashi.smartrestaurant.demo.security.PasswordEncodingClass;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.logging.LoggerGroup;
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
@Slf4j
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
        {
            log.warn("USER {} NOT FOUND ",user);
            throw new IllegalStateException("USER NOT FOUND");
        }

        log.info("FETCHED USER : {}", user.get().getUsername());

        return new User(user.get().getUsername(),user.get().getPassword(),
                user.get().isEnabled(),user.get().isAccountNonExpired(),user.get().isCredentialsNonExpired()
                ,user.get().isAccountNonLocked(),user.get().getAuthorities());
    }

    public void addUser(user user)
    {
        if(check(user))
        {
            log.warn("USER DUPLICATE FOUND");
            throw new IllegalStateException("user duplicate exception");
        }

        user.setPassword(passwordEncodingClass.encoder().encode(user.getPassword()));

        log.info("USER WITH USERNAME {} CREATED",user.getUsername());

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

        log.info("USER {} IS ENABLED",username);
        this.userRepo.save(user);
    }
}
