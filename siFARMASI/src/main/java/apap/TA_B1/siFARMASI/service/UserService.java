package apap.TA_B1.siFARMASI.service;

import apap.TA_B1.siFARMASI.model.MitraModel;
import apap.TA_B1.siFARMASI.model.UserModel;

import java.util.List;

public interface UserService {
    UserModel getUserById(Integer id);

    List<UserModel> getListUser();
    List<UserModel> getListAdmin();
    List<UserModel> getListDokter();
    List<UserModel> getListApoteker();
    List<UserModel> getListManager();

    UserModel addUser(UserModel user);
}