package com.ApiGateway.filter;

import com.ApiGateway.jwt.JwtUtil;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

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
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Missing Authorization Header");
                }

                //getting the Header
                String authHeader = Objects.requireNonNull(Objects.requireNonNull(exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION))).get(0);

                if(authHeader != null && authHeader.startsWith("Bearer ")){
                    //removing Bearer from tokenJwtAuthenticationFilter
                    authHeader = authHeader.substring(7);
                }
                try {
                    //REST call to USER-SERVICE(NOT RECOMMENDED)
//                    restTemplate.getForObject("http://USER-SERVICE/auth/validate?token="+ authHeader)
                    jwtUtil.validateToken(authHeader);
                }catch (Exception e){
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Unauthorized access");
                }

            }
            return chain.filter(exchange);
        }));
    }

    public static class Config{

    }

}
