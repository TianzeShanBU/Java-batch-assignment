package com.example.gateway.filter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

import static java.lang.String.format;

/**
 *  filter order global
 *  {
 *   "org.springframework.cloud.gateway.filter.LoadBalancerClientFilter@77856cc5": 10100,
 *   "org.springframework.cloud.gateway.filter.RouteToRequestUrlFilter@4f6fd101": 10000,
 *   "org.springframework.cloud.gateway.filter.NettyWriteResponseFilter@32d22650": -1,
 *   "org.springframework.cloud.gateway.filter.ForwardRoutingFilter@106459d9": 2147483647,
 *   "org.springframework.cloud.gateway.filter.NettyRoutingFilter@1fbd5e0": 2147483647,
 *   "org.springframework.cloud.gateway.filter.ForwardPathFilter@33a71d23": 0,
 *   "org.springframework.cloud.gateway.filter.AdaptCachedBodyGlobalFilter@135064ea": 2147483637,
 *   "org.springframework.cloud.gateway.filter.WebsocketRoutingFilter@23c05889": 2147483646
 * }
 */
@Component
public class  PrePostRequestFilter implements GlobalFilter, Ordered {

    final Logger logger = LoggerFactory.getLogger(PrePostRequestFilter.class);

    public static final String CORRELATION_ID = "correlation-id";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.info("pre log");

        ServerHttpRequest request = exchange.getRequest();
        HttpHeaders header = request.getHeaders();
        if(header.containsKey(CORRELATION_ID)){
            logger.info(format("!!!Tracked request with correlation id %s", header.get(CORRELATION_ID)));
        }else{
            request = exchange.getRequest()
                    .mutate()
                    .header(CORRELATION_ID, java.util.UUID.randomUUID().toString())
                    .build();

            return chain.filter(exchange.mutate().request(request).build())
                    .then(Mono.fromRunnable(() -> {logger.info("post log: co-relation id = " + header.get(CORRELATION_ID));
                        }));
        }

        return chain
                .filter(exchange)
                .then(Mono.fromRunnable(() -> logger.info("post log===")));
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
