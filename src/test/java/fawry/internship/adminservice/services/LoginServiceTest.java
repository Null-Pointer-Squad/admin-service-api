package fawry.internship.adminservice.services;


import fawry.internship.adminservice.exception.ErrorCode;
import fawry.internship.adminservice.exception.RuntimeErrorCodedException;
import fawry.internship.adminservice.model.LoginRequest;
import fawry.internship.adminservice.model.LoginResponse;
import fawry.internship.adminservice.security.JwtService;
import fawry.internship.adminservice.service.LoginService;
import fawry.internship.adminservice.service.implementation.LoginServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
public class LoginServiceTest
{

    @Mock
   private JwtService jwtService;
    @Mock
    private AuthenticationManager authenticationManager;

    private LoginService loginService;

   @BeforeEach
   public void setUp()
   {

        loginService = new LoginServiceImpl(jwtService, authenticationManager);


   }



   @Test
    void GivenValidCredentials_whenAuthentication_ThenTokenGenerated()
   {
       Mockito.when(jwtService.generateToken(Mockito.anyString())).thenReturn("token generated");
       LoginResponse response = loginService.authenticate(new LoginRequest("admin@fawry.com","123456"));
       assertThat(response.getToken()).isEqualTo("token generated");


   }

   @Test
   void GivenInvalidCredentials_WhenAuthentication_ThenAuthenticationException()
   {
       LoginRequest invalidCredentials = new LoginRequest("dummy","dummy");
      Mockito.
              when(
                      authenticationManager.authenticate(
                      new UsernamePasswordAuthenticationToken(invalidCredentials.getEmail(),invalidCredentials.getEmail())))
              .thenThrow(BadCredentialsException.class );
       assertThatThrownBy(()->loginService.authenticate(invalidCredentials))
               .isEqualTo(new RuntimeErrorCodedException(ErrorCode.AUTHENTICATION_EXCEPTION));



   }
}
