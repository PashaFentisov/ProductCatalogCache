package core.productcatalogcache.repository;

import core.productcatalogcache.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u left join fetch u.role WHERE u.username = :username")
    Optional<User> findByUsername(@Param("username") String username);
}
