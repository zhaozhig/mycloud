package com.example.getway.filter;

import com.example.getway.tools.MgtWhiteList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;



/**
 * @author zhaozg
 * @version 1.0
 * @date 2019/6/18
 */
@Slf4j
@Component
public class CustomGlobalFileter implements GlobalFilter,Ordered {

    @Autowired
    MgtWhiteList mgtWhiteList;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        /*String token = exchange.getRequest().getQueryParams().getFirst("token");
        if (token == null || token.isEmpty()) {
            log.info("token is empty...");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }*/
        String uri = "";

        /** 1. 验证 anon 白名单 **/
        boolean isOpen = mgtWhiteList.isOpenUrl(uri);

        if (isOpen) {
            return null;
        } else {

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            Authentication cation = null;
            if (authentication != null && authentication instanceof OAuth2Authentication) {

                cation = ((OAuth2Authentication) authentication).getUserAuthentication();

            } else {

            }

        }

        return chain.filter(exchange);
    }


    @Override
    public int getOrder () {
        return Ordered.LOWEST_PRECEDENCE;
    }

}
