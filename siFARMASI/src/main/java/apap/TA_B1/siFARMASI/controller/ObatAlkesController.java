package apap.TA_B1.siFARMASI.controller;

import apap.TA_B1.siFARMASI.model.MitraModel;
import apap.TA_B1.siFARMASI.model.ObatAlkesModel;
import apap.TA_B1.siFARMASI.model.RiwayatTambahStokModel;
<<<<<<< siFARMASI/src/main/java/apap/TA_B1/siFARMASI/controller/ObatAlkesController.java
import apap.TA_B1.siFARMASI.repository.RiwayatTambahStokDb;
import apap.TA_B1.siFARMASI.service.MitraService;
import apap.TA_B1.siFARMASI.service.ObatAlkesService;
import apap.TA_B1.siFARMASI.service.UserService;


import apap.TA_B1.siFARMASI.service.MitraService;
import apap.TA_B1.siFARMASI.service.ObatAlkesService;
import apap.TA_B1.siFARMASI.service.RiwayatTambahStokService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Arrays;
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
<<<<<<< siFARMASI/src/main/java/apap/TA_B1/siFARMASI/controller/ObatAlkesController.java
    private UserService userService;

    @Autowired
    private RiwayatTambahStokDb riwayatTambahStokDb;
=======
    private RiwayatTambahStokService riwayatTambahStokService;
>>>>>>> siFARMASI/src/main/java/apap/TA_B1/siFARMASI/controller/ObatAlkesController.java

    @GetMapping("/")
    public String viewAllObatAlkes(Model model){
        List<ObatAlkesModel> listObatAlkes = obatAlkesService.getListObatAlkes();
        model.addAttribute("listObatAlkes", listObatAlkes);
        return "obatalkes/list-obatalkes";
    }

    @GetMapping("/detail/{id}")
    public String detailObatAlkes(@PathVariable Integer id,Model model){
        ObatAlkesModel obatAlkes = obatAlkesService.getObatAlkesById(id);
            model.addAttribute("obatAlkes", obatAlkes);
            return "obatalkes/detail-obatalkes";
    }

    @GetMapping("/add")
    public String addNewObatAlkesForm(Model model) {
        ObatAlkesModel obatAlkes = new ObatAlkesModel();
        List<MitraModel> listMitra = mitraService.getListMitra();
        List<String> listKategori = Arrays.asList("Bebas Terbatas", "Bebas", "Keras", "Keras Tertentu", "Narkotika");
        List<String> listJenisSediaan = Arrays.asList("Tablet", "Kaplet", "Kapsul", "Buah");
        List<String> listKemasanSimpan = Arrays.asList("Strip", "Box", "Botol", "Lainnya");
        model.addAttribute("obatAlkes", obatAlkes);
        model.addAttribute("listKategori", listKategori);
        model.addAttribute("listMitra", listMitra);
        model.addAttribute("listKemasanSimpan", listKemasanSimpan);
        model.addAttribute("listJenisSediaan", listJenisSediaan);
        return "obatalkes/form-add-new-obatalkes";
    }

    @PostMapping("/add")
    public String addNewObatAlkesSubmit(@ModelAttribute ObatAlkesModel obatAlkes,
                                        @RequestParam("file")MultipartFile file) {
        // Simpan path file dalam objek obatAlkesModel
        obatAlkes.setPathFile(file.getOriginalFilename());

        // Menyimpan informasi ObatAlkesModel ke basis data
        obatAlkesService.addObatAlkes(obatAlkes);

        return "redirect:/obat-alkes/"; // Redirect to the list view
    }

    @GetMapping("/delete/{id}")
    public String deleteObatAlkes(@PathVariable Integer id) {
        obatAlkesService.deleteObatAlkes(id);
        return "redirect:/obat-alkes/";
    }


    @GetMapping("/input-obat-alkes")
    public String addObatAlkesFormPage (Model model) {
        logger.info("Handle obat alkes add form request");
        RiwayatTambahStokModel riwayatTambahStok = new RiwayatTambahStokModel();
        List<ObatAlkesModel> listObatAlkes = obatAlkesService.getListObatAlkes();
        List<MitraModel> listMitra = mitraService.getListMitra();

        model.addAttribute("riwayat", riwayatTambahStok);
        model.addAttribute("listObatAlkes", listObatAlkes);
        model.addAttribute("listMitra", listMitra);

        return "obatalkes/form-add-obatalkes";
    }

    @PostMapping("/input-obat-alkes")
    public String addObatAlkesSubmitPage(@ModelAttribute RiwayatTambahStokModel riwayatTambahStok, Model model) {
        logger.info("Handle obat alkes add form request");
        obatAlkesService.increaseStock(riwayatTambahStok.getId_obat(), riwayatTambahStok.getJumlah_obat());
        riwayatTambahStokService.addRiwayat(riwayatTambahStok);
        return "redirect:/";
    }
}