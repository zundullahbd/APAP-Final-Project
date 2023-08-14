package apap.TA_B1.siFARMASI.service;

import apap.TA_B1.siFARMASI.dto.LaporanDTO;

import java.util.List;

public interface LaporanService {
    List<LaporanDTO> generateLaporanKadaluwarsa(int tahunAwal, int tahunAkhir);
}