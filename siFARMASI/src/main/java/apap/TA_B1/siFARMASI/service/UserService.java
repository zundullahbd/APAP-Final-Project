package apap.TA_B1.siFARMASI.service;

import apap.TA_B1.siFARMASI.model.MitraModel;
import apap.TA_B1.siFARMASI.model.UserModel;
import apap.TA_B1.siFARMASI.model.*;

import java.util.List;

import java.util.List;

public interface UserService {

//    UserModel getUserById(Integer id);

    UserModel addUser(UserModel user);

    UserModel getUserByName(String name);

    public String encrypt(String password);

    UserModel deleteUser(UserModel user);

    void signup(String username, String name, String email, String password, String role);

    List<UserModel> getListUser();
    List<UserModel> getListAdmin();


    List<UserModel> getListDokter();

    List<UserModel> getListApoteker();

    List<UserModel> getListManager();

    UserModel addUserWithRole(UserModel user);

    void deleteUserVoid(UserModel user);

}

