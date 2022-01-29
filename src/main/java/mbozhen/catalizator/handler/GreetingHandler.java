package mbozhen.catalizator.handler;

import mbozhen.catalizator.domain.Message;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
public class GreetingHandler {

    public Mono<ServerResponse> hello(ServerRequest serverRequest) {
        Long start = serverRequest.queryParam("start")
                .map(Long::valueOf)
                .orElse(0L);
        Long count = serverRequest.queryParam("count")
                .map(Long::valueOf)
                .orElse(2L);

        Flux<Message> messageFlux = Flux.just(
                        "Hello, reactive!",
                        "Here you are again"
                )
                .skip(start)
                .take(count)
                .map(Message::new);

        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(messageFlux, Message.class);
    }

    public Mono<ServerResponse> index(ServerRequest serverRequest) {
        String userName = serverRequest.queryParam("user")
                .orElse("Nobody");
        return ServerResponse
                .ok()
                .render("index", Map.of("user", userName));
    }
}