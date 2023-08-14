package apap.TA_B1.siFARMASI.service;

import apap.TA_B1.siFARMASI.dto.LaporanDTO;
import apap.TA_B1.siFARMASI.model.ObatAlkesModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LaporanServiceImpl implements LaporanService {

    @Autowired
    private ObatAlkesService obatAlkesService;

    @Override
    public List<LaporanDTO> generateLaporanKadaluwarsa(int tahunAwal, int tahunAkhir) {
        List<ObatAlkesModel> obatAlkesList = obatAlkesService.getListObatAlkes();

        Map<Integer, Integer> jumlahKadaluwarsaPerTahun = new HashMap<>();
        for (ObatAlkesModel obatAlkes : obatAlkesList) {
            int tahunKadaluwarsa = obatAlkes.getKadaluwarsa().getYear();
            if (tahunKadaluwarsa >= tahunAwal && tahunKadaluwarsa <= tahunAkhir) {
                jumlahKadaluwarsaPerTahun.put(tahunKadaluwarsa, jumlahKadaluwarsaPerTahun.getOrDefault(tahunKadaluwarsa, 0) + 1);
            }
        }

        List<LaporanDTO> hasilLaporan = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : jumlahKadaluwarsaPerTahun.entrySet()) {
            hasilLaporan.add(new LaporanDTO(entry.getKey(), entry.getValue()));
        }

        return hasilLaporan;
    }
}