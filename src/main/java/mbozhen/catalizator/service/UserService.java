package mbozhen.catalizator.service;

import mbozhen.catalizator.repo.UserRepo;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserService implements ReactiveUserDetailsService {

    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return userRepo.findByUserName(username)
                .cast(UserDetails.class);
    }
}
