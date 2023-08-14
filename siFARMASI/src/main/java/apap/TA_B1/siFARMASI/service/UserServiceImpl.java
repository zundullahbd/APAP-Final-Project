package apap.TA_B1.siFARMASI.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import apap.TA_B1.siFARMASI.model.*;
import apap.TA_B1.siFARMASI.repository.*;

import java.time.Instant;
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
    private ManagerDb managerDb;


    @Override
    public UserModel getUserByName(String name) {
        UserModel user = userDb.findByUsername(name);
        return user;
    }


    @Override
    public UserModel addUser(UserModel user) {
        String pass = encrypt(user.getPassword()); // password di hash sebelum simpan ke db
        user.setPassword(pass); // setting password yang udah dihash
        user.setIsSso(false); // setting bukan user dari sso
        user.setCreated_at_timestamp(Instant.now()); // setting tanggal terbuat di waktu sekarang
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
            dokter.setName(user.getName());
            dokter.setRole(user.getRole());
            dokter.setUsername(user.getUsername());
            dokter.setPassword(user.getPassword());
            dokter.setEmail(user.getEmail());
            dokter.setCreated_at_timestamp(Instant.now());
            return dokterDb.save(dokter);

        } else if (user.getRole().equals("APOTEKER")) {
            ApotekerModel apoteker = new ApotekerModel();
            apoteker.setName(user.getName());
            apoteker.setRole(user.getRole());
            apoteker.setUsername(user.getUsername());
            apoteker.setPassword(user.getPassword());
            apoteker.setEmail(user.getEmail());
            apoteker.setCreated_at_timestamp(Instant.now());
            return apotekerDb.save(apoteker);
        }
        else if (user.getRole().equals("MANAGER")) {
            ManagerModel manager = new ManagerModel();
            manager.setName(user.getName());
            manager.setRole(user.getRole());
            manager.setUsername(user.getUsername());
            manager.setPassword(user.getPassword());
            manager.setEmail(user.getEmail());
            manager.setCreated_at_timestamp(Instant.now());
            return managerDb.save(manager);
        } else {
            return userDb.save(user);
        }


    }

    public void signup(String username, String name, String email, String password, String role) {
        UserModel existingUserByUsername = userDb.findByUsername(username);
        UserModel existingUserByEmail = userDb.findByEmail(email);

        if (existingUserByUsername != null) {
            // Handle username already exists
            // You can log a message, set a flag, or take any other action
            return; // Return without proceeding further
        }

        if (existingUserByEmail != null) {
            // Handle email already exists
            // You can log a message, set a flag, or take any other action
            return; // Return without proceeding further
        }

        // Create a new user
        UserModel user = new UserModel();
        user.setName(name);
        user.setUsername(username);
        user.setEmail(email);
        String pass = encrypt(password);
        user.setPassword(pass);
        user.setRole(role);
        user.setCreated_at_timestamp(Instant.now());

        userDb.save(user);
    }




    @Override
    public void deleteUserVoid(UserModel user) {
        userDb.delete(user);
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


//    @Override
//    public UserModel addUser(UserModel user) {
//        user.setCreated_at_timestamp(Instant.now());
//        return userDb.save(user);
//    }
}

