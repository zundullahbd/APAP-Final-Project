package apap.TA_B1.siFARMASI.service;

import apap.TA_B1.siFARMASI.model.ObatAlkesModel;
import apap.TA_B1.siFARMASI.repository.ObatAlkesDb;
import apap.TA_B1.siFARMASI.service.ObatAlkesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class ObatAlkesServiceImplTest {

    @MockBean
    private ObatAlkesDb obatAlkesDb;

    @Autowired
    private ObatAlkesService obatAlkesService;

    @Test
    public void testReduceStock() {
        ObatAlkesModel obatAlkes = new ObatAlkesModel();
        obatAlkes.setStok(10);

        obatAlkesService.reduceStock(obatAlkes, 3);

        assertEquals(7, obatAlkes.getStok());
        verify(obatAlkesDb, times(1)).save(obatAlkes);
    }

    @Test
    public void testIncreaseStock() {
        ObatAlkesModel obatAlkes = new ObatAlkesModel();
        obatAlkes.setStok(5);

        obatAlkesService.increaseStock(obatAlkes, 2);

        assertEquals(7, obatAlkes.getStok());
        verify(obatAlkesDb, times(1)).save(obatAlkes);
    }
}
