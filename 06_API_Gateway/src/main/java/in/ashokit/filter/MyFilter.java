package in.ashokit.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Set;

@Component
public class MyFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("Filter is executed");

        ServerHttpRequest request = exchange.getRequest();
        HttpHeaders headers = request.getHeaders();
        Set<String> keySet = headers.keySet();

        // Check if "secret" header is present
        if (!keySet.contains("secret")) {
            return respondWithError(exchange, "Missing 'secret' header");
        }

        // Get the value of "secret" header and validate it
        List<String> list = headers.get("secret");
        if (list == null || list.isEmpty() || !list.get(0).equals("ashokit@123")) {
            return respondWithError(exchange, "Invalid 'secret' value");
        }

        return chain.filter(exchange);  // Continue processing the request
    }

    @Override
    public int getOrder() {
        return 0;  // Lower value means higher priority
    }

    // Helper method to respond with an error message
    private Mono<Void> respondWithError(ServerWebExchange exchange, String message) {
        exchange.getResponse().setStatusCode(org.springframework.http.HttpStatus.FORBIDDEN);
        return exchange.getResponse().setComplete();
    }
}
