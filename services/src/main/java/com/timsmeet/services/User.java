package com.timsmeet.services;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.google.common.collect.Sets;

public class User implements UserDetails {

    /**
     * 
     */
    private static final long serialVersionUID = 3569353436923237864L;
    
    private String password;
    private final String username;
    private final Set<GrantedAuthority> authorities;
    private final boolean accountNonExpired;
    private final boolean accountNonLocked;
    private final boolean credentialsNonExpired;
    private final boolean enabled;
    private final String locationName;
    private final Long locationId;
    private final String organizationName;
    private final Long organizationId;

    
    public User(String username, String password, String locationName, Long locationId, String organizationName, Long organizationId, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = Collections.unmodifiableSet(Sets.newHashSet(authorities));
        
        this.locationId = locationId;
        this.locationName = locationName;
        this.organizationId = organizationId;
        this.organizationName = organizationName;
        
        this.accountNonExpired
            = this.accountNonLocked
            = this.credentialsNonExpired 
            = this.enabled
            = true;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public String getLocationName() {
        return locationName;
    }

    public Long getLocationId() {
        return locationId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

}
