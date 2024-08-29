package id.co.bca.intra.training.microservicesJWT.repository;

import id.co.bca.intra.training.microservicesJWT.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
