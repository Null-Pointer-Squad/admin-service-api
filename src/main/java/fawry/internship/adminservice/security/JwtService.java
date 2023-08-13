package fawry.internship.adminservice.security;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

public interface JwtService {
    String extractUsername(String token);

    String generateToken(String email);


}
