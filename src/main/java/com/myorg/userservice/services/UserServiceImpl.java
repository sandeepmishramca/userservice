package com.myorg.userservice.services;

import com.myorg.userservice.models.Token;
import com.myorg.userservice.models.User;
import com.myorg.userservice.repositories.TokenRepository;
import com.myorg.userservice.repositories.UserRepository;
import com.myorg.userservice.token.TokenManager;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private TokenRepository tokenRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private SecretKey secretKey;

    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            TokenRepository tokenRepository,
            BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.secretKey = Keys.hmacShaKeyFor("myveryveryverylongsecretkey123456789".getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public Token login(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if(userOptional.isEmpty()) {
            //TODO:throw exception
            return null;
        }
        User user = userOptional.get();
        if(!bCryptPasswordEncoder.matches(password,userOptional.get().getHashedPassword())){
            //TODO:throw exception
            return null;
        }
        Token token = TokenManager.createToken(user);//this is ramdom token
//        Token token = TokenManager.createJwtToken(user, this.secretKey);

        return tokenRepository.save(token);
    }

    @Override
    public void logOut(String token) {
        Optional<Token> optionalToken = tokenRepository.findByValueAndDeleted(token, false);
        if(optionalToken.isEmpty()) {
            //TODO: throw exception
        }
        Token tokenEntity = optionalToken.get();
        tokenEntity.setDeleted(true);
        tokenRepository.save(tokenEntity);
    }

    @Override
    public User signUp(String name, String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()) {
            //TODO: throw exception
            return null;
        }
        User user1 = new User();
        user1.setEmail(email);
        user1.setName(name);
        user1.setHashedPassword(bCryptPasswordEncoder.encode(password));
        return userRepository.save(user1);
    }

    @Override
    public User validate(String token) {
        /*
        1. check if token exist?
        2. token expiry check
        3. token is deleted?
         */
        // this is random token validation
        Optional<Token> tokenOptional = tokenRepository.
                findByValueAndDeletedAndExpiryAtGreaterThan(
                        token,
                        false,
                        new Date()
                );
        if (tokenOptional.isEmpty()){
            //TODO: throw exception
            return null;
        }
        return tokenOptional.get().getUser();
    }
//@Override
//public User validate(String tokenValue) {
//        /*
//        1. check if token exist?
//        2. token expiry check
//        3. token is deleted?
//         */
//    // this is JWT token validation
//    Jws<Claims> claimsJws = Jwts
//            .parser()
//            .verifyWith(this.secretKey)
//            .build()
//            .parseSignedClaims(tokenValue);
//    Date expiryAt = claimsJws.getPayload().getExpiration();
//    Long userId = claimsJws.getPayload().get("userId", Long.class);
//    String email = claimsJws.getPayload().get("email", String.class);
//    User user = new User();
//    user.setId(userId);
//    user.setEmail(email);
//    return user;
//}
}
