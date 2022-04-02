package com.alkbashi.smartrestaurant.demo.security.users;

import com.alkbashi.smartrestaurant.demo.security.registrationModel.registrationDTO;
import com.alkbashi.smartrestaurant.demo.security.roles.userRoles;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.stream.Collectors;

@Document
@Data
@NoArgsConstructor
public class user implements UserDetails
{
    public user(String username, String email, String password
            , Collection<userRoles> userRoles)
    {
        this.username = username;
        this.email = email;
        this.password = password;
        this.userRoles = userRoles;
    }

    public user(registrationDTO registrationDTO)
    {
        this.username = (registrationDTO.getFirstName()+" "+registrationDTO.getLastName()).toLowerCase();
        this.email = registrationDTO.getEmail().toLowerCase();
        this.password = registrationDTO.getPassword();
        //this.usersRoles = registrationDTO.??;
    }


    @Id
    private String id;

    private String username;
    private String email;
    private String password;
    private Collection<userRoles> userRoles;
    private LocalDateTime enabledAt;
    private boolean locked = false;
    private boolean enabled = false;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return this.userRoles.stream().distinct().map(s -> new SimpleGrantedAuthority(s.name()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword()
    {
        return this.password;
    }

    @Override
    public String getUsername()
    {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return !this.locked;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return this.enabled;
    }
}