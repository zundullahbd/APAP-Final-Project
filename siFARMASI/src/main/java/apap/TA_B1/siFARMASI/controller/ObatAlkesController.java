package apap.TA_B1.siFARMASI.controller;

import apap.TA_B1.siFARMASI.model.MitraModel;
import apap.TA_B1.siFARMASI.model.ObatAlkesModel;
import apap.TA_B1.siFARMASI.model.RiwayatTambahStokModel;
import apap.TA_B1.siFARMASI.repository.RiwayatTambahStokDb;
import apap.TA_B1.siFARMASI.service.MitraService;
import apap.TA_B1.siFARMASI.service.ObatAlkesService;
import apap.TA_B1.siFARMASI.service.RiwayatTambahStokService;
import apap.TA_B1.siFARMASI.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sun.security.auth.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/obat-alkes")
public class ObatAlkesController {
    private final Logger logger = LoggerFactory.getLogger(ObatAlkesController.class);
    @Autowired
    private ObatAlkesService obatAlkesService;

    @Autowired
    private MitraService mitraService;

    @Autowired
    private RiwayatTambahStokService riwayatTambahStokService;

    @GetMapping("/input-obat-alkes")
    public String addObatAlkesFormPage (Model model) {
        logger.info("Handle obat alkes add form request");
        RiwayatTambahStokModel riwayatTambahStok = new RiwayatTambahStokModel();
        List<ObatAlkesModel> listObatAlkes = obatAlkesService.getListObatAlkes();
        List<MitraModel> listMitra = mitraService.getListMitra();

        model.addAttribute("riwayat", riwayatTambahStok);
        model.addAttribute("listObatAlkes", listObatAlkes);
        model.addAttribute("listMitra", listMitra);

        return "obatalkes/form-add-obatalkes.html";
    }

    @PostMapping("/input-obat-alkes")
    public String addObatAlkesSubmitPage(@ModelAttribute RiwayatTambahStokModel riwayatTambahStok, Model model) {
        obatAlkesService.increaseStock(riwayatTambahStok.getId_obat(), riwayatTambahStok.getJumlah_obat());
        riwayatTambahStokService.addRiwayat(riwayatTambahStok);
        return "redirect:/";
    }
}