package apap.TA_B1.siFARMASI.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import apap.TA_B1.siFARMASI.model.JwtLoginRequest;
import apap.TA_B1.siFARMASI.model.JwtSignUpRequest;
import apap.TA_B1.siFARMASI.model.UserModel;
import apap.TA_B1.siFARMASI.repository.UserDb;
import apap.TA_B1.siFARMASI.security.xml.Attributtes;
import apap.TA_B1.siFARMASI.security.xml.ServiceResponse;
import apap.TA_B1.siFARMASI.service.UserService;
import apap.TA_B1.siFARMASI.setting.Setting;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.time.Instant;


@CrossOrigin
@Controller
public class PageController {
    private final Logger logger = LoggerFactory.getLogger(PageController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserDb userDb;

    @Autowired
    ServerProperties serverProperties;

    @GetMapping("/")
    public String home(Principal principal, Model model) {
        logger.info("Handle home request");
        UserModel user = userService.getUserByName(principal.getName());
        model.addAttribute("user", user);
        return "home";

    }
    @RequestMapping ("/login")
    public String loginFormPage(Model model) {
        logger.info("Handle login form page request");
        model.addAttribute("port", serverProperties.getPort());
        return "auth/login";
    }

    @PostMapping("/login")
    public String loginSubmitPage(@ModelAttribute JwtLoginRequest loginRequest, Model model) {
        logger.info("Handle login submit page");
        UserModel user = userDb.findByUsername(loginRequest.getUsername());
        BCryptPasswordEncoder pass = new BCryptPasswordEncoder();

        if (user == null) {
            String errMsg = "User not registered";
            model.addAttribute("error", errMsg);
            return "redirect:/error/403"; // Redirect back to the login page with the error
        }

        // Check if the provided password matches the stored hashed password using BCryptPasswordEncoder
        if (pass.matches(loginRequest.getPassword(), user.getPassword())) {
            model.addAttribute("port", serverProperties.getPort());

            // Redirect to the home page
            return "home"; // Adjust the view name as needed
        } else {
            String errMsg = "Incorrect username or password";
            model.addAttribute("error", errMsg);
            return "redirect:/auth/login?error"; // Redirect back to the login page with the error
        }
    }



    @RequestMapping("/signup")
    public String signupForm(Model model) {
        logger.info("Handle signup form page request");
        model.addAttribute("signupRequest", new JwtSignUpRequest());
        return "/auth/signup"; // Return the name of the Thymeleaf template for signup form
    }

    @PostMapping("/signup")
    public String signupSubmit(@ModelAttribute JwtSignUpRequest signupRequest, Model model) {
        logger.info("Handle  signup submit request");
        UserModel existingUser = userDb.findByUsername(signupRequest.getUsername());
        if (existingUser != null) {
            model.addAttribute("error", "Username already exists.");
            return "auth/signup"; // Return to the signup form with an error message
        }

        existingUser = userDb.findByEmail(signupRequest.getEmail());
        if (existingUser != null) {
            model.addAttribute("error", "Email already exists.");
            return "auth/signup"; // Return to the signup form with an error message
        }

        userService.signup(signupRequest.getUsername(), signupRequest.getName(), signupRequest.getEmail(), signupRequest.getPassword(), signupRequest.getRole());
        return "redirect:/login"; // Redirect to the login page after successful signup
    }

    private WebClient webClient = WebClient.builder().build();

    @GetMapping("/validate-ticket")
    public ModelAndView adminLoginSSO(
            @RequestParam(value = "ticket", required = false) String ticket,
            HttpServletRequest request
    )
    {
        logger.info("Handle admin login sso request");
        ServiceResponse serviceResponse = this.webClient.get().uri(
                String.format(
                        Setting.SERVER_VALIDATE_TICKET,
                        ticket,
                        Setting.CLIENT_LOGIN
                )
        ).retrieve().bodyToMono(ServiceResponse.class).block();

        Attributtes attributes = serviceResponse.getAuthenticationSuccess().getAttributes();
        String username = serviceResponse.getAuthenticationSuccess().getUser();


        UserModel user = userService.getUserByName(username);

        if (user == null) {
            user = new UserModel();
            user.setEmail(username + "@ui.ac.id");
            user.setName(attributes.getName());
            user.setPassword("sifarmasi");
            user.setUsername(username);
            user.setIsSso(true);
            user.setRole("ADMIN");
            user.setCreated_at_timestamp(Instant.now());
            userService.addUser(user);

        }
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, "sifarmasi");

        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);

        HttpSession httpSession = request.getSession(true);
        httpSession.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, securityContext);


        return new ModelAndView("redirect:/");
    }

    @GetMapping(value = "/login-sso")
    public ModelAndView loginSSO() {
        logger.info("Handle login sso get request");
        return new ModelAndView("redirect:" + Setting.SERVER_LOGIN + Setting.CLIENT_LOGIN);
    }

    @GetMapping(value = "/logout-sso")
    public ModelAndView logoutSSO(Principal principal) {
        logger.info("Handle logout sso request");
        UserModel user = userService.getUserByName(principal.getName());
        if (user.getIsSso()==false) {
            return new ModelAndView("redirect:/logout");
        }
        return new ModelAndView("redirect:" + Setting.SERVER_LOGOUT + Setting.CLIENT_LOGOUT);
    }
    @GetMapping("/logout")
    public String logout() {
        // Clear the authentication and invalidate the session
        logger.info("Handle logout request");
        SecurityContextHolder.getContext().setAuthentication(null);
        return "redirect:/login?logout"; // Redirect to the login page with a logout parameter
   }
}
