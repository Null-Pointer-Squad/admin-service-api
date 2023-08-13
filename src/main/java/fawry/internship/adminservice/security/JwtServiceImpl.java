package fawry.internship.adminservice.security;

import fawry.internship.adminservice.exception.ErrorCode;
import fawry.internship.adminservice.exception.RuntimeErrorCodedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements JwtService {


    private static final String SECRET_KEY = "5246C473269DC54AE212C19DF45695246C473269DC54As";
    @Override
    public String extractUsername(String token)
    {
        return extractClaim(token,Claims::getSubject);
    }


    private <T> T extractClaim(String token, Function<Claims,T> claimResolver )
    {
         return claimResolver.apply(extractAllClaims(token));

    }

    @Override
    public String generateToken(String email)
    {
       return  Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ 1000 * 60 * 5))
                .signWith(getSignKey()).compact();


    }

    private Claims extractAllClaims(String token)
    {
        try{
            return Jwts
                    .parserBuilder()
                    .setSigningKey(getSignKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

    }
    catch(JwtException ex)
    {
        throw new RuntimeErrorCodedException(ErrorCode.INVALID_AUTHORIZATION_TOKEN);
    }
    }

    private Key getSignKey()
    {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));
    }
}
