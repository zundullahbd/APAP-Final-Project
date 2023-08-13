package apap.TA_B1.siFARMASI.service;

import apap.TA_B1.siFARMASI.model.*;

import java.util.List;

public interface UserService {

    UserModel addUser(UserModel user);

    UserModel getUserByName(String name);

    public String encrypt(String password);

    UserModel deleteUser(UserModel user);

    void signup(String username, String name, String email, String password, String role);

    List<UserModel> getListUser();

    List<DokterModel> getListDokter();

    List<ApotekerModel> getListApoteker();

    List<ManagerModel> getListManager();

    UserModel addUserWithRole(UserModel user);

    void deleteUserVoid(UserModel user);

}
