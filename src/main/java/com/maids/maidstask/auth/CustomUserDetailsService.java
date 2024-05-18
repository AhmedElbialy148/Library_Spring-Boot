package com.maids.maidstask.auth;

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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return patronRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

}
