package apap.TA_B1.siFARMASI.service;

import apap.TA_B1.siFARMASI.dto.LaporanDTO;
import apap.TA_B1.siFARMASI.model.ObatAlkesModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class LaporanServiceImplTest {

    @Autowired
    private LaporanService laporanService;

    @MockBean
    private ObatAlkesService obatAlkesService;

    private List<ObatAlkesModel> mockObatAlkesList;

    @BeforeEach
    public void setUp() {
        mockObatAlkesList = new ArrayList<>();
        // Add mock ObatAlkesModel objects to the list
        mockObatAlkesList.add(new ObatAlkesModel(1, null, "Obat A", "Kategori A", "Sediaan A",
                "Kemasan Simpan A", "Kemasan Beli A", 1000, 1500, 10,
                LocalDate.of(2023, 8, 1), "Lokasi A", "path/to/fileA.pdf", null, null));
        mockObatAlkesList.add(new ObatAlkesModel(2, null, "Obat B", "Kategori B", "Sediaan B",
                "Kemasan Simpan B", "Kemasan Beli B", 1200, 1800, 15,
                LocalDate.of(2022, 10, 15), "Lokasi B", "path/to/fileB.pdf", null, null));
    }

    @Test
    public void testGenerateLaporanKadaluwarsa() {
        when(obatAlkesService.getListObatAlkes()).thenReturn(mockObatAlkesList);

        int tahunAwal = 2022;
        int tahunAkhir = 2023;
        List<LaporanDTO> hasilLaporan = laporanService.generateLaporanKadaluwarsa(tahunAwal, tahunAkhir);

        //Assertions
        assertEquals(2, hasilLaporan.size());
        assertEquals(2022, hasilLaporan.get(0).getTahun());
        assertEquals(1, hasilLaporan.get(0).getJumlahKadaluwarsa());
        assertEquals(2023, hasilLaporan.get(1).getTahun());
        assertEquals(1, hasilLaporan.get(1).getJumlahKadaluwarsa());
    }
}
