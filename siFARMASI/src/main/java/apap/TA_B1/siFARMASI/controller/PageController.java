package apap.TA_B1.siFARMASI.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class PageController {

    @GetMapping("/")
    public String home() {
        return "home"; // Return the name of the view template (HTML file)
    }
}
