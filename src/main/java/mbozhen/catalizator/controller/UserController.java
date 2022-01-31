package mbozhen.catalizator.controller;

import mbozhen.catalizator.config.JwtUtil;
import mbozhen.catalizator.domain.User;
import mbozhen.catalizator.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

@RestController
public class UserController {

    private static final ResponseEntity<Object> UNAUTHORIZED =
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public UserController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public Mono<ResponseEntity> login(ServerWebExchange webExchange) {
        return webExchange.getFormData()
                .flatMap(cred ->
                        userService.findByUsername(cred.getFirst("username"))
                                .cast(User.class)
                                .map(userDetails ->
                                        Objects.equals(
                                                cred.getFirst("password"),
                                                userDetails.getPassword()
                                        )
                                                ? ResponseEntity.ok(jwtUtil.generateToken(userDetails))
                                                : UNAUTHORIZED
                                )
                                .defaultIfEmpty(UNAUTHORIZED)
                );
    }
}
