package web.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import web.model.SecureUser;

import javax.sql.rowset.CachedRowSet;

public interface SecureUserRepository extends CrudRepository<SecureUser, Long> {

    public SecureUser getUserByUsername(@Param("username") String username);
}
