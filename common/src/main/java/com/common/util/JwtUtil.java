package com.common.util;

import com.alibaba.druid.util.StringUtils;
import io.jsonwebtoken.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @version 1.0
 * @projectName: shop
 * @author: lin
 * @date: 2024/1/19  20:46
 */
public class JwtUtil {

    //token时效12小时
    public static final long EXPIRE = 1000*60*60*24;
    //签名哈希的密钥，对于不同的加密算法来说含义不同
    public static final String APP_SECRET = "hss200923usersToken";

    /**
     * 根据用户id和用户名生成token
     * @param userId 用户id
     * @param username 用户名称
     * @return JWT规则生成的token
     */
    public static String getToken(String userId,String username){
        return Jwts.builder()
                .setHeaderParam("typ","JWT")
                .setHeaderParam("alg","HS256")
                .setSubject("users")
                .setIssuedAt(new Date())//token 保留时间
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))//token失效时间
                .claim("user_id",userId)
                .claim("username",username)
                //HS256算法实际上就是MD5加盐值，此时APP_SECRET就代表盐值
                .signWith(SignatureAlgorithm.HS256,APP_SECRET)
                .compact();
    }

    // 验证token是否还有效，返回具体内容
    public static Claims checkToken(String token){
        if(token == null){
            return null;
        }
        JwtParser parser = Jwts.parser();
        try {
            Jws<Claims> claimsJws = parser.setSigningKey(APP_SECRET).parseClaimsJws(token);
            Claims claims = claimsJws.getBody();
            System.out.println(claims.getExpiration()); // 有效期
            // 如果解析token正常，返回claims
            return claims;
        }catch (Exception e) {
            // 如果解析token抛出异常，返回null
            return null;
        }
    }

    /**
     * 根据token 获取用户username
     * @param request Http 请求对象
     * @return 解析token后获得的用户id
     */
    public static String getUserId(HttpServletRequest request){
        String jwtToken = request.getHeader("token");
        if(StringUtils.isEmpty(jwtToken)) return null;
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        Claims claims = claimsJws.getBody();
        return (String)claims.get("user_id");
    }

}
