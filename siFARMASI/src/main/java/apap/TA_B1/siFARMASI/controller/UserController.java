package apap.TA_B1.siFARMASI.controller;

import apap.TA_B1.siFARMASI.model.AdminModel;
import apap.TA_B1.siFARMASI.model.UserModel;
import apap.TA_B1.siFARMASI.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;
import java.util.List;
import apap.TA_B1.siFARMASI.model.*;
import apap.TA_B1.siFARMASI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@CrossOrigin
@Controller
@RequestMapping("/user")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    String admin = "ADMIN";
    String apoteker = "APOTEKER";
    String dokter = "DOKTER";
    String manager = "MANAGER";
    String nameUser = "nameUser";
    String addUser = "auth/add-user";
    String viewAllUser = "auth/viewall-user";
    String accessDenied = "auth/access-denied";
            
    @GetMapping("")
    public String viewUserList(Model model) {
        logger.info("Handling view user list request");
        List<UserModel> listUser = userService.getListUser();
        List<UserModel> listAdmin = userService.getListAdmin();
        List<UserModel> listDokter = userService.getListDokter();
        List<UserModel> listApoteker = userService.getListApoteker();
        List<UserModel> listManager = userService.getListManager();
        model.addAttribute("listUser", listUser);
        model.addAttribute("listAdmin", listAdmin);
        model.addAttribute("listDokter", listDokter);
        model.addAttribute("listApoteker", listApoteker);
        model.addAttribute("listManager", listManager);
        return "user/list-user";
    }

    @GetMapping("/adminAddUser")
    public String adminAddUserForm(Model model) {
        logger.info("Handling add user form request");
        model.addAttribute("userModel", new UserModel());
        return "user/form-add-user";
    }

    @PostMapping("/adminAddUser")
    public String adminAddUserSubmit(@ModelAttribute UserModel user, Model model) {
        logger.info("Handling add user submit request");
        try {
            userService.addUser(user);
        } catch(Exception e) {
            logger.error("Username already registered");
            model.addAttribute("userModel", user);
            model.addAttribute("error", "Username already registered");
            return "user/form-add-user";
        }
        return "redirect:/user";
    }

    @GetMapping("/listAllUsers")
    public String listAllUser(Model model) {
        List<UserModel> listUser = userService.getListUser();

        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var user =(User) authentication.getPrincipal();

        var username = user.getUsername();
        var userModel = userService.getUserByName(username);

        model.addAttribute("listUser", listUser);
        model.addAttribute("user", userModel);
        return viewAllUser;
    }

    @GetMapping("/manajemenUser")
    public String viewAllUser(Model model) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var authenticationUser = (User) authentication.getPrincipal();

        var authenticationUsername = authenticationUser.getUsername();
        var userModel = userService.getUserByName(authenticationUsername);
        if(userModel.getRole().equals(admin)){
            List<String> role = new ArrayList<>();
            role.add(admin);
            role.add(apoteker);
            role.add(dokter);
            role.add(manager);
            model.addAttribute("listRole", role);
            return "auth/manajemen-user";
        } else{
            return accessDenied;
        }
    }

    @GetMapping(value = "/add/{userRole}")
    public String addUserFormPage(@PathVariable String userRole, Model model){
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var authenticationUser = (User) authentication.getPrincipal();


        var authenticationUsername = authenticationUser.getUsername();
        var userModel = userService.getUserByName(authenticationUsername);
        var user = new UserModel();

        if(userModel.getRole().equals(admin)){

            if(userRole.equals(dokter) || userRole.equals(apoteker) || userRole.equals(manager)){
                List<String> listRole = new ArrayList<>();
                listRole.add(admin);
                listRole.add(apoteker);
                listRole.add(dokter);
                listRole.add(manager);
                model.addAttribute("userRole", userRole);
                model.addAttribute("user", user);
                model.addAttribute("listRole", listRole);
                return "auth/form-add-user";
            } else{
                return accessDenied;
            }
        } else{
            return accessDenied;
        }
    }

    @PostMapping(value="/add")
    public String addUserSubmit(@RequestParam String role, @ModelAttribute UserModel user, Model model){
        var auth = SecurityContextHolder.getContext().getAuthentication();
        var authenticationUser = (User) auth.getPrincipal();

        var authenticationUsername = authenticationUser.getUsername();
        var userModel = userService.getUserByName(authenticationUsername);

        if(userModel.getRole().equals(admin)){
            if(role.equals(dokter)){
                user.setRole(dokter);
                userService.addUserWithRole(user);
                model.addAttribute(nameUser, user.getUsername());
                return addUser;

            } else if (role.equals(apoteker)) {
                user.setRole(apoteker);
                userService.addUserWithRole(user);
                model.addAttribute(nameUser, user.getUsername());
                return addUser;
            } else if (role.equals(manager)) {
                user.setRole(manager);
                userService.addUserWithRole(user);
                model.addAttribute(nameUser, user.getUsername());
                return addUser;

            }
            else{
                return accessDenied;
            }
        }
        else{
            return accessDenied;
        }
    }

    @GetMapping(value = "/view/{userRole}")
    public String getUser(@PathVariable String userRole, Model model){
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var authenticationUser = (User) authentication.getPrincipal();
        var authenticationUsername = authenticationUser.getUsername();
        var userModel = userService.getUserByName(authenticationUsername);

        if(userModel.getRole().equals(admin)) {
            if(userRole.equals(manager)){
                List<UserModel> listManager = userService.getListManager();
                model.addAttribute("userRole", userRole);
                model.addAttribute("listUser", listManager);
                return viewAllUser;

            } else if (userRole.equals(dokter)){
                List<UserModel> listDokter = userService.getListDokter();
                model.addAttribute("userRole", userRole);
                model.addAttribute("listUser", listDokter);
                return viewAllUser;

            } else if (userRole.equals(apoteker)){
                List<UserModel> listApoteker = userService.getListApoteker();
                model.addAttribute("userRole", userRole);
                model.addAttribute("listUser", listApoteker);
                return viewAllUser;

            } else{
                return accessDenied;
            }
        } else{
            return accessDenied;
        }
    }

    @GetMapping("/delete/{username}")
    public String deleteUser (@PathVariable String username, Model model) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var authUser = (User) authentication.getPrincipal();


        var authUsername = authUser.getUsername();
        var userModel = userService.getUserByName(authUsername);


        if(userModel.getRole().equals(admin)){
            if(userModel.getUsername().equals(username)){
                return "auth/failed-delete-user";


            } else{
                var user = userService.getUserByName(username);
                userService.deleteUser(user);
                model.addAttribute( "username",user.getUsername());
                return "auth/delete-user";
            }
        }
        else {
            return accessDenied;
        }

    }
}

