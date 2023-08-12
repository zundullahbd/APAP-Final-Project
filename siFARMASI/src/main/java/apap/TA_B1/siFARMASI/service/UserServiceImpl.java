package apap.TA_B1.siFARMASI.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import apap.TA_B1.siFARMASI.model.*;
import apap.TA_B1.siFARMASI.repository.*;


import java.util.List;
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDb userDb;

    @Autowired
    private ApotekerDb apotekerDb;

    @Autowired
    private DokterDb dokterDb;

    @Autowired
    private AdminDb adminDb;

    @Autowired
    private ManagerDb managerDb;


    @Override
    public UserModel getUserByNama(String nama) {
        UserModel user = userDb.findByUsername(nama);
        return user;
    }


    @Override
    public UserModel addUser(UserModel user) {
        String pass = encrypt(user.getPassword());
        user.setPassword(pass);
        return userDb.save(user);
    }



    @Override
    public String encrypt(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }


    @Override
    public UserModel deleteUser(UserModel user) {
        userDb.delete(user);
        return user;
    }


    @Override
    public UserModel addUserWithRole(UserModel user) {
        String pass = encrypt(user.getPassword());
        user.setPassword(pass);

        if (user.getRole().equals("DOKTER")) {
            DokterModel dokter = new DokterModel();
            dokter.setNama(user.getNama());
            dokter.setRole(user.getRole());
            dokter.setUsername(user.getUsername());
            dokter.setPassword(user.getPassword());
            dokter.setEmail(user.getEmail());
            return dokterDb.save(dokter);

        } else if (user.getRole().equals("APOTEKER")) {
            ApotekerModel apoteker = new ApotekerModel();
            apoteker.setNama(user.getNama());
            apoteker.setRole(user.getRole());
            apoteker.setUsername(user.getUsername());
            apoteker.setPassword(user.getPassword());
            apoteker.setEmail(user.getEmail());
            return apotekerDb.save(apoteker);
        }
        else if (user.getRole().equals("MANAGER")) {
                ApotekerModel apoteker = new ApotekerModel();
                apoteker.setNama(user.getNama());
                apoteker.setRole(user.getRole());
                apoteker.setUsername(user.getUsername());
                apoteker.setPassword(user.getPassword());
                apoteker.setEmail(user.getEmail());
                return apotekerDb.save(apoteker);
        } else {
            return userDb.save(user);
        }


    }

    @Override
    public List<UserModel> getListUser() {

        return userDb.findAll();
    }

    @Override
    public List<DokterModel> getListDokter() {

        return dokterDb.findAll();
    }

    @Override
    public List<ApotekerModel> getListApoteker() {
        return apotekerDb.findAll();
    }

    @Override
    public List<ManagerModel> getListManager() {
        return managerDb.findAll();
    }

    @Override
    public void deleteUserVoid(UserModel user) {
        userDb.delete(user);
    }
}

