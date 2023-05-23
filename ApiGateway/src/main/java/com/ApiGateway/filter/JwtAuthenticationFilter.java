package com.ApiGateway.filter;

import com.ApiGateway.exception.TokenNotValidException;
import com.ApiGateway.jwt.JwtUtil;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationFilter extends AbstractGatewayFilterFactory<JwtAuthenticationFilter.Config> {

    private final RouteValidator validator;
//    private final RestTemplate restTemplate;
    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(RouteValidator validator, JwtUtil jwtUtil) {
        super(Config.class);
        this.validator = validator;
//        this.restTemplate = restTemplate;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (((exchange, chain) -> {
            //checking API is other than ignoring list
            if(validator.isSecured.test(exchange.getRequest())){
                //header contains token or not
                if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                    throw new TokenNotValidException("missing Authorization Header");
                }

                //getting the Header
                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);

                if(authHeader != null && authHeader.startsWith("Bearer ")){
                    //removing Bearer from tokenJwtAuthenticationFilter
                    authHeader = authHeader.substring(7);
                }
                try {
                    //REST call to USER-SERVICE(NOT RECOMMENDED)
//                    restTemplate.getForObject("http://USER-SERVICE/auth/validate?token="+ authHeader)
                    jwtUtil.validateToken(authHeader);
                }catch (Exception e){
                    throw new TokenNotValidException("Unauthorized access !!");
                }

            }
            return chain.filter(exchange);
        }));
    }

    public static class Config{

    }

}
