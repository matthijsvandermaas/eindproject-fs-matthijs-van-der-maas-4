//package com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.Security;
//
//
//
//import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.modelen.User;
//import com.example.einopdracht_fs_backend_matthijs_van_dermaas_4.repository.UserRepository;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import java.util.ArrayList;
//
//public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
//
//    private final UserRepository userRepository;
//
//    public UserDetailsService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username);
//        if (user == null) {
//            throw new UsernameNotFoundException("User not found");
//        }
//        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
//    }
//}
