package apap.TA_B1.siFARMASI.controller;

import apap.TA_B1.siFARMASI.dto.LaporanDTO;
import apap.TA_B1.siFARMASI.service.LaporanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class LaporanController {

    @Autowired
    private LaporanService laporanService;


    @GetMapping("/laporan")
    public String showLaporanForm(@ModelAttribute LaporanDTO laporanDTO) {
        return "laporan/laporan-form";
    }

    @GetMapping("/laporan/{jenisLaporan}/{tahunAwal}-{tahunAkhir}")
    public String showLaporanPage(@PathVariable String jenisLaporan,
                                 @PathVariable String tahunAwal,
                                 @PathVariable String tahunAkhir,
                                 Model model) {
        if (jenisLaporan.equals("kadaluwarsa")) {
            int tahunAwalInt = Integer.parseInt(tahunAwal);
            int tahunAkhirInt = Integer.parseInt(tahunAkhir);

            List<LaporanDTO> laporanKadaluwarsa = laporanService.generateLaporanKadaluwarsa(tahunAwalInt, tahunAkhirInt);
            model.addAttribute("laporanKadaluwarsa", laporanKadaluwarsa);

            return "laporan/laporan-kadaluwarsa";
        }

        // Opsi jenis laporan lain bisa ditambahkan di sini

        return "error/404";
    }
}
