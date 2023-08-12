package apap.TA_B1.siFARMASI.controller;

import apap.TA_B1.siFARMASI.model.MitraModel;
import apap.TA_B1.siFARMASI.model.ObatAlkesModel;
import apap.TA_B1.siFARMASI.service.MitraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import javax.validation.Valid;

@Controller
@RequestMapping("/mitra")
public class MitraController {
    @Autowired
    private MitraService mitraService;

    @GetMapping("")
    public String viewMitraList(Model model) {
        List<MitraModel> listMitra = mitraService.getListMitra();
        model.addAttribute("listMitra", listMitra);
        model.addAttribute("mitraModel", new MitraModel()); // Add an empty MitraModel object for the form
        return "mitra/list-mitra";
    }

    @GetMapping("/add")
    public String addMitraForm(Model model) {
        model.addAttribute("mitraModel", new MitraModel());
        return "mitra/form-add-mitra";
    }

    @PostMapping("/add")
    public String addMitraSubmit(@ModelAttribute MitraModel mitra) {
        mitraService.addMitra(mitra);
        return "redirect:/mitra";
    }

    @GetMapping("/edit/{id}")
    public String editMitraForm(@PathVariable Integer id, Model model) {
        MitraModel mitra = mitraService.getMitraById(id);
        model.addAttribute("mitra", mitra);
        return "mitra/form-update-mitra";
    }

    @PostMapping("/edit/{id}")
    public String editMitraSubmit(@PathVariable Integer id, @ModelAttribute @Valid MitraModel mitra,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<MitraModel> listMitra = mitraService.getListMitra();
            model.addAttribute("listMitra", listMitra);
            return "mitra/list-mitra";
        }
        mitra.setId(id);
        mitraService.updateMitra(mitra);
        return "redirect:/mitra";
    }

    @GetMapping("/delete/{id}")
    public String deleteMitra(@PathVariable Integer id) {
        mitraService.deleteMitra(id);
        return "redirect:/mitra";
    }
}
