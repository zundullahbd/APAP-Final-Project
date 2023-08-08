package apap.TA_B1.siFARMASI.controller;

import apap.TA_B1.siFARMASI.model.MitraModel;
import apap.TA_B1.siFARMASI.model.ObatAlkesModel;
import apap.TA_B1.siFARMASI.model.RiwayatTambahStokModel;
import apap.TA_B1.siFARMASI.repository.RiwayatTambahStokDb;
import apap.TA_B1.siFARMASI.repository.UserDb;
import apap.TA_B1.siFARMASI.service.MitraService;
import apap.TA_B1.siFARMASI.service.ObatAlkesService;
import apap.TA_B1.siFARMASI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/obat-alkes")
public class ObatAlkesController {
    @Autowired
    private ObatAlkesService obatAlkesService;

    @Autowired
    private MitraService mitraService;

    @Autowired
    private UserService userService;
    @Autowired
    private RiwayatTambahStokDb riwayatTambahStokDb;

    @GetMapping("/daftar-obat-alkes")
    public String viewAllObatAlkes(Model model){
        List<ObatAlkesModel> listObatAlkes = obatAlkesService.getListObatAlkes();
        model.addAttribute("listObatAlkes", listObatAlkes);
        return "obatalkes/list-obatalkes";
    }

    @GetMapping("/detail-obat-alkes/{id}")
    public String detailObatAlkes(@PathVariable Long id,Model model){
        ObatAlkesModel obatAlkes = obatAlkesService.getObatAlkesById(id);
        if (obatAlkes == null){
            model.addAttribute("id", id);
            return "obatalkes/obatalkes-not-found";
        }else{
            model.addAttribute("obatAlkes", obatAlkes);
            return "obatalkes/detail-obatalkes";
        }
    }

    @PostMapping("/hapus")
    private String hapusTiket(
        final HttpServletRequest rowId,
        Model model
    ){
        int obatAlkesIdRow = Integer.valueOf(rowId.getParameter("deleteObatAlkes"));
        ObatAlkesModel obatAlkes = obatAlkesService.getListObatAlkes().get(obatAlkesIdRow);
        model.addAttribute("namaObatAlkes", obatAlkes.getNama());
        obatAlkesService.deleteObatAlkes(obatAlkes);
        return "obatalkes/delete-obatalkes";

    }


    @GetMapping("/input-obat-alkes")
    public String addObatAlkesFormPage (Model model) {
        RiwayatTambahStokModel riwayatTambahStok = new RiwayatTambahStokModel();
        List<ObatAlkesModel> listObatAlkes = obatAlkesService.getListObatAlkes();
        List<MitraModel> listMitra = mitraService.getListMitra();

        model.addAttribute("riwayat", riwayatTambahStok);
        model.addAttribute("listObatAlkes", listObatAlkes);
        model.addAttribute("listMitra", listMitra);

        return "obatalkes/form-add-obatalkes.html";
    }

    @PostMapping("/input-obat-alkes")
    public String addObatAlkesSubmitPage (@ModelAttribute RiwayatTambahStokModel riwayatTambahStok, Model model) {
        obatAlkesService.increaseStock(riwayatTambahStok.getId_obat(), riwayatTambahStok.getJumlah_obat());
//        riwayatTambahStok.setId_user(userService.getUserById(1));
        riwayatTambahStok.setCreated_at(LocalDateTime.now());
        riwayatTambahStokDb.save(riwayatTambahStok);
        return "redirect:/";
    }
}
