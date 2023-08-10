package apap.TA_B1.siFARMASI.service;

import apap.TA_B1.siFARMASI.model.MitraModel;
import apap.TA_B1.siFARMASI.model.UserModel;

import java.util.List;

public interface UserService {
    UserModel getUserById(Integer id);

    List<UserModel> getListUser();

    UserModel addUser(UserModel user);
}