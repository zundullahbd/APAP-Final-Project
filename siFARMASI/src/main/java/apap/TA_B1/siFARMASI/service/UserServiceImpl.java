package apap.TA_B1.siFARMASI.service;

import apap.TA_B1.siFARMASI.model.UserModel;
import apap.TA_B1.siFARMASI.repository.UserDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserDb userDb;

    public UserModel getUserById(Integer id) {
        Optional<UserModel> user = userDb.findById(id);
        if(user.isPresent()) {
            return userDb.findById(id).get();
        }
        return null;
    }
}
