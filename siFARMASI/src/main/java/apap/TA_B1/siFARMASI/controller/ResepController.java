package apap.TA_B1.siFARMASI.controller;

import apap.TA_B1.siFARMASI.model.ObatAlkesModel;
import apap.TA_B1.siFARMASI.model.ResepModel;
import apap.TA_B1.siFARMASI.service.ObatAlkesService;
import apap.TA_B1.siFARMASI.service.ResepService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/resep")
public class ResepController {
    private final Logger logger = LoggerFactory.getLogger(ObatAlkesController.class);

    @Autowired
    private ResepService resepService;

    @Autowired
    private ObatAlkesService obatAlkesService;


    @GetMapping("/input-resep")
    public String addResepFormPage (Model model) {
        logger.info("Handle resep add form page");
        ResepModel resep = new ResepModel();
        List<ObatAlkesModel> listObatAlkes = obatAlkesService.getListObatAlkes();

        model.addAttribute("resep", resep);
        model.addAttribute("listObatAlkes", listObatAlkes);
        return "resep/form-add-resep.html";
    }

    @PostMapping("/input-resep")
    public String addResepSubmitPage (@ModelAttribute ResepModel resep, Model model) {
        logger.info("Handle resep add submit page");
        obatAlkesService.reduceStock(resep.getId_obat(), resep.getJumlah_obat());
        resepService.addResep(resep);
        return "redirect:/";
    }
}
