package apap.TA_B1.siFARMASI.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class PageController {
    private final Logger logger = LoggerFactory.getLogger(PageController.class);

    @GetMapping("/")
    public String home() {
        logger.info("Handle home page request");
        return "home"; // Return the name of the view template (HTML file)
    }
}
