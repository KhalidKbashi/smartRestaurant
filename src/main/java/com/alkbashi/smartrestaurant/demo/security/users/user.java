package com.alkbashi.smartrestaurant.demo.security.users;

import com.alkbashi.smartrestaurant.demo.security.registrationModel.registrationDTO;
import com.alkbashi.smartrestaurant.demo.security.roles.usersRole;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Document
@Data
@NoArgsConstructor
public class user implements UserDetails
{
    public user(String username, String email, String password
            , usersRole usersRoles)
    {
        this.username = username;
        this.email = email;
        this.password = password;
        this.usersRoles = usersRoles;
    }

    public user(registrationDTO registrationDTO)
    {
        this.username = registrationDTO.getFirstName()+" "+registrationDTO.getLastName();
        this.email = registrationDTO.getEmail();
        this.password = registrationDTO.getPassword();
        //this.usersRoles = registrationDTO.??;
    }


    @Id
    private String id;

    private String username;
    private String email;
    private String password;
    private usersRole usersRoles;
    private boolean locked = false;
    private boolean enabled = false;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return Collections.singletonList(new SimpleGrantedAuthority(this.usersRoles.name()));
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