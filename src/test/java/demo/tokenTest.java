package demo;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class tokenTest {
    @Test
    public void testToken(){
        try {
            // 设置过期时间
            Date date = new Date(System.currentTimeMillis() + 15 * 60 * 1000);
            // 私钥和加密算法
            Algorithm algorithm = Algorithm.HMAC256("atlan");
            // 设置头部信息
            Map<String, Object> header = new HashMap<>(2);
            header.put("Type", "Jwt");
            header.put("alg", "HS256");
            // 返回token字符串
            String result=JWT.create()
                    .withHeader(header)
                    .withClaim("username", "name")
                    .withClaim("password", "word")
                    .withExpiresAt(date)
                    .sign(algorithm);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
            //return null;
        }
    }

    @Test
    public void verifyToken(){
        String token="eyJhbGciOiJIUzI1NiIsIlR5cGUiOiJKd3QiLCJ0eXAiOiJKV1QifQ.eyJwYXNzd29yZCI6IndvcmQiLCJleHAiOjE1NjU0OTk3OTUsInVzZXJuYW1lIjoibmFtZSJ9.i4SDrvgNHIo7lD7kcjpc7snkAJalUWkK-WUq0qRpy0E";
        try {
            Algorithm algorithm = Algorithm.HMAC256("atlan");
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            System.out.println(jwt.getClaim("username"));
        } catch (Exception e){

        }
    }
    @Test
    public void parse(){
        String token="eyJhbGciOiJIUzI1NiIsIlR5cGUiOiJKd3QiLCJ0eXAiOiJKV1QifQ.eyJwYXNzd29yZCI6IndvcmQiLCJleHAiOjE1NjU1NzU3NDgsInVzZXJuYW1lIjoibmFtZSJ9.77OOMrJqEugpUCo_YwndIVR-R7ML-oiFx5m16hu61PM";
        try {
            DecodedJWT jwt = JWT.decode(token);
            System.out.println(jwt.getHeader());
            System.out.println(jwt.getClaim("username").asString());
        } catch (JWTDecodeException e){
            e.printStackTrace();
            //return null;
        }
    }



    @Test
    public void createJWT(){
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId("123")  //登录用户的id
                .setSubject("测试")  //登录用户的名称
                .setIssuedAt(new Date()) //用户登录的时间
                .signWith(SignatureAlgorithm.HS256,"atlan")//头部信息 第一个参数为加密方式为哈希 256  第二个参数为加密者，可以认为是密钥
                .setExpiration(new Date(new Date().getTime()+60000)); //设置token 的过期时间为一分钟

        System.out.println(jwtBuilder.compact());
    }
    /*@Test
    public void parseJson(){
        String token="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMjMiLCJzdWIiOiLmtYvor5UiLCJpYXQiOjE1NjU0OTgzMjIsImV4cCI6MTU2NTQ5ODM4Mn0.LGZHnKIT6xT5VI2SucyP4ixwdqZtTGWdJZBfV6XQSng";
        Claims claims = Jwts.parser().setSigningKey("atlan").parseClaimsJws(token).getBody();
        System.out.println(claims.getId());
    }*/
}
