package web.service;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import web.model.SecureUser;

public interface SecureUserRepository extends CrudRepository<SecureUser, Long> {

    @Query("SELECT u from SecureUser u WHERE u.username=:username")
    public SecureUser getUserByUserName(@Param("username") String username);
}
