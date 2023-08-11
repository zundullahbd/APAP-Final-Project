package apap.TA_B1.siFARMASI.service;

import apap.TA_B1.siFARMASI.model.UserModel;
import apap.TA_B1.siFARMASI.repository.UserDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
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

    @Override
    public List<UserModel> getListUser() {
        return userDb.findAll();
    }

    @Override
    public List<UserModel> getListAdmin() {
        return userDb.findAllByRole("admin");
    }

    @Override
    public List<UserModel> getListDokter() {
        return userDb.findAllByRole("dokter");
    }

    @Override
    public List<UserModel> getListApoteker() {
        return userDb.findAllByRole("apoteker");
    }

    @Override
    public List<UserModel> getListManager() {
        return userDb.findAllByRole("manager");
    }

    @Override
    public UserModel addUser(UserModel user) {
        user.setCreated_at_timestamp(Instant.now());
        return userDb.save(user);
    }
}
