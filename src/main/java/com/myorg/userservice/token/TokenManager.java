package com.myorg.userservice.token;

import com.myorg.userservice.models.Token;
import com.myorg.userservice.models.User;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang3.RandomStringUtils;

import javax.crypto.SecretKey;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenManager {

    public static Token createToken(User user) {
        Token token = new Token();
        //use Apache common lib to generate first add dependency in pom.xml
        token.setUser(user);
        token.setValue(RandomStringUtils.randomAlphanumeric(128));
        //set expiry time eg. 30 days from today
        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.DAY_OF_MONTH, 30);
        Date dateAfter30Days = calendar.getTime();

        token.setExpiryAt(dateAfter30Days);
        token.setDeleted(false);
        return token;
    }
    public static Token createJwtToken(User user, SecretKey secretKey) {
        Map<String,Object> dataInJwt = new HashMap<>();
        dataInJwt.put("userId", user.getId());
        dataInJwt.put("email", user.getEmail());

        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();

        calendar.add(Calendar.DAY_OF_MONTH, 30);
        Date dateAfter30Days = calendar.getTime();

        String jwt = Jwts.builder()
                .claims(dataInJwt)
                .expiration(dateAfter30Days)
                .signWith(secretKey)
                .compact();
        Token token = new Token();
        token.setValue(jwt);
        token.setUser(user);
        token.setExpiryAt(dateAfter30Days);
        token.setDeleted(false);
        return token;
    }
}
