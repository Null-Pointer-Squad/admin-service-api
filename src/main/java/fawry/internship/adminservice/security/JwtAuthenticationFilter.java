package fawry.internship.adminservice.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import fawry.internship.adminservice.exception.ErrorCode;
import fawry.internship.adminservice.exception.ErrorDetails;
import fawry.internship.adminservice.exception.RestExceptionHandler;
import fawry.internship.adminservice.exception.RuntimeErrorCodedException;
import fawry.internship.adminservice.model.ResponseEnvelop;
import fawry.internship.adminservice.model.ResponseFactory;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final RestExceptionHandler exceptionHandler;


    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException
    {

        final String authHeader = request.getHeader("Authorization");
        if(authHeader==null || authHeader.isEmpty()) {
          handleJwtException(request,response);
          return;
        }

        final String  jwt = authHeader.substring(7);

        try{
            final  String  userEmail = jwtService.extractUsername(jwt);
            SecurityContextHolder
                    .getContext()
                    .setAuthentication(new UsernamePasswordAuthenticationToken(userEmail,null));
            filterChain.doFilter(request,response);

        }
        catch (RuntimeErrorCodedException exception){

            handleJwtException(request,response);


    }




    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException
    {
        return request.getServletPath().equals("/login")||request.getServletPath().startsWith("/swagger-ui")
                || request.getServletPath().startsWith("/v3/api-docs");

    }

    private void handleJwtException( HttpServletRequest request, HttpServletResponse response) throws IOException {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setCode(ErrorCode.INVALID_AUTHORIZATION_TOKEN.getCode());
        errorDetails.setTimeStamp(new Date().getTime());
        ResponseEnvelop responseEnvelop = ResponseFactory.getFailureResponse();
        responseEnvelop.setErrorDetails(errorDetails);

        ObjectMapper mapper = new ObjectMapper();
        String body = mapper.writeValueAsString(responseEnvelop);

        response.setStatus(HttpStatus.UNAUTHORIZED.value());

        response.setContentType("application/json");
        response.getWriter().print(body);


    }


}
