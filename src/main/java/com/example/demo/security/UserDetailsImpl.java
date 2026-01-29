package com.example.demo.security;

import com.example.demo.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(user.getRole())); // USER / ADMIN
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // luôn true để tài khoản không hết hạn
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // luôn true để không bị khóa
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // luôn true để password không hết hạn
    }

    @Override
    public boolean isEnabled() {
        return true; // luôn true, user luôn kích hoạt
    }
}
