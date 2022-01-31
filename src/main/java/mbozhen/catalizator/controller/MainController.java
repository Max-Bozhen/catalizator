package mbozhen.catalizator.controller;

import mbozhen.catalizator.domain.Message;
import mbozhen.catalizator.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/controller")
public class MainController {

    private final MessageService messageService;

    @Autowired
    public MainController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public Flux<Message> list(@RequestParam(required = false, defaultValue = "0") Long start,
                              @RequestParam(required = false, defaultValue = "2") Long count) {
        return messageService.list();
    }

    @PostMapping
    public Mono<Message> add(@RequestBody Message message) {
        return messageService.addNewMessage(message);
    }
}
