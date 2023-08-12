package apap.TA_B1.siFARMASI.security.jwt;

import apap.TA_B1.siFARMASI.model.JwtLoginRequest;
import apap.TA_B1.siFARMASI.model.UserModel;
import apap.TA_B1.siFARMASI.repository.UserDb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDb userDao;

    @Autowired
    private PasswordEncoder bcryptEncoder;
    private static Logger logger = LoggerFactory.getLogger(JwtUserDetailsService.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = userDao.findByUsername(username);
        if (user != null) {
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                    new ArrayList<>());
        } else {
            logger.warn("Cannot find user with that username");
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    public UserModel save(JwtLoginRequest user) {
        UserModel newUser = new UserModel();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        return userDao.save(newUser);
    }

}
