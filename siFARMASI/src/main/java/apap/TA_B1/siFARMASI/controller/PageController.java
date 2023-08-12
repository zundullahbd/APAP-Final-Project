package apap.TA_B1.siFARMASI.controller;

import apap.TA_B1.siFARMASI.model.UserModel;
import apap.TA_B1.siFARMASI.repository.UserDb;
import apap.TA_B1.siFARMASI.security.xml.ServiceResponse;
import apap.TA_B1.siFARMASI.service.UserService;
import apap.TA_B1.siFARMASI.setting.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;


@CrossOrigin
@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDb userDb;

    @Autowired
    ServerProperties serverProperties;

    @RequestMapping("/")
    public String home(Principal principal, Model model) {
        UserModel user = userService.getUserByNama(principal.getName());
        model.addAttribute("user", user);
        return "home";

    }

    @RequestMapping ("/login")
    public String loginFormPage(Model model) {
        model.addAttribute("port", serverProperties.getPort());
        return "auth/login";
    }

    @PostMapping("/login")
    public String loginSubmitPage(Model model) {
        model.addAttribute("port", serverProperties.getPort());
        return "home";
    }

    private WebClient webClient = WebClient.builder().build();

    @GetMapping("/validate-ticket")
    public ModelAndView adminLoginSSO(
            @RequestParam(value = "ticket", required = false) String ticket,
            HttpServletRequest request
    )
    {
        var serviceResponse = this.webClient.get().uri(
                String.format(
                        Setting.SERVER_VALIDATE_TICKET, ticket, Setting.CLIENT_LOGIN
                )
        ).retrieve().bodyToMono(ServiceResponse.class).block();
        if (serviceResponse != null) {
            var attributes = serviceResponse.getAuthenticationSuccess().getAttributes();
            var username = serviceResponse.getAuthenticationSuccess().getUser();


            var user = userService.getUserByNama(username);

            if (user == null) {
                    user = new UserModel();
                    user.setEmail(username + "@ui.ac.id");
                    user.setNama(attributes.getNama());
                    user.setPassword("sifarmasi");
                    user.setUsername(username);
                    user.setIsSso(true);
                    user.setRole("ADMIN");
                    userService.addUser(user);
                }
            Authentication authentication = new UsernamePasswordAuthenticationToken(username, "sifarmasi");

            var securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(authentication);

            var httpSession = request.getSession(true);
            httpSession.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, securityContext);

        }
        return new ModelAndView("redirect:/");
    }

    @GetMapping(value = "/login-sso")
    public ModelAndView loginSSO() {
        return new ModelAndView("redirect:" + Setting.SERVER_LOGIN + Setting.CLIENT_LOGIN);
    }

    @GetMapping(value = "/logout-sso")
    public ModelAndView logoutSSO(Principal principal) {
        UserModel user = userService.getUserByNama(principal.getName());
        if (!user.getRole().equals("ADMIN")) {
            return new ModelAndView("redirect:/logout");
        }
        return new ModelAndView("redirect:" + Setting.SERVER_LOGOUT + Setting.CLIENT_LOGOUT);
    }
}