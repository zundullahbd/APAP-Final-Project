package apap.TA_B1.siFARMASI.service;

import apap.TA_B1.siFARMASI.model.AdminModel;
import apap.TA_B1.siFARMASI.repository.UserDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserDb userDb;

    @Override
    public AdminModel create(AdminModel pengguna) {
        return userDb.save(pengguna);
    }
}
