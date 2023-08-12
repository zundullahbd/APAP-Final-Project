package apap.TA_B1.siFARMASI.service;

import apap.TA_B1.siFARMASI.model.*;

import java.util.List;

public interface UserService {

    UserModel addUser(UserModel user);

    UserModel getUserByNama(String name);

    public String encrypt(String password);

    UserModel deleteUser(UserModel user);

    List<UserModel> getListUser();

    List<DokterModel> getListDokter();

    List<ApotekerModel> getListApoteker();

    List<ManagerModel> getListManager();

    UserModel addUserWithRole(UserModel user);

    void deleteUserVoid(UserModel user);

}
