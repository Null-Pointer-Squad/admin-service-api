package fawry.internship.adminservice.security;

import fawry.internship.adminservice.exception.ErrorCode;
import fawry.internship.adminservice.exception.RuntimeErrorCodedException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
public class EmailPasswordAuthenticationProvider implements AuthenticationProvider {
    UserDetailsService userDetailsService;
    PasswordEncoder passwordEncoder;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException
    {
        String email = (String)authentication.getPrincipal();
        AdminDetails adminDetails;
        try {
             adminDetails = (AdminDetails) userDetailsService.loadUserByUsername(email);
        }
        catch (UsernameNotFoundException ex)
        {
            throw new RuntimeErrorCodedException(ErrorCode.AUTHENTICATION_EXCEPTION);
        }


       if(!passwordEncoder.matches((String)authentication.getCredentials(), adminDetails.getPassword()))
           throw new RuntimeErrorCodedException(ErrorCode.AUTHENTICATION_EXCEPTION);

       if(!adminDetails.getAdmin().getEnabled())
           throw new RuntimeErrorCodedException(ErrorCode.DEACTIVATED_ACCOUNT);


        return new UsernamePasswordAuthenticationToken(email,null);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
