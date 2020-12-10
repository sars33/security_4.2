package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import web.model.SecureUser;
import web.repository.UserRepository;
import web.security.MyUserDetails;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

   // @Autowired
    private SecureUserRepository secureUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        SecureUser secureUser = secureUserRepository.getUserByUserName(username);
        if (secureUser == null) {
            throw new UsernameNotFoundException("Could not find the user");
        }
        return new MyUserDetails(secureUser);
    }
}
