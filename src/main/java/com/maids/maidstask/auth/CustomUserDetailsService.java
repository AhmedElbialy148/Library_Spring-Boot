package com.maids.maidstask.auth;

import com.maids.maidstask.exceptions.NotFoundException;
import com.maids.maidstask.patrons.repositories.PatronRepository;
import com.maids.maidstask.patrons.repositories.PatronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final PatronRepository patronRepo;
    @Autowired
    public CustomUserDetailsService(PatronRepository patronRepo) {
        this.patronRepo = patronRepo;
    }

    // NotFoundException is a custom exception class
    @Override
    public UserDetails loadUserByUsername(String username) throws NotFoundException {
        return patronRepo.findByUsername(username).orElseThrow(() -> new NotFoundException("User not found"));
    }
//    public UserDetails loadUserById(Integer id) throws UsernameNotFoundException {
//        return patronRepo.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
//    }

}
