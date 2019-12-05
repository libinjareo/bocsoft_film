package com.binsoft.film.controller.auth.util;

import com.alibaba.fastjson.JSON;
import com.binsoft.film.config.properties.JwtProperties;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Component
public  class JwtTokenUtil {

    @Autowired
    private  JwtProperties jwtProperties;

    /**
     * 从token中获取用户名
     * @param token
     * @return
     */
    public  String getUserNameFromToken(String token){
        return getClaimFromToken(token).getSubject();
    }

    /**
     * 获取jwt发布时间
     * @param token
     * @return
     */
    public  Date getIssuedAtDateFromToken(String token){
        return getClaimFromToken(token).getIssuedAt();
    }

    /**
     * 获取jwt失效时间
     * @param token
     * @return
     */
    public  Date getExpirationDateFromToken(String token){
        return getClaimFromToken(token).getExpiration();
    }

    /**
     * 获取jwt接受者
     * @param token
     * @return
     */
    public  String getAudienceFromToken(String token){
        return getClaimFromToken(token).getAudience();
    }

    /**
     * 获取私有的jwt claim
     * @param token
     * @param key
     * @return
     */
    public  String getPrivateClaimFromToken(String token,String key){
        return getClaimFromToken(token).get(key).toString();
    }

    /**
     * 获取md5 key 从token中
     * @param token
     * @return
     */
    public  String getMd5KeyFromToken(String token){
        return getPrivateClaimFromToken(token,jwtProperties.getMd5Key());
    }

    /**
     * 获取jwt的payload部分
     * @param token
     * @return
     */
    public  Claims getClaimFromToken(String token){
        return Jwts.parser().setSigningKey(jwtProperties.getSecret())
                .parseClaimsJws(token).getBody();
    }

    public  void parseToken(String token) throws JwtException{
        Jwts.parser().setSigningKey(jwtProperties.getSecret()).parseClaimsJws(token).getBody();
    }

    /**
     * 生成token(通过用户名和签名时候用的随机数)
     * @param userName
     * @param randomKey
     * @return
     */
    public  String generateToken(String userName,String randomKey){
        Map<String,Object> claims = new HashMap<>();
        claims.put(jwtProperties.getMd5Key(),randomKey);
        return doGenerateToken(claims,userName);
    }

    /**
     * <pre>
     * 验证token是否失效
     * true:过期 false:没过期
     * </pre>
     * @param token
     * @return
     */
    public boolean isTokenExpired(String token){
        try{
            final Date expiration = getExpirationDateFromToken(token);
            return expiration.before(new Date());
        }catch(ExpiredJwtException ex){
            return true;
        }

    }

    /**
     * 生成token
     * @param claims
     * @param subject
     * @return
     */
    private  String doGenerateToken(Map<String,Object> claims,String subject){
        final Date createdDate = new Date();
        final Date expirationDate = new Date(createdDate.getTime() + jwtProperties.getExpiration() *1000);

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(createdDate).setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512,jwtProperties.getSecret()).compact();
    }

    /**
     * 获取混淆MD5签名用的随机字符串
     * @return
     */
    public  String getRandomKey(){
        return getRandomString(6);
    }

    private  String getRandomString(int length){
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < length; i++){
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }



}
