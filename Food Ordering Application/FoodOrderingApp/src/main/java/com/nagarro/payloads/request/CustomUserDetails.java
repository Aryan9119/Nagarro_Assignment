package com.nagarro.payloads.request;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.nagarro.entities.Customer;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import com.nagarro.entities.*;

public class CustomUserDetails implements UserDetails {

    private final Customer customer;
    
    private Set<Role> roles;
    
    public CustomUserDetails(Customer customer, Set<Role> roles) {
        this.customer = customer;
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Convert roles to authorities
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_"+role.getRoleName()))
                .collect(Collectors.toList());
    }
    
    public Long getCustomerId() {
        return customer.getCustomerId();
    }
    
    public Set<Role> getRoles() {
    	return roles;
    }
    
    public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
    public String getPassword() {
        return customer.getPassword();
    }

    @Override
    public String getUsername() {
        return customer.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Adjust as needed
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Adjust as needed
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Adjust as needed
    }

    @Override
    public boolean isEnabled() {
        return true; // Adjust as needed
    }
}
