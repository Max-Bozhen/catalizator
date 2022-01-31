package mbozhen.catalizator.repo;

import mbozhen.catalizator.domain.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface UserRepo extends ReactiveCrudRepository<User, Long> {

    Mono<User> findByUserName(String name);

}
