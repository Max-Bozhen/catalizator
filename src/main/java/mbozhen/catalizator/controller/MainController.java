package mbozhen.catalizator.controller;

import mbozhen.catalizator.domain.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/controller")
public class MainController {

    @GetMapping
    public Flux<Message> list(@RequestParam(required = false,defaultValue = "0") Long start,
                              @RequestParam(required = false, defaultValue = "2") Long count){
        return Flux.just(
                        "Hello, reactive!",
                        "Here you are again"
                )
                .skip(start)
                .take(count)
                .map(Message::new);
    }
}
