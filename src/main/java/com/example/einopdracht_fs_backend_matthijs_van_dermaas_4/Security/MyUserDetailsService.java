package com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.Security;



import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.modelen.Role;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.modelen.User;
import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepos;

    public MyUserDetailsService(UserRepository repos) {
        this.userRepos = repos;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> ou = userRepos.findByUsername(username);
        if (ou.isPresent()) {
            User user = ou.get();
            List<Role> roles = user.getRoles();
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            for (Role role: roles) {
                grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleName()));
            }
            return new org.springframework.security.core.userdetails.User(username, user.getPassword(), grantedAuthorities);
        }
        else {
            throw new UsernameNotFoundException(username);
        }
    }
}
