package com.wit.sc.gateway.config.filter;

import com.wit.sc.gateway.route.white.WhiteUrlLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author twcao
 * @Title: WhiteUrlFilter
 * @ProjectName sc-parent
 * @Description: 单名单过滤器，只有配置在白名单的ip才可以访问网关(如果配置为空，则不允许任务ip访问)
 * @date 2019/3/13/013 15:23
 */
@Component
public class WhiteUrlFilter implements GlobalFilter, Ordered {

    @Autowired
    WhiteUrlLoader whiteUrlLoad;

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String addr = exchange.getRequest().getRemoteAddress().getAddress().getHostAddress();
        List<String> whiteUrls = whiteUrlLoad.loadWhiteUrl();
        if(!whiteUrls.contains(addr)) {
            //如果白名单的地址为空，或者请求的地址不在白名单内，则是非法请求
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.FORBIDDEN);
            DataBuffer buffer = response.bufferFactory().wrap(HttpStatus.FORBIDDEN.name().getBytes());
            return response.writeWith(Mono.just(buffer));
        }
        return chain.filter(exchange);
    }
}
