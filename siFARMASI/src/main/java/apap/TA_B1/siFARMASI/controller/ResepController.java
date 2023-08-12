package apap.TA_B1.siFARMASI.controller;


import apap.TA_B1.siFARMASI.model.ObatAlkesModel;
import apap.TA_B1.siFARMASI.model.ResepModel;
import apap.TA_B1.siFARMASI.repository.ResepDb;
import apap.TA_B1.siFARMASI.repository.UserDb;
import apap.TA_B1.siFARMASI.service.ObatAlkesService;
import apap.TA_B1.siFARMASI.service.ResepService;
import apap.TA_B1.siFARMASI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/resep")
public class ResepController {
    @Autowired
    private ResepService resepService;

    @Autowired
    private ObatAlkesService obatAlkesService;

    @Autowired
    private UserService userService;
    @Autowired
    private ResepDb resepDb;

    @GetMapping("/input-resep")
    public String addResepFormPage (Model model) {
        ResepModel resep = new ResepModel();
        List<ObatAlkesModel> listObatAlkes = obatAlkesService.getListObatAlkes();

        model.addAttribute("resep", resep);
        model.addAttribute("listObatAlkes", listObatAlkes);
        return "resep/form-add-resep.html";
    }

    @PostMapping("/input-resep")
    public String addResepSubmitPage (@ModelAttribute ResepModel resep, Model model) {
        obatAlkesService.reduceStock(resep.getId_obat(), resep.getJumlah_obat());
//        resep.setId_user(userService.getUserById(1));
        resep.setNomor("R-01");
        resep.setCreated_at(LocalDateTime.now());
        resepDb.save(resep);
        return "redirect:/";
    }
}
