package apap.TA_B1.siFARMASI.controller;

import apap.TA_B1.siFARMASI.model.*;
import apap.TA_B1.siFARMASI.service.UserService;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
    @Autowired
    private UserService userService;

    @GetMapping("/listAllUsers")
    public String listAllUser(Model model) {
        List<UserModel> listUser = userService.getListUser();

        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var user =(User) authentication.getPrincipal();

        var username = user.getUsername();
        var userModel = userService.getUserByName(username);

        model.addAttribute("listUser", listUser);
        model.addAttribute("user", userModel);
        return "auth/viewall-user";
    }

    @GetMapping("/manajemenUser")
    public String viewAllUser(Model model) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var authenticationUser = (User) authentication.getPrincipal();

        var authenticationUsername = authenticationUser.getUsername();
        var userModel = userService.getUserByName(authenticationUsername);
        if(userModel.getRole().equals("ADMIN")){
            List<String> role = new ArrayList<>();
            role.add("ADMIN");
            role.add("APOTEKER");
            role.add("DOKTER");
            role.add("MANAGER");
            model.addAttribute("listRole", role);
            return "auth/manajemen-user";
        } else{
            return "auth/access-denied";
        }
    }

    @GetMapping(value = "/add/{userRole}")
    public String addUserFormPage(@PathVariable String userRole, Model model){
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var authenticationUser = (User) authentication.getPrincipal();


        var authenticationUsername = authenticationUser.getUsername();
        var userModel = userService.getUserByName(authenticationUsername);
        var user = new UserModel();

        if(userModel.getRole().equals("ADMIN")){

            if(userRole.equals("DOKTER") || userRole.equals("APOTEKER") || userRole.equals("MANAGER")){
                List<String> listRole = new ArrayList<>();
                listRole.add("ADMIN");
                listRole.add("APOTEKER");
                listRole.add("DOKTER");
                listRole.add("MANAGER");
                model.addAttribute("userRole", userRole);
                model.addAttribute("user", user);
                model.addAttribute("listRole", listRole);
                return "auth/form-add-user";
            } else{
                return "auth/access-denied";
            }
        } else{
            return "auth/access-denied";
        }
    }

    @PostMapping(value="/add")
    public String addUserSubmit(@RequestParam String role, @ModelAttribute UserModel user, Model model){
        var auth = SecurityContextHolder.getContext().getAuthentication();
        var authenticationUser = (User) auth.getPrincipal();

        var authenticationUsername = authenticationUser.getUsername();
        var userModel = userService.getUserByName(authenticationUsername);

        if(userModel.getRole().equals("ADMIN")){
            if(role.equals("DOKTER")){
                user.setRole("DOKTER");
                userService.addUserWithRole(user);
                model.addAttribute("nameUser", user.getUsername());
                return "auth/add-user";

            } else if (role.equals("APOTEKER")) {
                user.setRole("APOTEKER");
                userService.addUserWithRole(user);
                model.addAttribute("nameUser", user.getUsername());
                return "auth/add-user";
            } else if (role.equals("MANAGER")) {
                user.setRole("MANAGER");
                userService.addUserWithRole(user);
                model.addAttribute("nameUser", user.getUsername());
                return "auth/add-user";

            }
            else{
                return "auth/access-denied";
            }
        }
        else{
            return "auth/access-denied";
        }
    }

    @GetMapping(value = "/view/{userRole}")
    public String getUser(@PathVariable String userRole, Model model){
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var authenticationUser = (User) authentication.getPrincipal();
        var authenticationUsername = authenticationUser.getUsername();
        var userModel = userService.getUserByName(authenticationUsername);

        if(userModel.getRole().equals("ADMIN")) {
            if(userRole.equals("MANAGER")){
                List<ManagerModel> listManager = userService.getListManager();
                model.addAttribute("userRole", userRole);
                model.addAttribute("listUser", listManager);
                return "auth/viewall-user";

            } else if (userRole.equals("DOKTER")){
                List<DokterModel> listDokter = userService.getListDokter();
                model.addAttribute("userRole", userRole);
                model.addAttribute("listUser", listDokter);
                return "auth/viewall-user";

            } else if (userRole.equals("APOTEKER")){
                List<ApotekerModel> listApoteker = userService.getListApoteker();
                model.addAttribute("userRole", userRole);
                model.addAttribute("listUser", listApoteker);
                return "auth/viewall-user";

            } else{
                return "auth/access-denied";
            }
        } else{
            return "auth/access-denied";
        }
    }

    @GetMapping("/delete/{username}")
    public String deleteUser (@PathVariable String username, Model model) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var authUser = (User) authentication.getPrincipal();


        var authUsername = authUser.getUsername();
        var userModel = userService.getUserByName(authUsername);


        if(userModel.getRole().equals("ADMIN")){
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
            return "auth/access-denied";
        }

    }
}

