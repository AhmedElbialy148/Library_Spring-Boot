package com.maids.maidstask.auth;

//import com.example.springboottest1.models.Role;
import com.maids.maidstask.auth.Dtos.LoginRequestDto;
import com.maids.maidstask.auth.Dtos.LoginResponseDto;
import com.maids.maidstask.auth.Dtos.RegisterRequestDto;
import com.maids.maidstask.auth.Dtos.RegisterResponseDto;
import com.maids.maidstask.exceptions.BadRequestException;
import com.maids.maidstask.patrons.models.Patron;
import com.maids.maidstask.patrons.repositories.PatronRepository;
import com.maids.maidstask.auth.Jwt.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final PatronRepository patronRepo;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(JwtService jwtService, PasswordEncoder passwordEncoder, PatronRepository patronRepo,AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.patronRepo = patronRepo;
        this.authenticationManager = authenticationManager;
    }

    public RegisterResponseDto register(RegisterRequestDto userData) {
        // Check if patron already exists
        if(this.patronRepo.existsByUsername(userData.username())){
            throw new BadRequestException("Patron already exists");
        }

        Patron patron = new Patron();
        patron.setUsername(userData.username());
        patron.setEmail(userData.email());
        patron.setAddress(userData.address());
        patron.setPhoneNumber(userData.phoneNumber());
        patron.setPassword(passwordEncoder.encode(userData.password()));

//        Role role = new Role("USER");
//        user.setRoles(List.of(role));

        patronRepo.save(patron);
        return new RegisterResponseDto("Patron created successfully");
    }

    public LoginResponseDto login(LoginRequestDto userData) throws BadRequestException{

//           Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userData.username(), userData.password()));
//            SecurityContextHolder.getContext().setAuthentication(authentication);

            Patron user = patronRepo.findByUsername(userData.username()).orElseThrow(() -> new BadRequestException("User not found"));
            if(!passwordEncoder.matches(userData.password(), user.getPassword())) {
                throw new BadRequestException("Invalid username or password");
            }
            return new LoginResponseDto(jwtService.generateToken(user));

    }
}
