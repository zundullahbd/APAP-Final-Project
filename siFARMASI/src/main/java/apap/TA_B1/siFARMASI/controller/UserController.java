package apap.TA_B1.siFARMASI.controller;

import apap.TA_B1.siFARMASI.model.MitraModel;
import apap.TA_B1.siFARMASI.model.UserModel;
import apap.TA_B1.siFARMASI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("")
    public String vieUserList(Model model) {
        List<UserModel> listUser = userService.getListUser();
        model.addAttribute("listUser", listUser);
        return "user/list-user";
    }

    @GetMapping("/add")
    public String addUserForm(Model model) {
        model.addAttribute("userModel", new UserModel());
        return "user/form-add-user";
    }

    @PostMapping("/add")
    public String addUserSubmit(@ModelAttribute UserModel user) {
        userService.addUser(user);
        return "redirect:/user";
    }

}
