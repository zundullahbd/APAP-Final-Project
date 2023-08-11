package apap.TA_B1.siFARMASI.controller;

import apap.TA_B1.siFARMASI.model.AdminModel;
import apap.TA_B1.siFARMASI.model.UserModel;
import apap.TA_B1.siFARMASI.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.Instant;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @GetMapping("")
    public String vieUserList(Model model) {
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

    @GetMapping("/add")
    public String addUserForm(Model model) {
        logger.info("Handling add user form request");
        model.addAttribute("userModel", new UserModel());
        return "user/form-add-user";
    }

    @PostMapping("/add")
    public String addUserSubmit(@ModelAttribute UserModel user, Model model) {
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

}
