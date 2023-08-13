package fawry.internship.adminservice.service.implementation;

import fawry.internship.adminservice.exception.ErrorCode;
import fawry.internship.adminservice.exception.RuntimeErrorCodedException;
import fawry.internship.adminservice.model.LoginRequest;
import fawry.internship.adminservice.model.LoginResponse;
import fawry.internship.adminservice.security.JwtService;
import fawry.internship.adminservice.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public LoginResponse authenticate(LoginRequest request) {
        //TODO handle authentication exception here

        //TODO handle deactivated email login and throw special exception for each case
        try{

         authenticationManager
                 .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
        }
        catch(AuthenticationException exception)
        {
            throw new RuntimeErrorCodedException(ErrorCode.AUTHENTICATION_EXCEPTION);
        }
         return new LoginResponse(jwtService.generateToken(request.getEmail()));

    }
}
