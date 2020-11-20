package com.lxiaocode.boardgame.auth;

import com.lxiaocode.boardgame.auth.exception.LoginException;
import com.lxiaocode.boardgame.common.response.ApiCode;
import com.lxiaocode.boardgame.common.response.DefaultApiCode;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.ToString;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.Option;
import java.util.Date;
import java.util.Optional;

/**
 * @author lixiaofeng
 * @date 2020/9/6 下午5:07
 * @blog http://www.lxiaocode.com/
 */
public class TokenUtil {

    public static final String SIGNING_KEY = "www.lxiaocode.com";

    public static final String AUTH_HEADER = "Authorization";

    public static final String TOKEN_PREFIX = "Bearer ";

    public static final String REFRESH_TOKEN = "RefreshToken";

    public static final long EXPIRATION_TIME = 30 * 60 * 1000;

    public static final long REFRESH_TOKEN_EXPIRATION_TIME = 7 * 24 * 3600000;

    private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;

    /**
     * create a token
     * @param userId
     * @return
     */
    public static Token createToken(String userId){
        String token = Jwts.builder()
                .setSubject(userId)
                .setExpiration(new Date(System.currentTimeMillis() +  EXPIRATION_TIME))
                .signWith(SIGNATURE_ALGORITHM, SIGNING_KEY)
                .compact();

        String refreshToken = createRefreshToken(userId);

        return new Token(token, refreshToken);
    }

    @Getter
    @ToString
    public static class Token {
        private final String accessToken;

        private final String refreshToken;

        public Token(String accessToken, String refreshToken){
            this.accessToken = accessToken;
            this.refreshToken = refreshToken;
        }
    }

    /**
     * get the token by request
     * @param request
     * @return
     */
    public static String getTokenByRequest(HttpServletRequest request){
        String token = request.getHeader(AUTH_HEADER);
        return StringUtils.isEmpty(token)? "":token;
    }

    /**
     * get the refresh token by request
     * @param request
     * @return
     */
    public static String getRefreshTokenByRequest(HttpServletRequest request){
        String token = request.getHeader(REFRESH_TOKEN);
        return StringUtils.isEmpty(token)? "":token;
    }

    /**
     * get the user id by token
     * @param token
     * @return
     */
    public static Optional<String> getUserIdByToken(String token){
        Claims claims =
                Optional.ofNullable(getClaimsFromToken(token)).orElseThrow(() -> new LoginException(DefaultApiCode.TOKEN_AUTHENTICATION_FAIL));

        String userId = claims.getSubject();
        return Optional.ofNullable(userId);
    }

    public static Date getExpirationByToken(String token) {
        return getClaimsFromToken(token).getExpiration();
    }

    private static Claims getClaimsFromToken(String token){
        Claims claims;

        try {
            claims = Jwts.parser()
                    .setSigningKey(SIGNING_KEY)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody();
        }catch (ExpiredJwtException e){
            throw new LoginException(DefaultApiCode.TOKEN_EXPIRED);
        }catch (Exception e){
            claims = null;
            e.printStackTrace();
        }

        return claims;
    }

    private static String createRefreshToken(String userId){
        String refreshToken = Jwts.builder()
                .setSubject(userId)
                .setExpiration(new Date(System.currentTimeMillis() +  REFRESH_TOKEN_EXPIRATION_TIME))
                .signWith(SIGNATURE_ALGORITHM, SIGNING_KEY)
                .compact();

        return refreshToken;
    }
}
